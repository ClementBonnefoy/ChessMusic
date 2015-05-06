package chesstube;

import java.util.Stack;

public class NoteStack {

	private Stack<Integer> notes;
	private Stack<Integer> times;
	
	public NoteStack(){
		notes=new Stack<Integer>();
		times=new Stack<Integer>();
	}
	
	public boolean isEmpty() {
		synchronized (this) {
			return notes.isEmpty();
		}
	}
	
	public void add(int note,int time){
		notes.add(note);
		times.add(time);
	}
	
	public int popNote(){
		return notes.pop();
	}
	
	public int popTime(){
		return times.pop();
	}
		
}
