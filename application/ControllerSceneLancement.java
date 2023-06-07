/**
 * Sample Skeleton for 'SceneLancement.fxml' Controller Class
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;

import fr.iut.info1.generateur.Generateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class ControllerSceneLancement {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pane_boutons"
    private Pane pane_boutons; // Value injected by FXMLLoader

    @FXML // fx:id="debutantBouton"
    private RadioButton debutantBouton; // Value injected by FXMLLoader

    @FXML // fx:id="largeur"
    private Label largeur; // Value injected by FXMLLoader

    @FXML // fx:id="panel_titre"
    private Pane panel_titre; // Value injected by FXMLLoader

    @FXML // fx:id="titre"
    private Label titre; // Value injected by FXMLLoader

    @FXML // fx:id="pane_informations"
    private Pane pane_informations; // Value injected by FXMLLoader

    @FXML // fx:id="dificulte"
    private ToggleGroup dificulte; // Value injected by FXMLLoader

    @FXML // fx:id="boutonExpert"
    private RadioButton boutonExpert; // Value injected by FXMLLoader

    @FXML // fx:id="boutonPersonnalise"
    private RadioButton boutonPersonnalise; // Value injected by FXMLLoader

    @FXML // fx:id="fond"
    private Pane fond; // Value injected by FXMLLoader

    @FXML // fx:id="boutonIntermediaire"
    private RadioButton boutonIntermediaire; // Value injected by FXMLLoader

    @FXML // fx:id="bombes"
    private Label bombes; // Value injected by FXMLLoader

    @FXML // fx:id="bouton_jouer"
    private Button bouton_jouer; // Value injected by FXMLLoader

    @FXML // fx:id="hauteur"
    private Label hauteur; // Value injected by FXMLLoader
    
    @FXML
    void clickJouer(ActionEvent event) {
    	
    	if (boutonPersonnalise.isSelected()) {
    		Main.demandeHauteurLargeurBombe(dimensions -> {
        		ControllerSceneJeu.taillesInit = dimensions;
        		ControllerSceneJeu.difficulted = "Personnalisé";
        		Main.ChargerSceneJeu();
    		});
        } else {
        	int hauteurF = Integer.valueOf(hauteur.getText());
        	int largeurF = Integer.valueOf(largeur.getText());
        	int bombesF = Integer.valueOf(bombes.getText());
        	
        	ControllerSceneJeu.taillesInit = new int[] {hauteurF, largeurF, bombesF};
        	ControllerSceneJeu.difficulted = debutantBouton.isSelected() ? "Debutant" : boutonExpert.isSelected() ? "Expert" : "Intermediaire";
        	Main.ChargerSceneJeu();
        }
    	
    	
    }

    @FXML
    void clickExpert(ActionEvent event) {
    	hauteur.setText("16");
        largeur.setText("30");
        bombes.setText("99");
    }

    @FXML
    void clickIntermediaire(ActionEvent event) {
    	hauteur.setText("16");
        largeur.setText("16");
        bombes.setText("40");
    }

    @FXML
    void clickDebutant(ActionEvent event) {
        hauteur.setText("9");
        largeur.setText("9");
        bombes.setText("10");
    }

    @FXML
    void clickPersonnalise(ActionEvent event) {
    	hauteur.setText("...");
        largeur.setText("...");
        bombes.setText("...");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert pane_boutons != null : "fx:id=\"pane_boutons\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert debutantBouton != null : "fx:id=\"debutantBouton\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert largeur != null : "fx:id=\"largeur\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert panel_titre != null : "fx:id=\"panel_titre\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert titre != null : "fx:id=\"titre\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert pane_informations != null : "fx:id=\"pane_informations\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert dificulte != null : "fx:id=\"dificulte\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert boutonExpert != null : "fx:id=\"boutonExpert\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert boutonPersonnalise != null : "fx:id=\"boutonPersonnalise\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert fond != null : "fx:id=\"fond\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert boutonIntermediaire != null : "fx:id=\"boutonIntermediaire\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert bombes != null : "fx:id=\"bombes\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert bouton_jouer != null : "fx:id=\"bouton_jouer\" was not injected: check your FXML file 'SceneLancement.fxml'.";
        assert hauteur != null : "fx:id=\"hauteur\" was not injected: check your FXML file 'SceneLancement.fxml'.";
    }
}
