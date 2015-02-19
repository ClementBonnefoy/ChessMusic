package pgn;

import move.Move;
import move.Promotion;
import board.Board;
import board.Color;
import board.File;
import board.Piece;
import board.Square;
import board.Type;
import static board.Type.Pawn;

public class PGNPromotion extends PGNMove {
	
	private Type promotionType;
	
	public PGNPromotion(Type promotionType, Color color, Square to, File fromFile,
			boolean capture, boolean check, boolean checkmate,
			int moveNumber) {
		super(Pawn, color, to, null, fromFile, capture, check, checkmate,
				moveNumber);
		this.promotionType = promotionType;
	}

	
	@Override
	public Move makeMove(Board board) {
		Square from = to.nextSquare(color.backwards());;
		Piece p = board.getPiece(to);
		Type eaten = p == null ? null : p.getType();

		if (eaten != null)
			from = Square.getSquare(fromFile, from.getRank());
		return new Promotion(promotionType, from, to, color, eaten, check, checkMate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (fromFile != null)
			sb.append(fromFile.toString().toLowerCase()+'x');
		sb.append(to.toString().toLowerCase());

		sb.append("="+promotionType.toString());
		
		if (check)
			sb.append('+');
		else if (checkMate)
			sb.append('#');
		return sb.toString();
	}
	
}
