package board;

public class Factory {
	
	protected Board board;
	
	void setBoard(Board board) {
		this.board = board;
	}

	public Square makeSquare(ESquare eSquare) {
		return new Square(eSquare);
	}
	
	public Piece makePiece (EPiece ePiece) {
		return new Piece (ePiece);
	}
	
}
