package tests;

import java.io.*;

import sml.compiler.Lexer;
import sml.compiler.parser;

public class SmlParserTest {
	static public void main(String argv[]) {    
		/* Start the parser */

		for (File f : new File("sml").listFiles()) {
			try {
				parser p = new parser(new Lexer(new FileReader(f)));
				Object result = p.parse().value; 
				System.out.println(result);
			} catch (Exception e) {
				/* do cleanup here -- possibly rethrow e */
				e.printStackTrace();
			}
		}
	}
}


