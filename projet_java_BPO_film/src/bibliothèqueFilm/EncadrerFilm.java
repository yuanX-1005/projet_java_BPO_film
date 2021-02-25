package biblioth�queFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour encadrer un film (par quatre ligne d'�toiles � '*' � au  
 * bord de l'�cran).
 */
public class EncadrerFilm implements Film {
	private Film film;
	private static final int NB_HLAJOUTER = 2; // nombre de lignes et collones supplementaire 
											// pour ajouter les * en bord d'�cran
	private static int num = 0;
	private static char sympole = '*'; //sympole pour encadrer le film.
	
	public EncadrerFilm(Film film) {
		this.film = film;
	}
	
	/**
	 * Obtenir une hauteur qui a ajouter les espaces pour le sympole d'encradrement.
	 * 
	 * @return Hauteur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return film.hauteur() + NB_HLAJOUTER;
	}

	/**
	 * Obtenir une largeur qui a ajouter les espaces pour le sympole d'encradrement.
	 * 
	 * @return largeur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return film.largeur() + NB_HLAJOUTER;
	}

	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param �cran : L'�cran o� afficher l'image
	 * @return vrai Si l'image suivante a �t� affich�e sur l'�cran et faux si le
	 *         film est termin�
	 */
	@Override
	public boolean suivante(char[][] �cran) {
		char[][] �cran2 = new char[film.hauteur()][film.largeur()]; // obtenir l'�cran vide de 1er film
		if(film.suivante(�cran2)) {
			for(int i = 0; i < largeur(); ++i) { // obtenir les symbole d'encadrement au bord de l'�cran
				�cran[num][i] = sympole;
				�cran[hauteur() - 1][i] = sympole;
			}
			for (int i = 1; i < hauteur(); ++i) {
				�cran[i][num]  = sympole;
				�cran[i][largeur() - 1] = sympole;
			}
			for(int i = 1; i < hauteur()-1; ++i) {
				for(int j =1; j < largeur()-1; ++j) { // obtenir les �crans de 1er film tourne autour de symbole '*'
					�cran[i][j] = �cran2[i-1][j-1];
				}
			}
			return true;
		}
		film.rembobiner(); // rembobiner le film
		return false;
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
