package tests.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import film.Film;
import film.Films;

public final class FabriqueAFilms {
	// retourne un film sans image
	public static Film filmVide(int  hauteur, int largeur) {
		return new Film() {
			@Override
			public int hauteur() {
				return hauteur;
			}

			@Override
			public int largeur() {
				return largeur;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				return false;
			}

			@Override
			public void rembobiner() {
				// il faut aller de l'avant dans la vie
			}			
		};
	}

	// retourne un film ne modifiant pas l'�cran
	public static Film filmNeutre(int  hauteur, int largeur, int nbImages) {
		return new Film() {
			private int nb = nbImages;
			@Override
			public int hauteur() {
				return hauteur;
			}

			@Override
			public int largeur() {
				return largeur;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				if (nb > 0) {
					--nb;
					return true;
				}
				return false;
			}

			@Override
			public void rembobiner() {
				nb = nbImages;
			}
		};
	}

	public static Film imageToFilm(char[][] image, int nbImages) {
		return new Film() {
			private int nb = nbImages;
			@Override
			public int hauteur() {
				return image.length;
			}

			@Override
			public int largeur() {
				return image[0].length;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				if (nb > 0) {
					for (int li = 0; li < this.hauteur(); ++li)
						for (int co = 0; co < this.largeur(); ++co)
		                	�cran[li][co] = image[li][co];
					--nb;
					return true;
				}
				return false;
			}

			@Override
			public void rembobiner() {
				nb = nbImages;
			}
		};
	}
	
	public static Film imageToFilm(char[][] image) {
		return imageToFilm(image, 1);
	}

	// Retourne un film infini dont toutes les images valent celle re�ue en param�tre.
	// Sauvegarder ou projeter un tel film n'a pas de sens. En calculer un extrait en a un.
	public static Film imageToFilmInfini(char[][] image) {
		return new Film() {

			@Override
			public int hauteur() {
				return image.length;
			}

			@Override
			public int largeur() {
				return image[0].length;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				for (int li = 0; li < this.hauteur(); ++li)
					for (int co = 0; co < this.largeur(); ++co)
						�cran[li][co] = image[li][co];
				return true;
			}

			@Override
			public void rembobiner() {
				// il faut aller de l'avant dans la vie
			}			
		};
	}
	
	public static Film bandeau(String msg, int largeur) {
		return new Film() {
			private int num = -largeur;

			@Override
			public int largeur() {
				return largeur;
			}

			@Override
			public int hauteur() {
				return 1;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				if (num > msg.length())
					return false;
				for (int co = 0; co < largeur; ++co)
					if (0 <= num + co && num + co < msg.length())
						�cran[0][co] = msg.charAt(num + co);
				++num;
				return true;
			}

			@Override
			public void rembobiner() {
				this.num = -largeur;
			}	
		};
	}
	
	public static Film artifice(int hauteur, int largeur) {
		return new Film() {
			private Random rd = new Random();
			private int reste;
			private char[][] image;
			
			{
				this.reste = hauteur * largeur;
				this.image = new char[hauteur][largeur];
				Films.effacer(this.image);
			}
		    
			@Override
		    public int largeur() {
		        return largeur;
		    }

		    @Override
		    public int hauteur() {
		        return hauteur;
		    }

		    @Override
		    public boolean suivante(char[][] �cran) {
		        if (reste == 0)
		            return false;
		        int num = rd.nextInt(reste);
		        char c = (char)('a' + rd.nextInt(26));
		        for (int li = 0; li < hauteur; ++li)
		            for (int co = 0; co < largeur; ++co) {
		                if (image[li][co] == ' ') {
		                    if (num == 0)
		                        image[li][co] = c;
		                    --num;
		                }
		                �cran[li][co] = image[li][co];
		            }
		        --reste;
		        return true;
		    }

		    @Override
		    public void rembobiner() {
		        Films.effacer(this.image);
		        this.reste = hauteur * largeur;
		    }
		};
	}
	
	// le film est compos� de NB_IMAGES images constantes (un 'o' et trois espaces).
	// � chaque rembobinage, le film change (la position du 'o' n'est pas la m�me).
	// Sauvegarder ce film n'a pas de sens.
	public static Film filmVariable() {
		return new Film() {
			private static final int PERIODE = 4;
			private int nb = 0;
			private static final int NB_IMAGES = 3;
			private int num = 0;
			
			@Override
			public int hauteur() {
				return 1;
			}

			@Override
			public int largeur() {
				return 4;
			}

			@Override
			public boolean suivante(char[][] �cran) {
				if (num == NB_IMAGES)
					return false;
				�cran[0][nb] = 'o';
				++num;
				return true;
			}

			@Override
			public void rembobiner() {
				num = 0;
				nb = (nb + 1) % PERIODE;
			}
		};
	}
	
	public static Film copieBrute(Film ... films) {
		return new CopieBrute(films); 
	}
	
	private FabriqueAFilms() {}
}

class CopieBrute implements Film {
	private List<char[][]> images;
	private int hauteur;
	private int largeur;
	private int num;
	
	public CopieBrute(Film ... films) {
		Film p = films[0];
		this.images = new ArrayList<>();
		this.num = 0;
		this.hauteur = p.hauteur();
		this.largeur = p.largeur();
		for (Film f : films) {
			assert(p.hauteur() == f.hauteur() && p.largeur() == f.largeur());
			colle(f);
		}
	}
	
	public CopieBrute(int hauteur, int largeur) {
		this(FabriqueAFilms.filmVide(hauteur, largeur));
	}

	// ajoute les images (une copie de l'�cran) d'un film au film
	public void colle(Film f) {
		f.rembobiner();
		char[][] �cran = Films.getEcran(f);
		while (f.suivante(�cran)) {
			this.colle(�cran);
			Films.effacer(�cran);
		}
		f.rembobiner();
	}

	// ajoute une image (une copie de l'�cran) au film
	public void colle(char[][] �cran) {
		char[][] image = new char[hauteur][];
		for (int li = 0; li < hauteur; ++li) {
			image[li] = �cran[li].clone();
		}
		images.add(image);
	}

	@Override
	public int hauteur() {
		return hauteur;
	}

	@Override
	public int largeur() {
		return largeur;
	}

	@Override
	public boolean suivante(char[][] �cran) {
		if (num == images.size())
			return false;
		for (int li = 0; li < hauteur; ++li)
			for (int co = 0; co < largeur; ++co)
				�cran[li][co] = images.get(num)[li][co];
		++num;
		return true;
	}

	@Override
	public void rembobiner() {
		num = 0;
	}
}

