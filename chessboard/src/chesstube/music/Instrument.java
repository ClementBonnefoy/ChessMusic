package chesstube.music;

public enum Instrument {
	
	piano(1),
	flute(73);
	
	
	private int midiNumber;
	
	private Instrument(int midiNumber){
		this.midiNumber=midiNumber;
	}
	
	public int getMidiNumber(){
		return midiNumber;
	}
	
	

}
