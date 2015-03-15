package sml.elements;

import sml.interfaces.IInstruction;

public class Tempo implements IInstruction {
	
	private int tempo;

	public Tempo(int tempo) {
		super();
		this.tempo = tempo;
	}

	@Override
	public int getTime(Declarations environnement) {
		return 0;
	}
	
	

}
