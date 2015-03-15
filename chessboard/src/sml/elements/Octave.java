package sml.elements;

import sml.interfaces.ISMLElement;

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
	
	

}
