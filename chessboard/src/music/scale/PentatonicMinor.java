package music.scale;

import music.NoteName;
import music.Scale;
import board.Rank;

public class PentatonicMinor extends Scale {
	
	private final static int[] scale={0,3,5,7,10};

	public PentatonicMinor(NoteName fundamental) {
		super(fundamental, scale);
	}
	
	public static void main(String[] args){
		Scale scale=new PentatonicMinor(NoteName.C);
		for(Rank r:Rank.values()){
			System.out.println(scale.getNote(r, 3).getMidiValue(NoteName.C));
		}
	}

}
