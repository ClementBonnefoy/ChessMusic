package sml.elements;

import sml.interfaces.IPlayableElement;

public class PlayableChord implements IPlayableElement{
	
	private IPlayableElement e1;
	private IPlayableElement e2;
	
	public PlayableChord(IPlayableElement e1, IPlayableElement e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

}
