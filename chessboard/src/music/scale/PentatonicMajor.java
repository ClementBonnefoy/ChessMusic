package music.scale;

import board.Rank;
import music.NoteName;
import music.Scale;

public class PentatonicMajor extends Scale {
	
	private final static int[] scale={0,2,4,7,9};

	public PentatonicMajor(NoteName fundamental) {
		super(fundamental, scale);
	}
	
	public static void main(String[] args){
		Scale scale=new PentatonicMajor(NoteName.C);
		for(Rank r:Rank.values()){
			System.out.println(scale.getNote(r, 3).getMidiValue(NoteName.C));
		}
	}

}
