package sml.interfaces;

import sml.elements.Declarations;
import sml.elements.Note;
import sml.elements.ScaleName;

public interface IPlayableScale extends IDeclarable {
	public Note getScaleFundamental(Declarations env);
	public IScale getScale(Declarations env);
}
