package board;

public interface Board {
	
	public void initializeProperty(Square sq);

	public void putOnSquare(Piece p, Square sq);
	
	public Piece getPiece(Square sq);
	
	public Property getProperty(Square sq);
	
	public boolean isEmpty(Square sq);
	
	public boolean isEnemy(Square sq);
	
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
