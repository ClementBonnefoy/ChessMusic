package chesswave;

import board.Piece;

public class StepElem {
	int pitch;
	Piece piece;
	int duree = 1;
	
	public StepElem(int pitch, Piece piece) {
		super();
		this.pitch = pitch;
		this.piece = piece;
	}
	
	public String toString() {
		return "("+pitch+","+piece+","+duree+")";
	}
	
}
