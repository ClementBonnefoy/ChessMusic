package move;

public class InvalidMoveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375494838084065796L;

	public InvalidMoveException() {
		super();
	}

	public InvalidMoveException(String message) {
		super("This move is not valid on the board : "+message);
	}

	
	
}
