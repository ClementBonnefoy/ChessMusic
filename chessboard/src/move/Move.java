package move;

import static board.Color.*;
import static board.Type.*;
import static board.File.*;

import pgn.PGNMove;
import board.Board;
import board.File;
import board.Piece;
import board.Rank;
import board.Square;
import board.Color;
import board.Type;

public class Move {

	protected final Square from, to;
	protected final Color movingColor;
	protected final Type movingType;
	protected final boolean check, checkMate;

	public Move(Square from, Square to, Color movingColor, Type movingType,
			boolean check, boolean checkMate) {
		super();
		this.from = from;
		this.to = to;
		this.movingColor = movingColor;
		this.movingType = movingType;
		this.check = check;
		this.checkMate = checkMate;
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

	public void applyTo (Board board) {
		checkValidity(board);
		
		specificApply(board);
		
		if (checkMate)
			return;
		
		board.setCurrentPlayer(movingColor.getOpponent());
	}

	protected void checkValidity(Board board) {

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

		if (movingType == King) {
			board.currentSide().setKingSideCastlingInvalid();
			board.currentSide().setQueenSideCastlingInvalid();
		}
		else if (movingType == Rook) {
			if (from.getFile() == FileH)
				board.currentSide().setKingSideCastlingInvalid();
			else if (from.getFile() == FileA)
				board.currentSide().setQueenSideCastlingInvalid();
		}

		if (board.getEnPassant() != null)
			board.setEnPassant(null);
		
	}
	
	// TODO Implementer undo et dans les sous-classes aussi

	
	public void undo(Board board) {
		
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
