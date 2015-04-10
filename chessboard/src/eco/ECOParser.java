package eco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pgn.InvalidPGNMoveException;
import pgn.PGNGame;
import pgn.PGNParser;

public class ECOParser {
	
	private ECOTree ecoTree;
	
	public ECOParser(String filename) {
		this(new File(filename));
	}

	public ECOParser(File file) {
		ecoTree = new ECOTree();
		Pattern p = Pattern.compile("([^:]+):[^:]+:([^:]+)");
		Matcher m;
		String line, code;
		PGNGame game;
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while (br.ready()) {
				line = br.readLine();
				m = p.matcher(line);
				m.matches();
				code = m.group(1);
				for (String opening : m.group(2).split(",")) {
					//System.out.println(opening);
					ecoTree.insert(code, PGNParser.makePGNGame(opening));
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPGNMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String findCode(PGNGame game){
		return this.ecoTree.findCode(game);
	}
	
	public static void main(String[] args) {
		ECOParser p = new ECOParser("eco.txt");
		
		try {
			PGNGame game = PGNParser.makePGNGame("1. d4 Nf6 2. c4 g6 3. Nc3 Bg7 4. e4 d6 5. Nf3 O-O 6. Be2 e5 7. O-O Nc6 8. d5 Ne7 9. Ne1 Nd7");
			System.out.println(p.ecoTree.findCode(game));

		} catch (InvalidPGNMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
