package board;

import static board.Rank.*;
import move.Direction;
import static move.Direction.*;
import static board.ESquare.G1;
import static board.ESquare.G8;
import static board.ESquare.C1;
import static board.ESquare.C8;

public enum Color {
	Black, White;
	
	public Color opponent() {
		return this == White ? Black : White;
	}
	
	public Rank promotionRank() {
		return this == White ? Rank8 : Rank1;
	}
	
	public Rank pawnStartingRank() {
		return this == White ? Rank2 : Rank7;
	}
	
	public ESquare kingSideSquare() {
		return this == White ? G1 : G8;
	}
	
	public ESquare queenSideSquare() {
		return this == White ? C1 : C8;
	}
	
	public Rank firstRank() {
		return this == White ? Rank1 : Rank8;
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
