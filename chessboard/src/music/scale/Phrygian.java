package music.scale;

import music.NoteName;
import music.Scale;

public class Phrygian extends Scale {


	public Phrygian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ true , false , false , false , true , false };
	
	
	@Override
	public String toString(){
		return "Gamme Phrygienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Phrygian(NoteName.C));
	}

	

}
