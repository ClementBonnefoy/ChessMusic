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
	
}
