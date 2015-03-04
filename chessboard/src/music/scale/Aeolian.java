package music.scale;

import music.Note;
import music.NoteName;
import music.Scale;

public class Aeolian extends Scale {


	public Aeolian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 2 , 3 , 5 , 7 , 8 , 10 };
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Aeolienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Aeolian gamme=new Aeolian(NoteName.A);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.A,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
