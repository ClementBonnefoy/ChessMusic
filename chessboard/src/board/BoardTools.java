package board;

import static board.Square.*;
import static board.Color.*;
import static board.Piece.*;
import static board.Rank.*;
import static board.Type.*;

public class BoardTools {
	
	/**
	* Clear any information stored in the board
	* 
	* @param  board the board to be cleared
    *
    */
	
	public static void clear(Board board) {
		for (Square c : Square.values())
			board.putOnSquare(null, c);
		board.setEnPassant(null);
		board.setCurrentPlayer(null);
		
		BoardTools.clearSide(board.currentSide());
		BoardTools.clearSide(board.opponentSide());
	}

	/**
	 * Initialize the board with the standard starting position
	 * 
	 * @param board the board to be initialized
	 * 
	 */
	
	public static void initBoard (Board board) {
		

		BoardTools.clear(board);
		
		board.setCurrentPlayer(White);
		
		board.currentSide().setKing(E1);
		board.opponentSide().setKing(E8);

		board.setLimit50moves(0);
		board.setMoveNumber(1);

		board.putOnSquare(WhiteRook,A1);
		board.putOnSquare(WhiteKnight,B1);
		board.putOnSquare(WhiteBishop,C1);
		board.putOnSquare(WhiteQueen,D1);
		board.putOnSquare(WhiteKing,E1);
		board.putOnSquare(WhiteBishop,F1);
		board.putOnSquare(WhiteKnight,G1);
		board.putOnSquare(WhiteRook,H1);

		for (Square c : Rank2) {
			board.putOnSquare(WhitePawn,c);
			board.currentSide().add(c);
		}

		for (Square c : Rank1)
			if (c != E1)
				board.currentSide().add(c);

		board.putOnSquare(BlackRook,A8);
		board.putOnSquare(BlackKnight,B8);
		board.putOnSquare(BlackBishop,C8);
		board.putOnSquare(BlackQueen,D8);
		board.putOnSquare(BlackKing,E8);
		board.putOnSquare(BlackBishop,F8);
		board.putOnSquare(BlackKnight,G8);
		board.putOnSquare(BlackRook,H8);

		for (Square c : Rank7) {
			board.putOnSquare(BlackPawn,c);
			board.opponentSide().add(c);
		}

		for (Square c : Rank8)
			if (c != E8)
				board.opponentSide().add(c);

	}

	/**
	 * Initialize the board with a specific starting position
	 * 
	 * @param board the board to be initialized
	 * @param fen the starting position
	 * @throws InvalidFenException if the fen is invalid
	 */

	public static void initBoard(Board board, String fen) throws InvalidFenException {

		if (fen == null) {
			initBoard(board);
			return;
		}

		if (fen.equals(""))
			throw new InvalidFenException(fen,"FEN is empty");

		
		String[] elems = fen.split(" ");

		if (elems.length != 6)
			throw new InvalidFenException(fen,"There should be 6 fields, not "+elems.length);

		int filePos;
		Piece p;
		Square c;

		String[] ranks = elems[0].split("/");

		if (ranks.length != 8)
			throw new InvalidFenException(fen,"There should be 8 ranks, not "+ranks.length);

		String rankString;

		BoardTools.clear(board);

		for (int rankPos = 0; rankPos < 8; rankPos++) {
			filePos = 0;
			rankString = ranks[7-rankPos];
			for (int j = 0; j < rankString.length(); j++) {
				if (Character.isDigit(rankString.charAt(j))) {
					filePos += Character.getNumericValue(rankString.charAt(j));
				}
				else {
					c = Square.getSquare(filePos, rankPos);
					p = Piece.getFromSymbol(rankString.charAt(j));
					board.setCurrentPlayer(p.getColor());
					if (p.getType() == King) {
						if (board.currentSide().getKing() != null)
							throw new InvalidFenException(fen,"There are too many "+p.getColor()+" kings");
						board.currentSide().setKing(c);
					}
					else
						board.currentSide().add(c);
					board.putOnSquare(p, c);
					filePos++;
				}
				if (j == rankString.length() - 1 && filePos != 8)
					throw new InvalidFenException(fen,"There should be 8 squares in rank "+(7-rankPos)+", not "+filePos);

			}

		}

		board.setCurrentPlayer(White);

		if (board.currentSide().getKing() == null)
			throw new InvalidFenException(fen,"There is no white king");
		if (board.opponentSide().getKing() == null)
			throw new InvalidFenException(fen,"There is no black king");

		if (!elems[2].contains("K"))
			board.currentSide().setKingSideCastlingInvalid();
		if (!elems[2].contains("Q"))
			board.currentSide().setQueenSideCastlingInvalid();
		if (!elems[2].contains("k"))
			board.opponentSide().setKingSideCastlingInvalid();
		if (!elems[2].contains("q"))
			board.opponentSide().setQueenSideCastlingInvalid();

		if (elems[1].charAt(0) == 'w')
			board.setCurrentPlayer(White);
		else
			board.setCurrentPlayer(Black);

		if (!elems[3].equals("-")) {
			if (Square.getCSquare(elems[3]).getRank() != Rank3 && Square.getCSquare(elems[3]).getRank() != Rank6)
				throw new InvalidFenException(fen,"enPassant can't be on Rank "+Square.getCSquare(elems[3]).getRank());
			board.setEnPassant(Square.getCSquare(elems[3]));
		}

		board.setLimit50moves(Integer.valueOf(elems[4]));

		board.setMoveNumber(Integer.valueOf(elems[5]));
	}
	
