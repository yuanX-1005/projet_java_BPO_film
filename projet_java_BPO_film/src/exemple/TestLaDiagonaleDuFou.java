package exemple;

import static org.junit.Assert.*;

import org.junit.Test;

import film.Film;
import film.Films;

public class TestLaDiagonaleDuFou {
	private static String image1 = 
			"a         " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator() +
			"          " + System.lineSeparator()
	;
	
	@Test
	public void test() {
		Film f = new LaDiagonaleDuFou();
		assertEquals(10, f.hauteur());
		assertEquals(10, f.largeur());
		char[][] écran = Films.getEcran(f);
		assertTrue(f.suivante(écran));
		System.out.println("'" + Films.toString(écran) + "'");
		System.out.println("'" + image1 + "'");
		assertEquals(image1, Films.toString(écran));
		f.rembobiner();
		assertTrue(f.suivante(écran));
		assertEquals(image1, Films.toString(écran));
	}
}
