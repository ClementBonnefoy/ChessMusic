package chesstube;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

import board.Board;
import engine.Evaluation;
import engine.MultiPositionEvaluatorProcess;
import pgn.PGNGame;
import midi.MidiTools;
import sml.elements.Body;
import sml.elements.Chord;
import sml.elements.ComplexNote;
import sml.elements.Declarations;
import sml.elements.Music;
import sml.elements.Play;
import sml.elements.PlayableChord;
import sml.elements.PlayableElement;
import sml.elements.PlayableSequence;
import sml.elements.Repeat;
import sml.elements.Rest;
import sml.elements.ScaleName;
import sml.elements.Tempo;
import sml.elements.Transpose;
import sml.elements.Variable;
import sml.interfaces.IDeclarable;
import sml.interfaces.IInstruction;
import sml.interfaces.IMusicalElement;
import sml.interfaces.IPlayableElement;


public class ChessTubeMidi{

	private final static int DEFAULT_TEMPO=120; //bpm
	private final static int QUARTER=6;//nombre de "tick" dans une noire

	private Sequence sequence;
	private Track[] tracks;
	private Track drumTrack;

	private InstrumentManager im;
	private ScaleManager sm;
	private TimeManager tm;

	private Music music;
	private Declarations env;

	private PGNGame pgnGame;
	private MultiPositionEvaluatorProcess mpep;

	private double move=0;
	private double step;

	private boolean convert=false;


	public ChessTubeMidi(Music smlMusic){

		im=new InstrumentManager(smlMusic);
		sm=new ScaleManager();
		tm=new TimeManager();

		music=smlMusic;
		env=smlMusic.getEnvironnement();

		try {
			sequence=new Sequence(Sequence.PPQ,1,im.getSize()); 
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}

		tracks=new Track[im.getSize()];
		for(int i=0;i<im.getSize();i++){
			tracks[i]=sequence.createTrack();
		}
		if(im.hasDrum()){
			drumTrack=sequence.createTrack();
		}
		setInstruments();


	}


