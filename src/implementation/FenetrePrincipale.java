package implementation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import outil.CouleurUtils;
import outil.HistoriqueUtils;

import facade.IFenetrePrincipale;

public class FenetrePrincipale implements IFenetrePrincipale {

	List<Color> couleursChoisies;
	private RenduBoutonCouleur boutonsCouleur;
	private JFrame frameFenetrePrincipale;
	private JButton aleatoire, retour;
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
					System.out.println("rgb : "+Math.abs(listeNuance.get(i).getBlue() - listeNuance.get(j).getBlue()));
					if(Math.abs(listeNuance.get(i).getBlue() - listeNuance.get(j).getBlue()) < interval){
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Methode qui permet d'initialiser les composant de la fenetre
	 */
	private void initialisationComposantFenetre()
	{
		setBoutonsCouleur(new RenduBoutonCouleur(this));
		getFrameFenetrePrincipale().add(getBoutonsCouleur());
		initialisationBoutonSud();

		faireCouleursAleatoire();

		getFrameFenetrePrincipale().setVisible(true);
	}

	private void initialisationBoutonSud() {
		JPanel panelSud = new JPanel();
		retour = new JButton("Annuler le dernier choix");
		retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ancienneCouleur = HistoriqueUtils.lireHistorique().split("_");
				Color ancienneColor = new Color(Integer.parseInt(ancienneCouleur[1]),Integer.parseInt(ancienneCouleur[2]) , Integer.parseInt(ancienneCouleur[3]));
				boutonsCouleur.colorerBouton(boutonsCouleur.listeBoutonsCouleur.get(Integer.parseInt(ancienneCouleur[0])), ancienneColor);

			}
		});

		aleatoire = new JButton("Couleurs al�atoires");
		aleatoire.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		panelSud.setLayout(new GridLayout(1, 2));
		panelSud.add(aleatoire);
		panelSud.add(retour);
		getFrameFenetrePrincipale().add(panelSud, BorderLayout.SOUTH);
	}


	private void initialisationFenetre(){
		this.couleursChoisies = new ArrayList<Color>();
		setFrameFenetrePrincipale(new JFrame());
		getFrameFenetrePrincipale().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrameFenetrePrincipale().setSize(350, 500);
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
		List<Color> listeNuanceGris = new ArrayList<Color>();
		List<Color> nouvelleListeCouleur = new ArrayList<Color>();
		nouvelleListeCouleur = listeCouleurOrigine;
		int interval = 255 / this.getNbCouleurs();
		int milieu = interval/2;
		// generation des gris à partir de la liste de couleur
		for (int i = 0; i < listeCouleurOrigine.size(); i++) {
			listeNuanceGris.add(CouleurUtils.genererGrisAPartirDeCouleur(listeCouleurOrigine.get(i)));
		}

		System.out.println("liste des couleurs : "+nouvelleListeCouleur);
		System.out.println("liste des gris  : "+listeNuanceGris);
		System.out.println("faire la methode de recalcul de la couleur");

		List<Color> nouveauxGris = new ArrayList<Color>();
		List<Color> nouvellesCouleurs = new ArrayList<Color>();
		while(!verifierNuance(listeNuanceGris))
		{
			System.out.println("ce n'ets pas bon");
			for (int i = 1; i <= nbCouleurs; i++) {
				Color grisOptimal = new Color(i*milieu, i*milieu, i*milieu);
				Color grisPlusProche = listeNuanceGris.get(0);
				Color colorPlusProche = nouvelleListeCouleur.get(0);
				// On parcourt tous les gris
				for (int gris = 0; gris < listeNuanceGris.size(); gris++) {
					if(listeNuanceGris.get(gris).getBlue()<grisPlusProche.getBlue())
					{
						grisPlusProche = listeNuanceGris.get(gris);
						colorPlusProche = nouvelleListeCouleur.get(gris);
					}
				}


				// Couleurs possibles pour le gris optimal
				ArrayList<Color> couleursOkPourGris = new ArrayList<Color>();
				for (int couleur1 = 0; couleur1 < 256; couleur1++) {
					for (int couleur2 = 0; couleur2 < 256; couleur2++) {
						for (int couleur3 = 0; couleur3 < 256; couleur3++) {
							Color t_color = new Color(couleur1, couleur2, couleur3);
							// Si le gris de la couleur correspond au gris optimal
							if(CouleurUtils.genererGrisAPartirDeCouleur(t_color).getBlue() == grisOptimal.getBlue())
							{
								couleursOkPourGris.add(t_color);
							}
						}
					}
				}

				// On choisit la couleur la plus proche de celle d'avant
				Color couleurCommeAvant = couleursOkPourGris.get(0);
				System.out.println("color plus proche : "+colorPlusProche);
				for (int couleur = 0; couleur < couleursOkPourGris.size(); couleur++) {
					if(Math.abs(couleursOkPourGris.get(couleur).getRGB()-colorPlusProche.getRGB())< Math.abs(couleurCommeAvant.getRGB()-colorPlusProche.getRGB()))
					{
						couleurCommeAvant = couleursOkPourGris.get(couleur);
						//System.out.println("couleur comme avant : "+couleurCommeAvant);
					}
				}

				// On les met dans les nouvelles listes
				nouveauxGris.add(grisOptimal);
				nouvellesCouleurs.add(couleurCommeAvant);

				// On les supprimes des autres liste
				nouvellesCouleurs.remove(colorPlusProche);
				listeNuanceGris.remove(grisPlusProche);
			}
		}

		System.out.println("nouvelles couleurs : "+nouvellesCouleurs);
		return nouvellesCouleurs;
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new FenetrePrincipale();
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
