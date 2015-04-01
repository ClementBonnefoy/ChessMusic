package chesswawe;

public class Main {

	public static void main(String[] args) {
		
		ChessWave cw = new ChessWave();
		
		cw.createMidiFromPGN("games/modern.pgn", "modern-chesswave.mid");
		
	}
	
}
