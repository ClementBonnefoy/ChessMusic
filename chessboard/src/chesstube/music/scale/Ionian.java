package chesstube.music.scale;

import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Ionian extends Scale {


	public Ionian(NoteName fundamental) {
		super(fundamental,SCALE);
	}

	private final static boolean [] SCALE = 
		{ false , false , true , false , false , false };
	
	
	@Override
	public String toString(){
		return "Gamme Ionienne de "+fundamental.toString()+"--->"+
				super.toString();
	}
	
	public static void main(String[] args){
		System.out.println(new Ionian(NoteName.C));
	}
	

	

}
