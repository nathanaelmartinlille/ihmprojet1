package facade;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

/**Interface qui permet de faire le rendu d'une image.
 * Composé d'un panel qui contiendra deux images, la premiere qui sera deposable et la deuxieme,
 * la representation graphique de la premiere mais en nuance de gris
 * @author nath
 */
public interface IRenduImage {

	/**Methode qui permet de generer une image en nuance de gris à partir des nuances de gris calculée par l'application.
	 * @param imageCouleur l'image en couleur
	 * @param nuanceGrisChoisi la liste des nuances de gris à determiner
	 * @return l'image en nuance de gris
	 */
	public Image genererImageNuanceGris(Image imageCouleur, List<Color> nuanceGrisChoisi);
	
	/**Recupere l'image qui a été deposé par l'utilisateur dans la zone de dépot.
	 * @return l'image qui a été deposée
	 */
	public Image recupererImageFromDrop();
	
}
