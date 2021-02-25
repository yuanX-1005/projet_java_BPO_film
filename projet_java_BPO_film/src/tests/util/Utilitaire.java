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

	// retourne le nombre d'images de f si celui ci est inférieur ou égal à expected
	// et expected + 1 dans le cas contraire (prévention pour les films infinis...)
	public static int nbImages(Film f, int expected) {
		int i = 0;
		char[][] écran = Films.getEcran(f);
		while (i <= expected && f.suivante(écran))
			++i;
		return i;
	}

	public static void basicCheck(Film film, int hauteur, int largeur, int longueur, String nom) {
		assertEquals("La hauteur n'est pas correcte", hauteur, film.hauteur());
		assertEquals("La largeur n'est pas correcte", largeur, film.largeur());
		assertEquals("Le nombre d'images n'est pas correct", longueur, nbImages(film, longueur));
		film.rembobiner();
		assertEquals("Après rembobinage, le nombre d'images n'est plus correct", longueur, nbImages(film, longueur));
		film.rembobiner();
		if (nom != null)
			assertEquals("Les images ne sont pas celles attendues", filesToString(RESSOURCES_DIR + "/" + nom), filmToString(film));
	}

	public static void checkEquality(Film f1, Film f2) {
		assertEquals(f1.hauteur(), f2.hauteur());
		assertEquals(f1.largeur(), f2.largeur());
		// les images des 2 films ne peuvent pas être parcourues en parallèle
		// dans tous les cas (même problème que pour l'incrustation)
		// on fait l'hypothèse que f1 a un nombre fini d'images.
		f1.rembobiner();
		ArrayList<char[][]> images = new ArrayList<>();
		while (true) {
			char[][] écran = Films.getEcran(f1);
			if (f1.suivante(écran))
				images.add(écran);
			else
				break;
		}
		f2.rembobiner();
		char[][] écran = Films.getEcran(f2);
		for (char[][] image : images) {
			assertTrue(f2.suivante(écran));
			checkEquality(image, écran);
			Films.effacer(écran);
		}
		assertFalse(f2.suivante(écran));
	}

	public static void checkEquality(char[][] écran1, char[][] écran2) {
		assertEquals(écran1.length, écran2.length);
		for (int i = 0; i < écran1.length; ++i)
			assertTrue(Arrays.equals(écran1[i], écran2[i]));
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
		char[][] écran = Films.getEcran(f);
		while (f.suivante(écran)) {
			sb.append(Films.toString(écran));
			Films.effacer(écran);
		}
		f.rembobiner();
		return sb.toString();
	}

	private Utilitaire() {}
	
}

