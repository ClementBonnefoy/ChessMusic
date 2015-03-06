package board;

import move.Move;
import move.MoveAction;
import move.Promotion;
import static board.Type.Pawn;

public class Piece implements MoveAction {

	protected EPiece ePiece;
	
	public Piece(EPiece ePiece) {
		super();
		this.ePiece = ePiece;
	}

	public Color getColor() {
		return ePiece.getColor();
	}
	
	public Type getType() {
		return ePiece.getType();
	}

	@Override
	public void onMove(Board board, Move move) {
		
	}

	@Override
	public void onUndoMove(Board board, Move move) {
		if (move instanceof Promotion)
			ePiece = EPiece.get(Pawn, ePiece.getColor());
	}
	
	public void setPromotion(EPiece promotionPiece) {
		if (ePiece.getType() != Pawn)
			throw new InvalidPromotionException();
		this.ePiece = promotionPiece;
	}
	
	public EPiece getEPiece() {
		return ePiece;
	}
	
	@Override
	public String toString() {
		return getEPiece().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ePiece == null) ? 0 : ePiece.hashCode());
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
		Piece other = (Piece) obj;
		if (ePiece != other.ePiece)
			return false;
		return true;
	}
	
	public Piece clone() {
		return new Piece(ePiece);
	}
	
}
