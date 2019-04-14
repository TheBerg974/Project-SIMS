/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import AssetMgr.AssetManager;
import CelestialBody.CelestialBody;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frankie
 */
public class EarthMoonDocumentController implements Initializable {

    ArrayList<CheckBox> objectArrayList = new ArrayList<>();
    ArrayList<CheckBox> presetArrayList = new ArrayList<>();
    ArrayList<TextField> textfieldArrayList = new ArrayList<>();

    ArrayList<CelestialBody> cbArrayList = new ArrayList<>();
    ArrayList<Node> removeNodeList = new ArrayList<>();
    ArrayList<CelestialBody> deadList = new ArrayList<>();

    //Variable to determine if a check box was selected or not
    boolean selected = false;
    boolean presetSelected = false;

    //The following variables are used in the initialize method
    private double mass = 0;
    private double radius = 0;
    private double velocityX = 0;
    private double velocityY = 0;

    //This value is used to find the time at which the object was on its previous step. 
    //For example: If object A is at step x at time y, and it takes 3 seconds per step, then previousTimeStep is time y-3, for step x-1
    private double previousTimeStep = 0.0;

    //Used for collision handling
    double massRatio = 0;
    double massRatio2 = 0;

    //These strings are assigned to a checkbox. If they are selected, these strings will determine the CelestialBodies' attributes
    String objectType;
    String presetType;

    @FXML
    private AnchorPane UI; //REMOVE STATIC?

    @FXML
    private Button buttonSpeedTimesFive;

    @FXML
    private Button buttonSpeedTimesTen;

    @FXML
    private Label labelpopUpInfo;

//	@FXML
//	Rectangle rect;
    @FXML
    private void handleMouseClickAction(MouseEvent me) {
        Stage popUpWindow = new Stage();
        popUpWindow.setHeight(300);
        popUpWindow.setWidth(500);
        popUpWindow.setTitle("No");
        popUpWindow.setResizable(false);

        labelpopUpInfo = new Label();
        labelpopUpInfo.setText("Please refrain from clicking on the screen, just let the simulation run!");

        StackPane window = new StackPane();
        window.getChildren().add(labelpopUpInfo);

        Scene scene = new Scene(window, 400, 300);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //This will ensure that the components are *never* covered up!
        UI.toBack();

        //Preloads all assets for project
        AssetManager.preloadAllAssets();

        Background background = AssetManager.getBackgroundImage();
        //set background gif
        UI.setBackground(background);
        
        CelestialBody Moon = new CelestialBody(new Vector2D(50, 0), 0.12, 27, new Vector2D(575, 725));
        CelestialBody Earth = new CelestialBody(new Vector2D(0, 0), 10, 100, new Vector2D(600, 450));

        Earth.setFill(AssetManager.getEarthImage());
        Moon.setFill(AssetManager.getMoonImage()); //Get a moon image

        cbArrayList.add(Moon);
        cbArrayList.add(Earth);

        addToPane(Earth);
        addToPane(Moon);

        //Time value when simulation begins
        long initialTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long instantTime) {
                //Calculates time values (current time, change in time, and the time value of the previous step)
                double currentTime = (instantTime - initialTime) / 1000000000.0;
                double deltaTime = currentTime - previousTimeStep;
                previousTimeStep = currentTime;
                SimulationPhysics.startSimulation(instantTime,cbArrayList,removeNodeList,deadList, UI, null, currentTime, deltaTime, previousTimeStep);
            }
        }.start();
    }

    /**
     * Will be used for the 'Clear pane ' button. Removes all CelestialBodies on
     * the screen.
     *
     * @param arrayListCB an ArrayList containing all the celestial bodies on
     * the screen
     */
    public void clearPane(ArrayList<CelestialBody> arrayListCB) {
        for (CelestialBody cb : arrayListCB) {
            removeFromPane(cb);
            removeNodeList.add(cb);
            deadList.add(cb);
        }
    }

    /**
     * Adds an object to the pane to be visible
     *
     * @param node the object to be added
     */
    public void addToPane(Node node) { //3 STATICS
        UI.getChildren().add(node);
    }

    /**
     * Removes an object from the pane
     *
     * @param node the object to be removed
     */
    public void removeFromPane(Node node) {
        UI.getChildren().remove(node);
    }

    /**
     * This method deals with the event that a collision happens between 2
     * bodies
     *
     * @param cbAL ArrayList of all celestial bodies added to the UI
     */
    public void handleCollisions(ArrayList<CelestialBody> cbAL) { //remove static?
        massRatio = (cbAL.get(0).getMass() / cbAL.get(1).getMass());
        massRatio2 = (cbAL.get(1).getMass() / cbAL.get(0).getMass());
        System.out.println(massRatio);
        System.out.println(massRatio2);

        if ((massRatio > 0.5) && (massRatio <= 1) || (massRatio2 > 0.5) && (massRatio2 <= 1)) { //if 1st body is larger or equal to 50% of the other body's mass, they both annihilate
            for (CelestialBody cb : cbArrayList) {
                removeNodeList.add(cb);
                deadList.add(cb);
            }
        } else if (massRatio < 0.5) { //if body 1 is smaller than body 2, and they collide, body 1 breaks up, and is absorbed by body 2 
            removeNodeList.add(cbAL.get(0));
            cbAL.get(1).setMass(cbAL.get(1).getMass() + cbAL.get(0).getMass());
            deadList.add(cbAL.get(0));
        } else if (massRatio2 < 0.5) { //if body 2 is smaller than body 1, and they collide, body 1 breaks up, and is absorbed by body 2
            removeNodeList.add(cbAL.get(1));
            cbAL.get(0).setMass(cbAL.get(0).getMass() + cbAL.get(1).getMass());
            deadList.add(cbAL.get(1));
        } else if ((massRatio == 0.5) || (massRatio2 == 0.5)) {
            for (CelestialBody cb : cbArrayList) {
                removeNodeList.add(cb);
                deadList.add(cb);
            }
        }
    }

}
