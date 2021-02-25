package bibliothèqueFilmTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bibliothèqueFilm.Repetition;
import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;



/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
 * 
 * Tester le classe Repetition
*/

class RepetitionTest {
	private char[][] écran; // l'écran où afficher l'image
	
	//creer un film f1 qui a 20 images dont l'hauteur et la largeur sont 10
	Film f1 = new LaDiagonaleDuFou();
	// créer un film f3 qui est un film repeter 3 fois le film f1
	Film f3 = new Repetition(f1,3);
	
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
	 * Tester que les films f1 et f3 sont bien crées 
	 */
	@Test
	void testRepetition() {
		assertNotNull(f1);
		assertNotNull(f3);
	}

	/**
	 * Tester que le film f3 a la même hauteur que le film f1
	 */
	@Test
	void testHauteur() {
		assertEquals (f1.hauteur(),f3.hauteur());
	}

	/**
	 * Tester que le film f3 a la même largeur que le film f1
	 */
	@Test
	void testLargeur() {
		assertEquals (f1.largeur(),f3.largeur());
	}

	/**
	 * Tester que le film f3 a bien 60 images  
	 */	
	@Test
	void testSuivante() {
		// les images de numéro 0 à 59 retourne vraie 
		for(int i = 0; i < (3*20); ++i) {
		assertEquals(EtatSuivant.OUI,getEtatSuivant(f3));
		}
		// la dernier image retourne faute
		assertEquals(EtatSuivant.NON,getEtatSuivant(f3));
	}
	
	/**
	 * Tester que repeter le film une nombre de fois nulle retourne false  
	 */	
	@Test
	void testSuivante2() {
		Film f4 = new Repetition(f1,0);
		assertEquals(EtatSuivant.NON,getEtatSuivant(f4));
	}
	
	/**
	 * Tester que repeter le film une nombre de fois negative retourne false  
	 */	
	@Test
	void testSuivante3() {
		Film f5 = new Repetition(f1,-2);
		assertEquals(EtatSuivant.NON,getEtatSuivant(f5));
	}

	//@Test
	void testRembobiner() {
		fail("Not yet implemented");
	}

}
