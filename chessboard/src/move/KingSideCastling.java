package move;

import board.Board;
import static board.Square.*;
import board.Color;
import static board.Color.*;
import static board.Type.*;

public class KingSideCastling extends Move {
	
	public KingSideCastling(Color movingColor, boolean check, boolean checkMate) {
		
		super(movingColor == White ? E1 : E8, movingColor == White ? G1 : G8,
				movingColor, King, check, checkMate);
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		if (movingColor == White)
			simpleMove(board, H1, F1);
		else
			simpleMove(board, H8, F8);
		
	}

	
	
}
