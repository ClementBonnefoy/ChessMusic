package sml.playableelements;

import sml.interfaces.IPlayableElement;

public class PlayableSequence implements IPlayableElement {
	
	private IPlayableElement e1;
	private IPlayableElement e2;
	
	public PlayableSequence(IPlayableElement e1, IPlayableElement e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

}
