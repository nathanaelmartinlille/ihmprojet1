package implementation;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import facade.IRecuperationCouleurSemiAuto;

public class RecuperationCouleurSemiAuto extends JPanel implements
		IRecuperationCouleurSemiAuto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean couleurDejaExistante(Color couleurChoisie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color preleverCouleur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Color> envoyerListeCouleurChoisie() {
		// TODO Auto-generated method stub
		return null;
	}
	
	PointerInfo pointer;
	Robot robot;
	Point coord;
	public Color couleurClique;
	boolean estClic;
	JFrame frameRecuperationCouleur;
	JTextArea texte;
	private OnFinishListener onFinishListener;

	// Couleurs
	private JLabel couleurRouge;
	private JLabel couleurVert;
	private JLabel couleurBleu;

	public RecuperationCouleurSemiAuto(){
		pointer = MouseInfo.getPointerInfo();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.out.println("erreur lors de l'initialisation du robot");
		}
		robot.delay(200);
		coord = pointer.getLocation();

		couleurClique = new Color(0);
	}

	/**
	 * Methode qui permet d'initialiser le panel pour choisir une couleur à l'ecran
	 */
	private void miseEnPlace()
	{
		estClic = false;
		//Vue de la couleur
		//JPanel vueCouleur = new JPanel();
		//vueCouleur.setSize(50, 50);
		//vueCouleur.setVisible(true);

		texte = new JTextArea();
		texte.setEditable(false);
		texte.setSize(50, 100);

		// RVB
		JPanel panelChoixCouleurRouge = new JPanel(new FlowLayout());
		JLabel rouge = new JLabel("Rouge : ");
		couleurRouge = new JLabel("coucou");
		panelChoixCouleurRouge.add(rouge, BorderLayout.NORTH);
		panelChoixCouleurRouge.add(couleurRouge, BorderLayout.NORTH);

		JPanel panelChoixCouleurVert = new JPanel(new  FlowLayout());
		JLabel vert = new JLabel("Vert : ");
		couleurVert = new JLabel("coucou");
		panelChoixCouleurVert.add(BorderLayout.NORTH, vert);
		panelChoixCouleurVert.add(BorderLayout.NORTH, couleurVert);

		JPanel panelChoixCouleurBleu = new JPanel(new FlowLayout());
		JLabel bleu = new JLabel("Bleu : ");
		couleurBleu = new JLabel("coucou");
		panelChoixCouleurBleu.add(BorderLayout.NORTH, bleu);
		panelChoixCouleurBleu.add(BorderLayout.NORTH, couleurBleu);

		// palette des couleurs pour le choix
				JPanel palette = new JPanel();
				JLabel image = new JLabel(new ImageIcon(getClass().getResource("/Images/pipette.png")));
				palette.add(image);
				palette.setSize(100, 40);
		
		frameRecuperationCouleur = new JFrame("Couleurs");
		frameRecuperationCouleur.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent key) {
				if(key.getKeyChar() == '\n'){
					estClic = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frameRecuperationCouleur.setLayout(new GridLayout(3,2));
				frameRecuperationCouleur.setSize(300, 300);


				//window.getContentPane().add(vueCouleur, BorderLayout.NORTH);
				frameRecuperationCouleur.add(texte, BorderLayout.WEST);
				frameRecuperationCouleur.add(panelChoixCouleurRouge, BorderLayout.EAST);
				frameRecuperationCouleur.add(panelChoixCouleurVert, BorderLayout.EAST);
				frameRecuperationCouleur.add(panelChoixCouleurBleu, BorderLayout.EAST);
				frameRecuperationCouleur.add(palette, BorderLayout.CENTER);
				frameRecuperationCouleur.setResizable(false);
				frameRecuperationCouleur.pack();
				frameRecuperationCouleur.setVisible(true);

				frameRecuperationCouleur.requestFocus();
	}

	public void updatePos(final List<Color> couleursUtilisees) //update mouse position. Identical to constructor
	{
		miseEnPlace();

		Thread t = new Thread(new Runnable() {
			Color color;
			@Override
			public void run() {
				while(!estClic) {
					//System.out.println("je ne suis pas clique");
					coord = MouseInfo.getPointerInfo().getLocation(); 
					color = robot.getPixelColor(coord.x, coord.y);

					couleurRouge.setText(""+color.getRed());
					couleurBleu.setText(""+color.getBlue());
					couleurVert.setText(""+color.getGreen());
					texte.setBackground(color);
					couleurClique = color;
					robot.delay(10);
					//window.repaint();
				}
				if(onFinishListener!=null)
					onFinishListener.onFinish();
				frameRecuperationCouleur.setVisible(false);
			}
		});

		t.start();
	}

	public Color getColor()
	{
		return couleurClique;
	}


	public void setOnFinishListener(OnFinishListener onFinishListener){
		this.onFinishListener=onFinishListener;
	}

	public interface OnFinishListener {
		public void onFinish();
	}

}
