package engine;

public class Evaluation {
	
	public Evaluation(double eval, boolean matewhite, boolean mateblack) {
		score=eval;
		mateForWhite=matewhite;
		mateForBlack=mateblack;
	}
	
	public Evaluation(double eval){
		this(eval,false,false);
	}

	private double score;
	private boolean mateForWhite;
	private boolean mateForBlack;
	
	public static Evaluation MATE_FOR_WHITE=new Evaluation(Double.MAX_VALUE,true,false);
	public static Evaluation MATE_FOR_BLACK=new Evaluation(Double.MIN_VALUE,false,true);
	
	public double getScore(){
		return score;
	}
	
	public boolean isMate(){
		return isMateForBlack() || isMateForWhite();
	}
	public boolean isMateForWhite(){
		return mateForWhite;
	}
	
	public boolean isMateForBlack(){
		return mateForBlack;
	}
	
	public String toString(){
		if(mateForWhite)
			return "mate +-";
		if(mateForBlack)
			return "mate -+";
		return ""+score;
	}
}
