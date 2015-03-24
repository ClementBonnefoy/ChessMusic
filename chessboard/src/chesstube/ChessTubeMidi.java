package chesstube;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import sml.elements.Music;
import sml.elements.Play;
import sml.elements.PlayableElement;
import sml.interfaces.IVisitable;
import sml.interfaces.IVisitor;


public class ChessTubeMidi implements IVisitor {

	private Sequence sequence;
	private InstrumentManager im;
	private ScaleManager sm;

	public ChessTubeMidi(Music smlMusic){
		
		im=new InstrumentManager(smlMusic);
		sm=new ScaleManager();
	
		try {
			sequence=new Sequence(Sequence.PPQ,1,im.getSize()); 
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		setInstruments();
		
		smlMusic.accept(this);
		
	}
	

	public void saveMidi(String fileName){
		File outputFile = new File(fileName);

		try
		{
			MidiSystem.write(sequence, 1, outputFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void setInstruments() {
		//TODO
	}


	@Override
	public void visit(IVisitable obj) {
		if(obj instanceof Play){
			Play p=(Play) obj;
			sm.setCurrentScale(SMLConverter.convertScale(p.getScale()));
		}
		if(obj instanceof PlayableElement){
			PlayableElement pe=(PlayableElement) obj;
			im.setCurrentInstrument(pe.getInstrument());
		}
		
	}
}
