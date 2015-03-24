package sml.elements;

import sml.interfaces.IInstrument;
import sml.interfaces.IVisitor;

public class Ens implements IInstrument{
	
	
	private IInstrument instrument1;
	private IInstrument instrument2;
	
	
	public Ens(IInstrument instrument1, IInstrument instrument2) {
		super();
		this.instrument1 = instrument1;
		this.instrument2 = instrument2;
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		instrument1.accept(visitor);
		instrument2.accept(visitor);
		
	}

	public IInstrument getFirstInstrument() {
		return instrument1;
	}
	
	public IInstrument getSecondInstrument() {
		return instrument2;
	}

}
