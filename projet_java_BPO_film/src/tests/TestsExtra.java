package tests;

import static org.junit.Assert.*;
import static tests.util.FabriqueAFilms.*;
import static tests.util.FabriqueAImages.*;
import static tests.util.Utilitaire.*;

import java.util.Arrays;
import org.junit.Test;

import exercices.Exercice;
import film.Film;
import film.Films;

// L'interface Film permet la définition de film divers.
public class TestsExtra {	
	@Test
	public void testFilmAléatoire() {
		// Un film peut produire des images aléatoires.
		Film artifice = Exercice.extrait(artifice(6, 6), 0, 35);
		Film f1 = copieBrute(artifice);
		artifice.rembobiner();
		Film f2 = copieBrute(artifice);
		
		assertEquals(f1.largeur(), f2.largeur());
		assertEquals(f1.hauteur(), f2.hauteur());
		
		char[][] ecran1 = Films.getEcran(f1);
		char[][] ecran2 = Films.getEcran(f2);
		boolean diff = false;
		int n = 0;
		while(f1.suivante(ecran1)) {
			assertTrue(f2.suivante(ecran2));
			for (int i = 0; !diff && i < ecran1.length; ++i)
				diff = diff || !Arrays.equals(ecran1[i], ecran2[i]);
			++n;
		}
		assertEquals(36, n);
		assertFalse(f2.suivante(ecran2));
		assertTrue("les deux films doivent être distincts", diff);
	}

	@Test
	public void testFilmVariable() {
		// Un film peut produire des films déterministes mais étant différents
		// d'un rembobinage à l'autre.
		Film variable = Exercice.extrait(filmVariable(), 0, 2);
		Film f1 = copieBrute(variable);
		variable.rembobiner();
		Film f2 = copieBrute(variable);
		
		assertEquals(f1.largeur(), f2.largeur());
		assertEquals(f1.hauteur(), f2.hauteur());
		
		char[][] ecran1 = Films.getEcran(f1);
		char[][] ecran2 = Films.getEcran(f2);
		boolean diff = false;
		int n = 0;
		while(f1.suivante(ecran1)) {
			assertTrue(f2.suivante(ecran2));
			for (int i = 0; !diff && i < ecran1.length; ++i)
				diff = diff || !Arrays.equals(ecran1[i], ecran2[i]);
			++n;
		}
		assertEquals(3, n);
		assertFalse(f2.suivante(ecran2));
		assertTrue("les deux films doivent être distincts", diff);
	}

	@Test
	public void testFilmNeutre() {
		// Un film n'affecte pas nécessairement tous les pixels de l'écran.
		final int TAILLE = 2;
		final int NB_IMAGES = 10;
		final char MARQUEUR = 'x';

		Film neutre = filmNeutre(TAILLE, TAILLE, NB_IMAGES);
		Film f = Exercice.extrait(neutre, 0, NB_IMAGES - 1);
		// la taille
		assertEquals(TAILLE, f.hauteur());
		assertEquals(TAILLE, f.largeur());
		// le nombre d'images
		assertEquals(NB_IMAGES, nbImages(f, NB_IMAGES));
		// les images (et le nombre d'images après avoir rembobiné f)
		f.rembobiner();
		int n = 0;
		char[][] ecran = Films.getEcran(f);
		ecran[0][0] = MARQUEUR;
		while (f.suivante(ecran)) {
			assertEquals(MARQUEUR, ecran[0][0]);
			++n;
		}
		assertEquals(NB_IMAGES, n);		
	}
	
	@Test
	public void testFilmInfini() {
		// Un film peut avoir un nombre infini d'images.
		final char CONTENU = 'a';
		final int TAILLE = 2;
		final int NB_IMAGES = 10;

		Film inf = imageToFilmInfini(charToImage(TAILLE, TAILLE, CONTENU));
		Film f = Exercice.extrait(inf, 0, NB_IMAGES - 1);
		// la taille
		assertEquals(TAILLE, f.hauteur());
		assertEquals(TAILLE, f.largeur());
		// le nombre d'images
		assertEquals(NB_IMAGES, nbImages(f, NB_IMAGES));
		// les images (et le nombre d'images après avoir rembobiné f)
		f.rembobiner();
		char [] expectedLine = new char[TAILLE];
		Arrays.fill(expectedLine, CONTENU);
		int n = 0;
		char[][] ecran = Films.getEcran(f);
		while (f.suivante(ecran)) {
			for (char[] ligne : ecran)
				assertArrayEquals(expectedLine, ligne);
			Films.effacer(ecran);
			++n;
		}
		assertEquals(NB_IMAGES, n);
	}
}
