package sml.elements;

public enum ScaleName {
	
	locrian,phrygian,aeolian,dorian,myxolydian,ionian,lydian;
	
	public static ScaleName scaleName(String name){
		return ScaleName.valueOf(name);
	}

}
