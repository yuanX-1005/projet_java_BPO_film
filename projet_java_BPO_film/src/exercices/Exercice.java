package exercices;

import static org.junit.Assert.assertNotNull;

import bibliothèqueFilm.Collage;
import bibliothèqueFilm.EncadrerFilm;
import bibliothèqueFilm.ExtraitFilm;
import bibliothèqueFilm.IncrusterFilm;
import bibliothèqueFilm.Repetition;
import film.Film;

public class Exercice {
	/*
	 * Dans chacune des 5 méthodes suivantes, une instruction doit être
	 * modifiée. Elles correspondent aux 5 opérations élémentaires du sujet.
	 * 
	 * Vous pouvez compléter les méthodes dans l'ordre que vous voulez. Vous
	 * pouvez exécuter les tests même si vous n'avez pas encore complété toutes
	 * les méthodes.
	 * 
	 */
	public static Film collage(Film f, Film g) {
		if (f == null || g == null)
			return null;
		// - Vous devez retourner le film correspondant au collage de g à la suite
		// de f.
		// - La taille des images du film résultant doit être suffisamment grande
		// pour contenir aussi bien celles de f que celles de g.
		Film c = new Collage(f,g); // à modifier
		
		assertNotNull("Vous devez compléter la méthode Exercice.collage", c);
		return c;
	}

	public static Film extrait(Film g, int d, int f) {
		if (g == null)
			return null;
		// - Vous devez retourner le film correspondant à l'extrait du film g,
		// c'est à dire incluant toutes les images de g portant les numéros
		// compris entre d et f (inclues). La première image porte le numéro 0.
		// - Seules les images portant des numéros légaux compris entre d et f
		// sont à considérer (les numéros inférieurs à zéro ou
		// supérieurs au nombre d'images de g doivent être ignorés).
		// - Si aucun des numéros compris entre d et f n'est légal, vous devez
		// retourner un film vide (mais dont la taille d'image est la même que
		// celle de g).
		Film e = new ExtraitFilm(g,d,f); // à modifier
		
		assertNotNull("Vous devez compléter la méthode Exercice.extrait", e);
		return e;
	}

	public static Film cadre(Film f) {
		if (f == null)
			return null;
		// - Vous devez retourner le film où chaque image de f est encadrée par
		// des caractères '*'.
		Film c = new EncadrerFilm(f); // à modifier
		
		assertNotNull("Vous devez compléter la méthode Exercice.cadre", c);
		return c;
	}

	public static Film cycle(Film f, int n) {
		if (f == null)
			return null;
		// - Vous devez retourner le film où f est répété n fois.
		// - Si n est inférieur ou égal à zéro, vous retournerez un film vide
		// (mais dont la taille d'image est la même que celle de f).
		Film c = new Repetition(f,n); // à modifier

		assertNotNull("Vous devez compléter la méthode Exercice.cycle", c);
		return c;
	}

	public static Film incrustation(Film f, Film g, int li, int co) {
		if (f == null || g == null)
			return null;
		// - Vous devez retourner le film où les images de g sont incrustées
		// dans celles de f à la position (li, co).
		// - Si g est composé de plus d'images que f, les images en trop sont
		// ignorées.
		// - Si f est composé de plus d'images que f, les dernières images de f
		// ne comportent pas d'incrustation.
		// - Si la taille des images de g est trop grande par rapport à la
		// taille des images de f, tout ce qui dépasse est tronqué.
		// Si li (ou co) est négatif, sa valeur est remplacée par zéro.
		Film i = new IncrusterFilm(g,f,li,co); // à modifier
		
		assertNotNull("Vous devez compléter la méthode Exercice.incrustation", i);
		return i;
	}
	
	private Exercice() {}
}
