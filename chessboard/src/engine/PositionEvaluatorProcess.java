package engine;

import board.Board;
import board.Color;

public class PositionEvaluatorProcess implements Runnable {

	private Stockfish engine; //Stockfish est associe a un processus
	private String fen;
	private Color currentPlayer;
	private double result=0;
	public boolean isAlive=false;

	public PositionEvaluatorProcess(Board b){
		this.fen=b.toFEN();
		this.currentPlayer=b.getCurrentPlayer();
		engine=new Stockfish();
	}

	@Override
	public void run() {

		engine.startEngine();
		isAlive=true;
		engine.startEvalScore(fen);

	}

	public double getScore(){
		
		while(isAlive){
			try {
				result=engine.getEvalScore();
				break;
			} catch (NoEvaluationException e) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		if(currentPlayer==Color.Black)
			return -result;
		return result;
	}


	public void stop(){
		engine.stopEngine();
		isAlive=false;
	}

}
