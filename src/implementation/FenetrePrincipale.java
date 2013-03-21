package implementation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import facade.IFenetrePrincipale;

public class FenetrePrincipale implements IFenetrePrincipale {

	List<Color> couleursChoisies;
	private RenduBoutonCouleur boutonsCouleur;
	private JFrame frameFenetrePrincipale;
	private JButton aleatoire;
	private int nbCouleurs = 3;
	
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
		int interval = 255 / this.getNbCouleurs();

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
		setBoutonsCouleur(new RenduBoutonCouleur(this));
		getFrameFenetrePrincipale().add(getBoutonsCouleur());
		
		aleatoire = new JButton("Couleurs aléatoires");
		aleatoire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("aleatoire");
				couleursChoisies.clear();
				Random rand = new Random();
				for(int i=0; i<getNbCouleurs(); i++)
				{
					Color randomColor;
					float r = rand.nextFloat();
					float g = rand.nextFloat();
					float b = rand.nextFloat();
					randomColor = new Color(r, g, b);
					while(!couleursChoisies.contains(randomColor)){
						r = rand.nextFloat();
						g = rand.nextFloat();
						b = rand.nextFloat();
						randomColor = new Color(r, g, b);
						couleursChoisies.add(randomColor);
					}
				}
				
				couleursChoisies = recalculerCouleur(couleursChoisies);

				for (int i = 0; i < getNbCouleurs(); i++) {
					getBoutonsCouleur().colorerBouton(getBoutonsCouleur().listeBoutonsCouleur.get(i), couleursChoisies.get(i));
					getBoutonsCouleur().changerLabelCouleurs(i, couleursChoisies.get(i));
				}
			}
		});
		getFrameFenetrePrincipale().add(aleatoire, BorderLayout.SOUTH);
		faireCouleursAleatoire();
		
		getFrameFenetrePrincipale().setVisible(true);
	}

	private void initialisationFenetre(){
		this.couleursChoisies = new ArrayList<Color>();
		setFrameFenetrePrincipale(new JFrame());
		getFrameFenetrePrincipale().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrameFenetrePrincipale().setSize(300, 500);
		getFrameFenetrePrincipale().setResizable(false);
		getFrameFenetrePrincipale().setMinimumSize(new Dimension(100,100));
		getFrameFenetrePrincipale().setLayout(new BorderLayout());
		JLabel phraseDebut = new JLabel("Bienvenue, choisissez vos couleurs");
		phraseDebut.setHorizontalAlignment(SwingConstants.CENTER);
		getFrameFenetrePrincipale().add(phraseDebut, BorderLayout.NORTH);
		/*JLabel explication = new JLabel("Pour commencer, veuillez choisir une couleur dans la liste de gauche");
		explication.setHorizontalAlignment(SwingConstants.CENTER);
		frameFenetrePrincipale.add(explication, BorderLayout.NORTH);*/
	}


	public void faireCouleursAleatoire()
	{
		Random rand = new Random();
		for(int i=0; i<getNbCouleurs(); i++)
		{
			Color randomColor;
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			randomColor = new Color(r, g, b);
			while(!couleursChoisies.contains(randomColor)){
				r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
				randomColor = new Color(r, g, b);
				couleursChoisies.add(randomColor);
			}
		}
		couleursChoisies = recalculerCouleur(couleursChoisies);

		for (int i = 0; i < getNbCouleurs(); i++) {
			getBoutonsCouleur().colorerBouton(getBoutonsCouleur().listeBoutonsCouleur.get(i), couleursChoisies.get(i)); 
			getBoutonsCouleur().changerLabelCouleurs(i, couleursChoisies.get(i));
		}
	}

	@Override
	public List<Color> recalculerCouleur(List<Color> listeCouleurOrigine) {
		List<Color> listeNuance = new ArrayList<Color>();
		
		verifierNuance(listeNuance);
		System.out.println("faire la methode de recalcul de la couleur");		
		return listeCouleurOrigine;
	}

	public static void main(String[] args) {
		FenetrePrincipale fen = new FenetrePrincipale();
		List<Color> nuanceGris = new ArrayList<Color>();
		nuanceGris.add(new Color(120, 120, 120));
		nuanceGris.add(new Color(20, 20, 20));
		nuanceGris.add(new Color(130, 130, 130));
		nuanceGris.add(new Color(100, 100, 100));
		fen.setNbCouleurs(4);
		fen.verifierNuance(nuanceGris);
	}


	public int getNbCouleurs() {
		return nbCouleurs;
	}


	public void setNbCouleurs(int nbCouleurs) {
		this.nbCouleurs = nbCouleurs;
	}


	public RenduBoutonCouleur getBoutonsCouleur() {
		return boutonsCouleur;
	}


	public void setBoutonsCouleur(RenduBoutonCouleur boutonsCouleur) {
		this.boutonsCouleur = boutonsCouleur;
	}


	public JFrame getFrameFenetrePrincipale() {
		return frameFenetrePrincipale;
	}


	public void setFrameFenetrePrincipale(JFrame frameFenetrePrincipale) {
		this.frameFenetrePrincipale = frameFenetrePrincipale;
	}
}