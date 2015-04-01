package chesswawe.piece;

import chesswawe.InstrumentConfiguration;
import music.Instrument;
import music.Scale;
import board.Piece;

public class Knight extends BassPiece {

	public final static Instrument INSTRUMENT=InstrumentConfiguration.knight;
	public final static int CHANNEL=2;

	public Knight(Piece piece, Scale scale) {
		super(piece,scale);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
