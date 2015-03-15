package sml.elements;

import sml.interfaces.IMusicalElement;

public class Sequence implements IMusicalElement {
	
	private IMusicalElement e1;
	private IMusicalElement e2;
	
	public Sequence(IMusicalElement e1, IMusicalElement e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public int getTime(Declarations environnement) {
		return e1.getTime(environnement)+e2.getTime(environnement);
	}

}
