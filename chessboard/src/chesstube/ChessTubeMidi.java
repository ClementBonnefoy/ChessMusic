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
import sml.elements.Rest;
import sml.elements.Tempo;
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
	
	private InstrumentManager im;
	private ScaleManager sm;
	private TimeManager tm;
	
	private Music music;
	private Declarations env;
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
		Body body=sml.getBody();
		if(!body.isATempoInstruction())
			setTempo(DEFAULT_TEMPO);
		while(body!=null){
			writeInstruction(body.getInstruction());
			body=body.getNext();
		}
		
	}


	private void writeInstruction(IInstruction instruction) {
		if(instruction instanceof Play){
			Play p=(Play) instruction;
			sm.setCurrentScale(SMLConverter.convertScale(p.getScale(),env));
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
			im.setCurrentInstrument(e.getInstrument());
			writeMusicalElement(e.getMusicalElement());
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


	private void writeMusicalElement(IMusicalElement musicalElement) {
		if(musicalElement instanceof ComplexNote){
			ComplexNote cn=(ComplexNote) musicalElement;
			writeNote(cn);
		}
		else if(musicalElement instanceof Rest){
			Rest r=(Rest)musicalElement;
			writeRest(r);
		}
		else if(musicalElement instanceof sml.elements.Sequence){
			sml.elements.Sequence s=(sml.elements.Sequence)musicalElement;
			writeMusicalElement(s.getFirstElement());
			writeMusicalElement(s.getSecondElement());
		}
		else if(musicalElement instanceof Chord){
			Chord c=(Chord)musicalElement;
			tm.saveTime();
			writeMusicalElement(c.getFirstElement());
			tm.reload();
			writeMusicalElement(c.getSecondElement());
			tm.reloadMax();
		}
		else if(musicalElement instanceof Variable){
			IDeclarable value=((Variable)musicalElement).getValue(env);
			writeMusicalElement((IMusicalElement)value);
		}
		
	}
	
	private void writeNote(ComplexNote cn) {
		ArrayList<Integer> channels=im.getCurrentChannels(env);
		int time=cn.getTime(env);
		int note=sm.getNote(cn).getMidiValue(sm.getCurrentFundamental());
		//System.out.println("Note("+note+","+time+")-->"+tm.getTime());
		
		for(int channel:channels){
			tracks[channel-1].add(MidiTools.createNoteOnEvent(note, tm.getTime(), channel));
		}
		tm.incrTime(time);
		for(int channel:channels){
			tracks[channel-1].add(MidiTools.createNoteOffEvent(note, tm.getTime(), channel));
		}
		
	}


	private void writeRest(Rest r) {
		tm.incrTime(r.getTime(env));
		
	}


	private void setInstruments() {
		im.configureTracks(tracks);
	}
}
