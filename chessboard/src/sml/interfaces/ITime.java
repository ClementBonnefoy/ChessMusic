package sml.interfaces;

import sml.elements.Declarations;

/**
 * interface pour les rythmes en sml
 *
 */
public interface ITime extends IDeclarable {
	
	/**
	 * retourne la durée en unité de temps
	 * @return
	 */
	public int getTime(Declarations environnement);

}
