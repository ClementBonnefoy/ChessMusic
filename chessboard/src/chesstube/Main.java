package chesstube;

import sml.compiler.SMLCompiler;
import sml.elements.Music;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Music result = SMLCompiler.compile("sml/auclairdelalune.sml");
		
		System.out.println(result);
		
		ChessTube ct = new ChessTube(result);
		
		ct.createMidiFromPGN("games/Game1.pgn", "game1.mid");
		
	}
	
}
