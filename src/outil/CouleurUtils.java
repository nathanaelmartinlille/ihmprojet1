package outil;

import java.awt.Color;

public class CouleurUtils {
	public static Color genererGrisAPartirDeCouleur(Color couleur) {
		int red = couleur.getRed();
		int green =couleur.getGreen();
		int blue = couleur.getBlue();
		// 0.3   Rouge + 0.59   Vert + 0.11   Bleu
		int newPixel = (int) (0.2125* red + 0.7154*green + 0.0721*blue);
		
		//Return back to original format
		return new Color(newPixel, newPixel, newPixel);
		
	}
}
