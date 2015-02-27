package board;

import static board.Color.*;
import static board.Type.*;

public enum EPiece {

	WhiteKing (White, King, 'K'),
	WhiteQueen (White, Queen, 'Q'),
	WhiteRook (White, Rook, 'R'),
	WhiteKnight (White, Knight, 'N'),
	WhiteBishop (White, Bishop, 'B'),
	WhitePawn (White, Pawn, 'P'),
	BlackKing (Black, King, 'k'),
	BlackQueen (Black, Queen, 'q'),
	BlackRook (Black, Rook, 'r'),
	BlackKnight (Black, Knight, 'n'),
	BlackBishop (Black, Bishop, 'b'),
	BlackPawn (Black, Pawn, 'p');

	private final Color color;
	private final Type type;
	private final char symbol;

	private EPiece(Color color, Type type, char symbol) {
		this.color = color;
		this.type = type;
		this.symbol = symbol;
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}
	
	public static EPiece get(Type type, Color color) {
		EPiece piece;
		switch (type) {
		case King: piece = color == White ? WhiteKing : BlackKing; break;
		case Queen: piece = color == White ? WhiteQueen : BlackQueen; break;
		case Rook: piece = color == White ? WhiteRook : BlackRook; break;
		case Knight: piece = color == White ? WhiteKnight : BlackKnight; break;
		case Bishop: piece = color == White ? WhiteBishop : BlackBishop; break;
		case Pawn: piece = color == White ? WhitePawn : BlackPawn; break;
		default: throw new IllegalArgumentException("Type is null");
		}
		
		return piece;
	}
	
	public static EPiece getFromSymbol(char symbol) {
		for (EPiece p : values())
			if (p.symbol == symbol)
				return p;
		throw new IllegalArgumentException("No piece for the symbol "+symbol);
	}
	
	public String toString() {
		return "" + symbol;
	}	
}

