package chesstube;

import java.util.ArrayList;

import javax.sound.midi.Track;

import midi.MidiTools;
import music.Instrument;
import sml.compiler.SMLCompiler;
import sml.elements.Declarations;
import sml.elements.Ens;
import sml.elements.Music;
import sml.elements.Variable;
import sml.interfaces.IInstrument;
import sml.interfaces.IVisitable;
import sml.interfaces.IVisitor;

public class InstrumentManager implements IVisitor {

	public ArrayList<Instrument> instruments;
	public IInstrument current;

	public InstrumentManager(Music smlMusic){
		instruments=new ArrayList<Instrument>();
		smlMusic.accept(this);
	}

	@Override
	public void visit(IVisitable obj) {
		if(obj instanceof sml.elements.Instrument){
			Instrument instr=SMLConverter.convertInstrument(
					(sml.elements.Instrument) obj);
			if(!instruments.contains(instr))
				instruments.add(instr);
		}

	}

	public void setCurrentInstrument(IInstrument current){
		this.current=current;
	}

	public int getSize(){
		return instruments.size();
	}

	private int getChannel(Instrument instr){
		int channel=instruments.indexOf(instr)+1;
		if(channel<1)
			throw new RuntimeException("undefined instrument");
		return channel;
	}

	public ArrayList<Integer> getCurrentChannels(Declarations env){
		return getChannels(current,env);
	}

	private ArrayList<Integer> getChannels(IInstrument instr,Declarations env){
		if(instr instanceof Ens){
			Ens ens=(Ens) instr;
			ArrayList<Integer> res1=getChannels(ens.getFirstInstrument(),env);
			ArrayList<Integer> res2=getChannels(ens.getSecondInstrument(),env);
			res1.addAll(res2);
			return res1;
		}
		if(instr instanceof sml.elements.Instrument){
			Instrument i=SMLConverter.convertInstrument(
					(sml.elements.Instrument) instr);
			ArrayList<Integer> res=new ArrayList<Integer>();
			res.add(getChannel(i));
			return res;	
		}
		if(instr instanceof Variable){
			IInstrument i=(IInstrument) ((Variable)instr).getValue(env);
			return getChannels(i, env);
		}
		return new ArrayList<Integer>();

	}

	public static void main(String [] args){
		Music music=SMLCompiler.getExample();
		InstrumentManager im=new InstrumentManager(music);
		System.out.println("nombre d'instruments :"+im.getSize());
		im.setCurrentInstrument(sml.elements.Instrument.flute);
		System.out.println("channel de la flute:" +
				im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(sml.elements.Instrument.piano);
		System.out.println("channel du piano:" 
				+im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(new Ens(sml.elements.Instrument.flute,
				sml.elements.Instrument.piano));
		System.out.println("channel de la flute et du piano:"
				+im.getCurrentChannels(music.getEnvironnement()));
	}

	public void configureTracks(Track[] tracks) {
		for(int i=0;i<tracks.length;i++){
			MidiTools.setInstrument(tracks[i],
					instruments.get(i) , i+1);
		}
		
	}



}
