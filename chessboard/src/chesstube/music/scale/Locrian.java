package chesstube.music.scale;

import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Locrian extends Scale {


	public Locrian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ true , false , false , true , false , false };
	
	
	@Override
	public String toString(){
		return "Gamme Locrienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Locrian(NoteName.C));
	}
	

	

}
