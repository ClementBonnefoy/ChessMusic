package music;

public enum Instrument {
	
	drumKit(-1),
	piano(0),
	harpsichord(6),
	vibraphone(11),
	marimba(12),
	xylophone(13),
	organ(16),
	churchorgan(19),
	guitar(24),
	electricBassFinger(33),
	electricBassPick(34),
	fretLessBass(35),
	synthBass1(38),
	synthBass2(39),
	alto(41),
	cello(42),
	pizzicato(45),
	harp(46),
	string(48),
	orchestraHit(55),
	trumpet(56),
	frenchHorn(60),
	brassSection(61),
	oboe(68),
	clarinet(71),
	flute(73),
	pad1(88),
	cristal(98);
	
	private final static int DEFAULT_VELOCITY=64;
	
	
	private int midiNumber;
	private int velocity=DEFAULT_VELOCITY;
	
	private Instrument(int midiNumber){
		this.midiNumber=midiNumber;
	}
	
	public int getMidiNumber(){
		return midiNumber;
	}

	public void setVelocity(int velocity) {
		this.velocity=velocity;
	} 

	public int getVelocity() {
		return velocity;	
	}

	public void setDefaultVelocity() {
		setVelocity(DEFAULT_VELOCITY);
		
	}
	
	

}
