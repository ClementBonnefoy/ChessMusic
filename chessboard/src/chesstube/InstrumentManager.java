package chesstube;

import java.util.ArrayList;

import sml.compiler.SMLCompiler;
import sml.elements.Declarations;
import sml.elements.Instrument;
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
		if(obj instanceof Instrument){
			if(!instruments.contains(obj))
				instruments.add((Instrument) obj);
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
		if(instr instanceof Instrument){
			Instrument i=(Instrument) instr;
			ArrayList<Integer> res=new ArrayList<Integer>();
			res.add(getChannel(i));
			return res;	
		}
		if(instr instanceof Variable){
			IInstrument i=(IInstrument) ((Variable)instr).getValue(env);
			return getChannels(i, env);
		}
		return null;

	}

	public static void main(String [] args){
		Music music=SMLCompiler.getExample();
		InstrumentManager im=new InstrumentManager(music);
		System.out.println("nombre d'instruments :"+im.getSize());
		im.setCurrentInstrument(Instrument.flute);
		System.out.println("channel de la flute:" +
				im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(Instrument.piano);
		System.out.println("channel du piano:" 
				+im.getCurrentChannels(music.getEnvironnement()));
		im.setCurrentInstrument(new Ens(Instrument.flute,Instrument.piano));
		System.out.println("channel de la flute et du piano:"
				+im.getCurrentChannels(music.getEnvironnement()));
	}



}
