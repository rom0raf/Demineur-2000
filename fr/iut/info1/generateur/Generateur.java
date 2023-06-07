package fr.iut.info1.generateur;

import fr.iut.info1.demineur.Demineur;
import java.util.Random;

public class Generateur {
	
	/**
	 * Fonction qui génère le démineur une fois que le joueur a cliqué sur la première case
	 * Ainsi, le joueur ne peut pas révéler une bombe au premier clic
	 * @param hauteur la hauteur du plateau du demineur (un entier)
	 * @param largeur la largeur du plateau du demineur (un entier)
	 * @param nbBombes le nombre de bombes que le joueur souhaite avoir sur le plateau
	 * @param coordPremierCliques un tableau avec l'abscisse et l'ordonnée de la case cliqué en premier par le joueur
	 * @return le demineur crée
	 */
	public static Demineur genererNewDemineur(int hauteur, int largeur, int nbBombes, int[] coordPremierClique){
		
		int x;
		int y;	
		int compteurBombes;
		Demineur demineur = new Demineur(hauteur, largeur);
		demineur.initToutesLesCases();
//		demineur.getRepresentationAffichage();

		/* Ajout des bombes */
		compteurBombes = 0;
		while (compteurBombes < nbBombes) {

			x = new Random().nextInt(hauteur);	
			y = new Random().nextInt(largeur);			
			
			if (!demineur.estCaseBombe(x,y) && estCoordValides(x,y,coordPremierClique)) {
				demineur.ajouterBombe(x,y);
				compteurBombes++;
			}
					
		}
		
		demineur.ajouterChiffresAutourBombes();
		demineur.revealCasesVides(coordPremierClique[0], coordPremierClique[1]);
//		demineur.getRepresentationAffichage();
		
		return demineur;
	}
	
	/**
	 * la fonction vérifie qu'il n'y a aucune bombe autour de la première case cliqué par le joueur
	 *
	 * @param x l'abscisse de la case
	 * @param y l'ordonnée de la case
	 * @param coordPremierCliques un tableau avec l'abscisse et l'ordonnée de la case cliqué en premier par le joueur
	 * @return true si il n'y a pas de bombes autour, et false sinon
	 */
	public static boolean estCoordValides(int x, int y, int[] coordPremierClique) {
		
		return x != coordPremierClique[0] && y != coordPremierClique[1] // Au centre
			   && x != coordPremierClique[0] - 1 && y != coordPremierClique[1] - 1 // En haut à gauche
			   && x != coordPremierClique[0] && y != coordPremierClique[1] - 1 // En haut
			   && x != coordPremierClique[0] + 1 && y != coordPremierClique[1] - 1 // En haut à droite
			   && x != coordPremierClique[0] - 1 && y != coordPremierClique[1] // Au centre à gauche
			   && x != coordPremierClique[0] + 1 && y != coordPremierClique[1] // Au centre à droite
			   && x != coordPremierClique[0] - 1 && y != coordPremierClique[1] + 1 // En bas à gauche
			   && x != coordPremierClique[0] && y != coordPremierClique[1] + 1 // En bas
			   && x != coordPremierClique[0] + 1 && y != coordPremierClique[1] + 1 // En bas à droite
			   ;
	}

}
