package biblioth�queFilm;

import film.Film;
/**
 * @author : MILLO Chelsey, YE Fangyuan Lisa
 * @date : 23/05/2020
*/

/**
 * Une classe implemente l'Interface Film, il redefinir l'ensemble de methode de 
 * l'Interface Film pour coller deux films, l�un � la suite de l'autre afin de cr�er   
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
	 * @return Hauteur minimale de l'�cran pour pouvoir afficher les images de
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
	 * @return largeur minimale de l'�cran pour pouvoir afficher les images de
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
	 * @param �cran : L'�cran o� afficher l'image
	 * @return vrai Si l'image suivante a �t� affich�e sur l'�cran et faux si le
	 *         film est termin�
	 */
	@Override
	public boolean suivante(char[][] �cran) {
		if(f1.suivante(�cran)) { // Obtenir d'abord les images de premier film
			return true;
		}
		else {
			if(f2.suivante(�cran)) { // Obtenir ensuite les images de deuxieme film
				return true;
			}
			else {
				rembobiner(); // il fait un boucle en testes Compositions !!!!
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
		f1.rembobiner();
		f2.rembobiner();
	}

}
