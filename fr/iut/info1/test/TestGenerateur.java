package fr.iut.info1.test;

import static org.junit.jupiter.api.Assertions.*;

import fr.iut.info1.demineur.Demineur;
import fr.iut.info1.generateur.Generateur;

import org.junit.jupiter.api.Test;

class TestGenerateur {

    @Test
    public void testGenererNewDemineur() {
        int hauteur = 10;
        int largeur = 10;
        int nbBombes = 10;
        int[] coordPremierClique = {0, 0};

        Demineur demineur = Generateur.genererNewDemineur(hauteur, largeur, nbBombes, coordPremierClique);

        assertEquals(hauteur, demineur.getHauteur());
        assertEquals(largeur, demineur.getLargeur());
        assertEquals(nbBombes, demineur.getCompteurBombes());

        // Vérifier que la première case cliquée ne contient pas de bombe
        assertFalse(demineur.estCaseBombe(coordPremierClique[0], coordPremierClique[1]));
    }

    @Test
    public void testEstCoordValides() {
        int[] coordPremierClique = {2, 2};

        assertFalse(Generateur.estCoordValides(1, 1, coordPremierClique)); // Au-dessus à gauche
        assertFalse(Generateur.estCoordValides(2, 2, coordPremierClique)); // Au centre
        assertFalse(Generateur.estCoordValides(2, 3, coordPremierClique)); // À droite
        assertFalse(Generateur.estCoordValides(3, 2, coordPremierClique)); // En bas
        assertFalse(Generateur.estCoordValides(2, 3, coordPremierClique)); // À droite

        assertTrue(Generateur.estCoordValides(0, 0, coordPremierClique)); // Coin supérieur gauche
        assertTrue(Generateur.estCoordValides(4, 4, coordPremierClique)); // En bas à gauche
    }
}
