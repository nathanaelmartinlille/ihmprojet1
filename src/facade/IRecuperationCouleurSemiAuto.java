package facade;

import java.awt.Color;
import java.util.List;

/**Interface qui permet de recuperer la couleur de façon semi - automatique.
 * Presence d'une pipette qui selectionne la couleur désirée à l'ecran
 * @author nath
 */
public interface IRecuperationCouleurSemiAuto {

	/**Methode qui retourne la couleur prélevée par la pipette.
	 * @return la couleur prélevé par la pipette
	 */
	public void preleverCouleur();
	
	/**Methode qui retourne la liste finale des couleurs choisie
	 * @return la liste finale des couleurs choisie par l'utilisateur
	 */
	public List<Color> envoyerListeCouleurChoisie();
	
	
	
}
