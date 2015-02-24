package chesswawe.piece;

import board.Piece;

public class King extends BasicPiece {

	public final static int INSTRUMENT=20;//Church Organ
	public final static int CHANNEL=3;
	
	public King(Piece piece) {
		super(piece);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}
	

}
