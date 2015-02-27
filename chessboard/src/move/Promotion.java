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

	
	public Promotion(Type type, ESquare from, ESquare to, Piece movingPiece,
			Piece eaten, boolean check, boolean checkMate,
			int limit50movesBefore, ESquare enPassantBefore) {
		super(from, to, movingPiece, check, checkMate,
				enPassantBefore);
		
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
		if (eaten != null)
			return new PGNPromotion(promotionType, movingPiece.getColor(),
					to, from.getFile(), true,
					check, checkMate,
					board.getMoveNumber());
		return new PGNPromotion(promotionType, movingPiece.getColor(), to, null, false,
				check, checkMate,
				board.getMoveNumber());
	}	
	
	
}
