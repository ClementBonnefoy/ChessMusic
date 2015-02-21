package chesswave;

import static board.Square.A1;
import static board.Square.A2;
import static board.Square.A3;
import static board.Square.A4;
import static board.Square.A5;
import static board.Square.A6;
import static board.Square.A7;
import static board.Square.A8;
import static board.Square.B1;
import static board.Square.B2;
import static board.Square.B3;
import static board.Square.B4;
import static board.Square.B5;
import static board.Square.B6;
import static board.Square.B7;
import static board.Square.B8;
import static board.Square.C1;
import static board.Square.C2;
import static board.Square.C3;
import static board.Square.C4;
import static board.Square.C5;
import static board.Square.C6;
import static board.Square.C7;
import static board.Square.C8;
import static board.Square.D1;
import static board.Square.D2;
import static board.Square.D3;
import static board.Square.D4;
import static board.Square.D5;
import static board.Square.D6;
import static board.Square.D7;
import static board.Square.D8;
import static board.Square.E1;
import static board.Square.E2;
import static board.Square.E3;
import static board.Square.E4;
import static board.Square.E5;
import static board.Square.E6;
import static board.Square.E7;
import static board.Square.E8;
import static board.Square.F1;
import static board.Square.F2;
import static board.Square.F3;
import static board.Square.F4;
import static board.Square.F5;
import static board.Square.F6;
import static board.Square.F7;
import static board.Square.F8;
import static board.Square.G1;
import static board.Square.G2;
import static board.Square.G3;
import static board.Square.G4;
import static board.Square.G5;
import static board.Square.G6;
import static board.Square.G7;
import static board.Square.G8;
import static board.Square.H1;
import static board.Square.H2;
import static board.Square.H3;
import static board.Square.H4;
import static board.Square.H5;
import static board.Square.H6;
import static board.Square.H7;
import static board.Square.H8;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.midi.MidiEvent;

import board.Board;
import board.InstanceBoard;
import board.Piece;
import board.Square;

public class Loop {
	private static Square[][] plage = 
		{ { A1 , A2 , A3 , A4 } ,	{ B1 , B2 , B3 , B4 } ,	{ C1 , C2 , C3 , C4 } ,	{ D1 , D2 , D3 , D4 } ,
		{ E1 , E2 , E3 , E4 } ,	{ F1 , F2 , F3 , F4 } ,	{ G1 , G2 , G3 , G4 } ,	{ H1 , H2 , H3 , H4 } ,
		{ A5 , A6 , A7 , A8 } ,	{ B5 , B6 , B7 , B8 } ,	{ C5 , C6 , C7 , C8 } ,	{ D5 , D6 , D7 , D8 } ,
		{ E5 , E6 , E7 , E8 } ,	{ F5 , F6 , F7 , F8 } ,	{ G5 , G6 , G7 , G8 } ,	{ H5 , H6 , H7 , H8 }
		};

	Step[] steps = new Step[plage.length];

	public Loop (Board board) {
		ArrayList<StepElem> alSteps;
		for (int i = 0; i < plage.length; i++) {
			alSteps = new ArrayList<>();
			for (int j = 0; j < 4; j++)
				if (board.getPiece(plage[i][j]) != null) {
					StepElem se = new StepElem(plage[i][j].getRank().getNum(),board.getPiece(plage[i][j]));
					alSteps.add(se);
				}
			steps[i] = new Step(alSteps);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Step step : steps) {
			sb.append(step.toString());
			sb.append('\n');
		}
		
		return sb.toString();
	}
	
//	
//	public void add(Square square, Piece piece) {
//		if (board.getPiece(square) != null) {
//			board.putOnSquare(p, c);
//		}
//		board.putOnSquare(piece, square);
//	}
//	
	
	
	
}
