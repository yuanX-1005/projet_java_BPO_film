package bibliothèqueFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour encadrer un film (par quatre ligne d'étoiles – '*' – au  
 * bord de l'écran).
 */
public class EncadrerFilm implements Film {
	private Film film;
	private static final int NB_HLAJOUTER = 2; // nombre de lignes et collones supplementaire 
											// pour ajouter les * en bord d'écran
	private static int num = 0;
	private static char sympole = '*'; //sympole pour encadrer le film.
	
	public EncadrerFilm(Film film) {
		this.film = film;
	}
	
	/**
	 * Obtenir une hauteur qui a ajouter les espaces pour le sympole d'encradrement.
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return film.hauteur() + NB_HLAJOUTER;
	}

	/**
	 * Obtenir une largeur qui a ajouter les espaces pour le sympole d'encradrement.
	 * 
	 * @return largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return film.largeur() + NB_HLAJOUTER;
	}

	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param écran : L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran) {
		char[][] écran2 = new char[film.hauteur()][film.largeur()]; // obtenir l'écran vide de 1er film
		if(film.suivante(écran2)) {
			for(int i = 0; i < largeur(); ++i) { // obtenir les symbole d'encadrement au bord de l'écran
				écran[num][i] = sympole;
				écran[hauteur() - 1][i] = sympole;
			}
			for (int i = 1; i < hauteur(); ++i) {
				écran[i][num]  = sympole;
				écran[i][largeur() - 1] = sympole;
			}
			for(int i = 1; i < hauteur()-1; ++i) {
				for(int j =1; j < largeur()-1; ++j) { // obtenir les écrans de 1er film tourne autour de symbole '*'
					écran[i][j] = écran2[i-1][j-1];
				}
			}
			return true;
		}
		film.rembobiner(); // rembobiner le film
		return false;
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
