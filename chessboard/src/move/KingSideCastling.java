package move;

import pgn.PGNKingSideCastling;
import pgn.PGNMove;
import board.Board;
import board.Square;
import static board.Square.*;
import board.Color;
import static board.Color.*;
import static board.Type.*;

public class KingSideCastling extends Move {
	
	public KingSideCastling(Color movingColor,
			boolean check, boolean checkMate,
			Square enPassantBefore) {
		
		super(movingColor == White ? E1 : E8, movingColor.kingSideSquare(),
				movingColor, King, check, checkMate,
				enPassantBefore);
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		if (movingColor == White)
			simpleMove(board, H1, F1);
		else
			simpleMove(board, H8, F8);
		
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		if (movingColor == White)
			simpleMove(board, F1, H1);
		else
			simpleMove(board, F8, H8);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNKingSideCastling(movingColor, check, checkMate, board.getMoveNumber());
	}
	
}
