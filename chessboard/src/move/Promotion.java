package move;

import pgn.PGNMove;
import pgn.PGNPromotion;
import board.Board;
import board.Square;
import board.Color;
import board.Piece;
import board.Type;
import static board.Type.*;

public class Promotion extends Move {

	private final Type promotionType;
	private final Type eaten;
	private final int limit50movesBefore;

	
	public Promotion(Type type, Square from, Square to, Color movingColor, 
			Type eaten, boolean check, boolean checkMate,
			int limit50movesBefore, Square enPassantBefore) {
		super(from, to, movingColor, Pawn, check, checkMate,
				enPassantBefore);
		
		this.promotionType = type;
		this.eaten = eaten;
		this.limit50movesBefore = limit50movesBefore;
	}

	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.putOnSquare(Piece.get(promotionType, movingColor), to);
		if (eaten != null)
			board.opponentSide().remove(to);
		board.setLimit50moves(0);
		
	}
	
	@Override
	public void specificUndo(Board board) {
		super.specificUndo(board);
		board.putOnSquare(Piece.get(Pawn, movingColor), from);
		
		if (eaten != null) {
			board.opponentSide().add(to);
			board.putOnSquare(Piece.get(promotionType, movingColor.getOpponent()), to);
		}
		board.setLimit50moves(limit50movesBefore);
	}
	
	@Override
	public PGNMove makePGNMove(Board board) {
		if (eaten != null)
			return new PGNPromotion(promotionType, movingColor, to, from.getFile(), true,
					check, checkMate,
					board.getMoveNumber());
		return new PGNPromotion(promotionType, movingColor, to, null, false,
				check, checkMate,
				board.getMoveNumber());
	}	
	
	
}
