package sml.elements;

import sml.interfaces.IDeclarable;
import sml.interfaces.ITime;

public class Time implements IDeclarable, ITime{
	
	private int time;
	
	public Time(int time){
		this.time=time;
	}

	@Override
	public int getTime(Declarations environnement) {
		return time;
	}
	
}
