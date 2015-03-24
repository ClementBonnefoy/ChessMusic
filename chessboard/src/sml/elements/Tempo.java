package sml.elements;

import sml.interfaces.IInstruction;
import sml.interfaces.IVisitor;

public class Tempo implements IInstruction {
	
	private int tempo;

	public Tempo(int tempo) {
		super();
		this.setTempo(tempo);
	}

	@Override
	public int getTime(Declarations environnement) {
		return 0;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	@Override
	public String toString(){
		return "tempo "+tempo;
	}
	
	

}
