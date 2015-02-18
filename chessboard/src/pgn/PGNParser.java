package pgn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import move.Move;

import board.BoardTools;
import board.EnumBoard;
import board.InvalidFenException;

public class PGNParser {


	private String game = "";
	private String[] moves;
	private String event;
	private String result;
	private String fen;

	public PGNParser(String filename) {
		this(new File(filename));
	}

	public PGNParser(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			StringBuilder sb = new StringBuilder();
			String line = "";

			while (line.length() == 0 || !Character.isDigit(line.charAt(0))) {
				line = br.readLine();
				if (line.startsWith("[Event"))
					event = line.split("\"")[1];
				else if (line.startsWith("[FEN"))
					fen = line.split("\"")[1];
				else if (line.startsWith("[Result"))
					result = line.split("\"")[1];
			}

			while (line != null) {
				line = line.replace(";.*", "");

				sb.append(line + ' ');
				line = br.readLine();
			}

			game = sb.toString();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void parse() throws InvalidPGNMoveException {

		// suppression commentaires et variations
		game = game.replaceAll("\\{[^}]*\\}|\\([^)]*\\)","");
		// suppression des espaces et points en double
		game = game.replaceAll("\\s+", " ").replaceAll("\\.\\.+", ".");
		// insertion d'un espace après le numéro de coup s'il manque
		game = game.replaceAll("\\.([^ ])","\\. $1");
		// on ne garde que les coups, séparés par des espaces (plus de "e.p.")
		game = game.replaceFirst("\\d+\\.(\\.\\.)? ","").replaceAll(" ([^KQRNBa-hO][^ ]* )+"," ");

		moves = game.split(" ");

		String moveRegex =
				"([KQRNB][a-h]?[1-8]?x?[a-h][1-8]|"+
						"([a-h]x)?[a-h][1-8](=[QRBN])?|"+
						"O-O(-O)?)"+
						"[+#]?";

		for (String s : moves)
			if (!s.matches(moveRegex))
				throw new InvalidPGNMoveException(s);

	}

	public PGNGame makePgnGame() {
		return new PGNGame(fen, event, result, moves);
	}

	public static void main (String[] args) {


		try (Scanner sc = new Scanner(System.in)) {

			for (File f : new File("games").listFiles()) {
				System.out.println("------ "+f.getName()+" -------");

				PGNParser pgn = new PGNParser(f);
				pgn.parse();
				PGNGame game = pgn.makePgnGame();
				BoardTools.initBoard(EnumBoard.TheBoard, pgn.fen);
				System.out.println(EnumBoard.TheBoard);
				Move move;
				for (PGNMove pgnMove : game) {
					move = pgnMove.makeMove(EnumBoard.TheBoard);
					move.applyTo(EnumBoard.TheBoard);
					System.out.println(pgnMove);

					System.out.println(EnumBoard.TheBoard);
				}
				sc.nextLine();

				//				System.out.println(game);

			}

		} catch (InvalidPGNMoveException e) {
			e.printStackTrace();
		} catch (InvalidFenException e) {
			e.printStackTrace();
		}

	}


}
