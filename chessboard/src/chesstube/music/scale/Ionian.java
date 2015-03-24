package chesstube.music.scale;

import chesstube.music.Note;
import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Ionian extends Scale {


	public Ionian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 2 , 4 , 5 , 7 , 9 , 11 };
	
	
	@Override
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Ionnienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Ionian gamme=new Ionian(NoteName.C);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.C,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
