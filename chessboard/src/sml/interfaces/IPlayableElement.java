package sml.interfaces;

import sml.elements.Declarations;



/**
 * interface sml pour les expressions pouvant être jouée
 *
 */public interface IPlayableElement extends ISMLElement {
	 
	 /**
		 * retourne la durée en unité de temps
		 * @return
		 */
		public int getTime(Declarations environnement);

}
