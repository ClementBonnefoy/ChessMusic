package board;


public class Property {

	protected Piece piece;
	
	public final Piece getPiece() {
		return piece;
	}
	
	public final void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void clear() {
		piece = null;
	}
	
	public void onMoveFrom(Board board, Square from) {
		
	}
	
	public void onMoveTo(Board board, Square to) {
		
	}

	public void onUndoFrom(Board board, Square from) {
		
	}
	
	public void onUndoTo(Board board, Square to) {
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Property other = (Property) obj;
		if (piece != other.piece)
			return false;
		return true;
	}
	
	
	
}
