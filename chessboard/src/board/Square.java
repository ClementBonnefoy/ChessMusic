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

	public Rank getRank() {
		return eSquare.getRank();
	}

	public File getFile() {
		return eSquare.getFile();
	}

	@Override
	public void onMove(Board board, Move move) {

	}

	@Override
	public void onUndoMove(Board board, Move move) {

	}

	public Square clone() {
		Square newSquare = new Square(eSquare);
		if (piece != null)
			newSquare.setPiece(piece.clone());
		return newSquare;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eSquare == null) ? 0 : eSquare.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (eSquare != other.eSquare)
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		return true;
	}

	public String toString() {
		return eSquare.toString();
	}

}
