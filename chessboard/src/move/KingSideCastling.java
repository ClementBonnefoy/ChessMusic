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
	
	private final boolean canQueenSideCastleBefore;

	
	public KingSideCastling(Piece movingPiece, Piece castlingRook,
			ESquare enPassantBefore, boolean canQueenSideCastleBefore) {
		
		super(movingPiece.getColor() == White ? E1 : E8,
				movingPiece.getColor().kingSideSquare(),
				movingPiece,
				enPassantBefore);
		
		this.castlingRook = castlingRook;
		this.canQueenSideCastleBefore = canQueenSideCastleBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		castlingRook.onMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, H1, F1);
		else
			simpleMove(board, H8, F8);
		
		board.currentSide().setKingSideCastling(false);
		board.currentSide().setQueenSideCastling(false);
		
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		castlingRook.onUndoMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, F1, H1);
		else
			simpleMove(board, F8, H8);
		
		board.currentSide().setKingSideCastling(true);
		board.currentSide().setQueenSideCastling(canQueenSideCastleBefore);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		boolean check, checkMate;
		
		applyTo(board);
		
		check = board.isInCheck();
		
		if (check)
			checkMate = board.isMate();
		else
			checkMate = false;
		
		undoFrom(board);
		
		return new PGNKingSideCastling(movingPiece.getColor(), check, checkMate, board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((castlingRook == null) ? 0 : castlingRook.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		KingSideCastling other = (KingSideCastling) obj;
		if (castlingRook == null) {
			if (other.castlingRook != null)
				return false;
		} else if (!castlingRook.equals(other.castlingRook))
			return false;
		return true;
	}
	
	
	
}
