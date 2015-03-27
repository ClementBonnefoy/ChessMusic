package tests;

import sml.compiler.SMLCompiler;
import sml.elements.Music;
import chesstube.ChessTubeMidi;

public class ChessTubeMidiTest {
	
	public static void main(String[] args){
		Music music=SMLCompiler.compile("sml/bolero.sml");
		ChessTubeMidi ctm=new ChessTubeMidi(music);
		ctm.saveMidi("bolero-chesstube.mid");
		
		
		
	}

}
