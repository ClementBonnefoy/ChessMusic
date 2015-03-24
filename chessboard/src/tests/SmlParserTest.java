package tests;

import java.io.*;

import sml.compiler.SMLCompiler;
import sml.elements.Music;

public class SmlParserTest {
	
	static public void main(String argv[]) {    
		/* Start the parser */

		for (File f : new File("sml").listFiles()) {
			try {
				Music result=SMLCompiler.compile(f);
				
				System.out.println("******* "+f.getName()+" *********");
				
				System.out.println(result);
				
				System.out.println("Temps total du morceau: "+result.getTime());
			} catch (Exception e) {
				/* do cleanup here -- possibly rethrow e */
				e.printStackTrace();
			}
		}
	}
	
}


