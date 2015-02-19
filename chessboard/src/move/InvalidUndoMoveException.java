package move;

public class InvalidUndoMoveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375494838084065796L;

	public InvalidUndoMoveException() {
		super();
	}

	public InvalidUndoMoveException(String message) {
		super("This undomove is not valid on the board : "+message);
	}

	
	
}