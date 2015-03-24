package sml.elements;

import sml.interfaces.IDeclarable;
import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

public class Declaration implements ISMLElement{
	
	private Variable variable;
	private IDeclarable value;
	
	public Declaration(Variable variable, IDeclarable value) {
		super();
		this.variable=variable;
		this.value = value;
	}

	public IDeclarable getValue() {
		return value;
	}

	public Variable getVariable() {
		return variable;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		variable.accept(visitor);
		value.accept(visitor);
		
	}
	
	

}
