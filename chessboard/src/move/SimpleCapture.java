package move;

import static board.Type.Pawn;
import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.File;
import board.Piece;

public class SimpleCapture extends Move {
	
	private Piece eaten;
	private final int limit50movesBefore;


	public SimpleCapture(ESquare from, ESquare to,
			Piece movingPiece, Piece eaten,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece,
				enPassantBefore);
		
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}
	
	public Piece getEaten() {
		return eaten;
	}
	
	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.opponentSide().remove(board.get(to));
		board.setLimit50moves(0);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.putOnSquare(eaten, to);
		board.opponentSide().add(board.get(to));
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
		
		PGNMove pm = super.makePGNMove(board);
		File file = pm.getFromFile();
		if (movingPiece.getType() == Pawn)
			file = from.getFile();
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, pm.getFromRank(), file,
				true, check, checkMate,
				board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eaten == null) ? 0 : eaten.hashCode());
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
		SimpleCapture other = (SimpleCapture) obj;
		if (eaten == null) {
			if (other.eaten != null)
				return false;
		} else if (!eaten.equals(other.eaten))
			return false;
		if (limit50movesBefore != other.limit50movesBefore)
			return false;
		return true;
	}
	
	
	
}
