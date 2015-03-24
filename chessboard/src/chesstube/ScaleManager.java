package chesstube;

import chesstube.music.Note;
import chesstube.music.Scale;
import sml.elements.ComplexNote;

public class ScaleManager{

	Scale current;
	
	public Note getNote(ComplexNote n){
		return current.getNote(n);
	}
	
	public void setCurrentScale(Scale scale){
		current=scale;
	}
	
	
	
	

}
