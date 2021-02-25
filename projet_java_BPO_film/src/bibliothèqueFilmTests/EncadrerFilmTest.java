package biblioth�queFilmTests;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
 * 
 * Tester le classe EncadrerFilm
*/

import org.junit.jupiter.api.Test;

import biblioth�queFilm.EncadrerFilm;
import exemple.LaDiagonaleDuFou;
import film.Film;
import film.Films;


class EncadrerFilmTest {
	private char[][] �cran; // l'�cran o� afficher l'image
	
	//creer un film f1 qui a 20 images dont l'hauteur et la largeur sont 10
	Film f1 = new LaDiagonaleDuFou();
	// cr�er un film f3 qui a encadrer le film f1 par symbole *
	Film f3 = new EncadrerFilm(f1);
	
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
	 * Tester que les films f1 et f3 sont bien cr�es 
	 */
	@Test
	void testEncadrerFilm() {
		assertNotNull(f1);
		assertNotNull(f3);
	}

	/**
	 * Tester que le film f3 a deux case en plus de l'hauteur pour mettre les *
	 */
	@Test
	void testHauteur() {
		assertEquals (f1.hauteur() + 2,f3.hauteur());
	}

	/**
	 * Tester que le film f3 a deux case en plus de la largeur pour mettre les *
	 */
	@Test
	void testLargeur() {
		assertEquals (f1.largeur() + 2,f3.largeur());
	}

	/**
	 * Tester que le film encadre f3 a le m�me nombre des images que le film f1 
	 */	
	@Test
	void testSuivante() {
		// les images de num�ro 0 � 19 retourne vraie 
		for(int i = 0; i < 20; ++i) {
		assertEquals(EtatSuivant.OUI,getEtatSuivant(f3));
		}
		// la dernier image retourne faute
		assertEquals(EtatSuivant.NON,getEtatSuivant(f3));
	}

	//@Test
	void testRembobiner() {
	}

}
