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
	RenduBoutonCouleur boutonsCouleur;
	JFrame frameFenetrePrincipale;
	JButton aleatoire;
	public int nbCouleurs = 3;
	/**
	 * Constructeur qui initialise les composants.
	 */
	public FenetrePrincipale() {
		demandeNbCouleur();
		// initialisation et ajout Ã  la fenetre des composants graphiques
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
		
		aleatoire = new JButton("Couleurs alŽatoires");
		aleatoire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("aleatoire");
				couleursChoisies.clear();
				Random rand = new Random();
				for(int i=0; i<nbCouleurs; i++)
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

				for (int i = 0; i < nbCouleurs; i++) {
					boutonsCouleur.colorerBouton(boutonsCouleur.listeBoutonsCouleur.get(i), couleursChoisies.get(i));
					boutonsCouleur.changerLabelCouleurs(i, couleursChoisies.get(i));
				}
			}
		});
		frameFenetrePrincipale.add(aleatoire, BorderLayout.SOUTH);
		faireCouleursAleatoire();
		
		frameFenetrePrincipale.setVisible(true);
	}

	private void initialisationFenetre(){
		this.couleursChoisies = new ArrayList<Color>();
		frameFenetrePrincipale = new JFrame();
		frameFenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFenetrePrincipale.setSize(300, 500);
		frameFenetrePrincipale.setResizable(false);
		frameFenetrePrincipale.setMinimumSize(new Dimension(100,100));
		frameFenetrePrincipale.setLayout(new BorderLayout());
		JLabel phraseDebut = new JLabel("Bienvenue, choisissez vos couleurs");
		phraseDebut.setHorizontalAlignment(SwingConstants.CENTER);
		frameFenetrePrincipale.add(phraseDebut, BorderLayout.NORTH);
		/*JLabel explication = new JLabel("Pour commencer, veuillez choisir une couleur dans la liste de gauche");
		explication.setHorizontalAlignment(SwingConstants.CENTER);
		frameFenetrePrincipale.add(explication, BorderLayout.NORTH);*/
	}


	public void faireCouleursAleatoire()
	{
		Random rand = new Random();
		for(int i=0; i<nbCouleurs; i++)
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

		for (int i = 0; i < nbCouleurs; i++) {
			boutonsCouleur.colorerBouton(boutonsCouleur.listeBoutonsCouleur.get(i), couleursChoisies.get(i)); 
			boutonsCouleur.changerLabelCouleurs(i, couleursChoisies.get(i));
		}
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