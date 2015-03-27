package midi;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * Classe outils pour le format midi
 *
 */
public class MidiTools {

	/**
	 * Track parameters
	 */
	private static final int VELOCITY = 64;
	
	/**
	 * Meta event types
	 */
	private static final int TEMPO = 0x51;
	
	
	
	/**
	 * modifie le tempo d'une track
	 * @param track : la track a modifier
	 * @param tempo : le tempo en BPM (beats per minute)
	 * @param time : l'instant ou a lieu ce changement 
	 */
	public static void setTempo(Track track,int tempo,int time){
		int tempoInMPQ = 60000000 / tempo;
		byte[] data = new byte[3];
		data[0] = (byte)((tempoInMPQ >> 16) & 0xFF);
		data[1] = (byte)((tempoInMPQ >> 8) & 0xFF);
		data[2] = (byte)(tempoInMPQ & 0xFF);
		addEvent(track, TEMPO, data, time);
	}

	


	private static MidiEvent createNoteEvent(int nCommand,
			int nKey,
			int nVelocity,
			long lTick,
			int channel)
	{
		ShortMessage	message = new ShortMessage();
		try
		{
			message.setMessage(nCommand,
					channel,
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



	public static MidiEvent createNoteOnEvent(int nKey, long lTick,int channel)
	{
		return createNoteEvent(ShortMessage.NOTE_ON,
				nKey,
				VELOCITY,
				lTick,
				channel);
	}



	public static MidiEvent createNoteOffEvent(int nKey, long lTick,int channel)
	{
		return createNoteEvent(ShortMessage.NOTE_OFF,
				nKey,
				0,
				lTick,
				channel);
	}
	
	public static void setInstrument(Track t, int instrument,int channel){
		ShortMessage sm = new ShortMessage( );
		try {
			sm.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //9 ==> is the channel 10.
		t.add(new MidiEvent(sm, 0));
	}
	
	private static void addEvent(Track track, int type, byte[] data, long tick)
	{
	    MetaMessage message = new MetaMessage();
	    try
	    {
	        message.setMessage(type, data, data.length);
	        MidiEvent event = new MidiEvent( message,tick);
	        track.add(event);
	    }
	    catch (InvalidMidiDataException e)
	    {
	        e.printStackTrace();
	    }
	}

}
