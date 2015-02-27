package music.scale;

import music.Note;
import music.NoteName;
import music.Scale;

public class Phrygian extends Scale {


	public Phrygian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 1 , 3 , 5 , 7 , 8 , 10 };
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Phrygienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Phrygian gamme=new Phrygian(NoteName.E);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.E,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
