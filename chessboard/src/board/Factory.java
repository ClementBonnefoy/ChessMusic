package board;

public class Factory {

	public Square makeSquare(ESquare sq) {
		return new Square(sq);
	}
	
	public Piece makePiece (EPiece piece) {
		return new Piece (piece);
	}
	
}
