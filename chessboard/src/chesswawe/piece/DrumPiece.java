package chesswawe.piece;

import board.Piece;
import board.Rank;

public abstract class DrumPiece extends ChessWavePiece {

	private static int[] scale = 
		{ 0 , 0 , 35 , 43 , 47 , 52 , 59 , 0 };
	
	public DrumPiece(Piece piece) {
		super(piece);
	}

	@Override
	protected int keyOfRankForWhite(Rank rank) {
		return(scale[rank.getNum()]);
	}

}
