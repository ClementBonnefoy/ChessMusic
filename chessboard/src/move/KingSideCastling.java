package move;

import static board.Color.White;
import static board.ESquare.E1;
import static board.ESquare.E8;
import static board.ESquare.F1;
import static board.ESquare.F8;
import static board.ESquare.H1;
import static board.ESquare.H8;
import pgn.PGNKingSideCastling;
import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

public class KingSideCastling extends Move {
	
	private final Piece castlingRook;
	
	public KingSideCastling(Piece movingPiece, Piece castlingRook,
			boolean check, boolean checkMate,
			ESquare enPassantBefore) {
		
		super(movingPiece.getColor() == White ? E1 : E8,
				movingPiece.getColor().kingSideSquare(),
				movingPiece, check, checkMate,
				enPassantBefore);
		
		this.castlingRook = castlingRook;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		castlingRook.onMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, H1, F1);
		else
			simpleMove(board, H8, F8);
		
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		castlingRook.onUndoMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, F1, H1);
		else
			simpleMove(board, F8, H8);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNKingSideCastling(movingPiece.getColor(), check, checkMate, board.getMoveNumber());
	}
	
}
