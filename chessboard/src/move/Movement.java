package move;

import java.util.ArrayList;

import board.Board;
import board.Square;
import board.Color;
import board.Type;
import static move.Direction.*;

public enum Movement {
	
	KingMovement {
		@Override
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			ArrayList<Square> basicDests = new ArrayList<>();
			for (Direction dir : Direction.values()) {
				if (!from.hasNextSquare(dir))
					continue;
				basicDests.add(from.nextSquare(dir));
			}
			
			return basicDests;
		}
		
		@Override
		public ArrayList<Square> realDestinations(Board board, Square from) {
			//TODO implementer echec...
			ArrayList<Square> realDests = basicDestinations(board, from);

			friendCaptureFilter(realDests, board);
			
			if (board.currentSide().canKingSideCastle())
				realDests.add(board.getCurrentPlayer().kingSideSquare());
			if (board.currentSide().canQueenSideCastle())
				realDests.add(board.getCurrentPlayer().queenSideSquare());
			
			checkFilter(realDests,board);
			
			return realDests;
		}
		
		
	}, QueenMovement {
		@Override
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			return basicLineMovements(Direction.values(), board, from);
		}

		@Override
		public ArrayList<Square> realDestinations(Board board, Square from) {
			ArrayList<Square> realDests = basicDestinations(board, from);
			
			friendCaptureFilter(realDests, board);
			
			checkFilter(realDests,board);
			
			return realDests;
		}
	}, RookMovement {
		Direction[] dirs = {North, East, South, West};
		
		@Override
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			return basicLineMovements(dirs, board, from);
		}

		@Override
		public ArrayList<Square> realDestinations(Board board, Square from) {
			ArrayList<Square> realDests = basicDestinations(board, from);
			
			friendCaptureFilter(realDests, board);
			
			checkFilter(realDests,board);
			
			return realDests;
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
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			
			ArrayList<Square> basicDests = new ArrayList<>();
			
			for (int i = 0; i < 8; i++) {
				Square c;
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
		public ArrayList<Square> realDestinations(Board board, Square from) {
			ArrayList<Square> realDests = basicDestinations(board, from);
			
			friendCaptureFilter(realDests, board);
			
			checkFilter(realDests,board);
			
			return realDests;
		}
	}, BishopMovement {
		
		Direction[] dirs = {NorthEast, SouthEast, SouthWest, NorthWest};

		@Override
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			return basicLineMovements(dirs, board, from);
		}

		@Override
		public ArrayList<Square> realDestinations(Board board, Square from) {
			ArrayList<Square> realDests = basicDestinations(board, from);
			
			friendCaptureFilter(realDests, board);
			
			checkFilter(realDests,board);
			
			return realDests;
		}
	}, PawnMovement {
		@Override
		public ArrayList<Square> basicDestinations(Board board, Square from) {
			ArrayList<Square> basicDests = new ArrayList<>();
			Color color = board.getCurrentPlayer();
			Direction dir = color.forwards();
			Square next = from.nextSquare(dir);
			
			if (board.isEmpty(next)) {
				basicDests.add(next);
				if (from.getRank() == color.pawnStartingRank()) {
					Square jump = next.nextSquare(dir);
					if (board.isEmpty(jump))
						basicDests.add(jump);
				}
				
			}
			
			if (board.getEnPassant() != null
					&& board.getEnPassant().getRank() == from.nextSquare(dir).getRank())
				basicDests.add(board.getEnPassant());

			dir = color.eastPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.isEnemy(next))
					basicDests.add(next);
			}
			
			dir = color.westPawnThreat();
			if (from.hasNextSquare(dir)) {
				next = from.nextSquare(dir);
				if (board.isEnemy(next))
					basicDests.add(next);
			}
			
			return basicDests;
		}

		@Override
		public ArrayList<Square> realDestinations(Board board, Square from) {

			ArrayList<Square> realDests = basicDestinations(board, from);
			
			checkFilter(realDests,board);
			
			return realDests;
		}
		
	};
	
	public abstract ArrayList<Square> basicDestinations(Board board, Square from);
	public abstract ArrayList<Square> realDestinations(Board board, Square from);
	
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
	
	private static ArrayList<Square> basicLineMovements(Direction[] dirs, Board board, Square from) {
		ArrayList<Square> basicDests = new ArrayList<>();
		Square tmp;
		
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
	
	private static void friendCaptureFilter(ArrayList<Square> dests, Board board)  {
		Square c;
		for (int i = 0; i < dests.size();) {
			c = dests.get(i);
			if (!board.isEmpty(c) && !board.isEnemy(c))
				dests.remove(i);
			else
				i++;
		}
		
	}
	
	//TODO checkFilter
	private static void checkFilter(ArrayList<Square> dests, Board board) {
		
	}
	
}
