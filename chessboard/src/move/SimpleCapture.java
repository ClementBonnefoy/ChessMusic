package move;

import pgn.PGNMove;
import board.Board;
import board.File;
import board.Piece;
import board.Square;
import board.Color;
import board.Type;
import static board.Type.Pawn;

public class SimpleCapture extends Move {
	
	private Type eaten;
	private final int limit50movesBefore;


	public SimpleCapture(Square from, Square to, Color movingColor,
			Type movingType, Type eaten, boolean check, boolean checkMate,
			int limit50movesBefore, Square enPassantBefore) {
		super(from, to, movingColor, movingType,
				check, checkMate,
				enPassantBefore);
		
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}
	
	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.opponentSide().remove(to);
		board.setLimit50moves(0);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.putOnSquare(Piece.get(eaten, movingColor.getOpponent()), to);
		board.opponentSide().add(to);
		board.setLimit50moves(limit50movesBefore);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		PGNMove pm = super.makePGNMove(board);
		File file = pm.getFromFile();
		if (movingType == Pawn)
			file = from.getFile();
		return new PGNMove(movingType, movingColor, to, pm.getFromRank(), file,
				true, check, checkMate,
				board.getMoveNumber());
	}
	
}
