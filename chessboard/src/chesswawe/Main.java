package chesswawe;

public class Main {

	public static void main(String[] args) {
		
		ChessWave cw = new ChessWave();
		
		cw.createMidiFromPGN("games/Fisher_Spassky_1992.pgn", "test.mid");
		
	}
	
}
