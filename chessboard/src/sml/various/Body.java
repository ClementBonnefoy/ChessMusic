package sml.various;

import sml.interfaces.IInstruction;

public class Body {
	
	private IInstruction instruction;
	private Body next;
	
	public Body(IInstruction instruction, Body next) {
		super();
		this.instruction = instruction;
		this.next = next;
	}
	
	
	

}
