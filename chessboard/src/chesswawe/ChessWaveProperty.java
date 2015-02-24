package chesswawe;

import board.AbstractProperty;
import board.Board;
import board.Square;

public class ChessWaveProperty extends AbstractProperty {
	
	private boolean hasAlreadyMoved = false;

	@Override
	public void onMoveFrom(Board board, Square from) {
		hasMoved();

	}

	@Override
	public void onMoveTo(Board board, Square to) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndoFrom(Board board, Square from) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndoTo(Board board, Square to) {
		// TODO Auto-generated method stub

	}

	public boolean hasAlreadyMoved() {
		return hasAlreadyMoved;
	}

	private void hasMoved() {
		this.hasAlreadyMoved = true;
	}

}
