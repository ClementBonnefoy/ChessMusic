package board;

import java.util.ArrayList;

public class Side extends ArrayList <Square>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Square king;
	
	private boolean canQueenSideCastle, canKingSideCastle;
	
	private Color color;
	
	public Side(Color color) {
		this.color = color;
	}
	
//	public Color getColor(){
//		return color;
//	}
	
	public boolean contains(Square square) {
		if (super.contains(square))
			return true;
		
		for (Square sq : this)
			if (sq.getESquare() == square.getESquare())
				return true;
		return false;
	}
	
	public boolean add(Square c) {
		if (contains(c))
			throw new IllegalArgumentException("The "+color+" side already contains "+c);
		return super.add(c);
	}
	
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(Square c) {
		if (!contains(c))
			throw new IllegalArgumentException("The "+color+" side doesn't contain "+c);
		super.remove(c);
	}
	
	public void replace(Square oldSquare, Square newSquare) {
		if (king == oldSquare)
			king = newSquare;
		else
			set(indexOf(oldSquare), newSquare);
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

	public boolean canQueenSideCastle() {
		return canQueenSideCastle;
	}

	public boolean canKingSideCastle() {
		return canKingSideCastle;
	}

	public void init() {
		clear();
		setKing(null);
		canQueenSideCastle = true;
		canKingSideCastle = true;
	}
	
	public Side simpleCopy() {
		Side newSide = new Side(color);
		
		newSide.canKingSideCastle = canKingSideCastle;
		newSide.canQueenSideCastle = canQueenSideCastle;
		newSide.king = king;
		newSide.color = color;
		
		return newSide;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Side other = (Side) obj;
		if (!containsAll(other) || !other.containsAll(this))
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

		for (Square c : this)
			sb.append(c+" ");
		
		return sb.toString();
		
	}

	
	
}
