package board;

import move.Move;
import move.MoveAction;

public class Square implements MoveAction {

	protected final ESquare eSquare;
	
	protected Piece piece;
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Square(ESquare eSquare) {
		super();
		this.eSquare = eSquare;
	}

	public ESquare getESquare() {
		return eSquare;
	}

	@Override
	public void onMove(Board board, Move move) {
		
	}

	@Override
	public void onUndoMove(Board board, Move move) {
		
	}

	
	public String toString() {
		return eSquare.toString();
	}
	
}
