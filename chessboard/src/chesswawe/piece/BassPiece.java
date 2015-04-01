package chesswawe.piece;

import music.Scale;
import board.Piece;
import board.Rank;

public abstract class BassPiece extends BasicPiece {

	public BassPiece(Piece piece, Scale s) {
		super(piece, s);
	}
	
	@Override
	protected int keyOfRankForWhite(Rank rank){
		return scale.getNote(rank,1).getMidiValue(scale.getFundamental());
	}

}
