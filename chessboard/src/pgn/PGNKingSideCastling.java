package pgn;

import move.KingSideCastling;
import move.Move;
import board.Board;
import board.Color;
import static board.Type.King;

public class PGNKingSideCastling extends PGNMove {

	public PGNKingSideCastling(Color color, boolean check, boolean checkmate, 
			int moveNumber) {
		super(King, color, color.kingSideSquare(),
				null, null, false, check, checkmate, moveNumber);
	}

	@Override
	public Move makeMove(Board board) {
		return new KingSideCastling(color, check, checkMate);
	}

	
	public String toString() {
		return "O-O";
	}
	
}
