package chesstube.music.scale;

import chesstube.music.Note;
import chesstube.music.NoteName;
import chesstube.music.Scale;

public class Myxolydian extends Scale {


	public Myxolydian(NoteName fundamental) {
		super(fundamental);
	}

	private final static int [] SCALE = 
		{ 0 , 2 , 4 , 5 , 7 , 9 , 10};
	
	
	protected int[] getScale() {
		return SCALE;
	}
	
	@Override
	public String toString(){
		return "Gamme Myxolydienne de "+fundamental.toString();
	}
	
	public static void main (String[] args){
		Myxolydian gamme=new Myxolydian(NoteName.G);
		System.out.println(gamme);
		Note[] partie=gamme.getNNotes(new Note(NoteName.G,3), 8);
		for(Note n: partie){
			System.out.println(n);
		}
	}

	

}
