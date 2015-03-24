package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IVisitor;

public class Chord implements IMusicalElement{
	
	private IMusicalElement e1;
	private IMusicalElement e2;
	
	public Chord(IMusicalElement e1, IMusicalElement e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public int getTime(Declarations environnement) {
		return Math.max(e1.getTime(environnement), e2.getTime(environnement));
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		e1.accept(visitor);
		e2.accept(visitor);
		
	}

}
