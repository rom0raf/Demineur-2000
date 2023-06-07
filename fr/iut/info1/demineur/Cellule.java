/**
 * Cellule.java 			22/05/2023
 * Iut Rodez, aucun copyright ni "copyleft"
 */
package fr.iut.info1.demineur;

/**
 * Classe Cellule:
 * 	contient toute les informations d'une case de demineur
 * 
 * @author rafael.roma
 * @author lohan.vignals
 * @author antonin.veyre
 * @author aurelien.soleil
 */
public class Cellule {

	/** true si la case est une bombe, false sinon */
	private boolean estBombe;
	
	/** true si la case est cachée, false sinon */
	private boolean estCachee;
	
	/** true si la case est marquée par un drapeau, false sinon*/
	private boolean estMarquee;
	
	/** compris entre -1 et 8 */
	private int numero;
	
	/**
	 * Constructeur de la classe Cellule
	 * @param estBombe true si la case est une bombe, false sinon
	 * @param estCachee true si la case est caché, false sinon
	 * @param estMarquee true si la case est marquee, false sinon
	 * @param numero le numéro de la case:
	 * 		  - Compris entre 1 est 8 si il y a des bombes aux alentours
	 * 		  - 0 si il n'y a aucune bombe aux alentours
	 * 		  - -1 si c'est une bombe
	 */
	public Cellule(boolean estBombe, boolean estCachee, boolean estMarquee, int numero) {
		this.checkUnitCase(estBombe, estCachee, estMarquee, numero);
		
		this.estBombe = estBombe;
		this.estCachee = estCachee;
		this.estMarquee = estMarquee;
		this.numero = numero;
	}

	/**
	 * Vérifie l'integrité des arguments passés en parametres lors de l'appel du constructeur
	 * de Cellule
	 * @param estBombe true si la case est une bombe, false sinon
	 * @param estCachee true si la case est caché, false sinon
	 * @param estMarquee true si la case est marquee, false sinon
	 * @param numero le numéro de la case:
	 * 		  - Compris entre 1 est 8 si il y a des bombes aux alentours
	 * 		  - 0 si il n'y a aucune bombe aux alentours
	 * 		  - -1 si c'est une bombe
	 * @throws IllegalArgumentException si:
	 * 		   - estBombe est true et numéro n'est pas -1
	 * 		   - numéro > 8 ou numéro < -1
	 * 		   - estMarquee est true et estCachee est false
	 */
	private void checkUnitCase(boolean estBombe, boolean estCachee, boolean estMarquee, int numero){
		if (estBombe && numero != -1){
			throw new IllegalArgumentException("Le numéro est incohérent sachant que la case possède une bombe");
		}

		if (numero < -1 || numero > 8) {
			throw new IllegalArgumentException("Le numéro compris dans la case n'est pas valable.");
		}

		if (estMarquee && !estCachee){
			throw new IllegalArgumentException("La case est marqué alors qu'elle a déjà était découvert");
		}
	}

	/**
	 * @return estCachee
	 */
	public boolean isEstCachee() {
		return estCachee;
	}

	/**
	 * @param estCacheeN boolean
	 */
	public void setEstCachee(boolean estCacheeN) {
		this.checkUnitCase(this.estBombe, estCacheeN, this.estMarquee, this.numero);
		this.estCachee = estCacheeN;
	}

	/**
	 * @return estMarquee
	 */
	public boolean isEstMarquee() {
		return estMarquee;
	}

	/**
	 * @param estMarqueeN boolean
	 */
	public void setEstMarquee(boolean estMarqueeN) {
		this.checkUnitCase(this.estBombe, this.estCachee, estMarqueeN, this.numero);
		this.estMarquee = estMarqueeN;
	}

	/**
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numeroN int
	 */
	public void setNumero(int numeroN) {
		this.checkUnitCase(this.estBombe, this.estCachee, this.estMarquee, numeroN);
		this.numero = numeroN;
	}

	/**
	 * @return estBombe
	 */
	public boolean isEstBombe() {
		return estBombe;
	}
	
	/**
	 * Change la valeur de estBombe
	 * @param estBombeN un boolean
	 */
	public void setEstBombe(boolean estBombeN) {
		this.checkUnitCase(estBombeN, this.estCachee, this.estMarquee, this.numero);
		this.estBombe = estBombeN;
	}

}
