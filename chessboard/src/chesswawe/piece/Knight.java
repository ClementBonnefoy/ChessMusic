package chesswawe.piece;

import board.Piece;

public class Knight extends BasicPiece {

	public final static int INSTRUMENT=34;//Electric Bass(finger)
	public final static int CHANNEL=2;

	public Knight(Piece piece) {
		super(piece);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}
	

}
