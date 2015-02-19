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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((army == null) ? 0 : army.hashCode());
		result = prime * result + (canKingSideCastle ? 1231 : 1237);
		result = prime * result + (canQueenSideCastle ? 1231 : 1237);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((king == null) ? 0 : king.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstanceSide other = (InstanceSide) obj;
		if (army == null) {
			if (other.army != null)
				return false;
		} else if (!army.containsAll(other.army) || !other.army.containsAll(army))
			return false;
		if (canKingSideCastle != other.canKingSideCastle)
			return false;
		if (canQueenSideCastle != other.canQueenSideCastle)
			return false;
		if (color != other.color)
			return false;
		if (king != other.king)
			return false;
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Square c : army)
			sb.append(c+" ");
		
		return sb.toString();
		
	}

	
	
}
