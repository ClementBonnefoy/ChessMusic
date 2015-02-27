package move;

import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.Piece;

public class EnPassantCapture extends Move {
	
	private final Piece captured;
	private final ESquare captureSquare;
	private final int limit50movesBefore;
	
	public EnPassantCapture(ESquare from, ESquare to, Piece movingPiece,
			Piece captured,
			boolean check, boolean checkMate,
			int limit50movesBefore) {
		super(from, to, movingPiece, check, checkMate, to);
		this.captured = captured;
		captureSquare = to.nextSquare(movingPiece.getColor().backwards());
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		board.opponentSide().remove(board.get(captureSquare));
		board.putOnSquare(null, captureSquare);
		board.setLimit50moves(0);
	}

	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.opponentSide().add(board.get(captureSquare));
		board.putOnSquare(captured, captureSquare);
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(), to, null, from.getFile(),
				true, check, checkMate,
				board.getMoveNumber());
		
	}

}
