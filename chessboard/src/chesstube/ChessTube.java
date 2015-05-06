package chesstube;

import java.io.File;

import move.InvalidMoveException;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import sml.elements.Music;

public class ChessTube{
	
	public static final int WAIT_TIME=10000; // temp d'attente pour l'évaluateur(ms)
	
	private boolean analyse=true;
	private Music music;
	private PGNGame pgnGame;
	
	public ChessTube(Music smlMusic,PGNGame pgnGame){
		this.music=smlMusic;
		this.pgnGame=pgnGame;
	}
	
	public ChessTube(Music smlMusic, File pgnGame){
		this(smlMusic,pgnGame,true);
	}


	public ChessTube(Music smlMusic, File pgnGame, boolean analyse) {
		this.analyse=analyse;
		this.music=smlMusic;
		
		if(analyse){
			PGNParser parser=new PGNParser(pgnGame);

			try {
				parser.parse();
				this.pgnGame = parser.makePgnGame();
			} catch (InvalidMoveException | InvalidPGNMoveException e) {
				e.printStackTrace();
				return;
			}
		}
		
	}

	public void saveAsMidi(String midiFileName) {
		ChessTubeMidi ctm=new ChessTubeMidi(music,analyse);
		ctm.setPGNGame(pgnGame);
		ctm.saveMidi(midiFileName);
		
	}

}
