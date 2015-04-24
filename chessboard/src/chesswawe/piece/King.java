package chesswawe.piece;

import music.Instrument;
import music.Scale;
import board.Piece;
import chesswawe.Configuration;

public class King extends BasicPiece {

	public final static Instrument INSTRUMENT=Configuration.king;
	public final static int CHANNEL=3;
	
	public King(Piece piece, Scale scale) {
		super(piece, scale);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}
	

}
