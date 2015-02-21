package chesswave;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;


public class MidiTools {
	
	private static final int	VELOCITY = 64;

	public static MidiEvent createNoteOnEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_ON,
				nKey,
				VELOCITY,
				lTick);
	}



	public static MidiEvent createNoteOffEvent(int nKey, long lTick)
	{
		return createNoteEvent(ShortMessage.NOTE_OFF,
				nKey,
				0,
				lTick);
	}

	private static MidiEvent createNoteEvent(int nCommand,
			int nKey,
			int nVelocity,
			long lTick)
	{
		ShortMessage	message = new ShortMessage();
		try
		{
			message.setMessage(nCommand,
					0,	// always on channel 1
					nKey,
					nVelocity);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		MidiEvent	event = new MidiEvent(message,
				lTick);
		return event;
	}

}
