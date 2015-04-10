package pgn;

import static board.Color.*;
import static board.Type.*;

import java.util.Arrays;
import java.util.Iterator;

import board.ESquare;
import board.Color;
import board.File;
import board.Rank;
import board.Type;


public class PGNGame implements Iterable<PGNMove>{
	private final String fen;
	private final String event;	
	private final String result;
	private final PGNMove[] moves;
	
	public PGNGame(String fen, String event, String result, String[] strings) {
		super();
		this.fen = fen;
		this.event = event;
		this.result = result;

		moves = new PGNMove[strings.length];

		Color color = White;
		int moveNumber = 1;
		if (fen != null) {
			String[] elems = fen.split(" ");
			color = elems[1].equals("w") ? White : Black;
			moveNumber = Integer.valueOf(elems[5]);
		}
		Type type;
		ESquare to;
		Rank rank;
		File file;
		boolean capture;
		boolean check;
		boolean checkmate;
		String current;

		for (int i = 0; i < strings.length; i++, moveNumber += color == White ? 0 : 1,
												color = color.getOpponent()) {
			rank = null;
			file = null;
			capture = false;
			checkmate = false;
			check = false;
			current = strings[i];

			if (current.matches(".+\\+")) {
				check = true;
				current = current.substring(0,current.length()-1);
			}
			else if (current.matches(".+#")) {
				check = true;
				checkmate = true;
				current = current.substring(0,current.length()-1);
			}

			if (current.equals("O-O")) {
				moves[i] = new PGNKingSideCastling(color,check,checkmate,moveNumber);
				continue;
			}
			else if (current.equals("O-O-O")) {
				moves[i] = new PGNQueenSideCastling(color,check,checkmate,moveNumber);
				continue;
			}
			

			switch (current.charAt(0)) {
			case 'K' : type = King; break;
			case 'Q' : type = Queen; break;
			case 'R' : type = Rook; break;
			case 'N' : type = Knight; break;
			case 'B' : type = Bishop; break;
			default : type = Pawn;
			if (current.charAt(1) == 'x') {
				capture = true;
				file = File.getFile(current.charAt(0));
				current = current.substring(2);
			}


			Type promotion = null;
			if (current.length() > 2) {
				switch (current.charAt(3)) {
				case 'Q': promotion = Queen; break;
				case 'R': promotion = Rook; break;
				case 'N': promotion = Knight; break;
				case 'B': promotion = Bishop; break;
				default: break;
				}
				
				current = current.substring(0, 2);
			}
			
			to = ESquare.getSquare(current);

			if (promotion != null)
				moves[i] = new PGNPromotion(promotion, color, to, file,
						capture, check, checkmate, moveNumber);
			else
				moves[i] = new PGNMove(type, color, to, null, file,
					capture, check, checkmate,
					moveNumber);
			continue;
			}

			char fileChar = current.charAt(current.length()-2);
			char rankChar = current.charAt(current.length()-1);
			to = ESquare.getSquare(File.getFile(fileChar), Rank.getRank(rankChar));

			current = current.substring(1, current.length() - 2);

			// s'il y a prise et/ou desambiguisation
			switch (current.length()) {
			case 3: // double desambiguisation et prise
				capture = true;
				file = File.getFile(current.charAt(0));
				rank = Rank.getRank(current.charAt(1));
				break;
			case 2:
				if (current.charAt(1) == 'x')
					capture = true;
				else
					rank = Rank.getRank(current.charAt(1));
				if (Character.isDigit(current.charAt(0)))
					rank = Rank.getRank(current.charAt(0));
				else
					file = File.getFile(current.charAt(0));
				break;
			case 1:
				if (current.charAt(0) == 'x')
					capture = true;
				else if (Character.isDigit(current.charAt(0)))
					rank = Rank.getRank(current.charAt(0));
				else
					file = File.getFile(current.charAt(0));
				break;
			default: break;

			}

			moves[i] = new PGNMove(type, color, to, rank, file,
					capture, check, checkmate,
					moveNumber);

		}

	}

	public String getFen() {
		return fen;
	}

	public String getEvent() {
		return event;
	}

	public String getResult() {
		return result;
	}

	public PGNMove get(int i) {
		return moves[i];
	}
	
	public int size() {
		return moves.length;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		if (event != null)
			sb.append("[Event \""+event+"\"]\n");
		if (fen != null)
			sb.append("[FEN \""+fen+"\"]\n");
		if (result != null)
			sb.append("[Result \""+result+"\"]\n");
		if (sb.length() != 0)
			sb.append('\n');

		PGNMove pm;
		for (int i = 0; i < moves.length; i++) {
			pm = moves[i];
			if (pm.getColor() == White)
				sb.append(pm.getMoveNumber() + ". ");
			else if (i == 0)
				sb.append(pm.getMoveNumber() + "... ");
			sb.append(pm+" ");
		}
		
		if (result != null)
			sb.append(result);
		
		return sb.toString();
	}

	@Override
	public Iterator<PGNMove> iterator() {
		return Arrays.asList(moves).iterator();
	}	
	
}
