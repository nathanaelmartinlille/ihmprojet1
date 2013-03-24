package implementation;

import implementation.RecuperationCouleurSemiAuto.OnFinishListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import outil.HistoriqueUtils;

/**Ce panel permet de modeliser une couleur avec sa valeur qui va avec.
 * @author nath
 *
 */
public class PanelBouton extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;
	JTextField valeurRouge;
	JTextField valeurBleue;
	JTextField valeurVerte;
	JTextField valeurHexa;
	JLabel labelBleu;
	JLabel labelRouge;
	JLabel labelVert;
	JLabel labelHexa;
	RenduBoutonCouleur boutonCouleur;
	Boolean couleur;

	public PanelBouton(RenduBoutonCouleur boutonCouleur, int position, Boolean couleur) {
		this.boutonCouleur = boutonCouleur;
		this.couleur = couleur;
		init(position);
		if(couleur)
			initHandler();
	}
	public void init(int position){
		if(couleur){
			valeurBleue = new JTextField("000");
			labelBleu = new JLabel("B");
			valeurRouge = new JTextField("000");
			labelRouge = new JLabel("R");
			valeurVerte = new JTextField("000");
			labelVert = new JLabel("V");
			valeurHexa = new JTextField("000");
			labelHexa = new JLabel("Hexa");

			// init des drag and drop
			valeurBleue.setDragEnabled(true);
			valeurBleue.setTransferHandler(new TransfertHandlerPerso());
			valeurRouge.setDragEnabled(true);
			valeurRouge.setTransferHandler(new TransfertHandlerPerso());
			valeurVerte.setDragEnabled(true);
			valeurVerte.setTransferHandler(new TransfertHandlerPerso());
			valeurHexa.setDragEnabled(true);
			valeurHexa.setTransferHandler(new TransfertHandlerPerso());
		}
		button = new JButton();
		//button.setBounds(20, 20, 80, 80);
		button.setMinimumSize(new Dimension(80, 80));
		button.setPreferredSize(button.getMinimumSize());
		button.setVisible(true);
		button.setOpaque(true);
		if(couleur)
			this.setLayout(new GridLayout(1,1));
		else
			this.setLayout(new FlowLayout());
		this.add(button);

		JPanel valeurCouleurCourante; 
		valeurCouleurCourante = new JPanel();
		valeurCouleurCourante.setLayout(new GridLayout(4, 2));//RVB 4933

		if(couleur){
			valeurCouleurCourante.add(valeurRouge);
			valeurCouleurCourante.add(labelRouge);
			valeurCouleurCourante.add(valeurVerte);
			valeurCouleurCourante.add(labelVert);
			valeurCouleurCourante.add(valeurBleue);
			valeurCouleurCourante.add(labelBleu);
			valeurCouleurCourante.add(valeurHexa);
			valeurCouleurCourante.add(labelHexa);
		}
		this.add(valeurCouleurCourante);
	}


	private void initHandler() {
		initBoutonHandler();
		initValeurCouleurBoutonHandler();


	}

	private void initValeurCouleurBoutonHandler() {
		ajoutListener(valeurRouge);
		ajoutListener(valeurVerte);
		ajoutListener(valeurBleue);
		
	}

	private void ajoutListener(final JTextField valeurCouleurField) {
		valeurCouleurField.addKeyListener(new KeyListener() {
			int valeurCouleur;
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				Color nouvelleColor = new Color(Integer.parseInt(valeurRouge.getText()), Integer.parseInt(valeurVerte.getText()), Integer.parseInt(valeurBleue.getText()));
				/// Si la couleur a déjà été utilisée
				if(boutonCouleur.fenetrePrincipale.couleursChoisies.contains(nouvelleColor))
				{
					new JDialog(boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale(), "couleur déjà utilisée").setVisible(true);
				}
				else
				{
					miseAJourCouleurBouton(button, nouvelleColor);
				}
				boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale().repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				valeurCouleur = Integer.parseInt(valeurCouleurField.getText());
				if(e.getKeyCode() == KeyEvent.VK_UP){
					valeurCouleur++;
					valeurCouleurField.setText(valeurCouleur+"");
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					valeurCouleur--;
					valeurCouleurField.setText(valeurCouleur+"");
				}
			}
		});	
		valeurCouleurField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color nouvelleColor = new Color(Integer.parseInt(valeurRouge.getText()), Integer.parseInt(valeurVerte.getText()), Integer.parseInt(valeurBleue.getText()));
				/// Si la couleur a déjà été utilisée
				if(boutonCouleur.fenetrePrincipale.couleursChoisies.contains(nouvelleColor))
				{
					new JDialog(boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale(), "couleur déjà utilisée").setVisible(true);
				}
				else
				{
					miseAJourCouleurBouton(button, nouvelleColor);
				}
				boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale().repaint();
			}
		});
	}
	private void initBoutonHandler() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				final RecuperationCouleurSemiAuto recuperationCouleurSemiAuto = new RecuperationCouleurSemiAuto();
				recuperationCouleurSemiAuto.setOnFinishListener(new OnFinishListener() {
					@Override
					public void onFinish() {
						/// Si la couleur a déjà été utilisée
						if(boutonCouleur.fenetrePrincipale.couleursChoisies.contains(recuperationCouleurSemiAuto.couleurClique))
						{
							new JDialog(boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale(), "couleur déjà utilisée").setVisible(true);
						}
						else
						{
							JButton boutonClique = (JButton)e.getSource();
							miseAJourCouleurBouton(boutonClique, recuperationCouleurSemiAuto.couleurClique);
						}
						boutonCouleur.fenetrePrincipale.getFrameFenetrePrincipale().repaint();
					}


				});
				recuperationCouleurSemiAuto.updatePos(boutonCouleur.fenetrePrincipale.couleursChoisies);
			}
		});
	}

	private void miseAJourCouleurBouton(JButton boutonClique, Color nouvelleCouleur) {
		System.out.println("ajout dans l'historique la couleur qui a été choisie");
		// on enregistre sous le format index du bouton + R + G + B
		HistoriqueUtils.ecrireHistorique(boutonCouleur.listeBoutonsCouleur.indexOf(boutonClique) + "_"+ boutonClique.getBackground().getRed() + "_" + boutonClique.getBackground().getGreen() + "_" + boutonClique.getBackground().getBlue());
		boutonCouleur.fenetrePrincipale.couleursChoisies.add(nouvelleCouleur);
		boutonCouleur.colorerBouton(boutonClique, nouvelleCouleur);
		miseAjourValeurBouton(nouvelleCouleur);
	}

	private void miseAjourValeurBouton(Color couleurClique) {
		this.valeurBleue.setText(couleurClique.getBlue()+"");
		this.valeurRouge.setText(couleurClique.getRed()+"");
		this.valeurVerte.setText(couleurClique.getGreen()+"");
		this.valeurHexa.setText(Integer.toHexString(couleurClique.getRGB() & 0x00FFFFFF));
		boutonCouleur.fenetrePrincipale.recalculerCouleur(boutonCouleur.fenetrePrincipale.couleursChoisies);
	}
	public JButton getBouton() {
		return button;
	}

	public void setBouton(JButton bouton) {
		this.button = bouton;
	}


}

class TransfertHandlerPerso extends TransferHandler
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean canImport(TransferHandler.TransferSupport info) {
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}
		return true;
	}

	//FIXME ajouter des listener sur les textfield pour changer la couleur directement


	public boolean importData(TransferHandler.TransferSupport support){
		if(!canImport(support))
			return false;

		Transferable data = support.getTransferable();

		try {
			data.getTransferData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected void exportDone(JComponent c, Transferable t, int action){
	}

	protected Transferable createTransferable(JComponent c) {
		//On retourne un nouvel objet implémentant l'interface Transferable
		//StringSelection implémente cette interface,  nous l'utilisons donc
		return new StringSelection(((JTextField)c).getText());
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}   
}
