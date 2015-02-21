package chesswave.piece;

import board.Square;
import board.Piece;
import board.Rank;

public class BasicChessWavePiece extends AbstractChessWavePiece {

	private static int[] scale = 
		{ 69 , 71 , 73 , 76 , 78 , 81, 84, 86 }; //gamme pentatonique (chinese style!)

	public BasicChessWavePiece(Piece piece) {
		super(piece);
	}

	@Override
	protected int keyOfRankForWhite(Rank rank){
		return scale[rank.getNum()];
	}


}
