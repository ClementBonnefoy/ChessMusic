package move;

import pgn.PGNMove;
import board.Board;
import board.Piece;
import board.Square;
import board.Color;
import static board.Type.*;

public class EnPassantCapture extends Move {
	
	private final Square captured;
	private final int limit50movesBefore;
	
	public EnPassantCapture(Square from, Square to, Color movingColor,
			boolean check, boolean checkMate,
			int limit50movesBefore) {
		super(from, to, movingColor, Pawn, check, checkMate, to);
		captured = to.nextSquare(movingColor.backwards());
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		board.opponentSide().remove(captured);
		board.putOnSquare(null, captured);
		board.setLimit50moves(0);
	}

	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.opponentSide().add(captured);
		board.putOnSquare(Piece.get(Pawn, movingColor.getOpponent()), captured);
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingType, movingColor, to, null, from.getFile(),
				true, check, checkMate,
				board.getMoveNumber());
		
	}

}
