package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IVisitor;

public class Repeat implements IMusicalElement {

	private int n;
	private IMusicalElement ime;
	
	public Repeat(int n, IMusicalElement m) {
		this.n=n;
		this.ime=m;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		ime.accept(visitor);
		
	}

	@Override
	public int getTime(Declarations environnement) {
		return ime.getTime(environnement)*n;
	}

	public int getRepeatNumber() {
		return n;
	}

	public IMusicalElement getMusicalElement() {
		return ime;
	}

}
