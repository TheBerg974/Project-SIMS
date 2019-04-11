/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boidsgame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pegasus
 */
public class ArenaWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane panel;

    @FXML
    private Rectangle infoRectangle;
    @FXML
    private Button howToPlayButton;
    @FXML
    private Button gameSettingsButton;
    @FXML
    private Button backButton;
    @FXML
    private Button backToMenuButton;
    @FXML
    private Button boidsSettings;
    @FXML
    private Button predatorSettings;
    @FXML
    private Button playerSettings;
    @FXML
    private Button arenaSettings;
    @FXML
    private Button startGameButton;
    @FXML
    private Button backToSettingsButton;
    @FXML
    private Button applyChangesPredator;
    @FXML
    private Button applyChangesArena;
    @FXML
    private Button applyChangesBoid;
    @FXML
    private Label title;
    @FXML
    private Label information;
    @FXML
    private TextField maxSpeedNormalPredator;
    @FXML
    private TextField maxSpeedScaryPredator;
    @FXML
    private TextField maxForceNormalPredator;
    @FXML
    private TextField maxForceScaryPredator;
    @FXML
    private TextField nbOfPredators;
    @FXML
    private TextField time;
    @FXML
    private TextField mastSepRadius;
    @FXML
    private TextField numberOfMasts;
    @FXML
    private TextField springSepRadius;
    @FXML
    private TextField numberOfSprings;
    @FXML
    private TextField springinessTF;
    @FXML
    private TextField separationR;
    @FXML
    private TextField cohesionR;
    @FXML
    private TextField alignmentR;
    @FXML
    private TextField predatorR;
    @FXML
    private TextField playerR;
    @FXML
    private TextField separationF;
    @FXML
    private TextField cohesionF;
    @FXML
    private TextField alignmentF;
    @FXML
    private TextField predatorF;
    @FXML
    private TextField playerF;
    @FXML
    private TextField maxSpeedBoid;
    @FXML
    private TextField maxForceBoid;
    @FXML
    private TextField numberOfBoids;
    @FXML
    private CheckBox differentMassesCheck;

    Label subtitle1;
    Label subtitle2;
    Label subtitle3;
    Label subtitle4;
    Label subtitle5;
    Label subtitle6;
    Label subtitle7;
    Label subtitle8;
    Label subtitle9;
    Label subtitle10;
    Label subtitle11;
    Label subtitle12;
    Label subtitle13;
    Label subtitle14;
    Label subtitle15;
    Label subtitle16;
    Label subtitle17;
    Label subtitle18;

    Rectangle picture1;
    Rectangle picture2;
    Rectangle picture3;
    Rectangle picture4;

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    @FXML
    public void displayHowToPlay() {
        backButton.setVisible(false);
        howToPlayButton.setVisible(false);
        gameSettingsButton.setVisible(false);
        backToMenuButton.setVisible(true);
        try {
            startGame();
        } catch (IOException ex) {
            Logger.getLogger(ArenaWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void displaySettings() {
        //display correct buttons
        backButton.setVisible(false);
        howToPlayButton.setVisible(false);
        gameSettingsButton.setVisible(false);
        backToMenuButton.setVisible(true);
        boidsSettings.setVisible(true);
        predatorSettings.setVisible(true);
        playerSettings.setVisible(true);
        arenaSettings.setVisible(true);
        startGameButton.setVisible(true);
        backToSettingsButton.setVisible(false);
        applyChangesPredator.setVisible(false);
        applyChangesArena.setVisible(false);
        applyChangesBoid.setVisible(false);

        //hide text fields
        maxForceNormalPredator.setVisible(false);
        maxForceScaryPredator.setVisible(false);
        maxSpeedNormalPredator.setVisible(false);
        maxSpeedScaryPredator.setVisible(false);
        nbOfPredators.setVisible(false);
        time.setVisible(false);
        mastSepRadius.setVisible(false);
        numberOfMasts.setVisible(false);
        springSepRadius.setVisible(false);
        numberOfSprings.setVisible(false);
        springinessTF.setVisible(false);
        separationR.setVisible(false);
        cohesionR.setVisible(false);
        alignmentR.setVisible(false);
        predatorR.setVisible(false);
        playerR.setVisible(false);
        separationF.setVisible(false);
        cohesionF.setVisible(false);
        alignmentF.setVisible(false);
        predatorF.setVisible(false);
        playerF.setVisible(false);
        maxSpeedBoid.setVisible(false);
        maxForceBoid.setVisible(false);
        numberOfBoids.setVisible(false);
        information.setVisible(false);

        //set checkbox to invisible
        differentMassesCheck.setVisible(false);

        //set correct title
        title.setFont(new Font("Matura MT Script Capitals", 100));
        title.setText("Game Settings");
        title.toFront();
        title.setTextFill(Paint.valueOf("black"));
        title.setLayoutX(180);
        title.setLayoutY(5);

        //hide extra labels
        removeFromPane(picture1);
        removeFromPane(picture2);
        removeFromPane(picture3);
        removeFromPane(picture4);
        removeFromPane(subtitle1);
        removeFromPane(subtitle2);
        removeFromPane(subtitle3);
        removeFromPane(subtitle4);
        removeFromPane(subtitle5);
        removeFromPane(subtitle6);
        removeFromPane(subtitle7);
        removeFromPane(subtitle8);
        removeFromPane(subtitle9);
        removeFromPane(subtitle10);
        removeFromPane(subtitle11);
        removeFromPane(subtitle12);
        removeFromPane(subtitle13);
        removeFromPane(subtitle14);
        removeFromPane(subtitle15);
        removeFromPane(subtitle16);
        removeFromPane(subtitle17);
        removeFromPane(subtitle18);

        //remove info rectangle
        infoRectangle.setVisible(false);

    }

    //back to main projet window
    @FXML
    public void backToProject() {
        //TODO
    }

    @FXML
    public void backToMainMenu() {
        settingUpSettingsMenu();
        backToSettingsButton.setVisible(false);
        gameSettingsButton.setVisible(true);
        howToPlayButton.setVisible(true);
        backButton.setVisible(true);

        title.setFont(new Font("Matura MT Script Capitals", 80));
        title.setText("Boids Game");
        title.setLayoutY(0);
        title.setLayoutX(270);
    }

    //displays the Boids Settings page
    @FXML
    public void displayBoidsSettings() {
        settingUpSettingsMenu();
        //sets up title
        title.toFront();
        title.setFont(new Font("Matura MT Script Capitals", 80));
        title.setText("Boids Settings");
        title.setTextFill(Paint.valueOf("black"));
        title.setLayoutX(230);
        title.setLayoutY(0);

        //sets up all the labels used in the boids page
        subtitle1 = new Label();
        subtitle1.setFont(new Font("Cooper Black", 36));
        subtitle1.setText("Radii");
        subtitle1.setLayoutX(100);
        subtitle1.setLayoutY(190);
        addToPane(subtitle1);
        subtitle2 = new Label();
        subtitle2.setFont(new Font("Cooper Black", 36));
        subtitle2.setText("Forces");
        subtitle2.setLayoutX(400);
        subtitle2.setLayoutY(190);
        addToPane(subtitle2);
        subtitle3 = new Label();
        subtitle3.setFont(new Font("Cooper Black", 27));
        subtitle3.setText("Separation");
        subtitle3.setLayoutX(100);
        subtitle3.setLayoutY(240);
        addToPane(subtitle3);
        subtitle4 = new Label();
        subtitle4.setFont(new Font("Cooper Black", 27));
        subtitle4.setText("Cohesion");
        subtitle4.setLayoutX(100);
        subtitle4.setLayoutY(340);
        addToPane(subtitle4);
        subtitle5 = new Label();
        subtitle5.setFont(new Font("Cooper Black", 27));
        subtitle5.setText("Alignment");
        subtitle5.setLayoutX(100);
        subtitle5.setLayoutY(440);
        addToPane(subtitle5);
        subtitle6 = new Label();
        subtitle6.setFont(new Font("Cooper Black", 27));
        subtitle6.setText("Predator");
        subtitle6.setLayoutX(100);
        subtitle6.setLayoutY(540);
        addToPane(subtitle6);
        subtitle7 = new Label();
        subtitle7.setFont(new Font("Cooper Black", 27));
        subtitle7.setText("Player");
        subtitle7.setLayoutX(100);
        subtitle7.setLayoutY(640);
        addToPane(subtitle7);
        subtitle8 = new Label();
        subtitle8.setFont(new Font("Cooper Black", 27));
        subtitle8.setText("Separation");
        subtitle8.setLayoutX(400);
        subtitle8.setLayoutY(240);
        addToPane(subtitle8);
        subtitle9 = new Label();
        subtitle9.setFont(new Font("Cooper Black", 27));
        subtitle9.setText("Cohesion");
        subtitle9.setLayoutX(400);
        subtitle9.setLayoutY(340);
        addToPane(subtitle9);
        subtitle10 = new Label();
        subtitle10.setFont(new Font("Cooper Black", 27));
        subtitle10.setText("Alignment");
        subtitle10.setLayoutX(400);
        subtitle10.setLayoutY(440);
        addToPane(subtitle10);
        subtitle11 = new Label();
        subtitle11.setFont(new Font("Cooper Black", 27));
        subtitle11.setText("Predator");
        subtitle11.setLayoutX(400);
        subtitle11.setLayoutY(540);
        addToPane(subtitle11);
        subtitle12 = new Label();
        subtitle12.setFont(new Font("Cooper Black", 27));
        subtitle12.setText("Player");
        subtitle12.setLayoutX(400);
        subtitle12.setLayoutY(640);
        addToPane(subtitle12);
        subtitle13 = new Label();
        subtitle13.setFont(new Font("Cooper Black", 36));
        subtitle13.setText("Max Speed");
        subtitle13.setLayoutX(670);
        subtitle13.setLayoutY(190);
        addToPane(subtitle13);
        subtitle14 = new Label();
        subtitle14.setFont(new Font("Cooper Black", 36));
        subtitle14.setText("Max Force");
        subtitle14.setLayoutX(670);
        subtitle14.setLayoutY(320);
        addToPane(subtitle14);
        subtitle15 = new Label();
        subtitle15.setFont(new Font("Cooper Black", 36));
        subtitle15.setText("Number of Boids");
        subtitle15.setLayoutX(670);
        subtitle15.setLayoutY(450);
        addToPane(subtitle15);
        subtitle16 = new Label();
        subtitle16.setFont(new Font("Cooper Black", 36));
        subtitle16.setText("Different Masses?");
        subtitle16.setLayoutX(670);
        subtitle16.setLayoutY(580);
        addToPane(subtitle16);

        //sets up Separation Radius textfield
        separationR.setVisible(true);
        separationR.setLayoutX(115);
        separationR.setLayoutY(275);
        separationR.setText("" + ArenaSetup.getSeparationRadius());

        //sets up Cohesion Radius textfield
        cohesionR.setVisible(true);
        cohesionR.setLayoutX(115);
        cohesionR.setLayoutY(375);
        cohesionR.setText("" + ArenaSetup.getCohesionRadius());

        //sets up Alignment Radius textfield
        alignmentR.setVisible(true);
        alignmentR.setLayoutX(115);
        alignmentR.setLayoutY(475);
        alignmentR.setText("" + ArenaSetup.getAlignmentRadius());

        //sets up Predator Radius textfield
        predatorR.setVisible(true);
        predatorR.setLayoutX(115);
        predatorR.setLayoutY(575);
        predatorR.setText("" + ArenaSetup.getPredatorRadius());

        //sets up Player Radius textfield
        playerR.setVisible(true);
        playerR.setLayoutX(115);
        playerR.setLayoutY(675);
        playerR.setText("" + ArenaSetup.getPlayerRadius());

        //sets up Separation Force textfield
        separationF.setVisible(true);
        separationF.setLayoutX(415);
        separationF.setLayoutY(275);
        separationF.setText("" + ArenaSetup.getSepartionForce());

        //sets up Cohesion Force textfield
        cohesionF.setVisible(true);
        cohesionF.setLayoutX(415);
        cohesionF.setLayoutY(375);
        cohesionF.setText("" + ArenaSetup.getCohesionForce());

        //sets up Alignment Force textfield
        alignmentF.setVisible(true);
        alignmentF.setLayoutX(415);
        alignmentF.setLayoutY(475);
        alignmentF.setText("" + ArenaSetup.getAlignForce());

        //sets up Predator Force textfield
        predatorF.setVisible(true);
        predatorF.setLayoutX(415);
        predatorF.setLayoutY(575);
        predatorF.setText("" + ArenaSetup.getPredatorForce());

        //sets up Player Force textfield
        playerF.setVisible(true);
        playerF.setLayoutX(415);
        playerF.setLayoutY(675);
        playerF.setText("" + ArenaSetup.getPlayerForce());

        //sets up Maximum Speed textfield
        maxSpeedBoid.setVisible(true);
        maxSpeedBoid.setLayoutX(695);
        maxSpeedBoid.setLayoutY(235);
        maxSpeedBoid.setText("" + ArenaSetup.getMaxSpeedBoid());

        //sets up Maximum Force textfield
        maxForceBoid.setVisible(true);
        maxForceBoid.setLayoutX(695);
        maxForceBoid.setLayoutY(365);
        maxForceBoid.setText("" + ArenaSetup.getMaxForceBoid());

        //sets up Number of Boids textfield
        numberOfBoids.setVisible(true);
        numberOfBoids.setLayoutX(695);
        numberOfBoids.setLayoutY(495);
        numberOfBoids.setText("" + ArenaSetup.getNbOfBoids());

        //sets up Different Masses Checkbox
        differentMassesCheck.setVisible(true);
        differentMassesCheck.setLayoutX(695);
        differentMassesCheck.setLayoutY(625);
        differentMassesCheck.setSelected(ArenaSetup.isIsDifferentMass());

        //sets up the Apply Changes button
        applyChangesBoid.setVisible(true);
    }

    //apply changes to boids according to the user inputs
    @FXML
    public void applyChangesToBoids() {
        try {
            //obtaining Radius input
            double sepRadius = Double.parseDouble(separationR.getText());
            double cohRadius = Double.parseDouble(cohesionR.getText());
            double alignRadius = Double.parseDouble(alignmentR.getText());
            double predRadius = Double.parseDouble(predatorR.getText());
            double playerRadius = Double.parseDouble(playerR.getText());
            //obtaining Force input
            double sepForce = Double.parseDouble(separationF.getText());
            double cohForce = Double.parseDouble(cohesionF.getText());
            double alignForce = Double.parseDouble(alignmentF.getText());
            double predForce = Double.parseDouble(predatorF.getText());
            double playerForce = Double.parseDouble(playerF.getText());
            //obtaining extra input
            double maxSPEED = Double.parseDouble(maxSpeedBoid.getText());
            double maxFORCE = Double.parseDouble(maxForceBoid.getText());
            int nbOfBoids = Integer.parseInt(numberOfBoids.getText());
            boolean differentMassesBoid = differentMassesCheck.isSelected();

            //checks for errors, throw exception and save settings
            //  //check Radius
            if ((sepRadius < 0) || (cohRadius < 0) || (alignRadius < 0) || (predRadius < 0) || (playerRadius < 0)) {
                throw new Exception();
            } 
            //  //check Force
            else if ((sepForce < 0) || (cohForce < 0) || (alignForce < 0) || (predForce < 0) || (playerForce < 0)) {
                throw new Exception();
            } 
            //  //check extra
            else if ((maxSPEED < 0) || (maxFORCE < 0) || (nbOfBoids < 1)) {
                throw new Exception();
            } else {
                //sets radius based on inputs
                ArenaSetup.setSeparationRadius(sepRadius);
                ArenaSetup.setCohesionRadius(cohRadius);
                ArenaSetup.setAlignmentRadius(alignRadius);
                ArenaSetup.setPredatorRadius(predRadius);
                ArenaSetup.setPlayerRadius(playerRadius);
                //sets force based on inputs
                ArenaSetup.setSepartionForce(sepForce);
                ArenaSetup.setCohesionForce(cohForce);
                ArenaSetup.setAlignForce(alignForce);
                ArenaSetup.setPredatorForce(predForce);
                ArenaSetup.setPlayerForce(playerForce);
                //sets extra based on radius
                ArenaSetup.setMaxSpeedBoid(maxSPEED);
                ArenaSetup.setMaxForceBoid(maxFORCE);
                ArenaSetup.setNbOfBoids(nbOfBoids);
                ArenaSetup.setIsDifferentMass(differentMassesBoid);
            }
            areInputsAllRight(true);
        } catch (Exception e) {
            areInputsAllRight(false);
        }
    }

    //display the Predator Setings page
    @FXML
    public void displayPredatorSettings() {
        settingUpSettingsMenu();
        //sets up the title text
        title.toFront();
        title.setFont(new Font("Chiller", 100));
        title.setText("Predator Settings");
        title.setTextFill(Paint.valueOf("red"));
        title.setLayoutX(250);
        title.setLayoutY(20);

        //displays the predator picture
        picture1 = new Rectangle(65, 60);
        picture1.setFill(AssetManager.normalPredators(2));
        picture1.setX(200);
        picture1.setY(200);
        addToPane(picture1);

        //displays filled predator picture
        picture2 = new Rectangle(65, 60);
        picture2.setFill(AssetManager.flippedScaryPredators(2));
        picture2.setX(700);
        picture2.setY(200);
        addToPane(picture2);

        //displays max speed text for both predators
        subtitle1 = new Label();
        subtitle1.setFont(new Font("Cooper Black", 27));
        subtitle1.setText("Max Speed");
        subtitle1.setLayoutX(160);
        subtitle1.setLayoutY(290);
        addToPane(subtitle1);
        subtitle2 = new Label();
        subtitle2.setFont(new Font("Cooper Black", 27));
        subtitle2.setText("Max Speed");
        subtitle2.setLayoutX(660);
        subtitle2.setLayoutY(290);
        addToPane(subtitle2);
        subtitle3 = new Label();
        subtitle3.setFont(new Font("Cooper Black", 27));
        subtitle3.setText("Max Force");
        subtitle3.setLayoutX(160);
        subtitle3.setLayoutY(610);//////
        addToPane(subtitle3);
        subtitle4 = new Label();
        subtitle4.setFont(new Font("Cooper Black", 27));
        subtitle4.setText("Max Force");
        subtitle4.setLayoutX(660);
        subtitle4.setLayoutY(565);
        addToPane(subtitle4);
        subtitle5 = new Label();
        subtitle5.setFont(new Font("Cooper Black", 27));
        subtitle5.setText("Number of Predators");
        subtitle5.setLayoutX(160);
        subtitle5.setLayoutY(450);
        addToPane(subtitle5);

        //sets up the textbox for max speed normal predator
        maxSpeedNormalPredator.setVisible(true);
        maxSpeedNormalPredator.setLayoutX(185);
        maxSpeedNormalPredator.setLayoutY(330);
        maxSpeedNormalPredator.setText("" + ArenaSetup.getMaxSpeedPredator());

        //sets up the textbox for max speed scary predator
        maxSpeedScaryPredator.setVisible(true);
        maxSpeedScaryPredator.setLayoutX(685);
        maxSpeedScaryPredator.setLayoutY(330);
        maxSpeedScaryPredator.setText("" + ArenaSetup.getMaxSpeedScaryPredator());

        //sets up the textbox for max force normal predator
        maxForceNormalPredator.setVisible(true);
        maxForceNormalPredator.setLayoutX(185);
        maxForceNormalPredator.setLayoutY(650);
        maxForceNormalPredator.setText("" + ArenaSetup.getMaxForcePredator());

        //sets up the textbox for max force scary predator
        maxForceScaryPredator.setVisible(true);
        maxForceScaryPredator.setLayoutX(685);
        maxForceScaryPredator.setLayoutY(605);
        maxForceScaryPredator.setText("" + ArenaSetup.getMaxForceScaryPredator());

        //sets up the textbox for number of predators
        nbOfPredators.setVisible(true);
        nbOfPredators.setLayoutX(185);
        nbOfPredators.setLayoutY(490);
        nbOfPredators.setText("" + ArenaSetup.getNumberOfPredators());

        //makes the "Apply changes" button visible
        applyChangesPredator.setVisible(true);
    }

    //apply changes to Predator based on inputs
    @FXML
    public void applyChangesToPredators() {
        try {
            //obtaining and setting user inputs for normal predator
            double maxSpeedNP = Double.parseDouble(maxSpeedNormalPredator.getText());
            int numberNP = Integer.parseInt(nbOfPredators.getText());
            double maxForceNP = Double.parseDouble(maxForceNormalPredator.getText());
            //obtaining and setting user inputs for scary predator
            double maxForceSP = Double.parseDouble(maxForceScaryPredator.getText());
            double maxSpeedSP = Double.parseDouble(maxSpeedScaryPredator.getText());

            //checking validity of the inputs and throwing exception if needed
            if ((maxSpeedNP < 0) || (numberNP < 0) || (maxForceNP < 0) || (maxSpeedSP < 0) || (maxForceSP < 0)) {
                throw new Exception();
            } else {
                //saving normal predator settings
                ArenaSetup.setMaxSpeedPredator(maxSpeedNP);
                ArenaSetup.setNumberOfPredators(numberNP);
                ArenaSetup.setMaxForcePredator(maxForceNP);
                //saving scary predator settings
                ArenaSetup.setMaxForceScaryPredator(maxForceSP);
                ArenaSetup.setMaxSpeedScaryPredator(maxSpeedSP);
            }

            //is everything is allright, display message
            areInputsAllRight(true);

        } catch (Exception e) {
            //error message
            areInputsAllRight(false);
        }

    }

    @FXML
    public void displayPlayerSettings() {
        settingUpSettingsMenu();
        //sets up title
        title.toFront();
        title.setFont(new Font("Matura MT Script Capitals", 80));
        title.setText("Player Settings");
        title.setTextFill(Paint.valueOf("black"));
        title.setLayoutX(230);
        title.setLayoutY(0);
        
        //displays player 1 picture
        picture1 = new Rectangle(103, 90);
        picture1.setFill(AssetManager.playerGoingUp(1, true));
        picture1.setX(117.6);
        picture1.setY(250);
        addToPane(picture1);
        //displays player 2 picture
        picture2 = new Rectangle(103, 90);
        picture2.setFill(AssetManager.playerGoingUp(2, true));
        picture2.setX(338.2);
        picture2.setY(250);
        addToPane(picture2);
        //displays player 3 picture
        picture3 = new Rectangle(103, 90);
        picture3.setFill(AssetManager.playerGoingUp(3, true));
        picture3.setX(558.8);
        picture3.setY(250);
        addToPane(picture3);
        //displays player 4 picture
        picture4 = new Rectangle(103, 90);
        picture4.setFill(AssetManager.playerGoingUp(4, true));
        picture4.setX(779.4);
        picture4.setY(250);
        addToPane(picture4);
        
        //sets up all the labels used in the player page
        subtitle1 = new Label();
        subtitle1.setFont(new Font("Cooper Black", 36));
        subtitle1.setText("Player 1");
        subtitle1.setLayoutX(100);
        subtitle1.setLayoutY(190);
        addToPane(subtitle1);
        subtitle2 = new Label();
        subtitle2.setFont(new Font("Cooper Black", 36));
        subtitle2.setText("Player 2");
        subtitle2.setLayoutX(320.6);
        subtitle2.setLayoutY(190);
        addToPane(subtitle2);
        subtitle3 = new Label();
        subtitle3.setFont(new Font("Cooper Black", 36));
        subtitle3.setText("Player 3");
        subtitle3.setLayoutX(541.2);
        subtitle3.setLayoutY(190);
        addToPane(subtitle3);
        subtitle4 = new Label();
        subtitle4.setFont(new Font("Cooper Black", 36));
        subtitle4.setText("Player 4");
        subtitle4.setLayoutX(761.8);
        subtitle4.setLayoutY(190);
        addToPane(subtitle4);
        
        
    }

    //display the Arena Settings page
    @FXML
    public void displayArenaSettings() {
        settingUpSettingsMenu();
        title.toFront();
        title.setFont(new Font("Matura MT Script Capitals", 80));
        title.setText("Arena Settings");
        title.setTextFill(Paint.valueOf("black"));
        title.setLayoutX(230);
        title.setLayoutY(20);

        subtitle1 = new Label();
        subtitle1.setFont(new Font("Cooper Black", 36));
        subtitle1.setText("Time");
        subtitle1.setLayoutX(100);
        subtitle1.setLayoutY(190);
        addToPane(subtitle1);
        subtitle2 = new Label();
        subtitle2.setFont(new Font("Cooper Black", 36));
        subtitle2.setText("Masts");
        subtitle2.setLayoutX(430);
        subtitle2.setLayoutY(190);
        addToPane(subtitle2);
        subtitle3 = new Label();
        subtitle3.setFont(new Font("Cooper Black", 36));
        subtitle3.setText("Springs");
        subtitle3.setLayoutX(760);
        subtitle3.setLayoutY(190);
        addToPane(subtitle3);
        subtitle4 = new Label();
        subtitle4.setFont(new Font("Cooper Black", 27));
        subtitle4.setText("Separation Radius");
        subtitle4.setLayoutX(350);
        subtitle4.setLayoutY(340);
        addToPane(subtitle4);
        subtitle5 = new Label();
        subtitle5.setFont(new Font("Cooper Black", 27));
        subtitle5.setText("Number of Masts");
        subtitle5.setLayoutX(350);
        subtitle5.setLayoutY(440);
        addToPane(subtitle5);
        subtitle6 = new Label();
        subtitle6.setFont(new Font("Cooper Black", 27));
        subtitle6.setText("Separation Radius");
        subtitle6.setLayoutX(680);
        subtitle6.setLayoutY(340);
        addToPane(subtitle6);
        subtitle7 = new Label();
        subtitle7.setFont(new Font("Cooper Black", 27));
        subtitle7.setText("Number of Springs");
        subtitle7.setLayoutX(680);
        subtitle7.setLayoutY(440);
        addToPane(subtitle7);
        subtitle8 = new Label();
        subtitle8.setFont(new Font("Cooper Black", 27));
        subtitle8.setText("Springiness");
        subtitle8.setLayoutX(680);
        subtitle8.setLayoutY(540);
        addToPane(subtitle8);

        picture1 = new Rectangle();
        picture1.setWidth(70);
        picture1.setHeight(70);
        picture1.setLayoutX(445);
        picture1.setLayoutY(240);
        picture1.setFill(AssetManager.mastSkin(false));
        addToPane(picture1);
        picture2 = new Rectangle();
        picture2.setWidth(150);
        picture2.setHeight(30);
        picture2.setLayoutX(760);
        picture2.setLayoutY(250);
        picture2.setFill(AssetManager.springSkin(true));
        addToPane(picture2);

        time.setVisible(true);
        time.setLayoutX(115);
        time.setLayoutY(230);
        time.setText("" + ArenaSetup.getTime());

        mastSepRadius.setVisible(true);
        mastSepRadius.setLayoutX(365);
        mastSepRadius.setLayoutY(380);
        mastSepRadius.setText("" + ArenaSetup.getMastRadius());

        numberOfMasts.setVisible(true);
        numberOfMasts.setLayoutX(365);
        numberOfMasts.setLayoutY(480);
        numberOfMasts.setText("" + ArenaSetup.getNumberOfMasts());

        springSepRadius.setVisible(true);
        springSepRadius.setLayoutX(695);
        springSepRadius.setLayoutY(380);
        springSepRadius.setText("" + ArenaSetup.getSpringRadius());

        numberOfSprings.setVisible(true);
        numberOfSprings.setLayoutX(695);
        numberOfSprings.setLayoutY(480);
        numberOfSprings.setText("" + ArenaSetup.getNumberOfSprings());

        springinessTF.setVisible(true);
        springinessTF.setLayoutX(695);
        springinessTF.setLayoutY(580);
        springinessTF.setText("" + ArenaSetup.getSpringiness());

        applyChangesArena.setVisible(true);
    }

    //apply changes to Arena based on inputs
    @FXML
    public void applyChangesToArena() {
        try {
            //obtaining and setting user inputs for time 
            double timeTF = Double.parseDouble(time.getText());
            //obtaining and setting user inputs for masts 
            double separationRM = Double.parseDouble(mastSepRadius.getText());
            int numberM = Integer.parseInt(numberOfMasts.getText());
            //obtaining and setting user inputs for springs 
            double separationRS = Double.parseDouble(springSepRadius.getText());
            double springinessSTF = Double.parseDouble(this.springinessTF.getText());
            int numberSpring = Integer.parseInt(numberOfSprings.getText());

            //checking validity of the inputs and throwing exception if needed
            if ((timeTF < 5) || (separationRM <= 0) || (numberM <= 0) || (separationRS <= 0) || (springinessSTF <= 0) || (numberSpring <= 0)) {
                throw new Exception();
            } else {
                //saving time settings
                ArenaSetup.setTime(timeTF);
                //saving mast settings
                ArenaSetup.setMastRadius(separationRM);
                ArenaSetup.setNumberOfMasts(numberM);
                //saving spring settings
                ArenaSetup.setSpringRadius(separationRS);
                ArenaSetup.setSpringiness(springinessSTF);
                ArenaSetup.setNumberOfSprings(numberSpring);
            }
            //if everything is right, display message
            areInputsAllRight(true);
        } catch (Exception e) {
            //error message
            areInputsAllRight(false);
        }
    }

    //it starts the game with the settings
    @FXML
    public void startGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth() - 10, screenBounds.getHeight() - 40);
        Stage stage = new Stage();
        stage.setScene(scene);
        //enables the use of buttons
        scene.getRoot().requestFocus();
        //makes it so that the new window cannot be resized
        stage.setResizable(false);
        //makes it so that the player cannot go to the parent window while the game is active
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(title.getScene().getWindow());

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setting background
        AssetManager.getAllGameAssets();
        panel.setBackground(AssetManager.getMenuBackgroundImage(screenWidth + 10, screenHeight + 40));
        settingUpButtons();

    }

//creates all the buttons + location. disabled most of them for later use
    public void settingUpButtons() {
        //setting up Back to Main Project button
        backButton.setLayoutX(5);
        backButton.setLayoutY(5);

        //setting up Game Settings button
        gameSettingsButton.setPrefSize(400, 200);
        gameSettingsButton.setLayoutX(300);
        gameSettingsButton.setLayoutY(200);
        //gameSettingsButton.setVisible(false);

        //setting up How to Play button
        howToPlayButton.setPrefSize(400, 200);
        howToPlayButton.setLayoutX(300);
        howToPlayButton.setLayoutY(600);
        //howToPlayButton.setVisible(false);

        title.setFont(new Font("Matura MT Script Capitals", 80));
        title.setText("Boids Game");
        title.setLayoutY(0);
        title.setLayoutX(270);

        backToMenuButton.setLayoutX(5);
        backToMenuButton.setLayoutY(5);
        backToMenuButton.setVisible(false);

        boidsSettings.setPrefSize(250, 250);
        boidsSettings.setLayoutX(167);
        boidsSettings.setLayoutY(200);
        boidsSettings.setVisible(false);

        predatorSettings.setPrefSize(250, 250);
        predatorSettings.setLayoutX(584);
        predatorSettings.setLayoutY(200);
        predatorSettings.setVisible(false);

        playerSettings.setPrefSize(250, 250);
        playerSettings.setLayoutX(167);
        playerSettings.setLayoutY(500);
        playerSettings.setVisible(false);

        arenaSettings.setPrefSize(250, 250);
        arenaSettings.setLayoutX(584);
        arenaSettings.setLayoutY(500);
        arenaSettings.setVisible(false);

        startGameButton.setPrefSize(500, 100);
        startGameButton.setLayoutX(250);
        startGameButton.setLayoutY(850);
        startGameButton.setVisible(false);

        backToSettingsButton.setLayoutX(5);
        backToSettingsButton.setLayoutY(5);
        backToSettingsButton.setVisible(false);

        applyChangesPredator.setLayoutX(370);
        applyChangesPredator.setLayoutY(700);
        applyChangesPredator.setVisible(false);

        applyChangesArena.setLayoutX(370);
        applyChangesArena.setLayoutY(700);
        applyChangesArena.setVisible(false);

        applyChangesBoid.setLayoutX(370);
        applyChangesBoid.setLayoutY(750);
        applyChangesBoid.setVisible(false);

        maxForceNormalPredator.setVisible(false);
        maxForceScaryPredator.setVisible(false);
        maxSpeedNormalPredator.setVisible(false);
        maxSpeedScaryPredator.setVisible(false);
        nbOfPredators.setVisible(false);

        time.setVisible(false);
        mastSepRadius.setVisible(false);
        numberOfMasts.setVisible(false);
        springSepRadius.setVisible(false);
        numberOfSprings.setVisible(false);
        springinessTF.setVisible(false);

        separationR.setVisible(false);
        cohesionR.setVisible(false);
        alignmentR.setVisible(false);
        predatorR.setVisible(false);
        playerR.setVisible(false);
        separationF.setVisible(false);
        cohesionF.setVisible(false);
        alignmentF.setVisible(false);
        predatorF.setVisible(false);
        playerF.setVisible(false);
        maxSpeedBoid.setVisible(false);
        maxForceBoid.setVisible(false);
        numberOfBoids.setVisible(false);

        //hide checkbox
        differentMassesCheck.setVisible(false);

        information = new Label();
        information.setFont(new Font("Cooper Black", 27));
        information.setVisible(false);
        addToPane(information);

        infoRectangle.setHeight(300);
        infoRectangle.setWidth(1110);
        infoRectangle.setLayoutY(750);
        infoRectangle.setLayoutX(-50);
        infoRectangle.setFill(AssetManager.getInfoMenuBackground());
        infoRectangle.setVisible(false);

    }

    //creates disables most buttons from the game settings menu. 
    public void settingUpSettingsMenu() {
        boidsSettings.setVisible(false);
        playerSettings.setVisible(false);
        startGameButton.setVisible(false);
        arenaSettings.setVisible(false);
        predatorSettings.setVisible(false);
        backToMenuButton.setVisible(false);
        backToSettingsButton.setVisible(true);
    }

    public void areInputsAllRight(boolean theyAreFine) {
        if (theyAreFine) {
            infoRectangle.setVisible(true);
            information.setVisible(true);
            information.setLayoutX(390);
            information.setLayoutY(870);
            information.setTextFill(Paint.valueOf("green"));
            information.setText("Settings chaged!");
        } else {
            infoRectangle.setVisible(true);
            information.setVisible(true);
            information.setLayoutX(190);
            information.setLayoutY(870);
            information.setTextFill(Paint.valueOf("red"));
            information.setText("There seems to be a problem with the inputs!\n\t\t         Settings not changed.");
        }
    }

    public void addToPane(Node node) {
        panel.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        panel.getChildren().remove(node);
    }

}
