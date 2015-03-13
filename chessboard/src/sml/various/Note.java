package sml.various;

public enum Note {

	A,AS,B,C,CS,D,DS,E,F,FS,G,GS; //en notation anglo-saxone, S=sharp
	
	public static Note note(String note){
		char n=note.charAt(0);
		switch(n){
		case 'a':
		case 'A':
			return A;
		case 'b':
		case 'B':
			return B;
		case 'c':
		case 'C':
			return C;
		case 'd':
		case 'D':
			return D;
		case 'e':
		case 'E':
			return E;
		case 'f':
		case 'F':
			return F;
		case 'g':
		case 'G':
			return G;
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


}


