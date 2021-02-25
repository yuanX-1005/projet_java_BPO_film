package bibliothèqueFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 24/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour incruster un film dans un autre film. Le point d'incrustation 
 * sera désigné par les numéros de ligne et de colonne que doit prendre le coin en haut à 
 * gauche du film devant être incrusté dans les images du film où il est incrusté 
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
	 * Obtenir une hauteur de la film à incrusté
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return f2.hauteur();
	}
	
	/**
	 * Obtenir une largeur de la film à incrusté
	 * 
	 * @return Largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return f2.largeur();
	}
	
	/**
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param écran : L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran2) {
		char[][] écran1 = new char [f1.hauteur()][f1.largeur()]; // obtenir l'écran vide de film devant être incrusté 
		if(f2.suivante(écran2)) { // obtenir les images de film où il est incrusté
			if(!f1.suivante(écran1)) // si les nombre d'images de film devant être incrusté sont inferieur à celui à incruté
				return true;		// obtenir directe les images de la film à incrusté.
			if(ligne < 0)ligne = 0;
			if(colonne < 0)colonne = 0;
			for(int i = ligne; i < hauteur() && i < f1.hauteur() + ligne; ++i) { // sinon obtenir les images incrustes
				for(int j = colonne; j < largeur() && j < f1.largeur() + colonne; ++j) {
					écran2[i][j] = écran1[i - (ligne)][j - (colonne)];
				}
			}
			return true;
		}
		f1.rembobiner(); // rembobiner les films
		f2.rembobiner();
		return false;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {		
	}

}
