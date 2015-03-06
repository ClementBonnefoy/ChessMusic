package move;

import static board.Color.*;

import pgn.PGNMove;
import board.*;

public class Move {

	protected final ESquare from, to;
	protected final Piece movingPiece;
	protected final ESquare enPassantBefore;

	public Move(ESquare from, ESquare to, Piece movingPiece,
			ESquare enPassantBefore) {
		super();
		this.from = from;
		this.to = to;
		this.movingPiece = movingPiece;
		this.enPassantBefore = enPassantBefore;
	}
	
	public ESquare getFrom() {
		return from;
	}

	public ESquare getTo() {
		return to;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public final void applyTo (Board board) {
		board.onMove(this);
		
		movingPiece.onMove(board, this);

		board.get(from).onMove(board, this);
		board.get(to).onMove(board, this);
		
		specificApply(board);
		
		board.invertPlayer();
	}

	protected static void simpleMove(Board board, ESquare from, ESquare to) {
		board.currentSide().replace(board.get(from), board.get(to));

		board.putOnSquare(board.getPiece(from), to);
		board.putOnSquare(null, from);

	}

	protected void specificApply(Board board) {

		simpleMove(board,from,to);

		board.setLimit50moves(board.getLimit50moves()+1);

		if (board.getCurrentPlayer() == Black)
			board.setMoveNumber(board.getMoveNumber()+1);

		if (board.getEnPassant() != null)
			board.setEnPassant(null);
		
	}
	
	public final void undo(Board board) {
		
		board.invertPlayer();
		
		board.onUndoMove(this);
		
		movingPiece.onUndoMove(board, this);

		board.get(from).onUndoMove(board, this);
		board.get(to).onUndoMove(board, this);
		
		specificUndo(board);
		
	}
	
	public void specificUndo(Board board) {
		
		simpleMove(board,to,from);
		
		board.setLimit50moves(board.getLimit50moves()-1);

		if (board.getCurrentPlayer() == Black)
			board.setMoveNumber(board.getMoveNumber()-1);
		
		board.setEnPassant(enPassantBefore);
	}

	public PGNMove makePGNMove(Board board) {
		
		EPiece ePiece = movingPiece.getEPiece();
		Movement mvmt = Movement.get(ePiece.getType());
		File fromFile = from.getFile();
		Rank fromRank = from.getRank();
		boolean concurrency = false, sameFile = false, sameRank = false;
		
		for (ESquare sq : mvmt.basicDestinations(board, to))
			if (sq != from && board.getPiece(sq) != null
				&& board.getPiece(sq).getEPiece() == ePiece) {
				concurrency = true;
				if (fromFile == sq.getFile())
					sameFile = true;
				if (fromRank == sq.getRank())
					sameRank = true;
			}
		
		File pgnFile = null;
		Rank pgnRank = null;
		
		if (concurrency) {
			if (sameFile && sameRank) {
				pgnFile = from.getFile();
				pgnRank = from.getRank();
			}
			else if (sameFile) {
				pgnRank = from.getRank();
			}
			else
				pgnFile = from.getFile();
			
		}
		
		boolean check, checkMate;
		
		applyTo(board);
		
		check = board.isInCheck();

		if (check)
			checkMate = board.isMate();
		else
			checkMate = false;
		
		undo(board);
		
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, pgnRank, pgnFile,
				false, check, checkMate,
				board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((enPassantBefore == null) ? 0 : enPassantBefore.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result
				+ ((movingPiece == null) ? 0 : movingPiece.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (enPassantBefore != other.enPassantBefore)
			return false;
		if (from != other.from)
			return false;
		if (movingPiece == null) {
			if (other.movingPiece != null)
				return false;
		} else if (!movingPiece.equals(other.movingPiece))
			return false;
		if (to != other.to)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Move [from=" + from + ", to=" + to + ", movingPiece="
				+ movingPiece + ", enPassantBefore=" + enPassantBefore + "]";
	}
	
	
	
	
	
}
