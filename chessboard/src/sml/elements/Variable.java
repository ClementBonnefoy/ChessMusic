package sml.elements;

import sml.interfaces.IDeclarable;
import sml.interfaces.IInstrument;
import sml.interfaces.IMusicalElement;
import sml.interfaces.IPlayableElement;
import sml.interfaces.ITime;
import sml.interfaces.IVisitor;

public class Variable implements IDeclarable, IMusicalElement,
	IPlayableElement, IInstrument, ITime{
	
	private final String name;
	
	public Variable(String name){
		super();
		if(name=="b")
		System.out.println("OMG");
		this.name=name;
	}
	
	public IDeclarable getValue(Declarations environnement){
		return environnement.getValue(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "var("+name+")";
	}

	@Override
	public int getTime(Declarations environnement) {
		IDeclarable value=getValue(environnement);
		if(value instanceof ITime)
			return ((ITime)value).getTime(environnement);
		if(value instanceof IMusicalElement)
			return ((IMusicalElement)value).getTime(environnement);
		throw new RuntimeException("not a time mesurable value");
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	
	}
	
	
	
	

}
