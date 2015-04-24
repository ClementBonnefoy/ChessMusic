package chesswawe.piece;

import music.Instrument;
import music.Scale;
import board.Piece;
import chesswawe.Configuration;

public class Bishop extends BassPiece {

	public final static Instrument INSTRUMENT=Configuration.bishop;
	public final static int CHANNEL=4;
	
	public Bishop(Piece piece, Scale scale) {
		super(piece, scale);
	}

	
	public int getChannel() {
		return CHANNEL;
	}

}
