package move;

import static board.Color.*;

import pgn.PGNMove;
import board.*;

public class Move {

	protected final Square from, to;
	protected final Color movingColor;
	protected final Type movingType;
	protected final boolean check, checkMate;
	protected final Square enPassantBefore;

	public Move(Square from, Square to, Color movingColor, Type movingType,
			boolean check, boolean checkMate, Square enPassantBefore) {
		super();
		this.from = from;
		this.to = to;
		this.movingColor = movingColor;
		this.movingType = movingType;
		this.check = check;
		this.checkMate = checkMate;
		this.enPassantBefore = enPassantBefore;
	}

	public Square getFrom() {
		return from;
	}

	public Square getTo() {
		return to;
	}

	public Color getMovingColor() {
		return movingColor;
	}

	public Type getMovingType() {
		return movingType;
	}

	public boolean isCheck() {
		return check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public final void applyTo (Board board) {
		checkApplyValidity(board);
		
		board.getProperty(from).onMoveTo(board, to);
		board.getProperty(to).onMoveFrom(board, from);
		
		specificApply(board);
		
		if (checkMate)
			return;
		
		board.setCurrentPlayer(movingColor.getOpponent());
	}

	protected void checkApplyValidity(Board board) {
		// TODO checkValidity
	}

	protected static void simpleMove(Board board, Square from, Square to) {
		board.currentSide().replace(from, to);

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
		
		board.setCurrentPlayer(movingColor);
		
		checkUndoValidity(board);
		
		board.getProperty(from).onUndoTo(board, to);
		board.getProperty(to).onUndoFrom(board, from);
		
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
		
		Piece piece = Piece.get(movingType, movingColor);
		Movement mvmt = Movement.get(movingType);
		File fromFile = from.getFile();
		Rank fromRank = from.getRank();
		boolean concurrency = false, sameFile = false, sameRank = false;
		
		for (Square sq : mvmt.basicDestinations(board, to))
			if (sq != from && board.getPiece(sq) == piece) {
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
		
		
		return new PGNMove(movingType, movingColor,
				to, pgnRank, pgnFile,
				false, check, checkMate,
				board.getMoveNumber());
	}
	
}
