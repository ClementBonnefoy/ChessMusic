package chesstube;

import java.util.Stack;

public class TimeManager {
	
	private int time=0;
	private int max=0;
	private Stack<Integer> stack=new Stack<Integer>();
	
	public int getTime(){
		return time;
	}
	
	public void incrTime(int incr){
		time+=incr;
		checkMax();
	}
	
	private void checkMax() {
		if(time>max)
			max=time;
		
	}

	public void saveTime(){
		stack.push(time);
	}
	
	public void reload(){
		time=stack.pop();
	}

	public void reloadMax() {
		time=max;
		
	}

}
