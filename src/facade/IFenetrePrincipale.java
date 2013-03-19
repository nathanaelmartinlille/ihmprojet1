package facade;

import java.awt.Color;
import java.util.List;


/**La fenetre principale permet de placer les composants dans la fenetre.
 * On retrouvera les differents panel qui s'y rattache tel que les selection de couleur, 
 * la surface de drop de l'image à analyser ainsi que les rendus des gris générés
 * @author nath
 */
public interface IFenetrePrincipale {

	/**Methode qui calcule la bonne visibilité des nuances de gris.
	 * @param listeNuance la liste des nuances de gris des couleurs
	 * @return true si les nuances sont assez espacée
	 * false sinon
	 */
	public Boolean verifierNuance(List<Color> listeNuance);
	
	/**Methode qui calcule les couleurs optimales pour faire des gris distinct.
	 * @param listeCouleurOrigine
	 * @return la liste des couleurs misent à jour
	 */
	public List<Color> recalculerCouleur(List<Color> listeCouleurOrigine);
	
	
}
