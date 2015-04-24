package chesstube;

import java.io.File;

import move.InvalidMoveException;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import sml.elements.Music;

public class ChessTube{
	
	public final static boolean ANALYSE=true;
	public static final int WAIT_TIME=5000; // temp d'attente pour l'évaluateur(ms)
	
	private Music music;
	private PGNGame pgnGame;
	
	public ChessTube(Music smlMusic,PGNGame pgnGame){
		this.music=smlMusic;
		this.pgnGame=pgnGame;
	}
	
	public ChessTube(Music smlMusic, File pgnGame){
		this.music=smlMusic;
		PGNParser parser=new PGNParser(pgnGame);

		try {
			parser.parse();
			this.pgnGame = parser.makePgnGame();
		} catch (InvalidMoveException | InvalidPGNMoveException e) {
			e.printStackTrace();
			return;
		}
	}


	public void saveAsMidi(String midiFileName) {
		ChessTubeMidi ctm=new ChessTubeMidi(music);
		ctm.setPGNGame(pgnGame);
		ctm.saveMidi(midiFileName);
		
	}

}
