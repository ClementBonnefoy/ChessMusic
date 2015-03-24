package sml.elements;

import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

public enum ScaleName implements ISMLElement{
	
	locrian,phrygian,aeolian,dorian,myxolydian,ionian,lydian;
	
	public static ScaleName scaleName(String name){
		return ScaleName.valueOf(name);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}
	

}
