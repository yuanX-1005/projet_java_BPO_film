package tests;

import org.junit.Test;

import exercices.Exercice;
import film.Film;
import static tests.util.FabriqueAFilms.*;
import static tests.util.FabriqueAImages.*;

import java.util.Random;

import static tests.util.Utilitaire.*;

public class TestsComposition {

	@Test
	public void testCompositions() {
		Film f = imageToFilm(charToImage(2, 2, 'a'), 2);
		Film g = imageToFilm(charToImage(2, 2, 'b'), 2);
		Film h = imageToFilm(charToImage(2, 2, 'c'), 2);
		Film film;
		
		// on teste une à une les manipulations combinées avec elles même
		film = Exercice.collage(f, Exercice.collage(g, h));
		basicCheck(film, 2, 2, 6, "composition.txt");
		film = Exercice.collage(Exercice.collage(f, g), h);
		basicCheck(film, 2, 2, 6, "composition.txt");
		// la composition précédente est employée pour la manipulation suivante
		f = film;
		film = Exercice.extrait(Exercice.extrait(f, 1, 4), 1, 2);
		basicCheck(film, 2, 2, 2, "composition2.txt");
		// et encore
		f = film;
		film = Exercice.cadre(Exercice.cadre(f));
		basicCheck(film, 6, 6, 2, "composition3.txt");
		// et encore
		f = film;
		film = Exercice.cycle(Exercice.cycle(f, 2), 2);
		basicCheck(film, 6, 6, 8, "composition4.txt");
		// maintenant 'film' est un cycle de cycle de cadre de cadre d'extrait d'extrait de
		// collage de collage de 3 films...
		// et encore
		f = film;
		g = imageToFilm(charToImage('d'), 8);
		h = imageToFilm(charToImage(7, 7, 'e'), 8);
		film = Exercice.incrustation(h, Exercice.incrustation(f, g, 1, 1), 1, 1);
		basicCheck(film, 7, 7, 8, "composition5.txt");
	}

	private static Random rd = new Random();
	
	@Test
	public void testEquivalences() {
		// f = collage(extrait(f, 0, n), extrait(f, n + 1, nbImages(f)))
		String s = "equivalence";
		int largeur = 3;
		int n = rd.nextInt(s.length() + largeur); 
		Film f = bandeau(s, 3);
		Film e1 = Exercice.extrait(bandeau(s, largeur), 0, n);
		Film e2 = Exercice.extrait(bandeau(s, largeur), n + 1, s.length() + largeur);
		checkEquality(f, Exercice.collage(e1,  e2));
		
		// cycle(f, n) = collage(f, collage(f, ...))
		n = 2 + rd.nextInt(4); // 2 <= n <= 5 
		Film f1 = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));		
		Film film1 = Exercice.cycle(f1, n);
		Film f2 = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));		
		Film film2 = f2;
		for (int i = 1; i < n; ++i) { 
			f2 = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));
			film2 = Exercice.collage(film2, f2);
		}
		checkEquality(film1, film2);
		
		// cadre(f) = incrustation(filmCadre(f.hauteur() + 1, f.largeur() + 1), f, 1, 1)
		f1 = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));
		film1 = Exercice.cadre(f1);		
		f2 = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));
		film2 = Exercice.incrustation(imageToFilm(cadreToImage(f2.hauteur() + 2, f2.largeur() + 2), 4), f2, 1, 1);
		checkEquality(film1, film2);
	}
	
	@Test
	public void testRéférencesPartagées() {
		Film f = Exercice.collage(imageToFilm(charToImage('a'), 2), imageToFilm(charToImage('b'), 2));
		// un même film devrait pouvoir intervenir plusieurs fois dans un même collage 
		// (ce n'est pas le cas pour les incrustations (un film ne peut être
		// parcouru de 2 fois en parallèle sans surcoût rédhibitoire)
		Film film1 = Exercice.collage(f, Exercice.collage(f, f));
		Film film2 = Exercice.cycle(f, 3);
		checkEquality(film1, film2);
	}
}
