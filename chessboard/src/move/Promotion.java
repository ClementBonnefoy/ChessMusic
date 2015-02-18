package move;

import board.Board;
import board.Square;
import board.Color;
import board.Piece;
import board.Type;
import static board.Type.*;

public class Promotion extends Move {

	private final Type type;
	private final Type eaten;
	
	public Promotion(Type type, Square from, Square to, Color movingColor, 
			Type eaten, boolean check, boolean checkMate) {
		super(from, to, movingColor, Pawn, check, checkMate);
		
		this.type = type;
		this.eaten = eaten;
	}

	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.putOnSquare(Piece.get(type, movingColor), to);
		if (eaten != null)
			board.opponentSide().remove(to);
		board.setLimit50moves(0);
		
	}

	
	

	
	
	
}
