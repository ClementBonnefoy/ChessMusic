package engine;

import board.Board;
import board.Color;

public class EngineTools {

	/**
	 * Renvoit l'evaluation de la position actuelle du Board.
	 * @param b le board
	 * @param waitTime : le temps de reflexion accorde a l'engine (en ms)
	 * @return l'evaluation en centipawn
	 */
	public static double getEvaluation(Board b,int waitTime){
		
		Stockfish client = new Stockfish();
		String FEN = b.toFEN();

		client.startEngine();
		double res= client.getEvalScore(FEN, waitTime);
		client.stopEngine();
		if(b.getCurrentPlayer()==Color.White)
			return res;
		return -res;
		
	}
	
	public static void main(String [] args){
		Board b=new Board();
		b.init();
		System.out.println(getEvaluation(b,4000));
	}

}
