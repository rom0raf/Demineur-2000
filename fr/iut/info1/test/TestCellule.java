package fr.iut.info1.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import fr.iut.info1.demineur.Cellule;

public class TestCellule {

    @Test
    public void testIsEstCachee() {
        Cellule cellule = new Cellule(false, true, false, 0);
        Assertions.assertTrue(cellule.isEstCachee());
    }

    @Test
    public void testSetEstCachee() {
        Cellule cellule = new Cellule(false, true, false, 0);
        cellule.setEstCachee(false);
        Assertions.assertFalse(cellule.isEstCachee());
    }

    @Test
    public void testIsEstMarquee() {
        Cellule cellule = new Cellule(false, true, true, 0);
        Assertions.assertTrue(cellule.isEstMarquee());
    }

    @Test
    public void testSetEstMarquee() {
        Cellule cellule = new Cellule(false, true, false, 0);
        cellule.setEstMarquee(true);
        Assertions.assertTrue(cellule.isEstMarquee());
    }

    @Test
    public void testGetNumero() {
        Cellule cellule = new Cellule(false, true, false, 3);
        Assertions.assertEquals(3, cellule.getNumero());
    }

    @Test
    public void testSetNumero() {
        Cellule cellule = new Cellule(false, true, false, 0);
        cellule.setNumero(5);
        Assertions.assertEquals(5, cellule.getNumero());
    }

    @Test
    public void testIsEstBombe() {
        Cellule cellule = new Cellule(true, true, false, -1);
        Assertions.assertTrue(cellule.isEstBombe());
    }

    @Test
    public void testSetEstBombe() {
        Cellule cellule = new Cellule(false, true, false, -1);
        cellule.setEstBombe(true);
        Assertions.assertTrue(cellule.isEstBombe());
    }
}
