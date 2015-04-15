package move;

import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

public class EnPassantCapture extends Move {
	
	private final Piece captured;
	private final ESquare captureSquare;
	private final int limit50movesBefore;
	
	public EnPassantCapture(ESquare from, ESquare to, Piece movingPiece,
			Piece captured,
			int limit50movesBefore) {
		super(from, to, movingPiece, to);
		this.captured = captured;
		captureSquare = to.nextSquare(movingPiece.getColor().backwards());
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		board.opponentSide().remove(board.get(captureSquare));
		board.putOnSquare(null, captureSquare);
		board.setLimit50moves(0);
	}

	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.opponentSide().add(board.get(captureSquare));
		board.putOnSquare(captured, captureSquare);
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
		
		undoFrom(board);
		
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(), to, null, from.getFile(),
				true, check, checkMate,
				board.getMoveNumber());
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((captureSquare == null) ? 0 : captureSquare.hashCode());
		result = prime * result
				+ ((captured == null) ? 0 : captured.hashCode());
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
		EnPassantCapture other = (EnPassantCapture) obj;
		if (captureSquare != other.captureSquare)
			return false;
		if (captured == null) {
			if (other.captured != null)
				return false;
		} else if (!captured.equals(other.captured))
			return false;
		if (limit50movesBefore != other.limit50movesBefore)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EnPassantCapture [captured=" + captured + ", captureSquare="
				+ captureSquare + ", limit50movesBefore=" + limit50movesBefore
				+ ", from=" + from + ", to=" + to + ", movingPiece="
				+ movingPiece + ", enPassantBefore=" + enPassantBefore + "]";
	}

	
	
}
