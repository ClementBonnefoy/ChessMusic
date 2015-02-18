package board;

public class InvalidFenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -517103001690959124L;

	public InvalidFenException() {
		super();
	}

	public InvalidFenException(String fen, String message) {
		super(fen+" is not valid : "+message);
	}
	
}
