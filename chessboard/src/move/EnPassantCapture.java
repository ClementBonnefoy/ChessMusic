package move;

import pgn.PGNMove;
import board.Board;
import board.Square;
import board.Color;
import static board.Type.*;

public class EnPassantCapture extends Move {
	
	private final Square captured;
	
	public EnPassantCapture(Square from, Square to, Color movingColor, boolean check, boolean checkMate) {
		super(from, to, movingColor, Pawn, check, checkMate);
		captured = to.nextSquare(movingColor.backwards());
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		board.opponentSide().remove(captured);
		board.putOnSquare(null, captured);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingType, movingColor, to, null, from.getFile(),
				true, check, checkMate,
				board.getMoveNumber());
		
	}

}
