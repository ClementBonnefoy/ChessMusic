package chesstube;

import java.util.ArrayList;

import javax.sound.midi.Track;

import midi.MidiTools;
import music.Instrument;
import sml.compiler.SMLCompiler;
import sml.elements.Declarations;
import sml.elements.Ens;
import sml.elements.InstrumentWithVelocity;
import sml.elements.Music;
import sml.elements.Variable;
import sml.interfaces.IInstrument;
import sml.interfaces.IVisitable;
import sml.interfaces.IVisitor;

public class InstrumentManager implements IVisitor {

	public static final int DRUM_CHANNEL = 9;
	
	private ArrayList<Instrument> instruments;
	private IInstrument current;
	private boolean drum;

	public InstrumentManager(Music smlMusic){
		instruments=new ArrayList<Instrument>();
		drum=false;
		smlMusic.accept(this);
	}

	@Override
	public void visit(IVisitable obj) {
		if(obj instanceof sml.elements.Instrument){
			Instrument instr=SMLConverter.convertInstrument(
					(sml.elements.Instrument) obj);
			if(!instruments.contains(instr)){
				if(instr == Instrument.drumKit){
					drum=true;
				}
				else{
					instruments.add(instr);
				}
			}
		}

	}

	public void setCurrentInstrument(IInstrument current, Declarations env){
		this.current=current;
		updateVelocity(current,env);
	}

	private void updateVelocity(IInstrument instru, Declarations env) {
		if(instru instanceof InstrumentWithVelocity){
			InstrumentWithVelocity i=((InstrumentWithVelocity) instru);
			for(Instrument inst: getInstruments(i.getInstrument(), env)){
				inst.setVelocity(i.getVelocity());
			}
			return;
		}
		if(instru instanceof Ens){
			updateVelocity(((Ens)instru).getFirstInstrument(), env);
			updateVelocity(((Ens)instru).getSecondInstrument(), env);
			return;
		}
		if(instru instanceof Variable){
			IInstrument i=(IInstrument) ((Variable)instru).getValue(env);
			updateVelocity(i, env);
			return;
		}
		if(instru instanceof sml.elements.Instrument){
			sml.elements.Instrument i=((sml.elements.Instrument)instru);
			SMLConverter.convertInstrument(i).setDefaultVelocity();
		}
	}

	public int getSize(){
		return instruments.size();
	}
	
	public ArrayList<Instrument> getInstruments(IInstrument instr,
			Declarations env){
		ArrayList<Instrument> res=new ArrayList<Instrument>();
		if(instr instanceof sml.elements.Instrument){
			res.add(SMLConverter.convertInstrument(
					(sml.elements.Instrument)instr));
			return res;
		}
		if(instr instanceof Ens){
			Ens ens=(Ens) instr;
			ArrayList<Instrument> res1=getInstruments(ens.getFirstInstrument(),env);
			ArrayList<Instrument> res2=getInstruments(ens.getSecondInstrument(),env);
			res.addAll(res1);
			res.addAll(res2);
			return res;
		}
		if(instr instanceof InstrumentWithVelocity){
			InstrumentWithVelocity i=((InstrumentWithVelocity) instr);
			return getInstruments(i.getInstrument(), env);	
		}
		if(instr instanceof Variable){
			IInstrument i=(IInstrument) ((Variable)instr).getValue(env);
			return getInstruments(i, env);
		}
		return new ArrayList<Instrument>();
	}

	private int getChannel(Instrument instr){
		if(instr == Instrument.drumKit){
			return DRUM_CHANNEL;
		}
		int channel=instruments.indexOf(instr);
		if(channel<0)
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
		if(instr instanceof InstrumentWithVelocity){
			InstrumentWithVelocity i=((InstrumentWithVelocity) instr);
			return getChannels(i.getInstrument(),env);	
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
		im.setCurrentInstrument(sml.elements.Instrument.flute,music.getEnvironnement());
		System.out.println("channel de la flute:" +
				im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(sml.elements.Instrument.piano,music.getEnvironnement());
		System.out.println("channel du piano:" 
				+im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(new Ens(sml.elements.Instrument.flute,
				sml.elements.Instrument.piano),music.getEnvironnement());
		System.out.println("channel de la flute et du piano:"
				+im.getCurrentChannels(music.getEnvironnement()));
	}

	public void configureTracks(Track[] tracks) {
		for(int i=0;i<tracks.length;i++){
			MidiTools.setInstrument(tracks[i],
					instruments.get(i) , i);
		}
		
	}

	public int getVelocity(int channel) {
		if(channel==DRUM_CHANNEL)
			return Instrument.drumKit.getVelocity();
		return instruments.get(channel).getVelocity();
	}

	public boolean hasDrum() {
		return drum;
	}



}
