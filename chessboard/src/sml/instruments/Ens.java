package sml.instruments;

import sml.interfaces.IInstrument;

public class Ens implements IInstrument{
	
	
	private IInstrument instrument1;
	private IInstrument instrument2
	;
	public Ens(IInstrument instrument1, IInstrument instrument2) {
		super();
		this.instrument1 = instrument1;
		this.instrument2 = instrument2;
	}

}
