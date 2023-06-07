// package fr.iut.info1.test;

// import static org.junit.jupiter.api.Assertions.*;

// import fr.iut.info1.controlleur.Controlleur;
// import fr.iut.info1.demineur.Demineur;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// class TestControlleur {

//     private Controlleur controlleur;
//     private Demineur demineur;

//     @BeforeEach
//     public void setUp() {
//         demineur = new Demineur(10, 10);
//         controlleur = new Controlleur(demineur);
//     }

//     @Test
//     public void testCliquerCase() {
//         assertFalse(demineur.estCaseRevelee(0, 0));
//         assertTrue(controlleur.cliquerCase(0, 0));
//         assertTrue(demineur.estCaseRevelee(0, 0));
//     }

//     @Test
//     public void testCliqueDroitEtGauche() {
//         // Ajoutez les cases nécessaires pour le test

//         demineur.marquerCellule(0, 1);
//         demineur.marquerCellule(1, 0);
//         demineur.marquerCellule(1, 1);

//         // Le nombre de bombes proches est 3
//         // Le nombre de cases marquées proches est 3
//         // Les cases à révéler sont (0, 0), (0, 1), (1, 0), (1, 1)

//         assertFalse(demineur.estCaseRevelee(0, 0));
//         assertFalse(demineur.estCaseRevelee(0, 1));
//         assertFalse(demineur.estCaseRevelee(1, 0));
//         assertFalse(demineur.estCaseRevelee(1, 1));

//         assertTrue(controlleur.cliqueDroitEtGauche(0, 0));

//         assertTrue(demineur.estCaseRevelee(0, 0));
//         assertTrue(demineur.estCaseRevelee(0, 1));
//         assertTrue(demineur.estCaseRevelee(1, 0));
//         assertTrue(demineur.estCaseRevelee(1, 1));
//     }

//     @Test
//     public void testCliqueDroitCase() {
//         assertFalse(demineur.estMarquee(0, 0));
//         controlleur.cliqueDroitCase(0, 0);
//         assertTrue(demineur.estMarquee(0, 0));

//         assertFalse(demineur.estMarquee(1, 1));
//         controlleur.cliqueDroitCase(1, 1);
//         assertTrue(demineur.estMarquee(1, 1));
//     }
// }
