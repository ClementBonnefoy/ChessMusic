package chesswave;

import java.util.ArrayList;

import board.Piece;

public class Step {
	ArrayList<StepElem> elems;
	
	public Step(ArrayList <StepElem> instanceElems) {
		this.elems = (ArrayList<StepElem>) instanceElems.clone();
		
	}
	
	
	public String toString () {
		return elems.toString();
		
	}
	
}
