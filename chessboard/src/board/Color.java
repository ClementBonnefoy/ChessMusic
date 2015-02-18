package board;

import static board.Rank.*;
import move.Direction;
import static move.Direction.*;

public enum Color {
	Black, White;
	
	public Color getOpponent() {
		return this == White ? Black : White;
	}
	
	public Rank promotionRank() {
		return this == White ? Rank8 : Rank1;
	}
	
	public Rank pawnStartingRank() {
		return this == White ? Rank2 : Rank7;
	}
	
	public Direction forwards() {
		return this == White ? North : South;
	}
	
	public Direction backwards() {
		return this == White ? South : North;
	}
	
	public Direction eastPawnThreat() {
		return this == White ? NorthEast : SouthEast;
	}
	
	public Direction westPawnThreat() {
		return this == White ? NorthWest : SouthWest;
	}
	
}
