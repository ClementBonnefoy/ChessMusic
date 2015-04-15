package board;

import static board.Color.Black;
import static board.Color.White;
import static board.EPiece.BlackBishop;
import static board.EPiece.BlackKing;
import static board.EPiece.BlackKnight;
import static board.EPiece.BlackPawn;
import static board.EPiece.BlackQueen;
import static board.EPiece.BlackRook;
import static board.EPiece.WhiteBishop;
import static board.EPiece.WhiteKing;
import static board.EPiece.WhiteKnight;
import static board.EPiece.WhitePawn;
import static board.EPiece.WhiteQueen;
import static board.EPiece.WhiteRook;
import static board.ESquare.A1;
import static board.ESquare.A8;
import static board.ESquare.B1;
import static board.ESquare.B8;
import static board.ESquare.C1;
import static board.ESquare.C8;
import static board.ESquare.D1;
import static board.ESquare.D8;
import static board.ESquare.E1;
import static board.ESquare.E8;
import static board.ESquare.F1;
import static board.ESquare.F8;
import static board.ESquare.G1;
import static board.ESquare.G8;
import static board.ESquare.H1;
import static board.ESquare.H8;
import static board.Rank.Rank1;
import static board.Rank.Rank2;
import static board.Rank.Rank3;
import static board.Rank.Rank6;
import static board.Rank.Rank7;
import static board.Rank.Rank8;
import static board.Type.King;
import static board.Type.Pawn;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import move.Direction;
import move.Move;
import move.Movement;

