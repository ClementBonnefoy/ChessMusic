package chesswawe.piece;

import board.Piece;

public class Queen extends BasicPiece {

	public final static int INSTRUMENT=89;//Pad 1(new age)
	public final static int CHANNEL=1;
	
	public Queen(Piece piece) {
		super(piece);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
