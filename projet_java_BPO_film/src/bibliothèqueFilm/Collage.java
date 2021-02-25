package bibliothèqueFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 23/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour coller deux films, l’un à la suite de l'autre afin de créer   
 * un nouveau film, les ecran de ce film doit suiffisamment grand pour afficher les 
 * images. 
 */
public class Collage implements Film {
	private Film f1,f2;
	
	public Collage(Film f1, Film f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	/**
	 * Obtenir une hauteur plus long entre les hauteurs de deux films
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		if (f1.hauteur() >= f2.hauteur())
			return f1.hauteur();
		else
			return f2.hauteur();
	}
	
	/**
	 * Obtenir une largeur plus long entre les largeurs de deux films
	 *
	 * @return largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		if(f1.largeur() >= f2.largeur())
			return f1.largeur();
		else
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
	public boolean suivante(char[][] écran) {
		if(f1.suivante(écran)) { // Obtenir d'abord les images de premier film
			return true;
		}
		else {
			if(f2.suivante(écran)) { // Obtenir ensuite les images de deuxieme film
				return true;
			}
			else {
				rembobiner(); // il fait un boucle en testes Compositions !!!!
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
		f1.rembobiner();
		f2.rembobiner();
	}

}
