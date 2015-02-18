package board;

import static board.Color.*;
import static board.EnumSide.*;
import board.Square;
import board.Color;
import board.InvalidFenException;
import board.Piece;

public enum EnumBoard implements Board {

	TheBoard;

	private Color currentPlayer;

	private Square enPassant;

	private int limit50moves;

	private int moveNumber;
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Square getEnPassant() {
		return enPassant;
	}

	public void setEnPassant(Square enPassant) {
		this.enPassant = enPassant;
	}

	public int getLimit50moves() {
		return limit50moves;
	}

	public void setLimit50moves(int limit50moves) {
		this.limit50moves = limit50moves;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}


	public Piece getPiece(Square c) {
		return c.getPiece();
	}

	@Override
	public boolean isEmpty(Square c) {
		return getPiece(c) == null;
	}
	
	@Override
	public boolean isEnemy(Square c) {
		return !isEmpty(c) && currentPlayer != getPiece(c).getColor();
	}

	@Override
	public void putOnSquare(Piece p, Square c) {
		c.setPiece(p);
		
	}

	@Override
	public Side currentSide() {
		return currentPlayer == White ? Whites : Blacks;
	}
	
	@Override
	public Side opponentSide() {
		return currentPlayer == White ? Blacks : Whites;
	}
	
	@Override
	public Side getSide(Color color) {
		return color == White ? Whites : Blacks;
	}

	public InstanceBoard store() {
		InstanceBoard board = new InstanceBoard();
		try {
			BoardTools.initBoard(board, BoardTools.toFEN(this));
		} catch (InvalidFenException e) {
			e.printStackTrace();
		}
		return board;
	}
	
	@Override
	public String toString() {
		return BoardTools.toString(this);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EnumBoard b = TheBoard;
		try {
			BoardTools.initBoard(b,"8/6KP/5kP1/8/3b4/3B4/8/8 b - e6 0 1");
		} catch (InvalidFenException e) {
			e.printStackTrace();
		}
		System.out.println(BoardTools.toFEN(b));
		
	}

	

}
