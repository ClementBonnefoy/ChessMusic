package pgn;

import move.*;
import board.*;
import static board.Type.*;


public class PGNMove {

	private final Type type;
	private final Color color;
	private final Square to;
	private final Rank fromRank;
	private final File fromFile;
	private final Type promotion;
	private final boolean capture;
	private final boolean checkMate;
	private final boolean check;
	private final boolean queenSideCastling;
	private final boolean kingSideCastling;
	private final int moveNumber;
	
	public PGNMove(Type type, Color color, Square to, Rank fromRank, File fromFile,
			Type promotion,
			boolean capture, boolean checkmate, boolean check,
			boolean queenSideCastling, boolean kingSideCastling,
			int moveNumber) {
		super();
		this.type = type;
		this.color = color;
		this.to = to;
		this.fromRank = fromRank;
		this.fromFile = fromFile;
		this.promotion = promotion;
		this.capture = capture;
		this.checkMate = checkmate;
		this.check = check;
		this.queenSideCastling = queenSideCastling;
		this.kingSideCastling = kingSideCastling;
		this.moveNumber = moveNumber;
	}

	public Type getType() {
		return type;
	}
	public Color getColor() {
		return color;
	}
	public Square getTo() {
		return to;
	}
	public Rank getFromRank() {
		return fromRank;
	}
	public File getFromFile() {
		return fromFile;
	}
	public Type getPromotion() {
		return promotion;
	}

	public boolean isCapture() {
		return capture;
	}
	public boolean isCheckmate() {
		return checkMate;
	}
	public boolean isCheck() {
		return check;
	}
	public boolean isQueenSideCastling() {
		return queenSideCastling;
	}
	public boolean isKingSideCastling() {
		return kingSideCastling;
	}	
	public int getMoveNumber() {
		return moveNumber;
	}
	
	public Move makeMove(Board board) {
		Square from;
		Piece p = board.getPiece(to);
		Type eaten = p == null ? null : p.getType();


		if (type == Pawn) {
			
			Square beforeTo = to.nextSquare(color.backwards());

			if (board.getEnPassant() == to) {
				from = Square.getSquare(fromFile, beforeTo.getRank());
				return new EnPassantCapture(from, to, color, check, checkMate);
			}
			
			if (capture) {
				from = Square.getSquare(fromFile, beforeTo.getRank());
				if (promotion != null)
					return new Promotion(promotion,from, to, color, eaten,
						check, checkMate);
				else
					return new SimpleCapture(from, to, color, Pawn, 
							eaten, check, checkMate);
			}
			
			if (promotion != null)
				return new Promotion(promotion,beforeTo, to, color, eaten,
					check, checkMate);
			
			if (board.isEmpty(beforeTo))
				return new JumpPawnMove(beforeTo.nextSquare(color.backwards()), color, check, checkMate);
			
			return new SimplePawnMove(beforeTo, color, check, checkMate);
		}

		if (kingSideCastling)
			return new KingSideCastling(color, check, checkMate);
		if (queenSideCastling)
			return new QueenSideCastling(color, check, checkMate);
		
		if (fromFile != null && fromRank != null)
			if (capture)
				return new SimpleCapture(Square.getSquare(fromFile, fromRank), to,
						color, type, eaten, check, checkMate);
			else
				return new Move(Square.getSquare(fromFile, fromRank), to, color, type,
					check, checkMate);
		
		Movement mvmt = Movement.get(type);
		Piece piece = Piece.get(type, color);
		for (Square c : mvmt.basicDestinations(board, to)) {
			if (board.getPiece(c) == piece) {
				if (fromFile != null && c.getFile() != fromFile)
					continue;
				if (fromRank != null && c.getRank() != fromRank)
					continue;
				if (capture)
					return new SimpleCapture(c, to, color, type, eaten, check, checkMate);
				return new Move(c, to, color, type, check, checkMate);
			}
		}

		throw new InvalidMoveException("Starting square wasn't found : to = " + to);

	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (kingSideCastling)
			sb.append("O-O");
		else if (queenSideCastling)
			sb.append("O-O-O");
		else if (type == Pawn) {
			if (fromFile != null)
				sb.append(fromFile.toString().toLowerCase()+'x');
			sb.append(to.toString().toLowerCase());
			if (promotion != null) {
				sb.append('=');
				switch (promotion) {
				case Queen: sb.append('Q'); break;
				case Rook: sb.append('R'); break;
				case Knight: sb.append('N'); break;
				case Bishop: sb.append('B'); break;
				case Pawn:
				case King:
					break;
				}
			}

		}
		else {
			sb.append(type.toString().toUpperCase());
			if (fromFile != null)
				sb.append(fromFile.toString().toLowerCase());
			if (fromRank != null)
				sb.append(fromRank.toString());
			if (capture)
				sb.append('x');
			sb.append(to.toString().toLowerCase());
		}


		if (check)
			sb.append('+');
		else if (checkMate)
			sb.append('#');

		return sb.toString();
	}

}
