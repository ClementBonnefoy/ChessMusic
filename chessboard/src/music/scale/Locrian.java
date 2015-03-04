package music.scale;

import music.Note;
import music.NoteName;
import music.Scale;

public class Locrian extends Scale {


	public Locrian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 1 , 3 , 5 , 6 , 8 , 10};
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Locrienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Locrian gamme=new Locrian(NoteName.B);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.B,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
