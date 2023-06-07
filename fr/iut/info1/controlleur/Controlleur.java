/**
 * Controlleur.java 				    26/05/2023
 * Iut Rodez, aucun copyright ni "copyleft" suce ma bite non merci
 */

package fr.iut.info1.controlleur;

// import fr.iut.info1.affichage.Vue;
import fr.iut.info1.demineur.Demineur;

/**
 * 
 * @author rafael.roma
 * @author aurelien.soleil
 * @author antonin.veyre
 * @author lohan.vignals
 */
public class Controlleur {
	
	/** Le modèle utilisé par le controlleur */
	private Demineur model;
	private int dejaMarque = 0;
	
	/**
	 * Initialise les variables par defaut
	 * @param view La vue de l'application
	 * @param model le modele de l'application
	 */
	public Controlleur(Demineur model) {
		
		if (model == null) {
			throw new NullPointerException("Le model est null. Impossible.");
		} else if (!model.estDemineurPret()) {
			throw new IllegalArgumentException("Le démineur n'est pas finit. Impossible.");
		}
		
		this.model = model;
	}
	/**
	 * Revèle la case cliqué
	 * @param x coordonnée x de la case
	 * @param y coordonnée y de la case
	 */
	public boolean cliquerCase(int x, int y) {
		return this.model.revealCase(x, y);
	}

	/**
	 * Lorsque le joueur fait simultanément un clic droit et un clique gauche,
	 * Le système révèle toutes les cases autour
	 * à la condition qu'il y est autant de bombes autour qu'il a marqué de cases
	 * @param x coordonnée x de la case
	 * @param y coordonnée y de la case
	 */
	public boolean cliqueDroitEtGauche(int x, int y) {
		
		if (this.model.estCaseCachee(x, y) && this.model.estCaseBombe(x,y)) {
			this.model.revealCase(x,y);
			return false;
		} else if (this.model.nbBombesProches(x,y) == this.model.nbCasesMarqueesProches(x,y)) {
			int[][] coordAReveler = new int[][]{
				{x - 1, y - 1}, {x - 1, y}, {x - 1, y + 1},
				{x, y - 1},  {x, y + 1},
				{x + 1, y - 1}, {x + 1, y}, {x + 1, y + 1},
			};
			for (int i = 0; i < coordAReveler.length; i++) {
				if (!this.model.revealCase(coordAReveler[i][0], coordAReveler[i][1])) {
					return false;
				}
			}
		}
		return true;

	}

	public void cliqueDroitCase(int x, int y) {
		
		if (model.estMarquee(x, y)) {
			System.out.println("ici 2");
			this.model.marquerCellulle(x, y);
			dejaMarque--;
		} else if (dejaMarque < model.getCompteurBombes()) {
			System.out.println("ici");
			this.model.marquerCellulle(x, y);
			dejaMarque ++;
		}
	}

	public int getDejaMarque() {
		return dejaMarque;
	}

}
