package board;

import java.util.EnumMap;

import static board.Color.*;

public class InstanceBoard extends EnumMap<Square, AbstractProperty> implements Board {

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
	public void putOnSquare(Piece p, Square sq) {
		get(sq).setPiece(p);
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
		return get(c).getPiece();
	}

	@Override
	public void initializeProperty(Square sq) {
		if (get(sq) != null)
			get(sq).clear();
		put(sq,new BasicProperty());
	}
	
	@Override
	public AbstractProperty getProperty(Square sq) {
		return get(sq);
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((blackSide == null) ? 0 : blackSide.hashCode());
		result = prime * result
				+ ((currentPlayer == null) ? 0 : currentPlayer.hashCode());
		result = prime * result
				+ ((enPassant == null) ? 0 : enPassant.hashCode());
		result = prime * result + limit50moves;
		result = prime * result + moveNumber;
		result = prime * result
				+ ((whiteSide == null) ? 0 : whiteSide.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO remplacer par un test sur les FEN
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstanceBoard other = (InstanceBoard) obj;
		if (blackSide == null) {
			if (other.blackSide != null)
				return false;
		} else if (!blackSide.equals(other.blackSide))
			return false;
		if (currentPlayer != other.currentPlayer)
			return false;
		if (enPassant != other.enPassant)
			return false;
		if (limit50moves != other.limit50moves)
			return false;
		if (moveNumber != other.moveNumber)
			return false;
		if (whiteSide == null) {
			if (other.whiteSide != null)
				return false;
		} else if (!whiteSide.equals(other.whiteSide))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return BoardTools.toString(this);
	}

}
