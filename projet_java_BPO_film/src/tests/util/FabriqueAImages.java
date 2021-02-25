package tests.util;

import java.util.Arrays;

public final class FabriqueAImages {
	public static char[][] charToImage(int hauteur, int largeur, char c) {
		char[][] image = new char[hauteur][largeur];
		for (char[] ligne : image)
			Arrays.fill(ligne,  c);
		return image;
	}
		
	public static char[][] charToImage(char c) {
		return charToImage(1, 1, c);
	}

	public static char[][] stringsToImage(String ... strings) {
		char[][] image = new char[strings.length][];
		for (int i = 0; i < image.length; ++i)
			image[i] = strings[i].toCharArray();
		return image;
	}
	
	public static char[][] cadreToImage(int hauteur, int largeur) {
		char[][] image = new char[hauteur][largeur];
		Arrays.fill(image[0], '*');
		for (char[] ligne : image)
			ligne[0] = ligne[largeur - 1] = '*';
		Arrays.fill(image[hauteur - 1], '*');
		return image;
	}

	private FabriqueAImages() {}
}

