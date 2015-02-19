package move;

import pgn.PGNMove;
import board.Board;
import board.Square;
import board.Color;
import static board.Type.*;

public class JumpPawnMove extends Move {
	private final int limit50movesBefore;


	public JumpPawnMove(Square from, Color movingColor,
			boolean check, boolean checkMate,
			int limit50movesBefore, Square enPassantBefore) {
		super(from, from.nextSquare(movingColor.forwards()).nextSquare(movingColor.forwards()),
				movingColor, Pawn, check, checkMate,
				enPassantBefore);
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		board.setLimit50moves(0);
		board.setEnPassant(from.nextSquare(movingColor.forwards()));
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingType, movingColor, to, null, null,
				false, check, checkMate,
				board.getMoveNumber());
	}
	
}
