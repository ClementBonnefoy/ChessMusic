package chesswawe.piece;

import board.Piece;

public class Pawn extends DrumPiece {

	
	private final static int CHANNEL=9;
	
	public Pawn(Piece piece) {
		super(piece);
	}

	
	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
