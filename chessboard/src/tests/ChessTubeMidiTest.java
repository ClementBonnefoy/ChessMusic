package tests;

import java.io.File;

import sml.compiler.SMLCompiler;
import sml.elements.Music;
import chesstube.ChessTubeMidi;

public class ChessTubeMidiTest {

	public static void main(	String[] args){

		/* Start the parser */

		for (File f : new File("sml").listFiles()) {
			try {


				Music result=SMLCompiler.compile(f);

				System.out.println("******* "+f.getName()+" *********");
				System.out.println(result);
				
				ChessTubeMidi ctm=new ChessTubeMidi(result);
				ctm.saveMidi(f.getName()+".mid");
			} catch (Exception e) {
				/* do cleanup here -- possibly rethrow e */
				e.printStackTrace();
			}
		}

		Music music=SMLCompiler.compile("sml/bolero.sml");
		System.out.println(music);
		ChessTubeMidi ctm=new ChessTubeMidi(music);
		ctm.saveMidi("exemple-bolero.mid");



	}

}
