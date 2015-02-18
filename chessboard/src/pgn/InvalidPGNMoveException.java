package pgn;

public class InvalidPGNMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7183685649656549751L;

	public InvalidPGNMoveException() {
		super();
	}

	public InvalidPGNMoveException(String message) {
		super("This pgn move is not valid : "+message);
	}

	
	
}
