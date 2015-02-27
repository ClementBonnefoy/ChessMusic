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
	
	private final boolean canKingSideCastle, canQueenSideCastle;
	private final Piece eaten;
	private final int limit50movesBefore;

	public DisablingCastlingMove(ESquare from, ESquare to, Piece movingPiece,
			Piece eaten, boolean check, boolean checkMate,
			boolean canKingSideCastle, boolean canQueenSideCastle,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece, check, checkMate, enPassantBefore);
		this.canKingSideCastle = canKingSideCastle;
		this.canQueenSideCastle = canQueenSideCastle;
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
		if (canKingSideCastle &&
				(movingPiece.getType() == King || from.getFile() == FileH))
			board.currentSide().setKingSideCastling(false);
		if (canQueenSideCastle &&
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
		
		board.currentSide().setKingSideCastling(canKingSideCastle);
		board.currentSide().setQueenSideCastling(canQueenSideCastle);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
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

	
	
}
