package board;

import java.util.EnumSet;
import java.util.Iterator;

public enum Rank implements Iterable<Square>{
	Rank1, Rank2, Rank3, Rank4, Rank5, Rank6, Rank7, Rank8;

	public int getNum() {
		return ordinal();
	}

	public static Rank getRank(int num) {
		return Rank.values()[num];
	}
	
	public static Rank getRank(char c) {
		return Rank.values()[c - '1'];
	}
	
	public Square getSquare(int num) {
		return Square.values()[(getNum() << 3) + num];
	}
	
	public Rank nextRank() {
		if (this == Rank8)
			return null;
		return Rank.values()[getNum()+1];
	}
	
	public Rank previousRank() {
		if (this == Rank1)
			return null;
		return Rank.values()[getNum()-1];
	}

	@Override
	public Iterator<Square> iterator() {
		return EnumSet.range(getSquare(0), getSquare(7)).iterator();
	}

	@Override
	public String toString() {
		return "" + super.toString().charAt(4);
	}

}
