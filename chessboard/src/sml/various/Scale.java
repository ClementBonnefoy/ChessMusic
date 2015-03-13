package sml.various;

import sml.interfaces.IDeclarable;

public class Scale implements IDeclarable{
	
	private Note note;
	private ScaleName scale;
	
	public Scale(Note note, ScaleName scale) {
		super();
		this.note = note;
		this.scale = scale;
	}
	
	

}
