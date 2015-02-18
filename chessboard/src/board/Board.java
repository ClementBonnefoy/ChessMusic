package board;

public interface Board {

	public void putOnSquare(Piece p, Square c);
	
	public Piece getPiece(Square c);
	
	public boolean isEmpty(Square c);
	
	public boolean isEnemy(Square c);
	
	public Side currentSide();
	
	public Side opponentSide();
	
	public Side getSide(Color color);
	
	public Color getCurrentPlayer() ;

	public void setCurrentPlayer(Color currentPlayer);

	public Square getEnPassant();

	public void setEnPassant(Square enPassant);

	public int getLimit50moves();
	
	public void setLimit50moves(int limit50moves);

	public int getMoveNumber();

	public void setMoveNumber(int moveNumber);
	
}
