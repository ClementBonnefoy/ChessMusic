package chesswawe;

public class Main {

	public static void main(String[] args) {
		
		ChessWave cw = new ChessWave();
		
		cw.createMidiFromPGN("games/Game1.pgn", "game1.mid");
		
	}
	
}
