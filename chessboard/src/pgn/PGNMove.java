package pgn;

import move.*;
import board.*;
import static board.Type.*;
import static board.File.FileA;
import static board.File.FileH;


public class PGNMove {

	protected final Type type;
	protected final Color color;
	protected final Square to;
	protected final Rank fromRank;
	protected final File fromFile;
	protected final boolean capture;
	protected final boolean check, checkMate;
	protected final int moveNumber;

	public PGNMove(Type type, Color color, Square to, Rank fromRank, File fromFile,
			boolean capture, boolean check, boolean checkmate,
			int moveNumber) {
		super();
		this.type = type;
		this.color = color;
		this.to = to;
		this.fromRank = fromRank;
		this.fromFile = fromFile;
		this.capture = capture;
		this.checkMate = checkmate;
		this.check = check;
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
	public boolean isCapture() {
		return capture;
	}
	public boolean isCheckmate() {
		return checkMate;
	}
	public boolean isCheck() {
		return check;
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
				return new EnPassantCapture(from, to, color, check, checkMate,
						board.getLimit50moves());
			}

			if (capture) {
				from = Square.getSquare(fromFile, beforeTo.getRank());
				return new SimpleCapture(from, to, color, Pawn, 
						eaten, check, checkMate,
						board.getLimit50moves(), board.getEnPassant());
			}


			if (board.isEmpty(beforeTo))
				return new JumpPawnMove(beforeTo.nextSquare(color.backwards()), color,
						check, checkMate,
						board.getLimit50moves(), board.getEnPassant());

			return new SimplePawnMove(beforeTo, color, check, checkMate,
					board.getLimit50moves(), board.getEnPassant());
		}

		if (fromFile != null && fromRank != null) {
			Square fromSquare = Square.getSquare(fromFile, fromRank);
			if (type == Rook) {
				if (board.currentSide().canKingSideCastle()
					&& fromFile == FileH)
					return new DisablingCastlingMove(fromSquare, to,
							color, Rook, eaten, check, checkMate,
							true, board.currentSide().canQueenSideCastle(),
							board.getLimit50moves(), board.getEnPassant());
				else if (board.currentSide().canQueenSideCastle()
						&& fromFile == FileA)
					return new DisablingCastlingMove(fromSquare, to,
							color, Rook, eaten, check, checkMate,
							board.currentSide().canKingSideCastle(), true,
							board.getLimit50moves(), board.getEnPassant());
			}
			if (capture)
				return new SimpleCapture(fromSquare, to,
						color, type, eaten, check, checkMate,
						board.getLimit50moves(), board.getEnPassant());
			else
				return new Move(fromSquare, to, color, type,
						check, checkMate, board.getEnPassant());
		}

		Movement mvmt = Movement.get(type);
		Piece piece = Piece.get(type, color);
		for (Square c : mvmt.basicDestinations(board, to)) {
			if (board.getPiece(c) == piece) {
				if (fromFile != null && c.getFile() != fromFile)
					continue;
				if (fromRank != null && c.getRank() != fromRank)
					continue;
				if (type == Rook) {
					if (board.currentSide().canKingSideCastle()
						&& fromFile == FileH)
						return new DisablingCastlingMove(c, to,
								color, Rook, eaten, check, checkMate,
								true, board.currentSide().canQueenSideCastle(),
								board.getLimit50moves(), board.getEnPassant());
					else if (board.currentSide().canQueenSideCastle()
							&& fromFile == FileA)
						return new DisablingCastlingMove(c, to,
								color, Rook, eaten, check, checkMate,
								board.currentSide().canKingSideCastle(), true,
								board.getLimit50moves(), board.getEnPassant());
				}
				if (type == King) {
					if (board.currentSide().canKingSideCastle())
							return new DisablingCastlingMove(c, to,
									color, King, eaten, check, checkMate,
									true, board.currentSide().canQueenSideCastle(),
									board.getLimit50moves(), board.getEnPassant());
						else if (board.currentSide().canQueenSideCastle())
							return new DisablingCastlingMove(c, to,
									color, King, eaten, check, checkMate,
									board.currentSide().canKingSideCastle(), true,
									board.getLimit50moves(), board.getEnPassant());
				}
					
				if (capture)
					return new SimpleCapture(c, to, color, type, eaten,
							check, checkMate,
							board.getLimit50moves(), board.getEnPassant());
				return new Move(c, to, color, type, check, checkMate,
						board.getEnPassant());
			}
		}

		throw new InvalidMoveException("Starting square wasn't found : to = " + to);

	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (capture ? 1231 : 1237);
		result = prime * result + (check ? 1231 : 1237);
		result = prime * result + (checkMate ? 1231 : 1237);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((fromFile == null) ? 0 : fromFile.hashCode());
		result = prime * result
				+ ((fromRank == null) ? 0 : fromRank.hashCode());
		result = prime * result + moveNumber;
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PGNMove other = (PGNMove) obj;
		if (capture != other.capture
			|| check != other.check
			|| checkMate != other.checkMate
			|| color != other.color
			|| fromFile != other.fromFile
			|| fromRank != other.fromRank
			|| moveNumber != other.moveNumber
			|| to != other.to
			|| type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (type == Pawn) {
			if (fromFile != null)
				sb.append(fromFile.toString().toLowerCase()+'x');
			sb.append(to.toString().toLowerCase());

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
