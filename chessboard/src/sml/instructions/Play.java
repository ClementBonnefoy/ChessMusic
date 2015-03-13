package sml.instructions;

import sml.interfaces.IInstruction;
import sml.interfaces.IPlayableElement;
import sml.various.Scale;

public class Play implements IInstruction {
	
	private Scale scale;
	private IPlayableElement playableElement;
	
	public Play(Scale scale, IPlayableElement playableElement) {
		super();
		this.scale = scale;
		this.playableElement = playableElement;
	}
	
	

}
