package facade;

import java.awt.Color;

import javax.swing.JButton;

/**Interface qui fait le rendu des boutons qui represente les couleurs chosie par l'utilisateur.
 * @author nath
 */
public interface IRenduBoutonCouleur {

	/**Colore le bouton mis en parametre.
	 * @param boutonAColorer le bouton à colorer
	 * @param couleurADefinir la couleur a set sur le bouton
	 */
	public void colorerBouton(JButton boutonAColorer, Color couleurADefinir);
	
}
