package move;

import static board.File.*;
import static move.Direction.East;
import static move.Direction.North;
import static move.Direction.NorthEast;
import static move.Direction.NorthWest;
import static move.Direction.South;
import static move.Direction.SouthEast;
import static move.Direction.SouthWest;
import static move.Direction.West;
import static board.Type.*;

import java.util.ArrayList;

import board.Board;
import board.Color;
import board.ESquare;
import board.Piece;
import board.Type;

public enum Movement {

	KingMovement {
		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			ArrayList<ESquare> basicDests = new ArrayList<>();
			for (Direction dir : Direction.values()) {
				if (!from.hasNextSquare(dir))
					continue;
				basicDests.add(from.nextSquare(dir));
			}

			return basicDests;
		}

		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			ArrayList<Move> realMoves = new ArrayList<>();

			for (ESquare to : basicDestinations(board, from)) {
				if ((board.isEmpty(to) || board.isEnemy(to))
						&&	(board.currentSide().canQueenSideCastle()
								|| board.currentSide().canKingSideCastle()))
					realMoves.add(
							new DisablingCastlingMove(
									from, to,
									board.getPiece(from), board.getPiece(to),
									board.currentSide().canKingSideCastle(),
									board.currentSide().canQueenSideCastle(),
									board.getLimit50moves(),
									board.getEnPassant()));
				else if (board.isEnemy(to))
					realMoves.add(
							new SimpleCapture(
									from, to,
									board.getPiece(from), board.getPiece(to),
									board.getLimit50moves(),
									board.getEnPassant()));
				else if (board.isEmpty(to))
					realMoves.add(
							new Move(from, to,
									board.getPiece(from), board.getEnPassant()));
			}

			if (board.currentSide().canKingSideCastle()) {
				ESquare throughF = ESquare.getSquare(FileF, board.getCurrentPlayer().firstRank());
				ESquare throughG = ESquare.getSquare(FileG, board.getCurrentPlayer().firstRank());

				if (board.isEmpty(throughF) && board.isEmpty(throughG)
						&& !board.isEnPrise(throughF) && !board.isEnPrise(throughG)) {

					Piece rook = board.getPiece(
							ESquare.getSquare(
									FileH, board.getCurrentPlayer().firstRank()));

					realMoves.add(
							new KingSideCastling(
									board.getPiece(from), rook,
									board.getEnPassant(),
									board.currentSide().canQueenSideCastle()));
				}
			}
			if (board.currentSide().canQueenSideCastle()){
				ESquare throughD = ESquare.getSquare(FileD, board.getCurrentPlayer().firstRank());
				ESquare throughC = ESquare.getSquare(FileC, board.getCurrentPlayer().firstRank());
				ESquare throughB = ESquare.getSquare(FileB, board.getCurrentPlayer().firstRank());
				if (board.isEmpty(throughD) && board.isEmpty(throughC) && board.isEmpty(throughB)
						&& !board.isEnPrise(throughD) && !board.isEnPrise(throughC)) {
					Piece rook = board.getPiece(
							ESquare.getSquare(
									FileA, board.getCurrentPlayer().firstRank()));

					realMoves.add(
							new QueenSideCastling(
									board.getPiece(from), rook,
									board.getEnPassant(),
									board.currentSide().canKingSideCastle()));
				}
			}

			checkFilter(realMoves,board);

