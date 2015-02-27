package move;

import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

public class SimplePawnMove extends Move {
	private final int limit50movesBefore;


	public SimplePawnMove(ESquare from, ESquare to, Piece movingPiece,
			boolean check, boolean checkMate,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to,
				movingPiece,
				check, checkMate,
				enPassantBefore);
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		board.setLimit50moves(0);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, null, null,
				false, check, checkMate,
				board.getMoveNumber());
	}

}
