package chesswawe.piece;

import chesswawe.InstrumentConfiguration;
import music.Instrument;
import music.Scale;
import board.Piece;

public class Queen extends BasicPiece {

	public final static Instrument INSTRUMENT=InstrumentConfiguration.queen;
	public final static int CHANNEL=1;
	
	public Queen(Piece piece, Scale scale) {
		super(piece,scale);
	}

	@Override
	public int getChannel() {
		return CHANNEL;
	}

}
