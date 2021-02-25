package biblioth�queFilmTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import biblioth�queFilm.IncrusterFilm;
import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;


/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
 * 
 * Tester le classe  IncrusterFilm
*/

class IncrusterFilmTest {
	private char[][] �cran; // l'�cran o� afficher l'image
	
	//creer un film f1 qui a 20 images dont l'hauteur et la largeur sont 10
	Film f1 = new LaDiagonaleDuFou();
	// creer un autre film f2 qui a 30 images dont l'hauteur et la largeur sont 20 
	Film f2 = new UnAutreFilm();
	// cr�er un film f3 qui incruster le film f1 dans le film f2 dans le coordonn�e (3,2) 
	Film f3 = new IncrusterFilm(f1,f2,3,2);
	
	
	/**
	 * Obtenir EtatSuivant d'un film
	 * 
	 * @param film : le film � verifie 
	 * @return EtatSuivant.OUI si l'image suivante a �t� affich�e sur l'�cran 
	 *		et EtatSuivant.NON si le film est termin�
	 */			
	private EtatSuivant getEtatSuivant(Film f) {
		�cran = Films.getEcran(f);
		if(f.suivante(�cran)) 
			return EtatSuivant.OUI;
		else 
			return EtatSuivant.NON; 
	}

	/**
	 * Tester que les films f1, f2, f3 et f4 sont bien cr�es 
	 */
	@Test
	void testIncrusterFilm() {
		assertNotNull(f1);
		assertNotNull(f2);
		assertNotNull(f3);
		//assertNotNull(f4);
	}

	/**
	 * Tester que le film f3 a une hauteur egale celle de la film f2
	 */
	@Test
	void testHauteur() {
		assertEquals(f2.hauteur(),f3.hauteur());
		//assertEquals(f1.hauteur(),f4.hauteur());
	}

	/**
	 * Tester que le film f3 a une largeur egale celle de la film f2
	 */
	@Test
	void testLargeur() {
		assertEquals(f2.largeur(),f3.largeur());
		//assertEquals(f1.largeur(),f4.largeur());
	}

	/**
	 * Tester que le film f3 a m�me nombre de images que celle de la film f2 
	 */	
	@Test
	void testSuivante() {
		// les images de num�ro 0 � 29 retourne vraie 
		for(int i = 0; i < 30; ++i) {
		assertEquals(EtatSuivant.OUI,getEtatSuivant(f3));
		}
		// la dernier image retourne faute
		assertEquals(EtatSuivant.NON,getEtatSuivant(f3));
		
		
	}
	
	/**
	 * Tester que le film f4 a m�me nombre de images que celle de la film f1 
	 */	
	@Test
	void testSuivante2() {
		Film f4 = new IncrusterFilm(f2,f1,3,2);
		// les images de num�ro 0 � 29 retourne vraie 
		for(int j = 0; j < 20; ++j) {
		assertEquals(EtatSuivant.OUI,getEtatSuivant(f4));
		}
		// la dernier image retourne faute
		assertEquals(EtatSuivant.NON,getEtatSuivant(f4));		
	}

	//@Test
	void testRembobiner() {
		fail("Not yet implemented");
	}

}
