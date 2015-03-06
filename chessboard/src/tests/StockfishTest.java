package tests;

import engine.NoEvaluationException;
import engine.Stockfish;

public class StockfishTest {
	public static void main(String[] args) {
		Stockfish client = new Stockfish();
		//String FEN = "2r1rk1N/4qpp1/p1b2n2/1pP4R/4p3/P3P1P1/B1Q2P1P/2R3K1 w - - 0 1";
		String FEN = "r1bq3k/p1p2Np1/5n1p/8/1PQ1P3/7P/P4PP1/R1BrR1K1 b - - 3 18";


		// initialize and connect to engine
		if (client.startEngine()) {
			System.out.println("Engine has started..");
		} else {
			System.out.println("Oops! Something went wrong..");
		}

		// send commands manually
		client.sendCommand("uci");

		// receive output dump
		System.out.println(client.getOutput(0));

		// get the best move for a position with a given think time
		System.out.println("Best move : " + client.getBestMove(FEN, 4000));

		// get all the legal moves from a given position
		//System.out.println("Legal moves : " + client.getLegalMoves(FEN));

		// draw board from a given position
		System.out.println("Board state :");
		client.drawBoard(FEN);

		// get the evaluation score of current position
		System.out.println("Evaluation score : " + client.getEvalScore(FEN, 2000));

		// starting evaluate a new position
		client.startEvalScore(FEN);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//get the evaluation score 
		try {
			System.out.println("Evaluation score : " + client.getEvalScore());
		} catch (NoEvaluationException e) {
			e.printStackTrace();
		}
		
		

		// stop the engine
		System.out.println("Stopping engine..");
		client.stopEngine();
	}
}
