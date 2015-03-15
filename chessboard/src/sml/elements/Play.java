package sml.elements;

import sml.interfaces.IInstruction;
import sml.interfaces.IPlayableElement;

public class Play implements IInstruction {
	
	private Scale scale;
	private IPlayableElement playableElement;
	
	public Play(Scale scale, IPlayableElement playableElement) {
		super();
		this.scale = scale;
		this.playableElement = playableElement;
	}

	@Override
	public int getTime(Declarations environnement) {
		return playableElement.getTime(environnement);
	}
	
	

}
