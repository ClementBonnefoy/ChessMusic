package chesswawe.piece;

import board.Piece;

public class Bishop extends BasicPiece {

	public final static int INSTRUMENT=35;//Electric Bass(pick)
	public final static int CHANNEL=4;
	
	public Bishop(Piece piece) {
		super(piece);
	}

	
	public int getChannel() {
		return CHANNEL;
	}

}
