package sml.elements;

import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

public enum Note implements ISMLElement {

	a,as,b,c,cs,d,ds,e,f,fs,g,gs; //en notation anglo-saxone, S=sharp
	
	public static Note note(String note){
		char n=note.charAt(0);
		switch(n){
		case 'a':
		case 'A':
			return a;
		case 'b':
		case 'B':
			return b;
		case 'c':
		case 'C':
			return c;
		case 'd':
		case 'D':
			return d;
		case 'e':
		case 'E':
			return e;
		case 'f':
		case 'F':
			return f;
		case 'g':
		case 'G':
			return g;
		default:
			throw new IllegalArgumentException("The String"+note+" is not"
					+ " a valid letter for a note");
		}
	}

	public Note incr(int i) {
		return Note.values()[(this.ordinal()+i)%12];
	}

	public Note decr(int i) {
		return Note.values()[(this.ordinal()-i+12)%12];
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}


}


