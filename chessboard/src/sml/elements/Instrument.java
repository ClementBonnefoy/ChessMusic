package sml.elements;

import sml.interfaces.IInstrument;
import sml.interfaces.IVisitor;

public enum Instrument implements IInstrument{
	
	piano,
	flute,
	harpsichord,
	vibraphone,
	marimaba,
	xylophone,
	electricBass;
	
	public static Instrument instrument(String s){
		return valueOf(s);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

}
