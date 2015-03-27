package chesstube.music.scale;

import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Dorian extends Scale {


	public Dorian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ false , true , false , false , false , true };
	
	
	
	@Override
	public String toString(){
		return "Gamme Dorienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Dorian(NoteName.C));
	}

	

}
