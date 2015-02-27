package move;

import static board.Type.Pawn;
import pgn.PGNMove;
import board.Board;
import board.ESquare;
import board.File;
import board.Piece;

public class SimpleCapture extends Move {
	
	private Piece eaten;
	private final int limit50movesBefore;


	public SimpleCapture(ESquare from, ESquare to, Piece movingPiece,
			Piece eaten, boolean check, boolean checkMate,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece,
				check, checkMate,
				enPassantBefore);
		
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}
	
	public Piece getEaten() {
		return eaten;
	}
	
	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.opponentSide().remove(board.get(to));
		board.setLimit50moves(0);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.putOnSquare(eaten, to);
		board.opponentSide().add(board.get(to));
		board.setLimit50moves(limit50movesBefore);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		PGNMove pm = super.makePGNMove(board);
		File file = pm.getFromFile();
		if (movingPiece.getType() == Pawn)
			file = from.getFile();
		return new PGNMove(movingPiece.getType(), movingPiece.getColor(),
				to, pm.getFromRank(), file,
				true, check, checkMate,
				board.getMoveNumber());
	}
	
}
