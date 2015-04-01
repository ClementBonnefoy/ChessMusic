package music.scale;

import music.NoteName;
import music.Scale;

public class Lydian extends Scale {


	public Lydian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ false , false , false , true , false , false };
	
	
	@Override
	public String toString(){
		return "Gamme Lydienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Aeolian(NoteName.C));
	}

	

}
