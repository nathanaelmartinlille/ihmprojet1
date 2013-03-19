package implementation;

import implementation.RecuperationCouleurSemiAuto.OnFinishListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		panelCouleur.setLayout(new GridLayout(fenetrePrincipale.nbCouleurs, 2));
		panelGris = new JPanel();
		panelGris.setLayout(new GridLayout(fenetrePrincipale.nbCouleurs, 1));
		
		this.setLayout(new BorderLayout());
		listeBoutonsCouleur = new ArrayList<JButton>();
		listeBoutonsGris = new ArrayList<JButton>();
		listePanels = new ArrayList<PanelBouton>();
		
		for(int i =0; i<fenetrePrincipale.nbCouleurs; i++)
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
		this.setSize(TAILLE_BOUTON * fenetrePrincipale.nbCouleurs, TAILLE_BOUTON * 3);
	}
	
	@Override
	public Color genererGrisAPartirDeCouleur(Color couleur) {
		int red = couleur.getRed();
		int green =couleur.getGreen();
		int blue = couleur.getBlue();
		// 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		int newPixel = (int) (0.2125* red + 0.7154*green + 0.0721*blue);
		
		//Return back to original format
		return new Color(newPixel, newPixel, newPixel);
		
	}

	@Override
	public void colorerBouton(JButton boutonAColorer, Color couleurADefinir) {
		listeBoutonsCouleur.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setBackground(couleurADefinir);
		listeBoutonsCouleur.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setForeground(new Color(255 - couleurADefinir.getRed(), 255 - couleurADefinir.getGreen(), 255 - couleurADefinir.getBlue()));

		// On associe le bouton gris
		
		listeBoutonsGris.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setBackground(genererGrisAPartirDeCouleur(couleurADefinir));
		listeBoutonsGris.get(listeBoutonsCouleur.indexOf(boutonAColorer)).setForeground(new Color(255 - couleurADefinir.getRed(), 255 - couleurADefinir.getGreen(), 255 - couleurADefinir.getBlue()));
	}
	
	public void changerLabelCouleurs(int numeroBouton, Color couleur)
	{
		System.out.println("changer label couleur");
		PanelBouton t_panel = listePanels.get(numeroBouton);
		t_panel.valeurBleue.setText(""+couleur.getBlue());
		t_panel.valeurRouge.setText(""+couleur.getRed());
		t_panel.valeurVerte.setText(""+couleur.getGreen());
		t_panel.valeurHexa.setText(Integer.toHexString(couleur.getRGB() & 0x00FFFFFF));
	}

}
