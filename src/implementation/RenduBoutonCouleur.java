package implementation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import outil.CouleurUtils;

import facade.IRenduBoutonCouleur;

public class RenduBoutonCouleur extends JPanel implements IRenduBoutonCouleur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_BOUTON = 50;
	FenetrePrincipale fenetrePrincipale;
	List<JButton> listeBoutonsCouleur;
	List<JButton> listeBoutonsGris;
	List<PanelBouton> listePanels;
	JPanel panelCouleur, panelGris;

	public RenduBoutonCouleur(FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale; 
		initComposant();
	}



	private void initComposant() {
		panelCouleur = new JPanel();
		panelCouleur.setLayout(new GridLayout(fenetrePrincipale.getNbCouleurs(), 2));
		panelGris = new JPanel();
		panelGris.setLayout(new GridLayout(fenetrePrincipale.getNbCouleurs(), 1));
		
		this.setLayout(new BorderLayout());
		listeBoutonsCouleur = new ArrayList<JButton>();
		listeBoutonsGris = new ArrayList<JButton>();
		listePanels = new ArrayList<PanelBouton>();
		
		for(int i =0; i<fenetrePrincipale.getNbCouleurs(); i++)
		{
			// on creer les panels de couleur ainsi que les panel pour les gris générés
			PanelBouton panelBoutonCouleur = new PanelBouton(this, i, true);
			PanelBouton panelBoutonGris = new PanelBouton(this, i, false);
			panelCouleur.add(panelBoutonCouleur);
			panelGris.add(panelBoutonGris);
			//panel.add(bouton);
			listeBoutonsCouleur.add(panelBoutonCouleur.getBouton());
			listeBoutonsGris.add(panelBoutonGris.getBouton());
			listePanels.add(panelBoutonCouleur);
		}
		this.add(panelCouleur, BorderLayout.WEST);
		this.add(panelGris, BorderLayout.EAST);
		this.setSize(TAILLE_BOUTON * fenetrePrincipale.getNbCouleurs(), TAILLE_BOUTON * 3);
	}
	//FIXME creer classe utilitaire pour generer le gris à partir d'une couleur

	@Override
	public void colorerBouton(JButton boutonAColorer, Color couleurADefinir) {
		listeBoutonsCouleur.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setBackground(couleurADefinir);
		listeBoutonsCouleur.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setForeground(new Color(255 - couleurADefinir.getRed(), 255 - couleurADefinir.getGreen(), 255 - couleurADefinir.getBlue()));

		// On associe le bouton gris
		
		listeBoutonsGris.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setBackground(CouleurUtils.genererGrisAPartirDeCouleur(couleurADefinir));
		listeBoutonsGris.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setForeground(new Color(255 - couleurADefinir.getRed(), 255 - couleurADefinir.getGreen(), 255 - couleurADefinir.getBlue()));
	}
	
	/**
	 * @param numeroBouton
	 * @param couleur
	 */
	public void changerLabelCouleurs(int numeroBouton, Color couleur)
	{
		PanelBouton t_panel = listePanels.get(numeroBouton);
		t_panel.valeurBleue.setText(""+couleur.getBlue());
		t_panel.valeurRouge.setText(""+couleur.getRed());
		t_panel.valeurVerte.setText(""+couleur.getGreen());
		t_panel.valeurHexa.setText(Integer.toHexString(couleur.getRGB() & 0x00FFFFFF));
	}

}
