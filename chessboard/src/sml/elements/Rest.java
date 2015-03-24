package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.ITime;
import sml.interfaces.IVisitor;

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

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		time.accept(visitor);
		
	}
	
	

}
