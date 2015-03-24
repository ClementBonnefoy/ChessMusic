package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IRole;
import sml.interfaces.ITime;
import sml.interfaces.IVisitor;

public class ComplexNote implements IMusicalElement {
	
	private IRole role;
	private Octave octave;
	private ITime time;
	
	public ComplexNote(IRole role, Octave octave, ITime time) {
		super();
		this.role = role;
		this.octave = octave;
		this.time = time;
	}

	public static ComplexNote parse(String c) {
		char l=c.charAt(0);
		c=c.substring(1);
		Integer n=Integer.valueOf(""+l);
		IRole r=Role.role(n);
		do{
			l=c.charAt(0);
			c=c.substring(1);
			if(l=='+')
				r=new AlterableRole(r,true);
			else if(l=='-')
				r=new AlterableRole(r,false);
			else 
				break;	
			
		}while(true);
		
		Octave o=new Octave(l);
		
		l=c.charAt(0);
		
		if(Character.isDigit(l)){
			return new ComplexNote(r,o,new Time(Integer.valueOf(c)));
		}
		return new ComplexNote(r,o,new Variable(c));
			
		
		
		
	}

	@Override
	public int getTime(Declarations environnement) {
		return time.getTime(environnement);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		role.accept(visitor);
		octave.accept(visitor);
		time.accept(visitor);
		
	}

	public IRole getRole() {
		return role;
	}

	public Octave getOctave() {
		return octave;
	}

	public ITime getTime() {
		return time;
	}
	
	
	
	

}
