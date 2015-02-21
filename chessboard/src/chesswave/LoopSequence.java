package chesswave;

import java.util.ArrayList;

public class LoopSequence {
	ArrayList<Loop> loopSeq;

	public LoopSequence() {
		super();
		this.loopSeq = new ArrayList<Loop>();
	}
	
	public void add(Loop loop) {
		loopSeq.add(loop);
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Loop loop : loopSeq) {
			sb.append(loop.toString());
			sb.append("-------------\n");
		}
		
		return sb.toString();
	}
	
}
