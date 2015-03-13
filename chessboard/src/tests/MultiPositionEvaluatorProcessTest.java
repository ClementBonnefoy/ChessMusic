package tests;

import board.Board;
import board.Factory;
import engine.MultiPositionEvaluatorProcess;
import move.InvalidMoveException;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;

public class MultiPositionEvaluatorProcessTest {
	
	private static String pgnFileName="games/Modern2170.pgn";
	
	public static void main(String[] args){
		
		PGNParser parser=new PGNParser(pgnFileName);

		PGNGame pgnGame = null;
		
		Board board=new Board();
		board.init();

		try {
			parser.parse();
			pgnGame = parser.makePgnGame();
		} catch (InvalidMoveException | InvalidPGNMoveException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		MultiPositionEvaluatorProcess mpep=new MultiPositionEvaluatorProcess(
				board,pgnGame);
		
		System.out.println("Starting ...");
		
		mpep.startEvaluation();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<=pgnGame.size();i++){
			System.out.println("Position "+i+" "+mpep.getEvaluation(i));
		}
		
		
	}

}
