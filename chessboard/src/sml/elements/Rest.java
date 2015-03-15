package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.ITime;

public class Rest implements IMusicalElement{
	
	private ITime time;

	public Rest(ITime time) {
		super();
		this.time = time;
	}

	@Override
	public int getTime(Declarations environnement) {
		return time.getTime(environnement);
	}
	
	

}