public class Board extends EnumMap<ESquare, Square> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4765222647439466591L;

	private Side whiteSide, blackSide;

	private Color currentPlayer;

	private ESquare enPassant;

	private int limit50moves;

	private int moveNumber;

	private Map<String,Integer> previousPositions;

	public void putOnSquare(Piece p, ESquare sq) {
		get(sq).setPiece(p);
	}

	public Side currentSide() {
		return currentPlayer == White ? whiteSide : blackSide;
	}

	public Side opponentSide() {
		return currentPlayer == White ? blackSide : whiteSide;
	}

	public Side getSide(Color color) {
		return color == White ? whiteSide : blackSide;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void invertPlayer() {
		currentPlayer = currentPlayer.opponent();
	}

	public ESquare getEnPassant() {
		return enPassant;
	}

	public void setEnPassant(ESquare enPassant) {
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

	public Board() { 
		super(ESquare.class);
		whiteSide = new Side(White);
		blackSide = new Side(Black);
		previousPositions = new HashMap<String, Integer>();
	}

	public Piece getPiece(ESquare c) {
		return get(c).getPiece();
	}

	public boolean isEmpty(ESquare c) {
		return getPiece(c) == null;
	}

	public boolean isEnemy(ESquare c) {
		return !isEmpty(c) && currentPlayer != getPiece(c).getColor();
	}

	public void storeCurrentPosition() {
		String positionFen = toFEN(true);
		if (previousPositions.containsKey(positionFen))
			previousPositions.put(positionFen, previousPositions.get(positionFen) + 1);
		else
			previousPositions.put(positionFen,1);
	}

	public void removeCurrentPosition() {
		String positionFen = toFEN(true);
		if (previousPositions.get(positionFen) > 1)
			previousPositions.put(positionFen, previousPositions.get(positionFen) - 1);
		else
			previousPositions.remove(positionFen);
	}
	
	public void reset() {
		for (ESquare sq : ESquare.values())
			put(sq,makeSquare(sq));

		setEnPassant(null);
		setCurrentPlayer(null);

		currentSide().reset();
		opponentSide().reset();
		
		previousPositions.clear();
	}

	public void init () {

		reset();

		setCurrentPlayer(White);

		currentSide().setKing(get(E1));
		opponentSide().setKing(get(E8));

		setLimit50moves(0);
		setMoveNumber(1);

		putOnSquare(makePiece(WhiteRook),A1);
		putOnSquare(makePiece(WhiteKnight),B1);
		putOnSquare(makePiece(WhiteBishop),C1);
		putOnSquare(makePiece(WhiteQueen),D1);
		putOnSquare(makePiece(WhiteKing),E1);
		putOnSquare(makePiece(WhiteBishop),F1);
		putOnSquare(makePiece(WhiteKnight),G1);
		putOnSquare(makePiece(WhiteRook),H1);

		for (ESquare c : Rank2) {
			putOnSquare(makePiece(WhitePawn),c);
			currentSide().add(get(c));
		}

		for (ESquare c : Rank1)
			if (c != E1)
				currentSide().add(get(c));

		putOnSquare(makePiece(BlackRook),A8);
		putOnSquare(makePiece(BlackKnight),B8);
		putOnSquare(makePiece(BlackBishop),C8);
		putOnSquare(makePiece(BlackQueen),D8);
		putOnSquare(makePiece(BlackKing),E8);
		putOnSquare(makePiece(BlackBishop),F8);
		putOnSquare(makePiece(BlackKnight),G8);
		putOnSquare(makePiece(BlackRook),H8);

		for (ESquare c : Rank7) {
			putOnSquare(makePiece(BlackPawn),c);
			opponentSide().add(get(c));
		}

		for (ESquare c : Rank8)
			if (c != E8)
				opponentSide().add(get(c));
		
		storeCurrentPosition();


	}

	public  void init(String fen) throws InvalidFenException {

		if (fen == null) {
			init();
			return;
		}

		if (fen.equals(""))
			throw new InvalidFenException(fen,"FEN is empty");


		String[] elems = fen.split(" ");

		if (elems.length != 6)
			throw new InvalidFenException(fen,"There should be 6 fields, not "+elems.length);

		int filePos;
		EPiece p;
		ESquare c;

		String[] ranks = elems[0].split("/");

		if (ranks.length != 8)
			throw new InvalidFenException(fen,"There should be 8 ranks, not "+ranks.length);

		String rankString;

		reset();

		for (int rankPos = 0; rankPos < 8; rankPos++) {
			filePos = 0;
			rankString = ranks[7-rankPos];
			for (int j = 0; j < rankString.length(); j++) {
				if (Character.isDigit(rankString.charAt(j))) {
					filePos += Character.getNumericValue(rankString.charAt(j));
				}
				else {
					c = ESquare.getSquare(filePos, rankPos);
					p = EPiece.getFromSymbol(rankString.charAt(j));
					setCurrentPlayer(p.getColor());
					if (p.getType() == King) {
						if (currentSide().getKing() != null)
							throw new InvalidFenException(fen,"There are too many "+p.getColor()+" kings");
						currentSide().setKing(get(c));
					}
					else
						currentSide().add(get(c));
					putOnSquare(makePiece(p), c);
					filePos++;
				}
				if (j == rankString.length() - 1 && filePos != 8)
					throw new InvalidFenException(fen,"There should be 8 squares in rank "+(7-rankPos)+", not "+filePos);

			}

		}

		setCurrentPlayer(White);

		if (currentSide().getKing() == null)
			throw new InvalidFenException(fen,"There is no white king");
		if (opponentSide().getKing() == null)
			throw new InvalidFenException(fen,"There is no black king");

		if (!elems[2].contains("K"))
			currentSide().setKingSideCastling(false);
		if (!elems[2].contains("Q"))
			currentSide().setQueenSideCastling(false);
		if (!elems[2].contains("k"))
			opponentSide().setKingSideCastling(false);
		if (!elems[2].contains("q"))
			opponentSide().setQueenSideCastling(false);

		if (elems[1].charAt(0) == 'w')
			setCurrentPlayer(White);
		else
			setCurrentPlayer(Black);

		if (!elems[3].equals("-")) {
			if (ESquare.getSquare(elems[3]).getRank() != Rank3 && ESquare.getSquare(elems[3]).getRank() != Rank6)
				throw new InvalidFenException(fen,"enPassant can't be on Rank "+ESquare.getSquare(elems[3]).getRank());
			setEnPassant(ESquare.getSquare(elems[3]));
		}

		setLimit50moves(Integer.valueOf(elems[4]));

		setMoveNumber(Integer.valueOf(elems[5]));
		
		storeCurrentPosition();

	}

	public Piece[] getPieces (File f) {
		Piece[] filePieces = new Piece[8];

		int i = 0;
		for (ESquare c : f)
			filePieces[i++] = getPiece(c);

		return filePieces;
	}

	public Piece[] getPieces (Rank r) {
		Piece[] rankPieces = new Piece[8];

		int i = 0;
		for (ESquare c : r)
			rankPieces[i++] = getPiece(c);

		return rankPieces;
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
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
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

	public Board clone() {
		Board newBoard = new Board();

		for (ESquare eSquare : keySet())
			newBoard.put(eSquare, get(eSquare).clone());

		newBoard.whiteSide = whiteSide.simpleCopy();
		newBoard.blackSide = blackSide.simpleCopy();

		for (Square square : whiteSide)
			newBoard.whiteSide.add(newBoard.get(square.getESquare()));
		for (Square square : blackSide)
			newBoard.blackSide.add(newBoard.get(square.getESquare()));

		newBoard.currentPlayer = currentPlayer;
		newBoard.enPassant = enPassant;
		newBoard.limit50moves = limit50moves;
		newBoard.moveNumber = moveNumber;

		return newBoard;
	}

	public String toFEN() {
		return toFEN(false);
	}

	private String toFEN(boolean fenToStore) {
		StringBuilder sb = new StringBuilder();

		int emptySquares;
		Rank rank;

		for (int i = 7; i >= 0; i--) {
			rank = Rank.getRank(i);
			emptySquares = 0;
			for (int j = 0; j < 8; j++) {
				if (getPiece(rank.getSquare(j)) == null) {
					emptySquares++;
					if (j == 7)
						sb.append(emptySquares);
				}
				else {
					if (emptySquares != 0)
						sb.append(emptySquares);
					sb.append(getPiece(rank.getSquare(j)).toString());
					emptySquares = 0;
				}
			}
			sb.append('/');
		}
		sb.deleteCharAt(sb.length()-1);

		sb.append(' ');

		if (getCurrentPlayer() == White)
			sb.append('w');
		else
			sb.append('b');

		sb.append(' ');

		if (getSide(White).canKingSideCastle())
			sb.append('K');
		if (getSide(White).canQueenSideCastle())
			sb.append('Q');
		if (getSide(Black).canKingSideCastle())
			sb.append('k');
		if (getSide(Black).canQueenSideCastle())
			sb.append('q');
		if (sb.charAt(sb.length()-1) == ' ')
			sb.append('-');

		sb.append(' ');

		if (getEnPassant() == null)
			sb.append('-');
		else
			sb.append(getEnPassant().toString().toLowerCase());

		if (fenToStore)
			return sb.toString();

		sb.append(' ');

		sb.append(getLimit50moves());

		sb.append(' ');

		sb.append(getMoveNumber());

		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String[] fen = toFEN().split(" ");


		for (int i = 1; i < fen.length; i++) {
			sb.append(fen[i]);
			if (i < fen.length -1)
				sb.append(' ');
			else
				sb.append('\n');
		}

		Rank rank;

		for(int j = 7; j >= 0; j--) {
			rank = Rank.values()[j];
			for (ESquare c : rank)
				if (isEmpty(c))
					sb.append('.');
				else
					sb.append(getPiece(c));
			sb.append('\n');
		}
		return sb.toString();
	}

	public Square makeSquare(ESquare eSquare) {
		return new Square(eSquare);
	}

	public Piece makePiece (EPiece ePiece) {
		return new Piece (ePiece);
	}

	public void onMove(Move move) {

	}

	public void onUndoMove(Move move) {

	}

	public boolean isEnPrise(ESquare eSquare) {
		Piece piece;
		Movement mvmt;
		for (Square square : opponentSide()) {
			piece = square.getPiece();
			mvmt = Movement.get(piece.getType());

			if (piece.getType() != Pawn)	{
				if (mvmt.basicDestinations(this, square.getESquare()).
						contains(eSquare))
					return true;
			}
			else {
				Direction dir = currentPlayer.eastPawnThreat();
				if (eSquare.hasNextSquare(dir))
					if (square.getESquare() == eSquare.nextSquare(dir))
						return true;
				dir = currentPlayer.westPawnThreat();
				if (eSquare.hasNextSquare(dir))
					if (square.getESquare() == eSquare.nextSquare(dir))
						return true;
			}
		}
		mvmt = Movement.get(King);
		if (mvmt.basicDestinations(this, opponentSide().getKing().getESquare()).
				contains(eSquare))
			return true;


		return false;
	}

	public boolean isInCheck() {

		return isEnPrise(currentSide().getKing().getESquare());
	}

	public boolean isMate() {
		if (!isInCheck())
			return false;

		Piece piece;
		Movement mvmt;

		for (Square square : currentSide()) {
			piece = square.getPiece();
			mvmt = Movement.get(piece.getType());	
			if (!mvmt.realMoves(this, square.getESquare()).isEmpty())
				return false;
		}

		mvmt = Movement.get(King);
		if (!mvmt.realMoves(this, currentSide().getKing().getESquare()).isEmpty())
			return false;
		return true;

	}

	public boolean isStaleMate() {
		if (isInCheck())
			return false;

		Piece piece;
		Movement mvmt;

		for (Square square : currentSide()) {
			piece = square.getPiece();
			mvmt = Movement.get(piece.getType());	
			if (!mvmt.realMoves(this, square.getESquare()).isEmpty())
				return false;
		}

		mvmt = Movement.get(King);
		if (!mvmt.realMoves(this, currentSide().getKing().getESquare()).isEmpty())
			return false;

		return true;

	}
	
	public boolean isDrawByRepeating() {
		Integer timesRepeated = previousPositions.get(toFEN(true));
		if (timesRepeated != null && timesRepeated >= 3)
			return true;
		return false;
	}
	
	public boolean isDrawBy50MovesLimit() {
		return limit50moves == 50;
	}

}
