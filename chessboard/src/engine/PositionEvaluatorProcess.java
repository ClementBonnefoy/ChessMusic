package engine;

import board.Board;
import board.Color;

public class PositionEvaluatorProcess implements Runnable {

	private Stockfish engine; //Stockfish est associe a un processus
	private Board board;
	private double result=0;
	public boolean isAlive=false;

	public PositionEvaluatorProcess(Board b){
		this.board=b;
		engine=new Stockfish();
	}

	@Override
	public void run() {

		engine.startEngine();
		isAlive=true;
		engine.startEvalScore(board.toFEN());

	}

	public double getScore(){

		System.out.println(board);
		
		if(isAlive)
			result=engine.getEvalScore();

		if(board.getCurrentPlayer()==Color.Black)
			return -result;
		return result;
	}


	public void stop(){
		engine.stopEngine();
		isAlive=false;
	}

}
