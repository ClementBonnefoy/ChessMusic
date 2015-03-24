package chesstube.music;

public class Note{

	private int octave; //octave de 0 a 8,ex: 4 pour le La 440
	private NoteName note;

	public Note(NoteName note,int octave){
		this.octave=octave;
		this.note=note;
	}

	public int getMidiNumber(){
		return 21+octave*12+note.getNumber();
	}

	public Note nextOctave() {
		return this.incr(12);
	}
	
	public Note prevOctave() {
		return this.decr(12);
	}

	public Note incr(int i) {
		int oct=octave;

		while(i>11){
			i-=12;
			oct++;
		}

		return new Note(note.incr(i),oct);
	}
	
	public Note decr(int i) {
		int oct=octave;

		while(i>11){
			i-=12;
			oct--;
		}

		return new Note(note.decr(i),oct);
	}
	
	@Override
	public String toString(){
		return note.toString()+octave;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + octave;
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (note != other.note)
			return false;
		if (octave != other.octave)
			return false;
		return true;
	}
	
	public static void main(String[] args){
		Note n=new Note(NoteName.C,3);
		System.out.println(n.incr(12));
		System.out.println(n.incr(1));
		System.out.println(n.incr(25));
	}

	


}
