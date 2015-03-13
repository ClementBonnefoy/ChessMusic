package sml.musicalelements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IRole;
import sml.interfaces.ITime;
import sml.various.Octave;
import sml.various.Time;

public class ComplexNote implements IMusicalElement {
	
	private IRole role;
	private Octave octave;
	private ITime time;
	
	public ComplexNote(IRole role, Octave octave, ITime time) {
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
