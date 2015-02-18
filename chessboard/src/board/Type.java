package board;

public enum Type {
	King ("K"),
	Queen ("Q"),
	Rook ("R"),
	Knight ("N"),
	Bishop ("B"),
	Pawn ("");
	
	private String pgnString;
	
	private Type(String pgnString) {
		this.pgnString = pgnString;
	}

	@Override
	public String toString() {
		return pgnString;
		
	}
}