package move;

import board.Board;
import board.Square;
import board.Color;
import board.Type;

public class SimpleCapture extends Move {
	
	Type eaten;

	public SimpleCapture(Square from, Square to, Color movingColor,
			Type movingType, Type eaten, boolean check, boolean checkMate) {
		super(from, to, movingColor, movingType, check, checkMate);
		
		this.eaten = eaten;
	}
	
	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.opponentSide().remove(to);
		board.setLimit50moves(0);
	}

	
	
}
