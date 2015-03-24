package sml.elements;

import sml.interfaces.ISMLElement;
import sml.interfaces.IVisitor;

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
		return "variables=\n\t" + declarations + "\nbody=\n\t" + body;
	}
	
	/**
	 * renvoit l'ensemble des variables du morceau
	 */
	public Declarations getEnvironnement(){
		return declarations;
	}
	
	/**
	 * renvoit la durée totale du morceau en unité de temps
	 */
	public int getTime(){
		return body.getTime(declarations);
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		declarations.accept(visitor);
		body.accept(visitor);
		
	}
	
	

}
