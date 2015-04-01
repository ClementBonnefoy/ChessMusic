package music;

public enum Instrument {
	
	piano(0),
	harpsichord(6),
	vibraphone(11),
	marimba(12),
	xylophone(13),
	electricBassFinger(33),
	electricBassPick(35),
	flute(73);
	
	
	private int midiNumber;
	
	private Instrument(int midiNumber){
		this.midiNumber=midiNumber;
	}
	
	public int getMidiNumber(){
		return midiNumber;
	}
	
	

}
