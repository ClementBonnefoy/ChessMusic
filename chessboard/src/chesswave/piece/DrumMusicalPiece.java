package chesswave.piece;

import board.Square;
import board.Piece;
import board.Rank;

public class DrumMusicalPiece extends AbstractChessWavePiece {

	private static int[] scale = 
		{ 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 };
	
	public DrumMusicalPiece(Piece piece) {
		super(piece);
	}

	@Override
	protected int keyOfRankForWhite(Rank rank) {
		return(scale[rank.getNum()]);
	}

}
