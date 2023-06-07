/**
 * Demineur.java 				22/05/2023
 * Iut Rodez, aucun copyright ni "copyleft"
 */
package fr.iut.info1.demineur;

/**
 * Classe Demineur
 * 
 * @author rafael.roma
 * @author antonin.veyre
 * @author lohan.vignals
 * @author aurelien.soleil
 */
public class Demineur {

	/** Les tuiles du demineur */
	private Cellule[][] tuiles;
	
	/** Savoir si la fonction ajouterChiffresAutourBombes a été exécutée */
	private boolean estChiffreAjoute;
	
	/** Savoir si la fonction ajouterBombe a été exécutée */
	private boolean estBombesAjoutees;
	
	private int hauteur;
	private int largeur;

	/** Permet de coompter le nombre de bomes sur le plateau*/
	private int compteurBombes;

	/**permet de savoir si le joueur a clique sur une bombe*/
	private boolean partiePerdu = false;

	private int revele;
	
	/**
	 * Constructeur de Demineur
	 * 
	 * @param hauteur la hauteur du plateau
	 * @param largeur la largeur du plateau
	 * @throws IllegalArgumentException si la hauteur ou la largeur est inférieure a 0
	 */
	public Demineur(int hauteur, int largeur) {
		if (hauteur <= 1 || largeur <= 1) {
			throw new IllegalArgumentException("Les longueur du tableau ne sont pas bonne");
		}
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.initToutesLesCases();
		this.estBombesAjoutees = this.estChiffreAjoute = false;
	}

	/**
	 * La fonction vérifie que les coordonnees sont bien valides
	 * elle doit être comprises entre la longueur elt la largeur des tuiles
	 * 
	 * @throws IllegalArgumentException si les coordonnees de la case sont invalides
	 */
	private void estCoordonneesValides(int x, int y) {
		if (x >= hauteur || x < 0 || y < 0 || y >= largeur) {
			throw new IllegalArgumentException("Les coordonnées de la case sont fausses ! ");
		}
	}

