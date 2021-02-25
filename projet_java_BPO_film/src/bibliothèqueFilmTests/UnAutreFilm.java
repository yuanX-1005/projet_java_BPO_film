package biblioth�queFilmTests;

import film.Film;

public class UnAutreFilm implements Film{
	private static int num = 0;
	private static final int NB_IMAGES = 30;

	@Override
	public int hauteur() {
		return 20; 
	}

	@Override
	public int largeur() {
		return hauteur(); // ce sera un carr�
	}

	@Override
	public boolean suivante(char[][] �cran) {
		if (num == NB_IMAGES)
			return false;
		for (int i = 0; i < hauteur(); ++i) {
			for (int j = 0; j < largeur(); ++j ) {
				�cran[i][j] = 'b';
			}
		}
		/*�cran[num % hauteur()][num % hauteur()] = 'b'; // un 'a' se balade sur
														// la diagonale */
		++num;
		return true;
	} 

	@Override
	public void rembobiner() {
		num = 0;
	}
}
