package chesswawe.piece;

import music.Instrument;
import music.Scale;
import board.Piece;
import chesswawe.InstrumentConfiguration;

public class Bishop extends BassPiece {

	public final static Instrument INSTRUMENT=InstrumentConfiguration.bishop;
	public final static int CHANNEL=4;
	
	public Bishop(Piece piece, Scale scale) {
		super(piece, scale);
	}

	
	public int getChannel() {
		return CHANNEL;
	}

}
