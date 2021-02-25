package bibliothèqueFilmTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibliothèqueFilm.Collage;
import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;

/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
 * 
 * Tester le classe Collage
*/

class CollageTest {
	private char[][] écran; // l'écran où afficher l'image
	
	//creer un film f1 qui a 20 images dont l'hauteur et la largeur sont 10
	Film f1 = new LaDiagonaleDuFou();
	// creer un autre film f2 qui a 30 images dont l'hauteur et la largeur sont 20 
	Film f2 = new UnAutreFilm();
	// créer un film f3 qui a collé les films f1 et f2
	Film f3 = new Collage(f1,f2);
	
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
	 * Tester que les films f1, f2 et f3 sont bien crées 
	 */
	@Test
	void testCollage() {
		assertNotNull(f1);
		assertNotNull(f2);
		assertNotNull(f3);
	}
	
	/**
	 * Tester que le film collé a une hauteur suffisamment grande 
	 */
	@Test
	void testHauteur() {
		// verifier que l'hauteur de film collée f3 est 20
		assertEquals (f2.hauteur(),f3.hauteur());
	}
	
	/**
	 * Tester que le film collé a une largeur suffisamment grande 
	 */	
	@Test
	void testLargeur() {
		// verifier que la largeur de film collée f3 est 20
		assertEquals (f2.largeur(),f3.largeur());		
	}
	
	/**
	 * Tester que le film collé a bien 50 images 
	 */	
	@Test
	void testSuivante() {
		// les images de numéro 0 à 49 retourne vraie 
		for(int i = 0; i < 50; ++i) {
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
