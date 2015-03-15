package sml.elements;

import sml.interfaces.ISMLElement;

public class Music implements ISMLElement{
	
	private Declarations declarations;
	private Body body;
	
	public Music(Declarations declarations, Body body) {
		super();
		this.declarations = declarations;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Music [declarations=\n\t[" + declarations + "], \nbody=\n\t" + body + "]";
	}
	
	/**
	 * renvoit la durée totale du morceau en unité de temps
	 */
	public int getTime(){
		return body.getTime(declarations);
	}
	
	

}
