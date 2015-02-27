package pgn;

import static board.File.FileH;
import static board.Type.King;
import move.KingSideCastling;
import move.Move;
import board.Board;
import board.Color;
import board.ESquare;
import board.Piece;

public class PGNKingSideCastling extends PGNMove {

	public PGNKingSideCastling(Color color, boolean check, boolean checkmate, 
			int moveNumber) {
		super(King, color, color.kingSideSquare(),
				null, null, false, check, checkmate, moveNumber);
	}

	@Override
	public Move makeMove(Board board) {
		Piece king = board.currentSide().getKing().getPiece();
		Piece rook = board.getPiece(ESquare.getSquare(FileH, to.getRank()));
		return new KingSideCastling(king, rook,
				check, checkMate, board.getEnPassant());
	}

	
	public String toString() {
		return "O-O";
	}
	
}
