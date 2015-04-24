package sml.elements;

import sml.interfaces.IInstruction;
import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

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

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		instruction.accept(visitor);
		if(next!=null)
			next.accept(visitor);
		
	}
	
	@Override
	public String toString(){
		StringBuilder bd=new StringBuilder();
		
		bd.append(instruction.toString());
		if(next!=null){
			bd.append(";");
			bd.append(next.toString());
		}
		
		return bd.toString();
	}
	
	public boolean isATempoInstruction(){
		return instruction instanceof Tempo;
	}

	public IInstruction getInstruction() {
		return instruction;
	}

	public Body getNext() {
		return next;
	}

	public int size() {
		if(instruction instanceof Play)
			return 1+(next==null?0:next.size());
		return (next==null?0:next.size());
	}
	
	
	

}
