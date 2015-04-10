package tests;

import java.io.File;

import chesswawe.ChessWave;

public class ChessWaveTest {

	public static void main(String[] args){
		System.out.println("ChessWave Test ...");
		for (File f : new File("games").listFiles()) {
			try {

				System.out.println("******* "+f.getName()+" *********");

				ChessWave cw=new ChessWave();
				String midiName=f.getName().split("\\.")[0]+".mid";
				cw.createMidiFromPGN(f, midiName);

			} catch (Exception e) {
				/* do cleanup here -- possibly rethrow e */
				e.printStackTrace();
			}
		}
	}
}
