package chesswave;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import chesswave.piece.AbstractChessWavePiece;
import chesswave.piece.BasicChessWavePiece;

import board.Square;
import board.Piece;


public class ChessWaveMidi {

	private Sequence sequence;
	private Track realTrack;
	private Track pawnsTrack,knightsTrack,kingsTrack,queensTrack,rooksTrack,bishopTrack;

	public ChessWaveMidi(){
		try {
			sequence=new Sequence(Sequence.PPQ,1);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
//		pawnsTrack=sequence.createTrack();
//		rooksTrack=sequence.createTrack();
//		queensTrack=sequence.createTrack();
//		bishopTrack=sequence.createTrack();
//		kingsTrack=sequence.createTrack();
//		knightsTrack=sequence.createTrack();
		realTrack = sequence.createTrack();
	}

	public void addKey(int time,AbstractChessWavePiece piece,Square c){

//		if(piece.hasMoved()) //si une piece est a sa case initiale, on ne fait aucun son
//			return;

		Track t=realTrack;

//		switch(piece.getType()){
//		case Pawn:
//			t=pawnsTrack;
//			break;
//		case Knight:
//			t=knightsTrack;
//			break;
//		case Bishop:
//			t=bishopTrack;
//			break;
//		case Rook:
//			t=rooksTrack;
//			break;
//		case Queen:
//			t=queensTrack;
//			break;
//		case King:
//			t=kingsTrack;
//			break;
//		}

		t.add(MidiTools.createNoteOnEvent(piece.keyOfRank(c.getRank()), time));
		t.add(MidiTools.createNoteOffEvent(piece.keyOfRank(c.getRank()), time+1));

	}

	public void saveMidi(String fileName){
		File outputFile = new File(fileName);

		try
		{
			MidiSystem.write(sequence, 1, outputFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args){
		ChessWaveMidi cwm=new ChessWaveMidi();


		cwm.addKey(0,AbstractChessWavePiece.fromPiece(Piece.WhiteKing) , Square.A1);
		cwm.addKey(1,AbstractChessWavePiece.fromPiece(Piece.WhiteKing) , Square.A1);
		cwm.addKey(2,AbstractChessWavePiece.fromPiece(Piece.WhiteKing) , Square.A1);
		cwm.addKey(3,AbstractChessWavePiece.fromPiece(Piece.WhiteKing) , Square.A1);


		cwm.saveMidi("test3.mid");
	}
}
