package sml.elements;

import sml.interfaces.ISMLElement;

public enum ScaleName implements ISMLElement{
	
	locrian,phrygian,aeolian,dorian,myxolydian,ionian,lydian;
	
	public static ScaleName scaleName(String name){
		return ScaleName.valueOf(name);
	}

}
