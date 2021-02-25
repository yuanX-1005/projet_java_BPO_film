package biblioth�queFilm;

import java.util.Arrays;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour obtenir un extrait d'un film. L'extrait est d�sign� 
 * par les num�ros de la premi�re et de la derni�re images � inclure et la 
 * premi�re image du film porte le num�ro 0. 
 */
public class ExtraitFilm implements Film{
	private Film film;
	private int premi�re,derni�re;
	private static int num = 0;
	
	
	public ExtraitFilm(Film film, int premi�re, int derni�re) {
		this.film = film;
		this.premi�re = premi�re;
		this.derni�re = derni�re;
	}

	/**
	 * Indique la hauteur des images de ce film (en nombre de caract�res).
	 * 
	 * @return Hauteur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return film.hauteur();
	}

	/**
	 * Indique la largeur des images de ce film (en nombre de caract�res).
	 * 
	 * @return largeur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return film.largeur();
	}

	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param �cran :  L'�cran o� afficher l'image          
	 * @return vrai Si l'image suivante a �t� affich�e sur l'�cran et faux si le
	 *         film est termin�
	 */
	@Override
	public boolean suivante(char[][] �cran) {
		if(premi�re < 0)premi�re = 0;
		for(;num < premi�re;++num) { // obtenir l'�cran avant de la premier images d�sire
			film.suivante(�cran);
			for (char[] ligne : �cran) //effacer l'�cran.
				Arrays.fill(ligne, ' ');
		}
		if(num > derni�re) { // si le num�ro des images superieur � le dernier images souhaire
			this.rembobiner();
			film.rembobiner(); //rembobiner le film
			return false;
		}
		else {
			++num;
			if(film.suivante(�cran)) return true; // sinon obtenir les images (entrer prmiere et derniere images souhaites)
			else {
				this.rembobiner(); // rembobiner le film
				film.rembobiner();
				return false;
			}
		} 	
		
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalit� (via
	 * des appels successifs � la m�thode suivante()).
	 */
	@Override
	public void rembobiner() {
		num = 0;
	}

}
