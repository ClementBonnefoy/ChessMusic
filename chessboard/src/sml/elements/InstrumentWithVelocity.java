package sml.elements;

import sml.interfaces.IInstrument;
import sml.interfaces.IVisitor;

public class InstrumentWithVelocity implements IInstrument {
	
	private IInstrument instrument;
	private int velocity;
	
	public InstrumentWithVelocity(IInstrument instru,int velo) {
		this.setInstrument(instru);
		this.setVelocity(velo);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		instrument.accept(visitor);
		
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

}
