package pgn;

import static board.File.FileA;
import static board.Type.King;
import move.Move;
import move.QueenSideCastling;
import board.Board;
import board.Color;
import board.ESquare;
import board.Piece;

public class PGNQueenSideCastling extends PGNMove {

	public PGNQueenSideCastling(Color color, boolean check, boolean checkmate, 
			int moveNumber) {
		super(King, color, color.queenSideSquare(),
				null, null, false, check, checkmate, moveNumber);
	}

	@Override
	public Move makeMove(Board board) {
		Piece king = board.currentSide().getKing().getPiece();
		Piece rook = board.getPiece(ESquare.getSquare(FileA, to.getRank()));
		return new QueenSideCastling(king, rook,
				check, checkMate, board.getEnPassant());
	}

	@Override
	public String toString() {
		return "O-O-O";
	}
	
}