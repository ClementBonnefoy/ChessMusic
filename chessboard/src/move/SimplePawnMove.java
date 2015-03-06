package move;

import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

public class SimplePawnMove extends Move {
	private final int limit50movesBefore;


	public SimplePawnMove(ESquare from, ESquare to, Piece movingPiece,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to,
				movingPiece,
				enPassantBefore);
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		board.setLimit50moves(0);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		board.setLimit50moves(limit50movesBefore);
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
		
		undo(board);
		
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, null, null,
				false, check, checkMate,
				board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + limit50movesBefore;
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
		SimplePawnMove other = (SimplePawnMove) obj;
		if (limit50movesBefore != other.limit50movesBefore)
			return false;
		return true;
	}
	
	

}
