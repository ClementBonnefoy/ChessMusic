package sml.elements;

import sml.interfaces.IMusicalElement;
import sml.interfaces.IRole;
import sml.interfaces.ITime;
import sml.interfaces.IVisitor;

public class ComplexNote implements IMusicalElement {

	private IRole role;
	private Octave octave;
	private ITime time;
	private boolean start,end;

	public ComplexNote(IRole role, Octave octave, ITime time) {
		super();
		this.role = role;
		this.octave = octave;
		this.time = time;
		start=true;
		end=true;
	}

	public static ComplexNote parse(String c) {
		boolean s=false,e=false;

		if(c.startsWith("...")){
			s=true;
			c=c.substring(3);
		}
		if(c.endsWith("...")){
			e=true;
			c=c.substring(0, c.length()-3);
		}

		IRole r;
		
		{
			String l="";
			do{
				l=l+c.charAt(0);
				c=c.substring(1);
			}while(Character.isDigit(c.charAt(0)));

			Integer n=Integer.valueOf(l);
			r=Role.role(n);
		}

		char l;

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

		ComplexNote res;
		if(Character.isDigit(l)){
			res = new ComplexNote(r,o,new Time(Integer.valueOf(c)));
		}
		else{
			res = new ComplexNote(r,o,new Variable(c));
		}
		if(s)
			res.setStart(false);
		if(e)
			res.setEnd(false);
		return res;




	}

	private void setEnd(boolean b) {
		end=b;

	}

	private void setStart(boolean b) {
		start=b;

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

	public String toString(Declarations env) {
		return "ComplexNote [role=" + role.getRole() + ", octave=" + octave.getValue() + ", time="
				+ time.getTime(env) + "]";
	}

	public boolean shouldStart() {
		return start;
	}

	public boolean shouldEnd(){
		return end;
	}



}
