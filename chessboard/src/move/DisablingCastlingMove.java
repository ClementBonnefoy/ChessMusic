package move;

import static board.File.FileA;
import static board.File.FileH;
import static board.Type.King;
import static board.Type.Rook;
import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

/**
 * This move is useful for storing the previous castling state,
 * in order to be able to undo it
 *
 */

public class DisablingCastlingMove extends Move {
	
	private final boolean canKingSideCastleBefore, canQueenSideCastleBefore;
	private final Piece eaten;
	private final int limit50movesBefore;

	public DisablingCastlingMove(ESquare from, ESquare to,
			Piece movingPiece, Piece eaten,
			boolean canKingSideCastleBefore, boolean canQueenSideCastleBefore,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece, enPassantBefore);
		this.canKingSideCastleBefore = canKingSideCastleBefore;
		this.canQueenSideCastleBefore = canQueenSideCastleBefore;
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);

		if (eaten != null) {
			board.opponentSide().remove(board.get(to));
			board.setLimit50moves(0);
		}
		if (canKingSideCastleBefore &&
				(movingPiece.getType() == King || from.getFile() == FileH))
			board.currentSide().setKingSideCastling(false);
		if (canQueenSideCastleBefore &&
				(movingPiece.getType() == King || from.getFile() == FileA))
			board.currentSide().setQueenSideCastling(false);
	}

	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		if (eaten != null) {
			board.opponentSide().add(board.get(to));
			board.putOnSquare(eaten, to);
			board.setLimit50moves(limit50movesBefore);
		}
		
		board.currentSide().setKingSideCastling(canKingSideCastleBefore);
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
		
		if (movingPiece.getType() == Rook) {
			PGNMove pm = super.makePGNMove(board);
			return new PGNMove(Rook, movingPiece.getColor(), to,
					pm.getFromRank(), pm.getFromFile(),
					eaten != null, check, checkMate,
					board.getMoveNumber());
		}
		
		return new PGNMove(King, movingPiece.getColor(), to, null, null,
				eaten != null, check, checkMate,
				board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (canKingSideCastleBefore ? 1231 : 1237);
		result = prime * result + (canQueenSideCastleBefore ? 1231 : 1237);
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
		DisablingCastlingMove other = (DisablingCastlingMove) obj;
		if (canKingSideCastleBefore != other.canKingSideCastleBefore)
			return false;
		if (canQueenSideCastleBefore != other.canQueenSideCastleBefore)
			return false;
		if (eaten == null) {
			if (other.eaten != null)
				return false;
		} else if (!eaten.equals(other.eaten))
			return false;
		if (limit50movesBefore != other.limit50movesBefore)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DisablingCastlingMove [canKingSideCastleBefore="
				+ canKingSideCastleBefore + ", canQueenSideCastleBefore="
				+ canQueenSideCastleBefore + ", eaten=" + eaten
				+ ", limit50movesBefore=" + limit50movesBefore + ", from="
				+ from + ", to=" + to + ", movingPiece=" + movingPiece
				+ ", enPassantBefore=" + enPassantBefore + "]";
	}

	
	
	
}
