package engine;

import board.Board;
import board.Color;

public class PositionEvaluatorProcess implements Runnable {

	private Stockfish engine; //Stockfish est associe a un processus
	private String fen;
	private Color currentPlayer;
	public boolean isAlive=false;
	public Evaluation evaluation=null;

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
	
	public Evaluation getEvaluation(){
		if(evaluation==null)
			return checkAndGetEvaluation();
		return evaluation;
	}

	public Evaluation checkAndGetEvaluation(){
		if(!isAlive)
			return evaluation;
		
		double score=0;
		while(isAlive){
			try {
				score=engine.getEvalScore();
				break;
			} catch (NoEvaluationException e) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		Double d=score;
		if(d.isNaN()){
			return evaluation;
		}
		if(d>=300){
			//this.stop();
			if(currentPlayer==Color.White)
				evaluation= Evaluation.MATE_FOR_WHITE;
			else 
				evaluation=Evaluation.MATE_FOR_BLACK;
			return evaluation;
		}
		if(d<=-300){
			//this.stop();
			if(currentPlayer==Color.White)
				evaluation = Evaluation.MATE_FOR_BLACK;
			else
				evaluation = Evaluation.MATE_FOR_WHITE;
			return evaluation;
		}

		if(currentPlayer==Color.Black)
			evaluation = new Evaluation(-d);
		else evaluation = new Evaluation(d);
		return evaluation;
	}


	public void stop(){
		engine.stopEngine();
		isAlive=false;
	}

}
