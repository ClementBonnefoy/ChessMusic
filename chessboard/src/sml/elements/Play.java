package sml.elements;

import sml.interfaces.IInstruction;
import sml.interfaces.IPlayableElement;
import sml.interfaces.IPlayableScale;
import sml.interfaces.IVisitor;

public class Play implements IInstruction {
	
	private IPlayableScale scale;
	private IPlayableElement playableElement;
	
	public Play(IPlayableScale scale, IPlayableElement playableElement) {
		super();
		this.scale = scale;
		this.playableElement = playableElement;
	}

	@Override
	public int getTime(Declarations environnement) {
		return playableElement.getTime(environnement);
	}
	
	@Override
	public String toString(){
		return "play "+scale;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		scale.accept(visitor);
		playableElement.accept(visitor);
		
	}

	public IPlayableScale getScale() {
		return scale;
	}

	public IPlayableElement getElement() {
		return playableElement;
	}
	
	
	
	

}
