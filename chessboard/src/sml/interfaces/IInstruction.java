package sml.interfaces;

import sml.elements.Declarations;


/**
 * interface sml pour les instructions
 *
 */
public interface IInstruction extends ISMLElement{
	
	/**
	 * retourne la durée en unité de temps
	 * @return
	 */
	public int getTime(Declarations environnement);

}
