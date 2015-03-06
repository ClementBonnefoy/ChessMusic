package move;

import pgn.PGNMove;
import pgn.PGNPromotion;
import board.Board;
import board.EPiece;
import board.ESquare;
import board.Piece;
import board.Type;

public class Promotion extends Move {

	private final Type promotionType;
	private final Piece eaten;
	private final int limit50movesBefore;

	
	public Promotion(Type type, ESquare from, ESquare to,
			Piece movingPiece, Piece eaten,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece, enPassantBefore);
		
		this.promotionType = type;
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.putOnSquare(movingPiece, to);
		movingPiece.setPromotion(
					EPiece.get(promotionType, movingPiece.getColor()));
		if (eaten != null)
			board.opponentSide().remove(board.get(to));
		board.setLimit50moves(0);
		
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		
		if (eaten != null) {
			board.opponentSide().add(board.get(to));
			board.putOnSquare(eaten, to);
		}
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		boolean check, checkMate;
		
		applyTo(board);
		
		check = board.isInCheck();
		
		if (check)
			checkMate = board.isMate();
		else
			checkMate = false;
		
		undo(board);
		
		if (eaten != null)
			return new PGNPromotion(promotionType, movingPiece.getColor(),
					to, from.getFile(), true,
					check, checkMate,
					board.getMoveNumber());
		return new PGNPromotion(promotionType, movingPiece.getColor(), to, null, false,
				check, checkMate,
				board.getMoveNumber());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eaten == null) ? 0 : eaten.hashCode());
		result = prime * result + limit50movesBefore;
		result = prime * result
				+ ((promotionType == null) ? 0 : promotionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		if (eaten == null) {
			if (other.eaten != null)
				return false;
		} else if (!eaten.equals(other.eaten))
			return false;
		if (limit50movesBefore != other.limit50movesBefore)
			return false;
		if (promotionType != other.promotionType)
			return false;
		return true;
	}	
	
	
	
}
