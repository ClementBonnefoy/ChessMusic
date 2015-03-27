package chesswawe;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import midi.MidiTools;
import board.ESquare;
import chesswawe.piece.Bishop;
import chesswawe.piece.ChessWavePiece;
import chesswawe.piece.King;
import chesswawe.piece.Knight;
import chesswawe.piece.Queen;
import chesswawe.piece.Rook;


public class ChessWaveMidi {
	
	private final static int TEMPO=500; //en bpm (beats per minute)

	private Sequence sequence;
	private Track pawnsTrack,knightsTrack,kingsTrack,queensTrack,rooksTrack,bishopsTrack;

	public ChessWaveMidi(){
	
		try {
			sequence=new Sequence(Sequence.PPQ,1,6); //6 because 6 tracks. 
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
		pawnsTrack=sequence.createTrack();
		rooksTrack=sequence.createTrack();
		queensTrack=sequence.createTrack();
		bishopsTrack=sequence.createTrack();
		kingsTrack=sequence.createTrack();
		knightsTrack=sequence.createTrack();
		
		setTempo();
		
		setInstruments();
		
	}

	




	public void addKey(int time,ChessWavePiece piece,ESquare c){

		Track t=null;

		switch(piece.getType()){
		case Pawn:
			t=pawnsTrack;
			break;
		case Knight:
			t=knightsTrack;
			break;
		case Bishop:
			t=bishopsTrack;
			break;
		case Rook:
			t=rooksTrack;
			break;
		case Queen:
			t=queensTrack;
			break;
		case King:
			t=kingsTrack;
			break;
		}

		t.add(MidiTools.createNoteOnEvent(piece.NoteOfRank(c.getRank()), time,piece.getChannel()));
		t.add(MidiTools.createNoteOffEvent(piece.NoteOfRank(c.getRank()), time+1,piece.getChannel()));

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
	
	private void setTempo(){
		MidiTools.setTempo(pawnsTrack, TEMPO, 0);
	}
	

	private void setInstruments() {
		MidiTools.setInstrument(bishopsTrack, Bishop.INSTRUMENT, Bishop.CHANNEL);
		MidiTools.setInstrument(rooksTrack, Rook.INSTRUMENT, Rook.CHANNEL);
		MidiTools.setInstrument(queensTrack, Queen.INSTRUMENT, Queen.CHANNEL);
		MidiTools.setInstrument(kingsTrack, King.INSTRUMENT, King.CHANNEL);
		MidiTools.setInstrument(knightsTrack, Knight.INSTRUMENT, Knight.CHANNEL);
		
	}
}
