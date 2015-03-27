package sml.elements;

import sml.interfaces.IPlayableElement;
import sml.interfaces.IVisitor;

public class PlayableChord implements IPlayableElement{
	
	private IPlayableElement e1;
	private IPlayableElement e2;
	
	public PlayableChord(IPlayableElement e1, IPlayableElement e2) {
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

	public IPlayableElement getFirstElement() {
		return e1;
	}

	public IPlayableElement getSecondElement() {
		return e2;
	}
	
	

}
