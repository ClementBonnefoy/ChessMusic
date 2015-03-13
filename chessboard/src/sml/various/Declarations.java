package sml.various;

import sml.interfaces.IDeclarable;

public class Declarations {
	
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
			variable.getName());
	}
	
	

}
