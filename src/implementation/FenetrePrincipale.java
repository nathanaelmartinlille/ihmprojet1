package implementation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import facade.IFenetrePrincipale;

public class FenetrePrincipale implements IFenetrePrincipale {

	List<Color> couleursChoisies;
	RenduBoutonCouleur boutonsCouleur;
	JFrame frameFenetrePrincipale;
	public int nbCouleurs = 3;
	/**
	 * Constructeur qui initialise les composants.
	 */
	public FenetrePrincipale() {
		demandeNbCouleur();
		// initialisation et ajout à la fenetre des composants graphiques
		initialisationFenetre();
		initialisationComposantFenetre();
	}


	private void demandeNbCouleur() {
		new DialogueChoixCouleur(this);
	}


	@Override
	public Boolean verifierNuance(List<Color> listeNuance) {
		int interval = 255 / this.nbCouleurs;

		for (int i = 0; i < listeNuance.size(); i++) {
			for (int j = 0; j < listeNuance.size(); j++) {
				if(i != j){
					// on regarde si le gris est trop proche d'un autre
					if(Math.abs(listeNuance.get(i).getBlue() - listeNuance.get(j).getBlue()) < interval){
						return false;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Methode qui permet d'initialiser les composant de la fenetre
	 */
	private void initialisationComposantFenetre()
	{
		boutonsCouleur = new RenduBoutonCouleur(this);
		frameFenetrePrincipale.add(boutonsCouleur);
		frameFenetrePrincipale.setVisible(true);
	}

	private void initialisationFenetre(){
		this.couleursChoisies = new ArrayList<Color>();
		frameFenetrePrincipale = new JFrame();
		frameFenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFenetrePrincipale.setSize(700, 700);
		frameFenetrePrincipale.setLayout(new BorderLayout());
		JLabel phraseDebut = new JLabel("Bienvenue, choisissez vos couleurs");
		phraseDebut.setHorizontalAlignment(SwingConstants.CENTER);
		frameFenetrePrincipale.add(phraseDebut, BorderLayout.NORTH);
	}




	@Override
	public List<Color> recalculerCouleur(List<Color> listeCouleurOrigine) {
		System.out.println("faire la methode de recalcul de la couleur");		
		return null;
	}

	public static void main(String[] args) {
		FenetrePrincipale fen = new FenetrePrincipale();
		List<Color> nuanceGris = new ArrayList<Color>();
		nuanceGris.add(new Color(120, 120, 120));
		nuanceGris.add(new Color(20, 20, 20));
		nuanceGris.add(new Color(130, 130, 130));
		nuanceGris.add(new Color(100, 100, 100));
		fen.nbCouleurs = 4;
		fen.verifierNuance(nuanceGris);
	}
}