package pgn;

import move.Move;
import move.Promotion;
import board.Board;
import board.Color;
import board.File;
import board.ESquare;
import board.Piece;
import board.Type;
import static board.Type.Pawn;

public class PGNPromotion extends PGNMove {
	
	private Type promotionType;
	
	public PGNPromotion(Type promotionType, Color color, ESquare to, File fromFile,
			boolean capture, boolean check, boolean checkmate,
			int moveNumber) {
		super(Pawn, color, to, null, fromFile, capture, check, checkmate,
				moveNumber);
		this.promotionType = promotionType;
	}

	
	@Override
	public Move makeMove(Board board) {
		ESquare from = to.nextSquare(color.backwards());;
		Piece eaten = board.getPiece(to);

		if (eaten != null)
			from = ESquare.getSquare(fromFile, from.getRank());
		Piece pawn = board.getPiece(from);
		return new Promotion(promotionType, from, to, pawn, eaten,
				check, checkMate,
				board.getLimit50moves(), board.getEnPassant());
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
