package tests;

import org.junit.Test;

import exercices.Exercice;
import film.Film;

import static tests.util.FabriqueAFilms.*;
import static tests.util.FabriqueAImages.*;
import static tests.util.Utilitaire.*;

public class TestsBasiques {
	@Test
	public void testCollage() {
		Film f;
		Film g;
		Film film;
		// classique
		f = imageToFilm(charToImage(2, 3, 'a'), 2);
		g = imageToFilm(charToImage(2, 3, 'b'), 3);
		film = Exercice.collage(f, g);
		basicCheck(film, 2, 3, 5, "collage.txt");

		// tailles différentes
		f = imageToFilm(charToImage(4, 3, 'a'), 2);
		g = imageToFilm(charToImage(2, 3, 'b'), 3);
		film = Exercice.collage(f, g);
		basicCheck(film, 4, 3, 5, "collage2.txt");

		f = imageToFilm(charToImage(2, 3, 'a'), 2);
		g = imageToFilm(charToImage(2, 4, 'b'), 1);
		film = Exercice.collage(f, g);
		basicCheck(film, 2, 4, 3, "collage3.txt");

		f = imageToFilm(charToImage(1, 1, 'a'), 2);
		g = imageToFilm(charToImage(2, 3, 'b'), 3);
		film = Exercice.collage(f, g);
		basicCheck(film, 2, 3, 5, "collage4.txt");
		
		// élément neutre
		f = filmVide(1,  6);
		g = bandeau("testons, testons", 6);
		film = Exercice.collage(f,  g);
		checkEquality(bandeau("testons, testons", 6), film);

		f = bandeau("vérifions, vérifions", 4);
		g = filmVide(1,  4);
		film = Exercice.collage(f,  g);
		checkEquality(bandeau("vérifions, vérifions", 4), film);

		f = filmVide(2, 3);
		g = filmVide(2,  3);
		film = Exercice.collage(f,  g);
		checkEquality(filmVide(2, 3), film);
	}

	@Test
	public void testExtrait() {
		Film f = imageToFilm(charToImage(2, 3, 'a'), 2);
		Film g = imageToFilm(charToImage(2, 3, 'b'), 2);
		Film h = copieBrute(f, g);
		
		// classique
		Film film;
		film = Exercice.extrait(h, 1, 2);
		basicCheck(film, 2, 3, 2, "extrait.txt");

		// numéros d'images hors borne
		film = Exercice.extrait(h, -1, 10);
		basicCheck(film, 2, 3, 4, "extrait2.txt");

		film = Exercice.extrait(f, -1, 0);
		basicCheck(film, 2, 3, 1, "extrait3.txt");

		film = Exercice.extrait(g, 2, 2);
		checkEquality(film, filmVide(2, 3));

		film = Exercice.extrait(g, 0, -1);
		checkEquality(film, filmVide(2, 3));
		
		// élément neutre
		film = Exercice.extrait(filmVide(2, 3), 0, 0);
		checkEquality(film, filmVide(2, 3));
}

	@Test
	public void testCadre() {
		Film f = imageToFilm(charToImage(2, 3, 'a'), 2);
		Film g = imageToFilm(charToImage(2, 3, 'b'), 2);
		Film h = copieBrute(f, g);
		
		// classique
		Film film;
		film = Exercice.cadre(h);
		basicCheck(film, 4, 5, 4, "cadre.txt");

		film = Exercice.cadre(imageToFilm(charToImage('a'), 1));
		basicCheck(film, 3, 3, 1, "cadre2.txt");

		// élément neutre
		film = Exercice.cadre(filmVide(10, 15));
		checkEquality(film, filmVide(12, 17));
	}

	@Test
	public void testCycle() {
		Film f = imageToFilm(charToImage(2, 3, 'a'), 2);
		Film g = imageToFilm(charToImage(2, 3, 'b'), 2);
		Film h = copieBrute(f, g);
		
		// classique
		Film film; 
		film = Exercice.cycle(h, 2);
		basicCheck(film, 2, 3, 8, "cycle.txt");

		// nombre de répétitions exotique 
		film = Exercice.cycle(f, 1);
		basicCheck(film, 2, 3, 2, "cycle2.txt");

		film = Exercice.cycle(f, 0);
		checkEquality(film, filmVide(2, 3));

		film = Exercice.cycle(g, -2);
		checkEquality(film, filmVide(2, 3));
		
		// élément neutre
		film = Exercice.cycle(filmVide(2, 3), 2);
		checkEquality(film, filmVide(2, 3));
	}

	@Test
	public void testIncrustation() {
		Film f, g, h, i, j, k;
		Film film;
		// classique
		f = imageToFilm(charToImage(3, 5, 'a'), 2);
		g = imageToFilm(charToImage(3, 5, 'b'), 2);
		h = copieBrute(f, g);
		i = imageToFilm(charToImage(1, 3, 'b'), 2);
		j = imageToFilm(charToImage(1, 3, 'a'), 2);
		k = copieBrute(i, j);

		film = Exercice.incrustation(h, k, 1, 1);
		basicCheck(film, 3, 5, 4, "incrustation.txt");

		// débordement
		film = Exercice.incrustation(h, k, 1, 3);
		basicCheck(film, 3, 5, 4, "incrustation2.txt");

		i = imageToFilm(charToImage(4, 3, 'b'), 2);
		j = imageToFilm(charToImage(4, 3, 'a'), 2);
		k = copieBrute(i, j);
		film = Exercice.incrustation(h, k, 1, 1);
		basicCheck(film, 3, 5, 4, "incrustation3.txt");
		
		// longueurs différentes
		i = imageToFilm(charToImage(1, 3, 'b'), 2);
		j = imageToFilm(charToImage(1, 3, 'a'), 3);
		k = copieBrute(i, j);
		film = Exercice.incrustation(h, k, 1, 1);
		basicCheck(film, 3, 5, 4, "incrustation.txt");

		f = imageToFilm(charToImage(3, 5, 'a'), 1);
		g = imageToFilm(charToImage(3, 5, 'b'), 1);
		h = copieBrute(f, g);
		i = imageToFilm(charToImage(1, 3, 'c'), 2);
		j = imageToFilm(charToImage(1, 3, 'd'), 1);
		k = copieBrute(i, j);
		film = Exercice.incrustation(h, k, 1, 1);
		basicCheck(film, 3, 5, 2, "incrustation4.txt");
		
		// position d'incrustation hors cadre
		f = imageToFilm(charToImage(3, 5, 'a'), 2);
		i = imageToFilm(charToImage('b'), 2);
		film = Exercice.incrustation(f, i, -1, -1);
		basicCheck(film, 3, 5, 2, "incrustation5.txt");
		
		// élément neutre
		f = imageToFilm(charToImage(3, 5, 'a'), 2);
		i = filmVide(1, 1);
		film = Exercice.incrustation(f, i, 1, 1);
		checkEquality(film, imageToFilm(charToImage(3, 5, 'a'), 2));
		
		f = filmVide(10, 10);
		i = imageToFilm(charToImage(3, 5, 'a'), 2);
		film = Exercice.incrustation(f, i, 1, 1);
		checkEquality(film, filmVide(10, 10));
		
		f = filmVide(10, 10);
		i = filmVide(3, 3);
		film = Exercice.incrustation(f, i, 1, 1);
		checkEquality(film, filmVide(10, 10));
	}
}

