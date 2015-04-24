package chesstube;

import engine.Evaluation;
import music.Note;
import music.NoteName;
import music.Scale;
import sml.elements.ComplexNote;
import sml.elements.ScaleName;

public class ScaleManager{
	
	private static final double CENTER=0;

	Scale current;
	
	public Note getNote(ComplexNote n, int transpose){
		return current.getNote(n,transpose);
	}
	
	public void setCurrentScale(Scale scale){
		current=scale;
	}
	
	/**
	 * Determine quel mode utiliser par rapport à une evaluation et un ecart type
	 * @param evaluation
	 * @param ecartType
	 * @return
	 */
	public static ScaleName getScale(Evaluation evaluation,double ecartType){
		if(evaluation.isMateForBlack())
			return ScaleName.locrian;
		if(evaluation.isMateForWhite())
			return ScaleName.lydian;
		double intervalle_size=ecartType/2;
		double cursor=CENTER;
		double eval=evaluation.getScore();
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
