package tests;

import java.io.File;
import java.util.Scanner;

import move.Move;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import board.Color;
import board.Board;
import board.Factory;
import board.InvalidFenException;

public class PgnParserTest {

	public static void main(String[] args) {



		try (Scanner sc = new Scanner(System.in)) {

			for (File f : new File("games").listFiles()) {
//				File f = new File("games/gameTest.pgn");
				System.out.println("------ "+f.getName()+" -------");

				PGNParser pgn = new PGNParser(f);
				pgn.parse();
				PGNGame game = pgn.makePgnGame();
				Board board = new Board();
				board.init(game.getFen());
//				System.out.println(board);
				
				Move[] tab = new Move[game.size()];
				Board[] boards = new Board[game.size()+1];
				
				boards[0] = board.clone();
				
				for (int i = 0; i < game.size(); i++) {
					System.out.println(game.get(i));
					tab[i] = game.get(i).makeMove(board);
					if (!tab[i].makePGNMove(board).equals(game.get(i)))
						throw new RuntimeException();
					tab[i].applyTo(board);
					boards[i+1] = board.clone();
					System.out.println();
					System.out.println(board);
					System.out.println();
				}
				
				for (int i = game.size() - 1; i >= 0; i--) {
//					System.out.println(EnumBoard.TheBoard);
					System.out.println();
					System.out.println(game.get(i));
					System.out.println();
					tab[i].undo(board);
					if (!boards[i].equals(board.clone()))
						throw new RuntimeException(
								boards[i]+"\n"
										+boards[i].getSide(Color.Black)+"\n"
										+boards[i].getSide(Color.White)+"\n\n"
										+board+"\n"
										+board.getSide(Color.Black)+"\n"
										+board.getSide(Color.White));
				}
//				
//				
				sc.nextLine();
//
//				//				System.out.println(game);

			}

		} catch (InvalidPGNMoveException e) {
			e.printStackTrace();
		} catch (InvalidFenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
}
