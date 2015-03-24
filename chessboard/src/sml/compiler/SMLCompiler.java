package sml.compiler;

import java.io.File;
import java.io.FileReader;

import sml.elements.Music;

public class SMLCompiler {
	
	public static Music compile(File f){
		Music result=null;
		try {
			parser p = new parser(new Lexer(new FileReader(f)));
			result = (Music)(p.parse().value);
			return result;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static Music compile(String path){
		return compile(new File(path));
	}
	
	public static Music getExample(){
		return compile("sml/exemple.sml");
	}
	

}
