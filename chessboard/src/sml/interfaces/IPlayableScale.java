package sml.interfaces;

import sml.elements.Declarations;
import sml.elements.Note;

public interface IPlayableScale extends IDeclarable {
	public Note getScaleFundamental(Declarations env);
	public IScale getScale(Declarations env);
}
