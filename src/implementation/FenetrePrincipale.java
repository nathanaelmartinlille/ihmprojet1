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
		// TODO Auto-generated method stub
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
		new FenetrePrincipale();
	}
}