package pgn;

import move.Move;
import move.QueenSideCastling;
import board.Board;
import board.Color;
import static board.Type.King;

public class PGNQueenSideCastling extends PGNMove {

	public PGNQueenSideCastling(Color color, boolean check, boolean checkmate, 
			int moveNumber) {
		super(King, color, color.queenSideSquare(),
				null, null, false, check, checkmate, moveNumber);
	}

	@Override
	public Move makeMove(Board board) {
		return new QueenSideCastling(color, check, checkMate, board.getEnPassant());
	}

	@Override
	public String toString() {
		return "O-O-O";
	}
	
}