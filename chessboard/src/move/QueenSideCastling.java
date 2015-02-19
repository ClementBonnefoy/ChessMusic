package move;

import pgn.PGNMove;
import pgn.PGNQueenSideCastling;
import board.Board;
import board.Square;
import static board.Square.*;
import board.Color;
import static board.Color.*;
import static board.Type.*;

public class QueenSideCastling extends Move {
	
	public QueenSideCastling(Color movingColor,
			boolean check, boolean checkMate,
			Square enPassantBefore) {
		super(movingColor == White ? E1 : E8, movingColor.queenSideSquare(),
				movingColor, King, check, checkMate,
				enPassantBefore);
	}

	@Override
	public void specificApply(Board board) {
		super.specificApply(board);
		
		if (movingColor == White)
			simpleMove(board, A1, D1);
		else
			simpleMove(board, A8, D8);
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		if (movingColor == White)
			simpleMove(board, D1, A1);
		else
			simpleMove(board, D8, A8);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		return new PGNQueenSideCastling(movingColor, check, checkMate,
				board.getMoveNumber());

	}
	
}

