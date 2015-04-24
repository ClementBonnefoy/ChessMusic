package chesstube;

import java.util.Stack;

public class TimeManager {
	
	private int time=0;
	private Stack<Integer> max=new Stack<Integer>();
	private Stack<Integer> stack=new Stack<Integer>();
	
	public int getTime(){
		return time;
	}
	
	public void incrTime(int incr){
		time+=incr;
		checkMax();
	}
	
	private void checkMax() {
		/*if(time>max)
			max=time;*/
		
	}

	public void saveTime(){
		stack.push(time);
	}
	
	public void reload(){
		max.push(time);
		time=stack.pop();
		
	}

	public void reloadMax() {
		int m=max.pop();
		if(time<m)
			time=m;
		
	}

}
