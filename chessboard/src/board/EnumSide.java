package board;

import java.util.ArrayList;

import static board.Color.*;

public enum EnumSide implements Side {
	
	Whites (White), Blacks (Black);
	
	private final Color color;
	
	private EnumSide(Color color) {
		this.color = color;
	}
	
	private final ArrayList<Square> army = new ArrayList<>();
	
	private Square king;
	
	private boolean canQueenSideCastle = true, canKingSideCastle = true;
	
	public Color getColor(){
		return color;
	}
	
	public void add(Square c) {
		army.add(c);
	}
	
	public void remove(Square c) {
		if (!army.contains(c))
			throw new IllegalArgumentException("The "+color+" side doesn't contain "+c);
		army.remove(c);
	}
	
	public void replace(Square oldSquare, Square newSquare) {
		if (king == oldSquare)
			king = newSquare;
		else
			army.set(army.indexOf(oldSquare), newSquare);
	}
	
	public void setKingSideCastling(boolean possible) {
		canKingSideCastle = possible;
	}

	public void setQueenSideCastling(boolean possible) {
		canQueenSideCastle = possible;
	}

	public Square getKing() {
		return king;
	}

	public void setKing(Square king) {
		this.king = king;
	}

	public ArrayList<Square> getArmy() {
		return army;
	}

	public boolean canQueenSideCastle() {
		return canQueenSideCastle;
	}

	public boolean canKingSideCastle() {
		return canKingSideCastle;
	}
	
	@Override
	public void initCastlings() {
		canQueenSideCastle = true;
		canKingSideCastle = true;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Square c : army)
			sb.append(c+" ");
		
		return sb.toString();
		
	}
	
}
