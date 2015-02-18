package board;

import java.util.ArrayList;

public class InstanceSide implements Side {
	
	private final ArrayList<Square> army = new ArrayList<>();
	
	private Square king;
	
	private boolean canQueenSideCastle, canKingSideCastle;
	
	private final Color color;
	
	public InstanceSide(Color color) {
		this.color = color;
	}
	
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
	
	public void setKingSideCastlingInvalid() {
		canKingSideCastle = false;
	}

	public void setQueenSideCastlingInvalid() {
		canQueenSideCastle = false;
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
