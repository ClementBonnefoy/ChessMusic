package chesstube;

import board.Board;
import engine.MultiPositionEvaluatorProcess;
import move.InvalidMoveException;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import sml.elements.Music;
import sml.elements.Play;
import sml.elements.ScaleName;
import sml.interfaces.IVisitable;
import sml.interfaces.IVisitor;
import sml.visitors.ChangeScale;

public class ChessTube implements IVisitor{
	
	private static final int WAIT_TIME=0; // temp d'attente pour l'évaluateur
	
	private Music music;
	private MultiPositionEvaluatorProcess mpep;
	private int coup=-1;
	
	public ChessTube(Music smlMusic){
		this.music=smlMusic;
	}
	
	
	public void createMidiFromPGN(String pgnFileName,String midiFileName){

			ChessTubeMidi md=new ChessTubeMidi(music);

			PGNParser parser=new PGNParser(pgnFileName);

			PGNGame pgnGame = null;

			try {
				parser.parse();
				pgnGame = parser.makePgnGame();
			} catch (InvalidMoveException | InvalidPGNMoveException e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			this.analyse(pgnGame);
			
			mpep.stop();
			
			md.saveMidi(midiFileName);

	}


	private void analyse(PGNGame pgnGame) {
		Board board=new Board();
		board.init();
		
		mpep=new MultiPositionEvaluatorProcess(board, pgnGame);
		mpep.startEvaluation();
		
		try {
			Thread.sleep(WAIT_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mpep.checkEcartType();
		
		music.accept(this);
		
	}


	@Override
	public void visit(IVisitable obj) {
		if(obj instanceof Play){
			Play p=(Play)obj;
			double score=0;
			if(coup!=-1)
				score=mpep.stopAndGetEvaluation(coup);
			System.out.println("ok");
			double ecartType=mpep.getEcartType();
			System.out.println("ok1'");
			ScaleName scale=ScaleManager.getScale(score, ecartType);
			System.out.println("ok2");
			ChangeScale cs=new ChangeScale(scale);
			System.out.println("ok3");
			p.accept(cs);
			System.out.println("ok4");
			coup++;
		}
		
	}

}
