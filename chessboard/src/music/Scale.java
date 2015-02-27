package music;

import java.util.ArrayList;

public abstract class Scale {
	
	protected NoteName fundamental;
	protected ArrayList<Note> notes;
	
	public Scale(NoteName fundamental){
		this.fundamental=fundamental;
		this.notes=this.getAllNotes();
	}
	
	
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
	
	public boolean isInTheScale(Note note){
		return notes.contains(note);
	}
	
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

}
