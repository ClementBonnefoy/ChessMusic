package chesswawe.piece;

import board.Piece;
import board.Rank;

public abstract class BasicPiece extends ChessWavePiece {

	private static int[] scale = 
		{ 64 , 66 , 69 , 71 , 73 , 76 , 78 , 81 }; //gamme pentatonique (chinese style!)

	public BasicPiece(Piece piece) {
		super(piece);
	}

	@Override
	protected int keyOfRankForWhite(Rank rank){
		return scale[rank.getNum()];
	}


}
