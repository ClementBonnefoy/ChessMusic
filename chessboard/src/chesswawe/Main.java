package chesswawe;

public class Main {

	public static void main(String[] args) {
		
		ChessWave cw = new ChessWave();
		
		cw.createMidiFromPGN("games/clement92_1.pgn", "clement92_1-chesswave.mid");
		
	}
	
}
