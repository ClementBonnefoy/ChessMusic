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

	@Override
	public String toString() {
		return "Body [instruction=" + instruction + ", next=" + next + "]";
	}
	
	
	
	

}
