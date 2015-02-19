package move;

import pgn.PGNMove;
import board.Board;
import board.File;
import board.Square;
import board.Color;
import board.Type;
import static board.Type.Pawn;

public class SimpleCapture extends Move {
	
	Type eaten;

	public SimpleCapture(Square from, Square to, Color movingColor,
			Type movingType, Type eaten, boolean check, boolean checkMate) {
		super(from, to, movingColor, movingType, check, checkMate);
		
		this.eaten = eaten;
	}
	
	@Override
	protected void specificApply(Board board) {
		super.specificApply(board);
		
		board.opponentSide().remove(to);
		board.setLimit50moves(0);
	}

	@Override
	public PGNMove makePGNMove(Board board) {
		PGNMove pm = super.makePGNMove(board);
		File file = pm.getFromFile();
		if (movingType == Pawn)
			file = this.from.getFile();
		return new PGNMove(movingType, movingColor, to, pm.getFromRank(), file,
				true, check, checkMate,
				board.getMoveNumber());
	}
	
}
