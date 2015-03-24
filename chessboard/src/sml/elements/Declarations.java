package sml.elements;

import sml.interfaces.IDeclarable;
import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

public class Declarations implements ISMLElement{
	
	private Declaration declaration;
	private Declarations next;
	
	public Declarations(Declaration declaration, Declarations next) {
		super();
		this.declaration = declaration;
		this.next = next;
	}

	public IDeclarable getValue(Variable variable) {
		if(declaration.getVariable().equals(variable)){
			return declaration.getValue();
		}
		if(next != null)
			return next.getValue(variable);
		throw new IllegalArgumentException("Error: undefined variable "+
			variable);
	}

	@Override
	public String toString() {
		StringBuilder b=new StringBuilder();
		b.append(declaration.getVariable());
		if(next!=null){
			b.append(",");
			b.append(next.toString());
		}
		return b.toString();
		
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		declaration.accept(visitor);
		if(next!=null)
			next.accept(visitor);
		
	}
	
	
	
	

}
