package implementation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogueChoixCouleur extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private FenetrePrincipale fenetrePrincipale;
	private String nbCouleurs;
	private int nbCouleursInt;
	private JTextField texte;
	private String erreur;

	public DialogueChoixCouleur(FenetrePrincipale fenetrePrincipale)
	{
		this.fenetrePrincipale = fenetrePrincipale;
		creerDialog();
	}

	public void creerDialog()
	{
		this.setSize(300, 100);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.setLayout(new FlowLayout());

		//Cr�ation du label
		JLabel phrase = new JLabel("Bonjour, combien de couleurs voulez-vous ?");
		this.add(phrase);

		texte = new JTextField();
		texte.addActionListener(this);
		texte.setPreferredSize(new Dimension(150, 30));
		this.add(texte);	


		// Bouton valider
		JButton boutonValider = new JButton("Valider");
		this.add(boutonValider);
		boutonValider.addActionListener(this);
		fenetrePrincipale.nbCouleurs = this.nbCouleursInt;



		this.setModal(true);
		//dialog.setLocationRelativeTo(this.main);
		this.setVisible(true);
	}

	public boolean estCorrect()
	{
		erreur = "";
		if(!nbCouleurs.isEmpty()){
			try  {
				setNbCouleursInt(Integer.parseInt(nbCouleurs));
			}
			catch(NumberFormatException nfe)
			{
				erreur+="Vous devez entrer un nombre";
				return false;
			}

			if(getNbCouleursInt() > 10)
			{
				erreur += "Vous n'avez pas le droit d'entrer plus de 10 couleurs.";
				return false;
			}
			else if(getNbCouleursInt() < 1)
			{
				erreur = "Vous devez rentrer au moins une couleur.";
				return false;
			}
			return true;
		}
		erreur += "le champ est vide";
		return false;
	}
	
	public void afficherErreur(String erreur)
	{
		JDialog dialogue = new JDialog();
		dialogue.add(new JLabel(erreur));
		dialogue.setModal(true);
		dialogue.pack();
		dialogue.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.nbCouleurs = texte.getText();
		// pourquoi une classe pour une simple verification???
		if(estCorrect())
		{
			this.setVisible(false);
			fenetrePrincipale.nbCouleurs = this.nbCouleursInt;
		}
		else
		{
			// On affiche une erreur
			this.afficherErreur(erreur);
		}
	}

	public int getNbCouleursInt() {
		return nbCouleursInt;
	}

	public void setNbCouleursInt(int nbCouleursInt) {
		this.nbCouleursInt = nbCouleursInt;
	}




}
