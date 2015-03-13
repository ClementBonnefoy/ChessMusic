package sml.musicalelements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IRole;
import sml.various.Octave;
import sml.various.Time;

public class ComplexNote implements IMusicalElement {
	
	private IRole role;
	private Octave octave;
	private Time time;
	
	public ComplexNote(IRole role, Octave octave, Time time) {
		super();
		this.role = role;
		this.octave = octave;
		this.time = time;
	}

	public static ComplexNote parse(String c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
