package chesswawe.piece;

import board.Piece;

public class Rook extends BasicPiece {

	public final static int INSTRUMENT=81;//Lead 1(square)
	public static final int CHANNEL=0;
	
	public Rook(Piece piece) {
		super(piece);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
