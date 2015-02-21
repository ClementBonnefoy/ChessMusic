package chesswave.piece;

import board.Square;
import board.Color;
import board.Piece;
import board.Rank;
import board.Type;

public abstract class AbstractChessWavePiece {
	
	protected Piece piece;
	private boolean hasAlreadyMoved = true;
	
	public AbstractChessWavePiece (Piece piece){
		super();
		this.piece=piece;
	}
	
//	public Piece getPiece(){
//		return piece;
//	}
	
	public Type getType(){
		return piece.getType();
	}
	
	
	public boolean hasMoved() {
		return hasAlreadyMoved;
	}
	
	public int keyOfRank(Rank rank){
		if(piece.getColor()==Color.Black){
			return keyOfRankForWhite(Rank.values()[7-rank.getNum()]);
		}
		return keyOfRankForWhite(rank);
	}

	protected abstract int keyOfRankForWhite(Rank rank);
	
	public static AbstractChessWavePiece fromPiece(Piece piece) {
		if (piece.getType() == board.Type.Pawn)
			return new DrumMusicalPiece(piece);
		return new BasicChessWavePiece(piece);
	}
	
	
	
	

}
