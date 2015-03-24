package chesstube.music;

import java.util.ArrayList;

import sml.elements.ComplexNote;

public abstract class Scale {
	
	protected NoteName fundamental;
	@Deprecated
	protected ArrayList<Note> notes;
	
	public Scale(NoteName fundamental){
		this.fundamental=fundamental;
		this.notes=this.getAllNotes();
	}
	
	@Deprecated
	private ArrayList<Note> getAllNotes() {

		Note fund=new Note(fundamental,0);
		Note current=fund.prevOctave();
		ArrayList<Note> res=new ArrayList<Note>();
		res.add(current);
		while(fund.getMidiNumber()<109){ //108 = note MIDI max

			for (int i:this.getScale()){
				current=fund.incr(i);
				res.add(current);
			}

			fund=fund.nextOctave();

		}

		ArrayList<Note> filterRes=new ArrayList<Note>();
		
		for(Note n: res){
			if(n.getMidiNumber()>20 && n.getMidiNumber()<109)
				filterRes.add(n);
		}

		return filterRes;

	}
	
	@Deprecated
	public boolean isInTheScale(Note note){
		return notes.contains(note);
	}
	
	@Deprecated
	public Note[] getNNotes(Note starting_note,int n){
		
		Note [] res = new Note[n];
		boolean add=false;
		int j=0;
		
		for(int i=0;i<notes.size() && n>0;i++){
			Note current=notes.get(i);
			if(current.equals(starting_note)){
				add=true;
			}
			if(add){
				res[j++]=current;
				n--;
			}
			else if(current.getMidiNumber()>starting_note.getMidiNumber())
				throw new IllegalArgumentException("Error: "+starting_note+
						"is not in the scale");
			
		}
		return res;
		
		
	}
	
	protected abstract int [] getScale();


	public Note getNote(ComplexNote n) {
		int octave=n.getOctave().getValue();
		int role=n.getRole().getRole();
		int alteration=n.getRole().getAlteration();
		
		Note note=new Note(fundamental,0);
		while(role>7){
			role-=7;
			octave--;
		}
		note=note.incr(this.getScale()[role-1]);
		note=note.incr(alteration);
		while(octave>0){
			note=note.nextOctave();
			octave--;
		}
			
		return note;
		
		
	}

}
