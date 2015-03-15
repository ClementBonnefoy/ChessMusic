package sml.elements;

import sml.interfaces.IInstruction;
import sml.interfaces.ISMLElement;

public class Body implements ISMLElement {
	
	private IInstruction instruction;
	private Body next;
	
	public Body(IInstruction instruction, Body next) {
		super();
		this.instruction = instruction;
		this.next = next;
	}

	public int getTime(Declarations environnement) {
		int res=instruction.getTime(environnement);
		res+=(next!=null)?next.getTime(environnement):0;
		return res;
	}
	
	
	

}
