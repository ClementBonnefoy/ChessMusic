package move;

import static board.Color.White;
import static board.ESquare.A1;
import static board.ESquare.A8;
import static board.ESquare.D1;
import static board.ESquare.D8;
import static board.ESquare.E1;
import static board.ESquare.E8;
import pgn.PGNMove;
import pgn.PGNQueenSideCastling;
import board.Board;
import board.ESquare;
import board.Piece;

public class QueenSideCastling extends Move {
	
	private final Piece castlingRook;
	
	public QueenSideCastling(Piece movingPiece, Piece castlingRook,
			boolean check, boolean checkMate,
			ESquare enPassantBefore) {
		super(movingPiece.getColor() == White ? E1 : E8,
				movingPiece.getColor().queenSideSquare(),
				movingPiece, check, checkMate,
				enPassantBefore);
		this.castlingRook = castlingRook;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		castlingRook.onMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, A1, D1);
		else
			simpleMove(board, A8, D8);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		castlingRook.onUndoMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, D1, A1);
		else
			simpleMove(board, D8, A8);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNQueenSideCastling(movingPiece.getColor(), check, checkMate,
				board.getMoveNumber());

	}
	
}

