package board;

import java.util.EnumMap;

import static board.Color.*;

public class InstanceBoard extends EnumMap<Square, Piece> implements Board {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4765222647439466591L;
	
	private final InstanceSide whiteSide, blackSide;
	
	private Color currentPlayer;
	
	private Square enPassant;

	private int limit50moves;
	
	private int moveNumber;
	
	@Override
	public void putOnSquare(Piece p, Square c) {
		put(c,p);
	}

	
	@Override
	public Side currentSide() {
		return currentPlayer == White ? whiteSide : blackSide;
	}
	
	@Override
	public Side opponentSide() {
		return currentPlayer == White ? blackSide : whiteSide;
	}
	
	@Override
	public Side getSide(Color color) {
		return color == White ? whiteSide : blackSide;
	}
	
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

	public InstanceBoard (){
		super(Square.class);
		
		whiteSide = new InstanceSide(White);
		blackSide = new InstanceSide(Black);
	}
	
	public Piece getPiece(Square c) {
		return get(c);
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
	public String toString() {
		return BoardTools.toString(this);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InstanceBoard b = new InstanceBoard();
		try {
			BoardTools.initBoard(b,"8/6KP/5kP1/8/3b4/3B4/8/8 b - e6 0 1");
			
		} catch (InvalidFenException e) {
			e.printStackTrace();
		}
		System.out.println(BoardTools.toFEN(b));
		
	}

}
