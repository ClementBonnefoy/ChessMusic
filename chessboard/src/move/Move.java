package move;

import static board.Color.*;

import pgn.PGNMove;
import board.*;

public class Move {

	protected final ESquare from, to;
	protected final Piece movingPiece;
	protected final boolean check, checkMate;
	protected final ESquare enPassantBefore;

	public Move(ESquare from, ESquare to, Piece movingPiece,
			boolean check, boolean checkMate, ESquare enPassantBefore) {
		super();
		this.from = from;
		this.to = to;
		this.movingPiece = movingPiece;
		this.check = check;
		this.checkMate = checkMate;
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

	public boolean isCheck() {
		return check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public final void applyTo (Board board) {
		checkApplyValidity(board);
		
		movingPiece.onMove(board, this);

		board.get(from).onMove(board, this);
		board.get(to).onMove(board, this);
		
		specificApply(board);
		
		if (checkMate)
			return;
		
		board.setCurrentPlayer(movingPiece.getColor().getOpponent());
	}

	protected void checkApplyValidity(Board board) {
		// TODO checkValidity
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
	
	public void checkUndoValidity(Board board) {
		//TODO checkUndoValidity
		
		if (board.getMoveNumber() == 0)
			throw new InvalidUndoMoveException("This is the starting position : "+board);
	}
	
	public final void undo(Board board) {
		
		board.setCurrentPlayer(movingPiece.getColor());
		
		checkUndoValidity(board);
		
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
		
		
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, pgnRank, pgnFile,
				false, check, checkMate,
				board.getMoveNumber());
	}
	
}