	/**
	 * La fonction initialise toutes les cases du demineur
	 */
	public void initToutesLesCases() {
		this.tuiles = new Cellule[this.hauteur][this.largeur];
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				this.tuiles[i][j] = new Cellule(
					false,
					true,
					false,
					0
				);
			}
		}
	}

	/**
	 * La fonction sert à savoir si la case contient une bombe
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return true si la case contient une bombe, false sinon
	 */
	public boolean estCaseBombe(int x, int y) {
		estCoordonneesValides(x,y);
		return tuiles[x][y].isEstBombe();
	}

	/**
	 * La fonction détermine si la case a été révélé ou non par le joueur
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return true si la case n'a pas encore été révélé par l'utilisateur, sinon false
	 */
	public boolean estCaseCachee(int x, int y) {
		estCoordonneesValides(x,y);
		return tuiles[x][y].isEstCachee();
	}

	/**
	 * La fonction indique si la case est marqué par l'utilisateur
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return true si la case est déjà marqué par l'utilisateur, sinon false
	 */
	public boolean estMarquee(int x, int y) {
		estCoordonneesValides(x,y);
		return tuiles[x][y].isEstMarquee();
	}

	/**
	 * La fonction ajoute une bombe à une case passé en argument
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 */
	public void ajouterBombe(int x, int y) {
		compteurBombes ++;
		estCoordonneesValides(x, y);
		this.estBombesAjoutees = true;
		this.tuiles[x][y] = new Cellule(true, true, false, -1);
	}

	/**
	 * la fonction marque d'un drapeau la case si elle n'est pas marqué
	 * sinon elle démarque la case
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 */
	public void marquerCellulle(int x, int y) {
		estCoordonneesValides(x, y);
		try {
			this.tuiles[x][y].setEstMarquee(!this.tuiles[x][y].isEstMarquee());
		} catch(Exception err) {
			// Rien
		}
	}

	/**
	 * La fonction ajoute les chiffres autour de chaque bombe
	 * les chiffres déterminent combien il y a de bombes autour d'une case
	 */
	public void ajouterChiffresAutourBombes() {
		if (this.estBombesAjoutees) {
			for (int i = 0; i < this.hauteur; i++) {
				for (int j = 0; j < this.largeur; j++) {
					if (!estCaseBombe(i, j)) {
						this.tuiles[i][j].setNumero(this.nbBombesProches(i, j));
					}
				}
			}
			this.estChiffreAjoute = true;
		} else {
			throw new IllegalArgumentException("Il n'y a aucune bombe. Impossible");
		}
		
	}

	/**
	 * La fonction révèle toutes les cases qui sont vides autour d'une case donnée
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 */
	public void revealCasesVides(int x, int y) {
		try {
	        estCoordonneesValides(x, y);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Les coordonnées de la case sont invalides");
	        return;
	    }
	    
	    if (!estCaseCachee(x, y) || estCaseBombe(x, y)) {
	        return;
	    }
	    
	    this.tuiles[x][y].setEstCachee(false);
		revele ++;
	    
	    if (nbBombesProches(x, y) == 0) {
	        for (int i = x - 1; i <= x + 1; i++) {
	            for (int j = y - 1; j <= y + 1; j++) {
	                try {
	                    estCoordonneesValides(i, j);
	                    if (!estCaseBombe(i, j) && estCaseCachee(i, j)) {
	                        revealCasesVides(i, j);
	                    }
	                } catch (IllegalArgumentException e) {
	                    // Les coordonnées sont invalides, on les ignores
	                }
	            }
	        }
	    }
	}
	
	/**
	 * la fonction révèle la case où le joueur a cliqué
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return true si il n'y a aucun problème sur la case révélé et false sinon(révèle une bombe)
	 */
	public boolean revealCase(int x, int y) {
		try {
			estCoordonneesValides(x,y);
			if (estCaseBombe(x, y)) {
				partiePerdu = true;
				return false;
			}
			if (this.tuiles[x][y].getNumero() == 0) {
				revealCasesVides(x, y);
			} else {
				this.tuiles[x][y].setEstCachee(false);
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * La fonction donne le nombre de bombes autour d'une case
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return un int donnant le nombre de bombes
	 */
	public int nbBombesProches(int x, int y) {
		int nbBombes = 0;
		
		int[][] coordATester = new int[][]{
			{x - 1, y - 1}, {x - 1, y}, {x - 1, y + 1},
			{x, y - 1},  {x, y + 1},
			{x + 1, y - 1}, {x + 1, y}, {x + 1, y + 1},
		};
		
		for (int i = 0; i < coordATester.length; i++) {
			try {
				if (estCaseBombe(coordATester[i][0], coordATester[i][1])) {
					nbBombes++;
				}
			} catch (IllegalArgumentException e) {
				continue;
			}
		}
	
		return nbBombes; 
	}

	/**
	 * La fonction donne le nombre de cases marquées autour d'une case
	 * 
	 * @param x l'abscisse de la case
	 * @param y l'ordonnee de la case
	 * @return un int donnant le nombre de cases marquées
	 */
	public int nbCasesMarqueesProches(int x, int y) {
		int nbMarquees = 0;
		
		int[][] coordATester = new int[][]{
			{x - 1, y - 1}, {x - 1, y}, {x - 1, y + 1},
			{x, y - 1},  {x, y + 1},
			{x + 1, y - 1}, {x + 1, y}, {x + 1, y + 1},
		};
		
		for (int i = 0; i < coordATester.length; i++) {
			try {
				if (estMarquee(coordATester[i][0], coordATester[i][1])) {
					nbMarquees++;
				}
			} catch (IllegalArgumentException e) {
				continue;
			}
		}
	
		return nbMarquees; 
	}

	/**
	 * La fonction vérifie que toutes les bombes et tous les chiffres ont bien était ajoutées
	 * 
	 * @return true si les bombes et les chiffres ont été ajoutés, sinon false
	 */
	public boolean estDemineurPret() {
		return this.estBombesAjoutees && this.estChiffreAjoute;
	}

	/**
	 * La fonction donne la représentation du demineur
	 */
	public void getRepresentationAffichage() {
		System.out.print("  ");
	    for (int j = 0; j < largeur; j++) {
	        System.out.print(j + " ");
	    }
	    System.out.println();
	
	    for (int i = 0; i < hauteur; i++) {	        System.out.print(i + " ");
	        for (int j = 0; j < largeur; j++) {
	            if (!estCaseCachee(i, j)) {
	                if (estCaseBombe(i, j)) {
	                    System.out.print("X ");
	                } else {
	                    int nombreBombesVoisines = nbBombesProches(i, j);
	                    System.out.print(nombreBombesVoisines + " ");
	                }
	            } else if (this.tuiles[i][j].isEstMarquee()) {
	                System.out.print("M ");
	            } else {
	                System.out.print("# ");
	            }
	        }
	        System.out.println();
	    }
	}
	
	/**
	 * la fonction permet de déterminer si le joueur a révélé toutes les cases
	 * @return true si il a révélé toutes les cases, false sinon
	 */
	private boolean toutesCasesReveles(){
		if(revele == hauteur * largeur - this.compteurBombes) {
			return true;
		}
		return false;
	}

	/**
	 * la fonction affiche l'état de la partie 
	 * @return 0 si le joueur peut encore jouer, 1 si il a perdu, et 2 si il a gagné
	 */
	public int getStatutPartie() {
		if(partiePerdu) {
			return 1;
		}
		if (toutesCasesReveles()) {
			return 2;
		}
		return 0;
	}

	/**
	 * @return the tuiles
	 */
	public int getNumero(int x, int y) {
		return tuiles[x][y].getNumero();
	}

	/**
	 * @return the partiePerdu
	 */
	public boolean isPartiePerdu() {
		return partiePerdu;
	}

	/**
	 * @return partieGagne
	 */ 
	public boolean isPartieGagne() {
		return toutesCasesReveles();
	}

	/**
	 * @return the compteurBombes
	 */
	public int getCompteurBombes() {
		return compteurBombes;
	}

	/** @return la hauteur*/
	public int getHauteur() {
		return hauteur;
	}

	/** @return la hauteur*/
	public int getLargeur() {
		return largeur;
	}


	/**
	 * Révèle toutes les bombes
	 */
	public void revealToutesLesBombes() {
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				if (tuiles[i][j].isEstBombe()) {
					tuiles[i][j].setEstCachee(false);
				}
			}
		}
	} 
	
}
