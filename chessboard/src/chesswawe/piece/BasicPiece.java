package chesswawe.piece;

import music.Scale;
import board.Piece;
import board.Rank;

public abstract class BasicPiece extends ChessWavePiece {

	protected Scale scale;

	public BasicPiece(Piece piece,Scale scale) {
		super(piece);
		this.scale=scale;
	}

	@Override
	protected int keyOfRankForWhite(Rank rank){
		return scale.getNote(rank,4).getMidiValue(scale.getFundamental());
	}


}
