package chesstube.music.scale;

import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Myxolydian extends Scale {


	public Myxolydian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ false , false , true , false , false , true };
	
	
	@Override
	public String toString(){
		return "Gamme Myxolydienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Myxolydian(NoteName.C));
	}
	

	

}
