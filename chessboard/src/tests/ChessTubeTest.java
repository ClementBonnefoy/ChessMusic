package tests;

import java.io.File;

import sml.compiler.SMLCompiler;
import sml.elements.Music;
import chesstube.ChessTube;

public class ChessTubeTest {

	public static final String pgnGame="Kasparov_Short_1993.pgn";
	public static final String sml="bolero.sml";

	public static void main(String[] args){

		/* Start the parser */
		System.out.println("ChessTube Test ...");
		File f=new File("sml/"+sml);
		try {

			
			System.out.println("******* "+f.getName()+" *********");
			Music result=SMLCompiler.compile(f);

			System.out.println(result);

			ChessTube ct=new ChessTube(result,new File("games/"+pgnGame),true);
			//ctm.play();

			String midiName=pgnGame.split("\\.")[0]+"_"+
					f.getName().split("\\.")[0]+".mid";
			ct.saveAsMidi(midiName);
			System.out.println("Sauvegardé sous le nom : "+midiName);

		} catch (Exception e) {
			/* do cleanup here -- possibly rethrow e */
			e.printStackTrace();
		}






	}

}
