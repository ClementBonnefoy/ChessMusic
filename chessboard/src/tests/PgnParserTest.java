package tests;

import java.io.File;
import java.util.Scanner;

import move.Move;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import board.BoardTools;
import board.Color;
import board.EnumBoard;
import board.InstanceBoard;
import board.InvalidFenException;

public class PgnParserTest {

	public static void main(String[] args) {



		try (Scanner sc = new Scanner(System.in)) {

			for (File f : new File("games").listFiles()) {
//				File f = new File("games/Game1.pgn");
				System.out.println("------ "+f.getName()+" -------");

				PGNParser pgn = new PGNParser(f);
				pgn.parse();
				PGNGame game = pgn.makePgnGame();
				BoardTools.initBoard(EnumBoard.TheBoard, game.getFen());
//				System.out.println(EnumBoard.TheBoard);
				
				Move[] tab = new Move[game.size()];
				InstanceBoard[] boards = new InstanceBoard[game.size()+1];
				
				boards[0] = EnumBoard.TheBoard.store();
				
				for (int i = 0; i < game.size(); i++) {
//					System.out.println(game.get(i));
					tab[i] = game.get(i).makeMove(EnumBoard.TheBoard);
					tab[i].applyTo(EnumBoard.TheBoard);
					boards[i+1] = EnumBoard.TheBoard.store();

//					System.out.println(EnumBoard.TheBoard);
//					System.out.println();
				}
				
				for (int i = game.size() - 1; i >= 0; i--) {
//					System.out.println(EnumBoard.TheBoard);
					System.out.println();
					System.out.println(game.get(i));
					System.out.println();
					tab[i].undo(EnumBoard.TheBoard);
					if (!boards[i].equals(EnumBoard.TheBoard.store()))
						throw new RuntimeException(
								boards[i]+"\n"
										+boards[i].getSide(Color.Black)+"\n"
										+boards[i].getSide(Color.White)+"\n\n"
										+EnumBoard.TheBoard+"\n"
										+EnumBoard.TheBoard.getSide(Color.Black)+"\n"
										+EnumBoard.TheBoard.getSide(Color.White));
				}
				
				
				sc.nextLine();

				//				System.out.println(game);

			}

		} catch (InvalidPGNMoveException e) {
			e.printStackTrace();
		} catch (InvalidFenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
}
