package bibliothèqueFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 23/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour repeter un film une nombre de fois donné.
 */
public class Repetition implements Film {
	private Film film;
	private int nbRep; // nombre de fois repeter de la film
	private static int num = 0;

	public Repetition(Film film, int nbRep) {
		this.film = film;
		this.nbRep = nbRep;
	}

	/**
	 * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return film.hauteur() ;
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
	 * @param écran :L'écran où afficher l'image       
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran) {
		/*if (nbRep <= 0) // repeter un film un nombre de fois negative ou vide 
			return false; // conduire à un film vide.*/
		if (nbRep > 0 && num != nbRep) {
			if(!film.suivante(écran)) { // si le film a déjà projecte une fois
				film.rembobiner();	// rebombiner le film
				num++;
				if(num < nbRep) { // tant il n'a pas finir d'afficher nbRep foix le film
					return film.suivante(écran);  // il continuer d'obtenir les images de film
				}
				else {
					rembobiner();
					return false;
				}
			}
			else return true;
		}
		else return false;
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
