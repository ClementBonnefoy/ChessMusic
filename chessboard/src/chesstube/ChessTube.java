package chesstube;

import move.InvalidMoveException;
import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;
import sml.elements.Music;

public class ChessTube {
	
	public Music music;
	
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

	}

}
