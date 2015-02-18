package move;

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

}