	public void saveMidi(String fileName){

		if(!convert){
			convert(music);
			convert=true;
		}

		File outputFile = new File(fileName);

		int[] allowedTypes = MidiSystem.getMidiFileTypes(sequence);
		if (allowedTypes.length == 0) {
			System.err.println("No supported MIDI file types.");
		} else {
			try {
				MidiSystem.write(sequence, allowedTypes[0], outputFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void play(){
		if(!convert){
			convert(music);
			convert=true;
		}

		Sequencer seq=null;
		try {
			seq=MidiSystem.getSequencer();
			seq.open();
			seq.setSequence(sequence);
			seq.start();
		} catch (MidiUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void convert(Music sml) {
		if(ChessTube.ANALYSE){
			
			Board b=new Board();
			b.init();
			mpep=new MultiPositionEvaluatorProcess(b, pgnGame);
			mpep.startEvaluation();
			System.out.println("En attente de l'evaluateur ("+ChessTube.WAIT_TIME/1000+" secondes)...");
			try {
				Thread.sleep(ChessTube.WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mpep.checkEvaluations();
			
			System.out.println("Calcul du pas: "+pgnGame.size()+" et "+sml.size());
			step=((double)pgnGame.size())/sml.size();
		}
		Body body=sml.getBody();
		if(!body.isATempoInstruction())
			setTempo(DEFAULT_TEMPO);
		while(body!=null){
			writeInstruction(body.getInstruction());
			body=body.getNext();
		}
		
		if(mpep!=null){
			System.out.println("ecarttype= "+mpep.getEcartType());
			mpep.stop();
		}

	}


	private void writeInstruction(IInstruction instruction) {
		if(instruction instanceof Play){
			Play p=(Play) instruction;
			if(ChessTube.ANALYSE){
				Evaluation eval;

				mpep.checkEcartType();
				double ecartType=mpep.getEcartType();
				eval=mpep.getEvaluation((int)move);

				/*determine et modifie la gamme*/
				ScaleName scale=ScaleManager.getScale(eval, ecartType);
				//ScaleName scale=ScaleName.values()[(int)(Math.random()*ScaleName.values().length)];
				System.out.println("coup "+(int)move+", eval: "+eval+", scale: "+scale.toString());
				sm.setCurrentScale(SMLConverter.convertScale(scale, p.getScale().getScaleFundamental(env)));
				
				move+=step;
			}
			else{
				sm.setCurrentScale(SMLConverter.convertScale(p.getScale(),env));
			}
			writePlayableElement(p.getElement());
		}
		else if(instruction instanceof Tempo){
			Tempo t=(Tempo) instruction;
			setTempo(t.getValue());
		}

	}


	private void setTempo(int value) {
		MidiTools.setTempo(tracks[0], value*QUARTER, tm.getTime());

	}


	private void writePlayableElement(IPlayableElement element) {
		if(element instanceof PlayableElement){
			PlayableElement e=(PlayableElement)element;
			im.setCurrentInstrument(e.getInstrument(),env);
			writeMusicalElement(e.getMusicalElement(),0);
		}
		else if(element instanceof PlayableSequence){
			PlayableSequence s=(PlayableSequence)element;
			writePlayableElement(s.getFirstElement());
			writePlayableElement(s.getSecondElement());
		}
		else if(element instanceof PlayableChord){
			PlayableChord c=(PlayableChord)element;
			tm.saveTime();
			writePlayableElement(c.getFirstElement());
			tm.reload();
			writePlayableElement(c.getSecondElement());
			tm.reloadMax();
		}

	}


	private void writeMusicalElement(IMusicalElement musicalElement, int transpose) {
		if(musicalElement instanceof ComplexNote){
			ComplexNote cn=(ComplexNote) musicalElement;
			writeNote(cn, transpose);
		}
		else if(musicalElement instanceof Rest){
			Rest r=(Rest)musicalElement;
			writeRest(r);
		}
		else if(musicalElement instanceof sml.elements.Sequence){
			sml.elements.Sequence s=(sml.elements.Sequence)musicalElement;
			writeMusicalElement(s.getFirstElement(),transpose);
			writeMusicalElement(s.getSecondElement(),transpose);
		}
		else if(musicalElement instanceof Repeat){
			Repeat r=(Repeat)musicalElement;
			for(int i=0; i < r.getRepeatNumber() ; i++)
				writeMusicalElement(r.getMusicalElement(),transpose);
		}
		else if(musicalElement instanceof Transpose){
			Transpose t=(Transpose)musicalElement;
			writeMusicalElement(t.getMusicalElement(),transpose+t.getTransposeNumber());
		}
		else if(musicalElement instanceof Chord){
			Chord c=(Chord)musicalElement;
			tm.saveTime();
			writeMusicalElement(c.getFirstElement(),transpose);
			tm.reload();
			writeMusicalElement(c.getSecondElement(),transpose);
			tm.reloadMax();
		}
		else if(musicalElement instanceof Variable){
			IDeclarable value=((Variable)musicalElement).getValue(env);
			writeMusicalElement((IMusicalElement)value,transpose);
		}

	}

	private void writeNote(ComplexNote cn,int transpose) {
		ArrayList<Integer> channels=im.getCurrentChannels(env);
		int time=cn.getTime(env);
		int note=sm.getNote(cn,transpose).getMidiValue(sm.getCurrentFundamental());
		//System.out.println("Note("+note+","+time+")-->"+tm.getTime());

		if(cn.shouldStart()){
			for(int channel:channels){
				if(channel==InstrumentManager.DRUM_CHANNEL){
					drumTrack.add(MidiTools.createNoteOnEvent(cn.getRole().getRole(),im.getVelocity(channel),tm.getTime(), channel));
				}
				else
					tracks[channel].add(MidiTools.createNoteOnEvent(note,im.getVelocity(channel),tm.getTime(), channel));
			}
		}
		tm.incrTime(time);
		if(cn.shouldEnd()){
			for(int channel:channels){
				if(channel==InstrumentManager.DRUM_CHANNEL){
					drumTrack.add(MidiTools.createNoteOffEvent(cn.getRole().getRole(),tm.getTime(), channel));
				}
				else
					tracks[channel].add(MidiTools.createNoteOffEvent(note, tm.getTime(), channel));
			}
		}
	}


	private void writeRest(Rest r) {
		tm.incrTime(r.getTime(env));

	}


	private void setInstruments() {
		im.configureTracks(tracks);
	}


	public void setPGNGame(PGNGame pgnGame) {
		this.pgnGame=pgnGame;

	}
}
