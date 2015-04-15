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
	
	private final boolean canKingSideCastleBefore;
	
	public QueenSideCastling(Piece movingPiece, Piece castlingRook,
			ESquare enPassantBefore, boolean canKingSideCastleBefore) {
		super(movingPiece.getColor() == White ? E1 : E8,
				movingPiece.getColor().queenSideSquare(),
				movingPiece, enPassantBefore);
		this.castlingRook = castlingRook;
		this.canKingSideCastleBefore = canKingSideCastleBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		castlingRook.onMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, A1, D1);
		else
			simpleMove(board, A8, D8);
		
		board.currentSide().setKingSideCastling(false);
		board.currentSide().setQueenSideCastling(false);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		castlingRook.onUndoMove(board, this);
		
		if (movingPiece.getColor() == White)
			simpleMove(board, D1, A1);
		else
			simpleMove(board, D8, A8);
		
		board.currentSide().setKingSideCastling(canKingSideCastleBefore);
		board.currentSide().setQueenSideCastling(true);
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
		return new PGNQueenSideCastling(movingPiece.getColor(), check, checkMate,
				board.getMoveNumber());

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
		QueenSideCastling other = (QueenSideCastling) obj;
		if (castlingRook == null) {
			if (other.castlingRook != null)
				return false;
		} else if (!castlingRook.equals(other.castlingRook))
			return false;
		return true;
	}
	
	
	
}

