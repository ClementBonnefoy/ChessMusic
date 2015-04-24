package sml.elements;

import sml.interfaces.IInstrument;
import sml.interfaces.IVisitor;

public enum Instrument implements IInstrument{
	
	drumKit,
	organ,
	piano,
	flute,
	harpsichord,
	vibraphone,
	marimba,
	xylophone,
	electricBass,
	cello,
	string,
	alto,
	pizzicato,
	churchorgan;
	
	public static Instrument instrument(String s){
		return valueOf(s);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

}
