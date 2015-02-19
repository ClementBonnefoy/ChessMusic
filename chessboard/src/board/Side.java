package board;

import java.util.ArrayList;

public interface Side {

	public void add(Square c);
	
	public void remove(Square c);
	
	public void replace(Square oldSquare, Square newSquare);
	
	public void setKingSideCastling(boolean possible);

	public void setQueenSideCastling(boolean possible);

	public Square getKing();

	public void setKing(Square king);

	public ArrayList<Square> getArmy();

	public boolean canQueenSideCastle();

	public boolean canKingSideCastle();
	
	void initCastlings();
	
}
