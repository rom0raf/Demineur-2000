package fr.iut.info1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.iut.info1.demineur.Demineur;

class TestDemineur {
	
    private Demineur demineur;

    @BeforeEach
    public void setUp() {
        demineur = new Demineur(3, 3);
    }

    @Test
    public void testAjouterBombe() {
        demineur.ajouterBombe(0, 0);
        assertTrue(demineur.estCaseBombe(0, 0));
    	assertFalse(demineur.estCaseBombe(1, 1));
    }

    @Test
    public void testMarquerCellulle() {
        demineur.marquerCellulle(0, 0);
        assertTrue(demineur.estMarquee(0, 0));
        demineur.marquerCellulle(0, 0);
        assertFalse(demineur.estMarquee(0, 0));
    }

    @Test
    public void testAjouterChiffresAutourBombes() {
        demineur.ajouterBombe(0, 0);
        demineur.ajouterBombe(0, 1);
        demineur.ajouterChiffresAutourBombes();

        assertEquals(-1, demineur.getNumero(0, 0));
        assertEquals(1, demineur.getNumero(0, 2));
        assertEquals(2, demineur.getNumero(1, 1));
    }

    @Test
    public void testRevealCase() {
        demineur.ajouterBombe(0, 0);
        demineur.ajouterChiffresAutourBombes();

        assertFalse(demineur.revealCase(0, 0));
        assertTrue(demineur.estCaseCachee(1, 1));

        assertTrue(demineur.revealCase(1, 1));
        assertFalse(demineur.estCaseCachee(1, 1));
    }
}
