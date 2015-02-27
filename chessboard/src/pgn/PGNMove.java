package pgn;

import move.*;
import board.*;
import static board.Type.*;
import static board.File.FileA;
import static board.File.FileH;


public class PGNMove {

	protected final Type type;
	protected final Color color;
	protected final ESquare to;
	protected final Rank fromRank;
	protected final File fromFile;
	protected final boolean capture;
	protected final boolean check, checkMate;
	protected final int moveNumber;

	public PGNMove(Type type, Color color, ESquare to, Rank fromRank, File fromFile,
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
	public ESquare getTo() {
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
		ESquare from;
		Piece piece;
		Piece eaten = board.getPiece(to);


		if (type == Pawn) {

			ESquare beforeTo = to.nextSquare(color.backwards());

			if (board.getEnPassant() == to) {
				from = ESquare.getSquare(fromFile, beforeTo.getRank());
				piece = board.getPiece(from);
				return new EnPassantCapture(from, to, piece, eaten, check, checkMate,
						board.getLimit50moves());
			}

			if (capture) {
				from = ESquare.getSquare(fromFile, beforeTo.getRank());
				piece = board.getPiece(from);
				return new SimpleCapture(from, to, piece, 
						eaten, check, checkMate,
						board.getLimit50moves(), board.getEnPassant());
			}


			if (board.isEmpty(beforeTo)) {
				from = beforeTo.nextSquare(color.backwards());
				return new JumpPawnMove(from,to,
						board.getPiece(from),
						check, checkMate,
						board.getLimit50moves(), board.getEnPassant());
			}
			
			return new SimplePawnMove(beforeTo, to,
					board.getPiece(beforeTo),
					check, checkMate,
					board.getLimit50moves(), board.getEnPassant());
		}

		if (fromFile != null && fromRank != null) {
			ESquare fromSquare = ESquare.getSquare(fromFile, fromRank);
			if (isDisablingCastlingMove(board))
				return new DisablingCastlingMove(fromSquare, to,
						board.getPiece(fromSquare), eaten, check, checkMate,
						board.currentSide().canKingSideCastle(),
						board.currentSide().canQueenSideCastle(),
						board.getLimit50moves(), board.getEnPassant());
			if (capture)
				return new SimpleCapture(fromSquare, to,
						board.getPiece(fromSquare), eaten, check, checkMate,
						board.getLimit50moves(), board.getEnPassant());
			else
				return new Move(fromSquare, to, board.getPiece(fromSquare),
						check, checkMate, board.getEnPassant());
		}

		Movement mvmt = Movement.get(type);
		EPiece ePiece = EPiece.get(type, color);
		for (ESquare c : mvmt.basicDestinations(board, to)) {
			if (board.getPiece(c) != null && 
					board.getPiece(c).getEPiece() == ePiece) {
				if (fromFile != null && c.getFile() != fromFile)
					continue;
				if (fromRank != null && c.getRank() != fromRank)
					continue;
				if (isDisablingCastlingMove(board))
					return new DisablingCastlingMove(c, to,
							board.getPiece(c), eaten, check, checkMate,
							board.currentSide().canKingSideCastle(),
							board.currentSide().canQueenSideCastle(),
							board.getLimit50moves(), board.getEnPassant());

				if (capture)
					return new SimpleCapture(c, to, board.getPiece(c), eaten,
							check, checkMate,
							board.getLimit50moves(), board.getEnPassant());
				return new Move(c, to, board.getPiece(c), check, checkMate,
						board.getEnPassant());
			}
		}

		throw new InvalidMoveException("Starting square wasn't found : to = " + to);

	}

	public boolean isDisablingCastlingMove(Board board) {
		if (type == Rook) {
			if (board.currentSide().canKingSideCastle()
					&& fromFile == FileH)
				return true;
			if (board.currentSide().canQueenSideCastle()
					&& fromFile == FileA)
				return true;
		}
		else if (type == King) {
			if (board.currentSide().canKingSideCastle()
					|| board.currentSide().canQueenSideCastle())
				return true;
		}

		return false;
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
		sb.append(type.toString().toUpperCase());
		if (fromFile != null)
			sb.append(fromFile.toString().toLowerCase());
		if (fromRank != null)
			sb.append(fromRank.toString());
		if (capture)
			sb.append('x');
		sb.append(to.toString().toLowerCase());


		if (check)
			sb.append('+');
		else if (checkMate)
			sb.append('#');

		return sb.toString();
	}

}
