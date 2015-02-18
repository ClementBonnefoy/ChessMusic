package move;

import board.Board;
import static board.Square.*;
import board.Color;
import static board.Color.*;
import static board.Type.*;

public class QueenSideCastling extends Move {
	
	public QueenSideCastling(Color movingColor, boolean check, boolean checkMate) {
		super(movingColor == White ? E1 : E8, movingColor == White ? C1 : C8,
				movingColor, King, check, checkMate);
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		if (movingColor == White)
			simpleMove(board, A1, D1);
		else
			simpleMove(board, A8, D8);
	}
	
	
	
}

