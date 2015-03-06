package music.scale;

import music.Note;
import music.NoteName;
import music.Scale;

public class Dorian extends Scale {


	public Dorian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 2 , 3 , 5 , 7 , 9 , 10};
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Dorienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Dorian gamme=new Dorian(NoteName.D);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.D,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
