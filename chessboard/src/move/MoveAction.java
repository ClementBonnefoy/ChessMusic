package move;

import board.Board;

public interface MoveAction {

	public void onMove(Board board, Move move);

	public void onUndoMove(Board board, Move move);

	
}
