package board;

import move.Direction;

public enum Square {
	A1, B1, C1, D1, E1, F1, G1, H1, 
	A2, B2, C2, D2, E2, F2, G2, H2, 
	A3, B3, C3, D3, E3, F3, G3, H3, 
	A4, B4, C4, D4, E4, F4, G4, H4, 
	A5, B5, C5, D5, E5, F5, G5, H5, 
	A6, B6, C6, D6, E6, F6, G6, H6, 
	A7, B7, C7, D7, E7, F7, G7, H7, 
	A8, B8, C8, D8, E8, F8, G8, H8;

	Piece piece;

	Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public File getFile() {
		return File.values()[ordinal() & 7];
	}

	public Rank getRank() {
		return Rank.values()[ordinal() >> 3];
	}

	public static Square getSquare(int file, int rank) {
		return Rank.getRank(rank).getSquare(file);
	}

	public static Square getSquare(File file, Rank rank) {
		return Square.values()[(rank.ordinal() << 3) + file.ordinal()];
	}

	public static Square getSquare(String notation) {
		if (notation.length() != 2)
			throw new IllegalArgumentException("This notation don't fit with any square : "+notation);

		return Square.getSquare(File.getFile(notation.charAt(0)), Rank.getRank(notation.charAt(1)));
	}

	public boolean hasNextSquare(Direction dir) {
		return dir.hasNextSquare(this);
	}


	public Square nextSquare(Direction dir) {
		return dir.nextSquare(this);
	}



}

