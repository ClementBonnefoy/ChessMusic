package chesstube;

import music.Note;
import music.NoteName;
import music.Scale;
import sml.elements.ComplexNote;
import sml.elements.ScaleName;

public class ScaleManager{
	
	private static final double CENTER=0;

	Scale current;
	
	public Note getNote(ComplexNote n){
		return current.getNote(n);
	}
	
	public void setCurrentScale(Scale scale){
		current=scale;
	}
	
	public static ScaleName getScale(double eval,double ecartType){
		double intervalle_size=ecartType/2;
		double cursor=CENTER;
		if(eval>cursor){
			cursor+=intervalle_size/2;
			if(eval<cursor)
				return ScaleName.dorian;
			cursor+=intervalle_size;
			if(eval<cursor)
				return ScaleName.myxolydian;
			cursor+=intervalle_size;
			if(eval<cursor)
				return ScaleName.ionian;
			return ScaleName.lydian;
			
		}
		cursor-=intervalle_size/2;
		if(eval>cursor)
			return ScaleName.dorian;
		cursor-=intervalle_size;
		if(eval>cursor)
			return ScaleName.aeolian;
		cursor-=intervalle_size;
		if(eval>cursor)
			return ScaleName.phrygian;
		return ScaleName.locrian;
		
		
	}

	public NoteName getCurrentFundamental() {
		return current.getFundamental();
	}
	
	
	
	

}
