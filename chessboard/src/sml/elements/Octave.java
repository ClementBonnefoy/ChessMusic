package sml.elements;

import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

public class Octave implements ISMLElement {
	
	private char octave;

	public Octave(String octave) {
		super();
		this.octave = octave.charAt(0);
	}
	
	public Octave(char octave) {
		super();
		this.octave = octave;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	public int getValue() {
		return octave-'a';
	}
	
	

}
