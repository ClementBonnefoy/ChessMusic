package sml.elements;

import sml.interfaces.IInstrument;
import sml.interfaces.IMusicalElement;
import sml.interfaces.IPlayableElement;

public class PlayableElement implements IPlayableElement {
	
	private IInstrument instrument;
	private IMusicalElement musicalElement;
	
	public PlayableElement(IInstrument instrument,
			IMusicalElement musicalElement) {
		super();
		this.instrument = instrument;
		this.musicalElement = musicalElement;
	}

	@Override
	public int getTime(Declarations environnement) {
		return musicalElement.getTime(environnement);
	}
	
	

}