	/**
	 * Clear the side
	 * 
	 * @param side the side to be cleared
	 */

	public static void clearSide(Side side) {
		side.getArmy().clear();
		side.setKing(null);
		side.initCastlings();
	}
	
	public static Piece[] getPieces (Board board, File f) {
		Piece[] filePieces = new Piece[8];

		int i = 0;
		for (Square c : f)
			filePieces[i++] = board.getPiece(c);

		return filePieces;
	}

	public static Piece[] getPieces (Board board, Rank r) {
		Piece[] rankPieces = new Piece[8];

		int i = 0;
		for (Square c : r)
			rankPieces[i++] = board.getPiece(c);

		return rankPieces;

	}

	public static Piece[][] getAllPieces(Board board) {
		int i = 0, j;

		Piece[][] pieces = new Piece[8][8];

		for (Rank rank : Rank.values()) {
			j = 0;
			for (File file : File.values()) {
				pieces[i][j++] = board.getPiece(Square.getSquare(file, rank));
			}

		}

		return pieces;
	}
	
	/**
	 * Convert a board into a string through the Forsyth-Edwards notation
	 * 
	 * @param board the board to be converted
	 * @return the fen representation of the board
	 */

	public static String toFEN(Board board) {
		StringBuilder sb = new StringBuilder();

		int emptySquares;
		Rank rank;

		for (int i = 7; i >= 0; i--) {
			rank = Rank.getRank(i);
			emptySquares = 0;
			for (int j = 0; j < 8; j++) {
				if (board.getPiece(rank.getSquare(j)) == null) {
					emptySquares++;
					if (j == 7)
						sb.append(emptySquares);
				}
				else {
					if (emptySquares != 0)
						sb.append(emptySquares);
					sb.append(board.getPiece(rank.getSquare(j)).toString());
					emptySquares = 0;
				}
			}
			sb.append('/');
		}
		sb.deleteCharAt(sb.length()-1);

		sb.append(' ');

		if (board.getCurrentPlayer() == White)
			sb.append('w');
		else
			sb.append('b');

		sb.append(' ');

		if (board.getSide(White).canKingSideCastle())
			sb.append('K');
		if (board.getSide(White).canQueenSideCastle())
			sb.append('Q');
		if (board.getSide(Black).canKingSideCastle())
			sb.append('k');
		if (board.getSide(Black).canQueenSideCastle())
			sb.append('q');
		if (sb.charAt(sb.length()-1) == ' ')
			sb.append('-');

		sb.append(' ');

		if (board.getEnPassant() == null)
			sb.append('-');
		else
			sb.append(board.getEnPassant().toString().toLowerCase());

		sb.append(' ');

		sb.append(board.getLimit50moves());

		sb.append(' ');

		sb.append(board.getMoveNumber());

		return sb.toString();
	}

	static String toString(Board board) {
		StringBuilder sb = new StringBuilder();

		String[] fen = BoardTools.toFEN(board).split(" ");


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
			for (Square c : rank)
				if (board.getPiece(c) == null)
					sb.append('.');
				else
					sb.append(board.getPiece(c));
			sb.append('\n');
		}
		return sb.toString();
	}

}
