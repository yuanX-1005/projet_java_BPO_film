package biblioth�queFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour incruster un film dans un autre film. Le point d'incrustation 
 * sera d�sign� par les num�ros de ligne et de colonne que doit prendre le coin en haut � 
 * gauche du film devant �tre incrust� dans les images du film o� il est incrust� 
 *
 */
public class IncrusterFilm implements Film{
	private Film f1, f2;
	private int ligne, colonne;
	
	public IncrusterFilm(Film f1, Film f2, int ligne, int colonne) {
		this.f1 = f1;
		this.f2 = f2;
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/**
	 * Obtenir une hauteur de la film � incrust�
	 * 
	 * @return Hauteur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return f2.hauteur();
	}
	
	/**
	 * Obtenir une largeur de la film � incrust�
	 * 
	 * @return Largeur minimale de l'�cran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return f2.largeur();
	}
	
	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param �cran : L'�cran o� afficher l'image
	 * @return vrai Si l'image suivante a �t� affich�e sur l'�cran et faux si le
	 *         film est termin�
	 */
	@Override
	public boolean suivante(char[][] �cran2) {
		char[][] �cran1 = new char [f1.hauteur()][f1.largeur()]; // obtenir l'�cran vide de film devant �tre incrust� 
		if(f2.suivante(�cran2)) { // obtenir les images de film o� il est incrust�
			if(!f1.suivante(�cran1)) // si les nombre d'images de film devant �tre incrust� sont inferieur � celui � incrut�
				return true;		// obtenir directe les images de la film � incrust�.
			if(ligne < 0)ligne = 0;
			if(colonne < 0)colonne = 0;
			for(int i = ligne; i < hauteur() && i < f1.hauteur() + ligne; ++i) { // sinon obtenir les images incrustes
				for(int j = colonne; j < largeur() && j < f1.largeur() + colonne; ++j) {
					�cran2[i][j] = �cran1[i - (ligne)][j - (colonne)];
				}
			}
			return true;
		}
		f1.rembobiner(); // rembobiner les films
		f2.rembobiner();
		return false;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalit� (via
	 * des appels successifs � la m�thode suivante()).
	 */
	@Override
	public void rembobiner() {		
	}

}
