package tests;

import java.io.File;

import sml.compiler.SMLCompiler;
import sml.elements.Music;
import chesstube.ChessTube;

public class SmlMidiTest {

	public static void main(String[] args){

		System.out.println("Sml -> Midi Conversion test ...");

		for (File f : new File("sml").listFiles()) {
			try {
				
				if(f.getName().startsWith("."))
					continue;
				
				System.out.println("******* "+f.getName()+" *********");

				Music result=SMLCompiler.compile(f);
				System.out.println(result);

				ChessTube ct=new ChessTube(result,null,false);
				//ctm.play();

				String midiName=f.getName().split("\\.")[0]+".mid";
				ct.saveAsMidi(midiName);
				System.out.println("Sauvegardé sous le nom : "+midiName);

			} catch (Exception e) {
				/* do cleanup here -- possibly rethrow e */
				e.printStackTrace();
			}
		}
	}

}
