/**
 * Sample Skeleton for 'SceneJeu.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import fr.iut.info1.controlleur.Controlleur;
import fr.iut.info1.demineur.Demineur;
import fr.iut.info1.generateur.Generateur;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.util.Duration;

public class ControllerSceneJeu {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="paneBombeRest1"
    private Pane paneBombeRest1; // Value injected by FXMLLoader

    @FXML // fx:id="labelDificulte"
    private Label labelDificulte; // Value injected by FXMLLoader

    @FXML // fx:id="labelTemps"
    private Label labelTemps; // Value injected by FXMLLoader

    @FXML // fx:id="labelVicDef"
    private Label labelVicDef; // Value injected by FXMLLoader

    @FXML // fx:id="paneDificulte"
    private Pane paneDificulte; // Value injected by FXMLLoader

    @FXML // fx:id="boutonRestart"
    private Button boutonRestart; // Value injected by FXMLLoader

    @FXML // fx:id="paneJeu"
    private Pane paneJeu; // Value injected by FXMLLoader

    @FXML // fx:id="paneBombeRest"
    private Pane paneBombeRest; // Value injected by FXMLLoader

    @FXML // fx:id="labelNbBombes"
    private Label labelNbBombes; // Value injected by FXMLLoader

    @FXML // fx:id="boutonRetour"
    private Button boutonRetour; // Value injected by FXMLLoader

    @FXML // fx:id="panePrincipal"
    private Pane panePrincipal; // Value injected by FXMLLoader

    @FXML // fx:id="paneInfoFin"
    private Pane paneInfoFin; // Value injected by FXMLLoader

    @FXML // fx:id="labelTempsFinal"
    private Label labelTempsFinal; // Value injected by FXMLLoader

    public static int[] taillesInit;
    
    public static String difficulted;
    
    public static AtomicBoolean isFirstRightClick;
    
    public static Demineur theDemineur;
    
    public static Controlleur theControlleur;
    
    private int seconds = 0;
    
    private Timeline timeline;
    
    @FXML
    void clickRestart(ActionEvent event) {
    	if (Main.showConfirmationDialog()) {
        	initialize();
        }
    }

    @FXML
    void clickRetour(ActionEvent event) {
        if (Main.showConfirmationDialog()) {
        	Main.chargerSceneDebut();
        }
    }

    private void afficherGagnerPerdu(boolean gagner) {
    	if (gagner) {
    		labelVicDef.setText("Gagner");
    		labelTempsFinal.setText(labelTemps.getText());
    	} else {
    		labelVicDef.setText("Perdu");
    		labelTempsFinal.setText(labelTemps.getText());
    	}
    	paneInfoFin.setVisible(true);
    }
    
    private void generateGrid(int cellSize, int gridSize, int gridSize2) {
    	
    	boolean estPerdu = (theDemineur == null) ? false : theDemineur.isPartiePerdu();;
    	
    	paneJeu.getChildren().clear();
	    for (int x = 0; x < gridSize; x++) {
	        for (int y = 0; y < gridSize2; y++) {
	            Rectangle cell = new Rectangle(y * cellSize, x * cellSize, cellSize, cellSize);
	            cell.setFill(Color.GREY);
	            cell.setStroke(Color.BLACK);
	            
	            final int cellX = x;
	            final int cellY = y;
	            
	            if (!estPerdu) {
	            	
	            	cell.setOnMouseClicked(event -> {
		                if (event.getButton() == MouseButton.PRIMARY) {
		                	
		                	if (isFirstRightClick.get()) {
		                		isFirstRightClick.set(false);
		                		
		                		int[] coords = new int[] {cellX, cellY};
		                		theDemineur = Generateur.genererNewDemineur(taillesInit[0], taillesInit[1], taillesInit[2], coords);
		                		theControlleur = new Controlleur(theDemineur);
		                		startChronometer();
		                	} else {
		                		if (!theDemineur.estMarquee(cellX, cellY)) {
		                			boolean click = theControlleur.cliquerCase(cellX, cellY);
			                		if (click) {
										if (theDemineur.isPartieGagne()) {
											stopChronometer();
											afficherGagnerPerdu(true);
										}
									}
		                		}
		                	}
		                    // Clic gauche
		                	
		                } else if (event.getButton() == MouseButton.SECONDARY) {
		                    // Clic droit
		                	if (theControlleur != null) {
		                		theControlleur.cliqueDroitCase(cellX, cellY);
		                	}
		                } else if (event.getButton() == MouseButton.MIDDLE) {
		                	
		                    // Clic gauche + Clic droit (simultanés)
		                	if (theControlleur != null) {
		                		boolean click = theControlleur.cliquerCase(cellX, cellY);
			                	if (!click) {
			                		if (theControlleur.cliqueDroitEtGauche(cellX, cellY)){
			                			afficherGagnerPerdu(false);
			                		}
			                	}
		                	}
		                    
		                }
		                generateGrid(cellSize, gridSize, gridSize2);
		            });
	            	
	            } 
	            
	            cell.setOnMouseEntered(event -> {
	                // Changement de couleur lors du survol de la souris
	                cell.setFill(Color.LIGHTGRAY);
	            });
	
	            cell.setOnMouseExited(event -> {
	            	if (theDemineur != null) {
		            	if (theDemineur.estCaseCachee(cellX, cellY)) {
		            		cell.setFill(Color.GREY);
		            	} else {
		            		cell.setFill(Color.WHITE);
		            	}	
	            	} else {
	            		cell.setFill(Color.GREY);
	            	}   	
	            });
	            
	            if (theDemineur != null ) {
	            	
	            	if (theDemineur.estCaseBombe(cellX, cellY) && estPerdu) {
	            		
	            		ImageView bomb = new ImageView(new Image("application/bombe.png"));
	            		bomb.setFitWidth(cell.getWidth());
	                	bomb.setFitHeight(cell.getHeight());
	                	bomb.setX(cell.getX() + (cell.getWidth() / 2) - 12);
	                	bomb.setY(cell.getY() + (cell.getHeight() / 2) - 11);
	                    
	                    paneJeu.getChildren().add(cell);
                		paneJeu.getChildren().add(bomb);
	            		
	                } else if (theDemineur.estMarquee(x, y)){
	                	
	                	ImageView flag = new ImageView(new Image("application/flag.png"));
	                	flag.setFitWidth(cell.getWidth());
	                	flag.setFitHeight(cell.getHeight());
	                    flag.setX(cell.getX() + (cell.getWidth() / 2) - 12);
	                    flag.setY(cell.getY() + (cell.getHeight() / 2) - 11);
	                    
	                    paneJeu.getChildren().add(cell);
                		paneJeu.getChildren().add(flag);
	                    
	                } else if (!theDemineur.estCaseCachee(cellX, cellY)) {
	                	
	                	int numero = theDemineur.getNumero(cellX, cellY);
	                	cell.setFill(Color.WHITE);
	                	if (numero != 0) {
	                		Text number = new Text("" + numero);
	                		number.setX(cell.getX() + (cell.getWidth() / 2) - 4);
	                		number.setY(cell.getY() + (cell.getHeight() / 2) + 4);
	                		number.setTextAlignment(TextAlignment.CENTER);

							switch (numero) { 
								case 1: number.setFill(Color.BLUE); break;
								case 2: number.setFill(Color.GREEN); break;
								case 3: number.setFill(Color.RED); break;
								case 4: number.setFill(Color.DARKBLUE); break;
								case 5: number.setFill(Color.BROWN); break;
								case 6: number.setFill(Color.TURQUOISE); break;
								case 7: number.setFill(Color.PINK); break;
								case 8: number.setFill(Color.GRAY); break; 
							}
	                		
	                		paneJeu.getChildren().add(cell);
	                		paneJeu.getChildren().add(number);
	                		
	                	} else {
	                		paneJeu.getChildren().add(cell);
	                	}
	                } else {
	                	paneJeu.getChildren().add(cell);
	                }
	            } else {
	            	paneJeu.getChildren().add(cell);
	            }
				
	            if (theControlleur != null) {
	            	labelNbBombes.setText("" + (taillesInit[2] - theControlleur.getDejaMarque()));
	            }
	        }
	    }
	    if (estPerdu) {
	        afficherGagnerPerdu(false);
	        stopChronometer();
        } else if (theDemineur != null && theDemineur.isPartieGagne()) {
			stopChronometer();
			afficherGagnerPerdu(true);
		}
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert paneBombeRest1 != null : "fx:id=\"paneBombeRest1\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert labelDificulte != null : "fx:id=\"labelDificulte\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert labelTemps != null : "fx:id=\"labelTemps\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert labelVicDef != null : "fx:id=\"labelVicDef\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert paneDificulte != null : "fx:id=\"paneDificulte\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert boutonRestart != null : "fx:id=\"boutonRestart\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert paneJeu != null : "fx:id=\"paneJeu\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert paneBombeRest != null : "fx:id=\"paneBombeRest\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert labelNbBombes != null : "fx:id=\"labelNbBombes\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert boutonRetour != null : "fx:id=\"boutonRetour\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert panePrincipal != null : "fx:id=\"panePrincipal\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert paneInfoFin != null : "fx:id=\"paneInfoFin\" was not injected: check your FXML file 'SceneJeu.fxml'.";
        assert labelTempsFinal != null : "fx:id=\"labelTempsFinal\" was not injected: check your FXML file 'SceneJeu.fxml'.";

        paneInfoFin.setVisible(false);
        labelDificulte.setText(difficulted);
        labelNbBombes.setText("" + taillesInit[2]);
        labelTemps.setText("00:00");
        
        theDemineur = null;
        theControlleur = null;
        isFirstRightClick = new AtomicBoolean(true);
        
        generateGrid(24, taillesInit[0], taillesInit[1]);
        
    }
    
    private void startChronometer() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            return; // Le chronomètre est déjà en cours d'exécution
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() { 
		        	@Override
		        	public void handle(ActionEvent event) {
		        		seconds++;
		                updateDisplay();
		        }
	        }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
	private void stopChronometer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
    
	private void updateDisplay() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        String time = String.format("%02d:%02d", minutes, remainingSeconds);
        labelTemps.setText(time);
    }
}
