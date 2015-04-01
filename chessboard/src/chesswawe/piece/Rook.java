package chesswawe.piece;

import chesswawe.InstrumentConfiguration;
import music.Instrument;
import music.Scale;
import board.Piece;

public class Rook extends BasicPiece {

	public final static Instrument INSTRUMENT=InstrumentConfiguration.rook;
	public static final int CHANNEL=0;
	
	public Rook(Piece piece, Scale scale) {
		super(piece,scale);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
