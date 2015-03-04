package music.scale;

import music.Note;
import music.NoteName;
import music.Scale;

public class Lydian extends Scale {


	public Lydian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 2 , 4 , 6 , 7 , 9 , 11};
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Lydienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Lydian gamme=new Lydian(NoteName.F);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.F,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
