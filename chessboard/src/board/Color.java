package board;

import static board.Rank.*;
import move.Direction;
import static move.Direction.*;
import static board.Square.G1;
import static board.Square.G8;
import static board.Square.C1;
import static board.Square.C8;

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
	
	public Square kingSideSquare() {
		return this == White ? G1 : G8;
	}
	
	public Square queenSideSquare() {
		return this == White ? C1 : C8;
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
