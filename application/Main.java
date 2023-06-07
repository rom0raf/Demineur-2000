/**
 * Main.java 				    26/05/2023
 * Iut Rodez, aucun copyright ni "copyleft"
 */
package application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.function.Consumer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Main extends Application {

	private static Scene SceneLancement;
	
	private static Scene SceneJeu;
	
	/**	 */
	private static Stage PrimaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			PrimaryStage = primaryStage;
			
			chargerSceneDebut();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void chargerSceneDebut() {
		try {
			
			FXMLLoader chargeurFXML = new FXMLLoader();
			chargeurFXML.setLocation(Main.class.getResource("SceneLancement.fxml"));
			
			Parent racine = chargeurFXML.load();
			
			SceneLancement = new Scene(racine);
			SceneLancement.getStylesheets().add(Main.class.getResource("SceneLancement.css").toExternalForm());
			
			PrimaryStage.setTitle("Démineur 2000 - Une nouvelle génération de Démineur version Windows XP");
			PrimaryStage.setHeight(780);
			PrimaryStage.setWidth(614);
			PrimaryStage.setScene(SceneLancement);
			PrimaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public static void demandeHauteurLargeurBombe(Consumer<int[]> callback) {
		// Créer les éléments visuels
        Label lblHeight = new Label("Hauteur :");
        TextField txtHeight = new TextField();
        Label lblWidth = new Label("Largeur :");
        TextField txtWidth = new TextField();
        Label lblBombes = new Label("Bombes :");
        TextField txtBombes = new TextField();
        Button btnSubmit = new Button("Valider");

        // Gestionnaire d'événements pour le clic sur le bouton "Valider"
        btnSubmit.setOnAction(event -> {
            String heightText = txtHeight.getText();
            String widthText = txtWidth.getText();
            String bombesTxt = txtBombes.getText();

            try {
                int height = Integer.parseInt(heightText);
                int width = Integer.parseInt(widthText);
                int bombes = Integer.parseInt(bombesTxt);

                // Vérifier si les dimensions sont valides
                if (height > 1 && width > 1 && bombes > 0  && bombes < (height*width)) {
                    // Dimensions valides, fermer la fenêtre
                    callback.accept(new int[] { height, width, bombes });
                    Stage stage = (Stage) btnSubmit.getScene().getWindow();
                    stage.close();
                } else {
                    showAlert("Erreur", "Les dimensions doivent être supérieures à zéro.");
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez saisir des entiers valides pour les dimensions.");
            }
        });

        // Créer la disposition de la fenêtre
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(lblHeight, txtHeight, lblWidth, txtWidth, lblBombes, txtBombes, btnSubmit);

        // Créer la scène et afficher la fenêtre
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Saisie des dimensions");
        stage.show();
        
        
    }

	public static void ChargerSceneJeu() {
		try {
			
			FXMLLoader chargeurFXML = new FXMLLoader();
			chargeurFXML.setLocation(Main.class.getResource("SceneJeu.fxml"));
			
			Parent racine = chargeurFXML.load();
			
			SceneJeu = new Scene(racine);
			SceneJeu.getStylesheets().add(Main.class.getResource("SceneJeu.css").toExternalForm());
			
			PrimaryStage.setTitle("Démineur 2000 - Une nouvelle génération de Démineur version Windows XP");
			PrimaryStage.setHeight(600);
			PrimaryStage.setWidth(830);
			PrimaryStage.setScene(SceneJeu);
			PrimaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean showConfirmationDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Êtes-vous sûr de votre choix ?");
        confirmationDialog.setContentText("Veuillez confirmer votre choix.");

        ButtonType confirmButton = new ButtonType("Confirmer");
        ButtonType cancelButton = new ButtonType("Annuler");

        confirmationDialog.getButtonTypes().setAll(confirmButton, cancelButton);

        // Attendre la réponse de l'utilisateur
        ButtonType result = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

        return result == confirmButton;
    }
	
	public static void main(String[] args) {
		launch();
	}

}
// --module-path "Z:/Sae_Developpement_d_applicaction/Workspace SAE/lib/javafx-sdk-20.0.1/lib" --add-modules javafx.controls