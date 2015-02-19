package move;

import pgn.PGNMove;
import board.Board;
import board.Color;
import board.Piece;
import board.Square;
import board.Type;
import static board.Type.*;
import static board.File.*;

/**
 * This move is useful for storing the previous castling state,
 * in order to be able to undo it
 *
 */

public class DisablingCastlingMove extends Move {
	
	private final boolean canKingSideCastle, canQueenSideCastle;
	private final Type eaten;
	private final int limit50movesBefore;

	public DisablingCastlingMove(Square from, Square to, Color movingColor,
			Type movingType,
			Type eaten, boolean check, boolean checkMate,
			boolean canKingSideCastle, boolean canQueenSideCastle,
			int limit50movesBefore, Square enPassantBefore) {
		super(from, to, movingColor, movingType, check, checkMate, enPassantBefore);
		this.canKingSideCastle = canKingSideCastle;
		this.canQueenSideCastle = canQueenSideCastle;
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);

		if (eaten != null) {
			board.opponentSide().remove(to);
			board.setLimit50moves(0);
		}
		if (canKingSideCastle &&
				(movingType == King || from.getFile() == FileH))
			board.currentSide().setKingSideCastling(false);
		if (canQueenSideCastle &&
				(movingType == King || from.getFile() == FileA))
			board.currentSide().setQueenSideCastling(false);
	}

	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		if (eaten != null) {
			board.opponentSide().add(to);
			board.putOnSquare(Piece.get(eaten, movingColor.getOpponent()), to);
			board.setLimit50moves(limit50movesBefore);
		}
		
		board.currentSide().setKingSideCastling(canKingSideCastle);
		board.currentSide().setQueenSideCastling(canQueenSideCastle);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		if (movingType == Rook) {
			PGNMove pm = super.makePGNMove(board);
			return new PGNMove(Rook, movingColor, to,
					pm.getFromRank(), pm.getFromFile(),
					eaten != null, check, checkMate,
					board.getMoveNumber());
		}
		
		
		return new PGNMove(King, movingColor, to, null, null,
				eaten != null, check, checkMate,
				board.getMoveNumber());
	}

	
	
}
