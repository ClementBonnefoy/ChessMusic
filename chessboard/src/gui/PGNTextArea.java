package gui;

import static board.Color.Black;
import static board.Color.White;

import java.util.LinkedList;

import javax.swing.JTextArea;

import pgn.PGNMove;

public class PGNTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;

	private LinkedList<Integer> cursors = new LinkedList<>();
	
	public PGNTextArea() {
		super(27, 15);
		cursors.addFirst(0);
	}
	
	public void removeLastMove() {
		this.replaceRange("", cursors.pop(), getText().length());
	}
	
	public void addMove(PGNMove move, int moveNumber) {
		cursors.addFirst(getText().length());
		
		StringBuilder sb = new StringBuilder();
		if (move.getColor() == White)
			sb.append(moveNumber+". ");
		else if (getText().equals(""))
			sb.append(moveNumber+"... ");
		sb.append(move.toString());
		if (move.getColor() == Black)
			sb.append('\n');
		else
			sb.append(' ');
		
		append(sb.toString());
		
	}
	
}
