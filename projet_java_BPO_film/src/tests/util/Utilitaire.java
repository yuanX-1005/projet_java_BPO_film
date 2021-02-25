package tests.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import film.Film;
import film.Films;

import static org.junit.Assert.*;

public class Utilitaire {
	private static final String RESSOURCES_DIR = "ressources";

	// retourne le nombre d'images de f si celui ci est inf�rieur ou �gal � expected
	// et expected + 1 dans le cas contraire (pr�vention pour les films infinis...)
	public static int nbImages(Film f, int expected) {
		int i = 0;
		char[][] �cran = Films.getEcran(f);
		while (i <= expected && f.suivante(�cran))
			++i;
		return i;
	}

	public static void basicCheck(Film film, int hauteur, int largeur, int longueur, String nom) {
		assertEquals("La hauteur n'est pas correcte", hauteur, film.hauteur());
		assertEquals("La largeur n'est pas correcte", largeur, film.largeur());
		assertEquals("Le nombre d'images n'est pas correct", longueur, nbImages(film, longueur));
		film.rembobiner();
		assertEquals("Apr�s rembobinage, le nombre d'images n'est plus correct", longueur, nbImages(film, longueur));
		film.rembobiner();
		if (nom != null)
			assertEquals("Les images ne sont pas celles attendues", filesToString(RESSOURCES_DIR + "/" + nom), filmToString(film));
	}

	public static void checkEquality(Film f1, Film f2) {
		assertEquals(f1.hauteur(), f2.hauteur());
		assertEquals(f1.largeur(), f2.largeur());
		// les images des 2 films ne peuvent pas �tre parcourues en parall�le
		// dans tous les cas (m�me probl�me que pour l'incrustation)
		// on fait l'hypoth�se que f1 a un nombre fini d'images.
		f1.rembobiner();
		ArrayList<char[][]> images = new ArrayList<>();
		while (true) {
			char[][] �cran = Films.getEcran(f1);
			if (f1.suivante(�cran))
				images.add(�cran);
			else
				break;
		}
		f2.rembobiner();
		char[][] �cran = Films.getEcran(f2);
		for (char[][] image : images) {
			assertTrue(f2.suivante(�cran));
			checkEquality(image, �cran);
			Films.effacer(�cran);
		}
		assertFalse(f2.suivante(�cran));
	}

	public static void checkEquality(char[][] �cran1, char[][] �cran2) {
		assertEquals(�cran1.length, �cran2.length);
		for (int i = 0; i < �cran1.length; ++i)
			assertTrue(Arrays.equals(�cran1[i], �cran2[i]));
	}

	public static String filesToString(String ... filePathes) {
	    StringBuilder content = new StringBuilder();
	    try {
	    	for (String filePath : filePathes)
	    		content.append(new String(Files.readAllBytes( Paths.get(filePath))));
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    return content.toString();
	}
	
	public static String filmToString(Film f) {
		StringBuilder sb = new StringBuilder();
		f.rembobiner();
		char[][] �cran = Films.getEcran(f);
		while (f.suivante(�cran)) {
			sb.append(Films.toString(�cran));
			Films.effacer(�cran);
		}
		f.rembobiner();
		return sb.toString();
	}

	private Utilitaire() {}
	
}

