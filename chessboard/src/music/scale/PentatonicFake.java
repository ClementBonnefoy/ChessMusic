package music.scale;

import board.Rank;
import music.NoteName;
import music.Scale;

public class PentatonicFake extends Scale {
	
	private final static int[] scale={0,2,5,7,9};

	public PentatonicFake(NoteName fundamental) {
		super(fundamental, scale);
	}
	
	public static void main(String[] args){
		Scale scale=new PentatonicFake(NoteName.C);
		for(Rank r:Rank.values()){
			System.out.println(scale.getNote(r, 3).getMidiValue(NoteName.C));
		}
	}

}
