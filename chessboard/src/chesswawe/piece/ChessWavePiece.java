package chesswawe.piece;

import music.Scale;
import board.Color;
import board.Piece;
import board.Rank;
import board.Type;

public abstract class ChessWavePiece{

	protected Piece piece;

	public ChessWavePiece (Piece piece){
		super();
		this.piece=piece;
	}

	public Piece getPiece(){
		return piece;
	}

	public Type getType(){
		return piece.getType();
	}

	/**
	 * retourne la note associe au numero de rangee donne en parametre
	 * @param rank : la rangee
	 * @return
	 */
	public int NoteOfRank(Rank rank){
		if(piece.getColor()==Color.Black){
			return keyOfRankForWhite(Rank.values()[7-rank.getNum()]);
		}
		return keyOfRankForWhite(rank);
	}

	protected abstract int keyOfRankForWhite(Rank rank);

	public abstract int getChannel();

	public static ChessWavePiece fromPiece(Piece piece,Scale scale) {
		switch(piece.getType()){
		case Pawn:
			return new Pawn(piece);
		case Bishop:
			return new Bishop(piece,scale);
		case King:
			return new King(piece,scale);
		case Knight:
			return new Knight(piece,scale);
		case Queen:
			return new Queen(piece,scale);
		case Rook:
			return new Rook(piece,scale);
		}
		return null;
	}







}
