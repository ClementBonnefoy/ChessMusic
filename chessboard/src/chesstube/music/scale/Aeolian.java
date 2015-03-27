package chesstube.music.scale;

import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Aeolian extends Scale {

	private final static boolean [] SCALE = 
		{ false , true , false , false , true , false };

	public Aeolian(NoteName fundamental) {
		super(fundamental,SCALE);
	}
	
	@Override
	public String toString(){
		return "Gamme Eolienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Aeolian(NoteName.C));
	}

	

}
