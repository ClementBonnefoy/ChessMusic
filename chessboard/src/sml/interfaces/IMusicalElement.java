package sml.interfaces;

import sml.elements.Declarations;



/**
 * interface sml pour les elements musicaux
 *
 */
public interface IMusicalElement extends IDeclarable{
	
	/**
	 * retourne la durée en unité de temps de l'élement. 
	 * @return
	 */
	public int getTime(Declarations environnement);

}
