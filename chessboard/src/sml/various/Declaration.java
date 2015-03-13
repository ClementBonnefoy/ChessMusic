package sml.various;

import sml.interfaces.IDeclarable;

public class Declaration {
	
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
	
	

}
