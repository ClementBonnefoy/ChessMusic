package engine;

import board.Board;
import pgn.PGNGame;

public class MultiPositionEvaluatorProcess {
	
	PositionEvaluatorProcess [] evaluators;
	double moyenne=0;
	double ecartType=0;
	
	public MultiPositionEvaluatorProcess(Board initialBoard,PGNGame moves){
		
		Board b=initialBoard;
		int nbmoves=moves.size();
		
		evaluators= new PositionEvaluatorProcess[nbmoves+1]; 
		//+1 car pour n coups, n+1 positions
		
		evaluators[0]=new PositionEvaluatorProcess(b);
		
		for(int i=0;i<nbmoves;i++){
			
			moves.get(i).makeMove(b).applyTo(b);
			evaluators[i+1]=new PositionEvaluatorProcess(b);
			
		}
	}
	
	public void startEvaluation(){
		for(PositionEvaluatorProcess t: evaluators){
			t.run();
		}
	}
	
	public void checkMoyenne(){
		double somme=0;
		for(int i=0;i<evaluators.length;i++){
			double score=evaluators[i].getScore();
			somme+=score;
		}
		moyenne=somme/evaluators.length;
	}
	
	public void checkEcartType(){
		checkMoyenne();
		double somme=0;
		for(int i=0;i<evaluators.length;i++){
			double score=evaluators[i].getScore()-moyenne;
			somme+=score*score;
		}
		ecartType=Math.sqrt(somme/evaluators.length);
	}
	
	/**
	 * Retourne l'ecart type (checkEcartType pour calculer)
	 */
	public double getEcartType(){
		return ecartType;
	}
	
	/**
	 * Retourne la moyenne (checkMoyenne pour calculer)
	 */
	public double getMoyenne(){
		return moyenne;
	}
	
	/**
	 * renvoit l'evaluation de la position apres avoir joue le coup de numero 
	 * moveNumber par rapport au PGNGame passe dans le constructeur. le processus 
	 * de calcul s'arrete ensuite
	 * Attention : moveNumber=0 correspond a la position initiale
	 * @param moveNumber 
	 * @return
	 */
	public double stopAndGetEvaluation(int moveNumber){
		double res = evaluators[moveNumber].getScore();
		evaluators[moveNumber].stop();
		return res;
	}
	
	/**
	 * renvoit l'evaluation de la position apres avoir joue le coup de numero 
	 * moveNumber par rapport au PGNGame passe dans le constructeur. 
	 * Attention : moveNumber=0 correspond a la position initiale
	 * @param moveNumber 
	 * @return
	 */
	public double getEvaluation(int moveNumber){
		double res = evaluators[moveNumber].getScore();
		return res;
	}

	public void stop() {
		for(PositionEvaluatorProcess p:evaluators)
			p.stop();
		
	}
	
	

}
