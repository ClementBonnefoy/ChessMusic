package move;

import pgn.PGNMove;
import board.Board;
import board.Square;
import board.Color;
import static board.Type.*;

public class SimplePawnMove extends Move {

	public SimplePawnMove(Square from, Color movingColor, boolean check, boolean checkMate) {
		super(from, from.nextSquare(movingColor.forwards()), movingColor, Pawn,
				check, checkMate);
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		board.setLimit50moves(0);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNMove(movingType, movingColor, to, null, null,
				false, check, checkMate,
				board.getMoveNumber());
	}

}
