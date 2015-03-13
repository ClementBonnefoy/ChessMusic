package sml.various;

import sml.interfaces.IDeclarable;
import sml.interfaces.IInstrument;
import sml.interfaces.IMusicalElement;
import sml.interfaces.IPlayableElement;
import sml.interfaces.ITime;

public class Variable implements IDeclarable, IMusicalElement,
	IPlayableElement, IInstrument, ITime{
	
	private String name;
	
	public Variable(String name){
		super();
		this.name=name;
	}
	
	public IDeclarable getValue(Declarations env){
		return env.getValue(this);
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
	
	
	
	

}