			return realMoves;
		}


	}, QueenMovement {
		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			return basicLineMovements(Direction.values(), board, from);
		}

		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			ArrayList<Move> realMoves = realMoves(board,from,basicDestinations(board, from));

			checkFilter(realMoves,board);

			return realMoves;
		}
	}, RookMovement {
		Direction[] dirs = {North, East, South, West};

		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			return basicLineMovements(dirs, board, from);
		}

		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			ArrayList<Move> realMoves = new ArrayList<>();

			if ((board.currentSide().canKingSideCastle()
					&& board.get(from).getFile() == FileH)
					|| (board.currentSide().canQueenSideCastle()
							&& board.get(from).getFile() == FileA))
				for (ESquare eSquare : basicDestinations(board, from)) {
					if (board.isEmpty(eSquare) || board.isEnemy(eSquare))
						realMoves.add(
								new DisablingCastlingMove(
										from, eSquare,
										board.getPiece(from), board.getPiece(eSquare),
										board.currentSide().canKingSideCastle(),
										board.currentSide().canQueenSideCastle(),
										board.getLimit50moves(),
										board.getEnPassant()));
				}
			else
				realMoves = realMoves(board,from,basicDestinations(board, from));

			checkFilter(realMoves,board);

			return realMoves;
		}
	}, KnightMovement {
		Direction[] dirs =
			{North, NorthWest,
				North, NorthEast,
				East, NorthEast,
				East, SouthEast,
				South, SouthEast,
				South, SouthWest,
				West, SouthWest,
				West, NorthWest};
		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			
			ArrayList<ESquare> basicDests = new ArrayList<>();

			for (int i = 0; i < 8; i++) {
				ESquare c;
				if (!from.hasNextSquare(dirs[2*i]))
					continue;
				c = from.nextSquare(dirs[2*i]);
				if (!c.hasNextSquare(dirs[2*i+1]))
					continue;
				c = c.nextSquare(dirs[2*i+1]);
				basicDests.add(c);
			}


			return basicDests;
		}
		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			ArrayList<Move> realMoves = realMoves(board,from,basicDestinations(board, from));

			checkFilter(realMoves,board);

			return realMoves;
		}
	}, BishopMovement {

		Direction[] dirs = {NorthEast, SouthEast, SouthWest, NorthWest};

		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			return basicLineMovements(dirs, board, from);
		}

		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			ArrayList<Move> realMoves = realMoves(board,from,basicDestinations(board, from));

			checkFilter(realMoves,board);

			return realMoves;
		}
	}, PawnMovement {
		private Type[] promotions = {Queen, Rook, Knight, Bishop};

		@Override
		public ArrayList<ESquare> basicDestinations(Board board, ESquare from) {
			ArrayList<ESquare> basicDests = new ArrayList<>();
			Color color = board.getCurrentPlayer();
			Direction dir = color.forwards();
			ESquare next = from.nextSquare(dir);

			if (board.isEmpty(next)) {
				basicDests.add(next);
				if (from.getRank() == color.pawnStartingRank()) {
					ESquare jump = next.nextSquare(dir);
					if (board.isEmpty(jump))
						basicDests.add(jump);
				}

			}

			dir = color.eastPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.getEnPassant() == next || board.isEnemy(next))
					basicDests.add(next);
			}

			dir = color.westPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.getEnPassant() == next || board.isEnemy(next))
					basicDests.add(next);
			}

			return basicDests;
		}

		@Override
		public ArrayList<Move> realMoves(Board board, ESquare from) {
			if (board.getPiece(from).getColor() != board.getCurrentPlayer())
				return new ArrayList<>();
			
			ArrayList<Move> realMoves = new ArrayList<>();
			Color color = board.getCurrentPlayer();
			Direction dir = color.forwards();
			ESquare next = from.nextSquare(dir);

			if (board.isEmpty(next)) {
				if (color.promotionRank() == next.getRank()) {
					for (Type type : promotions)
						realMoves.add(
								new Promotion(
										type,
										from, next,
										board.getPiece(from), null, 
										board.getLimit50moves(),
										board.getEnPassant()));
				}
				else {
					realMoves.add(
							new SimplePawnMove(
									from, next,
									board.getPiece(from),
									board.getLimit50moves(),
									board.getEnPassant()));
					if (from.getRank() == color.pawnStartingRank()) {
						ESquare jump = next.nextSquare(dir);
						if (board.isEmpty(jump))
							realMoves.add(
									new JumpPawnMove(
											from, jump,
											board.getPiece(from),
											board.getLimit50moves(),
											board.getEnPassant()));
					}
				}

			}

			dir = color.eastPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.getEnPassant() == next)
					realMoves.add(
							new EnPassantCapture(
									from, next,
									board.getPiece(from),
									board.getPiece(next.nextSquare(color.backwards())),
									board.getLimit50moves()));
				else if (board.isEnemy(next)) {
					if (color.promotionRank() == next.getRank()) {
						for (Type type : promotions)
							realMoves.add(
									new Promotion(
											type,
											from, next,
											board.getPiece(from), board.getPiece(next), 
											board.getLimit50moves(),
											board.getEnPassant()));
					}
					else
						realMoves.add(
							new SimpleCapture(
									from, next,
									board.getPiece(from), board.getPiece(next),
									board.getLimit50moves(),
									board.getEnPassant()));
				}
			}

			dir = color.westPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.getEnPassant() == next)
					realMoves.add(
							new EnPassantCapture(
									from, next,
									board.getPiece(from),
									board.getPiece(next.nextSquare(color.backwards())),
									board.getLimit50moves()));
				else if (board.isEnemy(next)) {
					if (color.promotionRank() == next.getRank()) {
						for (Type type : promotions)
							realMoves.add(
									new Promotion(
											type,
											from, next,
											board.getPiece(from), board.getPiece(next), 
											board.getLimit50moves(),
											board.getEnPassant()));
					}
					else
						realMoves.add(
							new SimpleCapture(
									from, next,
									board.getPiece(from), board.getPiece(next),
									board.getLimit50moves(),
									board.getEnPassant()));
				}
			}
			
			checkFilter(realMoves,board);

			return realMoves;
		}

	};

	public abstract ArrayList<ESquare> basicDestinations(Board board, ESquare from);
	public abstract ArrayList<Move> realMoves(Board board, ESquare from);

	public static Movement get(Type type) {
		switch (type) {
		case King:
			return KingMovement;
		case Queen:
			return QueenMovement;
		case Rook:
			return RookMovement;
		case Knight:
			return KnightMovement;
		case Bishop:
			return BishopMovement;
		case Pawn:
			return PawnMovement;
		default:
			throw new IllegalArgumentException("Type is null");
		}
	}

	private static ArrayList<ESquare> basicLineMovements(Direction[] dirs, Board board, ESquare from) {
		ArrayList<ESquare> basicDests = new ArrayList<>();
		ESquare tmp;

		for (Direction dir : dirs) {
			tmp = from;
			while (tmp.hasNextSquare(dir)) {
				tmp = tmp.nextSquare(dir);
				if (board.isEmpty(tmp))
					basicDests.add(tmp);
				else {
					basicDests.add(tmp);
					break;
				}
			}
		}

		return basicDests;
	}

	private static ArrayList<Move> realMoves(Board board, ESquare from, ArrayList<ESquare> eSquares) {
		ArrayList<Move> moves = new ArrayList<Move>();

		for (ESquare eSquare : eSquares)
			if (board.isEmpty(eSquare))
				moves.add(
						new Move(from, eSquare, board.getPiece(from), board.getEnPassant()));
			else if (board.isEnemy(eSquare))
				moves.add(
						new SimpleCapture(
								from, eSquare,
								board.getPiece(from), board.getPiece(eSquare), 
								board.getLimit50moves(),
								board.getEnPassant()));

		return moves;
	}


	private static void checkFilter(ArrayList<Move> moves, Board board) {
		ArrayList<Move> illegalMoves = new ArrayList<>();
		for (Move move : moves) {

			move.applyTo(board);

			board.invertPlayer();

			if (board.isInCheck())
				illegalMoves.add(move);

			board.invertPlayer();

			move.undo(board);

		}
		
		moves.removeAll(illegalMoves);

	}

}
