package board;


public abstract class AbstractProperty {

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
	
	public abstract void onMoveFrom(Board board, Square from) ;
	
	public abstract void onMoveTo(Board board, Square to) ;

	public abstract void onUndoFrom(Board board, Square from) ;
	
	public abstract void onUndoTo(Board board, Square to) ;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractProperty other = (AbstractProperty) obj;
		if (piece != other.piece)
			return false;
		return true;
	}
	
	
	
}
