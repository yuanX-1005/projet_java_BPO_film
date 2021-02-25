package bibliothèqueFilmTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibliothèqueFilm.ExtraitFilm;
import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
 * 
 * Tester le classe ExtraitFilm
*/

class ExtraitFilmTest {
	private char[][] écran; // l'écran où afficher l'image
	
	//creer un film f1 qui a 20 images dont l'hauteur et la largeur sont 10
	Film f1 = new LaDiagonaleDuFou();
	// créer un film f3 qui est un extrait de film f1 (de 8eme à 11eme images)
	Film f3 = new ExtraitFilm(f1,8,11);
	
	/**
	 * Obtenir EtatSuivant d'un film
	 * 
	 * @param film : le film à verifie 
	 * @return EtatSuivant.OUI si l'image suivante a été affichée sur l'écran 
	 *		et EtatSuivant.NON si le film est terminé
	 */			
	private EtatSuivant getEtatSuivant(Film f) {
		écran = Films.getEcran(f);
		if(f.suivante(écran)) 
			return EtatSuivant.OUI;
		else 
			return EtatSuivant.NON; 
	}

	/**
	 * Tester que les films f1, et f3 sont bien crées 
	 */
	@Test
	void testExtraitFilm() {
		assertNotNull(f1);
		assertNotNull(f3);
	}

	/**
	 * Tester que l'hauteur de film f3 egale celle de film f1  
	 */
	@Test
	void testHauteur() {
		assertEquals (f3.hauteur(),10);
	}

	/**
	 * Tester que la largeur de film f3 egale celle de film f1  
	 */
	@Test
	void testLargeur() {
		assertEquals (f3.largeur(),10);
	}

	/**
	 * Tester que le film f3 a bien 4 images
	 */
	@Test
	void testSuivante() {
		// les images de numéro 7 à 10 retourne vraie 
		for(int i = 0; i < 4; ++i) {
		assertEquals(EtatSuivant.OUI,getEtatSuivant(f3));
		}
		// la dernier image retourne faute
		assertEquals(EtatSuivant.NON,getEtatSuivant(f3));		
	}

	//@Test
	void testRembobiner() {
		fail("Not yet implemented");
	}

}
