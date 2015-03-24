package tests;

import gui.ChessFrame;
import board.Board;

public class UITest {

	public static void main(String [] args) {
		Board b = new Board();
		b.init();
		new ChessFrame(b);
	}
	
}
