package bibliothèqueFilm;

import java.util.Arrays;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour obtenir un extrait d'un film. L'extrait est désigné 
 * par les numéros de la première et de la dernière images à inclure et la 
 * première image du film porte le numéro 0. 
 */
public class ExtraitFilm implements Film{
	private Film film;
	private int première,dernière;
	private static int num = 0;
	
	
	public ExtraitFilm(Film film, int première, int dernière) {
		this.film = film;
		this.première = première;
		this.dernière = dernière;
	}

	/**
	 * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return film.hauteur();
	}

	/**
	 * Indique la largeur des images de ce film (en nombre de caractères).
	 * 
	 * @return largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return film.largeur();
	}

	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param écran :  L'écran où afficher l'image          
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran) {
		if(première < 0)première = 0;
		for(;num < première;++num) { // obtenir l'écran avant de la premier images désire
			film.suivante(écran);
			for (char[] ligne : écran) //effacer l'écran.
				Arrays.fill(ligne, ' ');
		}
		if(num > dernière) { // si le numéro des images superieur à le dernier images souhaire
			this.rembobiner();
			film.rembobiner(); //rembobiner le film
			return false;
		}
		else {
			++num;
			if(film.suivante(écran)) return true; // sinon obtenir les images (entrer prmiere et derniere images souhaites)
			else {
				this.rembobiner(); // rembobiner le film
				film.rembobiner();
				return false;
			}
		} 	
		
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {
		num = 0;
	}

}
