/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import CelestialBody.CelestialBody;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    ArrayList<CheckBox> objectArrayList = new ArrayList<>();
    ArrayList<CheckBox> presetArrayList = new ArrayList<>();
    ArrayList<TextField> textfieldArrayList = new ArrayList<>();

    ArrayList<CelestialBody> cbArrayList = new ArrayList<>();
    ArrayList<Node> removeNodeList = new ArrayList<>();
    ArrayList<CelestialBody> deadList = new ArrayList<>();

    boolean selected = false;
    double massRatio = 0;
    double massRatio2 = 0;

    @FXML
    private AnchorPane panel;
    @FXML
    private AnchorPane UI; //REMOVE STATIC?

    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonTime100;
    @FXML
    private Button buttonTime500;
    @FXML
    private Button buttonClearPane;
    @FXML
    private Button buttonCreateObject;

    @FXML
    private Label labelMass;
    @FXML
    private Label labelRadius;
    @FXML
    private Label labelVelocityX;
    @FXML
    private Label labelVelocityY;
    @FXML
    private Label labelChooseObject;
    @FXML
    private Label labelChoosePreset;
    @FXML
    private Label labelPlanet;
    @FXML
    private Label labelStar;
    @FXML
    private Label labelAsteroid;
    @FXML
    private Label labelEarth;
    @FXML
    private Label labelSun;
    @FXML
    private Label labelPhiOrionis;

    @FXML
    private TextField textFieldMass;
    @FXML
    private TextField textFieldRadius;
    @FXML
    private TextField textFieldVelocityX;
    @FXML
    private TextField textFieldVelocityY;

    @FXML
    private CheckBox checkBoxPlanet;
    @FXML
    private CheckBox checkBoxStar;
    @FXML
    private CheckBox checkBoxAsteroid;
    @FXML
    private CheckBox checkBoxEarth;
    @FXML
    private CheckBox checkBoxSun;
    @FXML
    private CheckBox checkBoxPhiOrionis;

    @FXML
    Rectangle rect;

    //The following variables are used in the initialize method
    private double mass = 0;
    private double radius = 0;
    private double velocityX = 0;
    private double velocityY = 0;

//This value is used to find the time at which the object was on its previous step. 
    //For example: If object A is at step x at time y, and it takes 3 seconds per step, then previousTimeStep is time y-3, for step x-1
    private double previousTimeStep = 0.0;

    private CelestialBody body; //Unfuck this variable. Make it point to the bodies one can create on the GUI 
    private Vector2D velocityVector;
    private Vector2D positionVector;

    @FXML
    private void handleMouseClickAction(MouseEvent me) {
        if (cbArrayList.size() < 2) {
            CelestialBody cb = new CelestialBody(new Vector2D(velocityX, velocityY), mass, radius, new Vector2D(me.getSceneX(), me.getSceneY()));
            cb.toBack();
            cbArrayList.add(cb);
            addToPane(cb);
            //onClick, add a planet based on what the textfields and check marks say. Disable other checkboxes (arraylist)
            //if large objects >2, pop up window saying "you can't add more!", and prevent them from doing so (disable textfields and checkboxes)
        }
    }

    @FXML
    private void handleCreateObjectButton(ActionEvent ae) {
        mass = Double.parseDouble(textFieldMass.getText());
        radius = Double.parseDouble(textFieldRadius.getText());
        velocityX = Double.parseDouble(textFieldVelocityX.getText());
        velocityY = Double.parseDouble(textFieldVelocityY.getText());
    }

    @FXML
    private void onObjectCheckBoxAction(ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        int counter = 0;
        for (CheckBox cbox : objectArrayList) {
            if (cbox.isSelected()) {
                //objectArrayList.remove(cbox);
            } else if (!cbox.isSelected()) {
                cbox.setDisable(true);
                counter++;
            }

            if (counter == 3) {
                for (CheckBox cb : objectArrayList) {
                    cb.setDisable(false);
                }
            }
        }
    }

    @FXML
    private void onPresetCheckBoxAction(ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        int counter = 0;
        for (CheckBox cbox : presetArrayList) {
            //if one preset checkbox is selected, disable all the textfields (they are not needed since the presets give values for their radius and mass)  
            if (cbox.isSelected()) {
                for (TextField tf : textfieldArrayList) {
                    tf.setDisable(true);
                }
            } else if (!cbox.isSelected()) {
                cbox.setDisable(true);
                counter++;
            }
            //If all 3 checkboxes are unselected, re-enable them, and re-enable the textfields
            if (counter == 3) {
                for (CheckBox cb : presetArrayList) {
                    cb.setDisable(false);
                }
                for (TextField tf : textfieldArrayList) {
                    tf.setDisable(false);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //This will ensure that the GUI is *never* covered up!
        UI.toBack();

        buttonCreateObject.setStyle("-fx-background-color: #00ff00; ");
        rect.toBack();

        //Adds Planet, Star and Asteroid checkboxes to ArrayList
        objectArrayList.add(checkBoxPlanet);
        objectArrayList.add(checkBoxStar);
        objectArrayList.add(checkBoxAsteroid);

        //Adds Earth, Sun, and Phi Orionis presets checkboxes to ArrayList
        presetArrayList.add(checkBoxEarth);
        presetArrayList.add(checkBoxSun);
        presetArrayList.add(checkBoxPhiOrionis);

        //Adds all the textfields to an ArrayList so they can be easily dealt with
        textfieldArrayList.add(textFieldMass);
        textfieldArrayList.add(textFieldRadius);

        CelestialBody body1 = new CelestialBody(new Vector2D(50, 0), 100, 50, new Vector2D(100, 600));
        CelestialBody body2 = new CelestialBody(new Vector2D(0, 15), 70, 10, new Vector2D(700, 300));

        //cbArrayList.add(body1);
        //cbArrayList.add(body2);
        //Time value when simulation begins
        long initialTime = System.nanoTime();

        // addToPane(body1);
        //addToPane(body2);
        new AnimationTimer() {
            @Override
            public void handle(long instantTime) {

                //Calculates time values (current time, change in time, and the time value of the previous step)
                double currentTime = (instantTime - initialTime) / 1000000000.0;
                double deltaTime = currentTime - previousTimeStep;
                previousTimeStep = currentTime;

                if (cbArrayList.size() == 2) {
                    Vector2D gravitationalForce = SimulationPhysics.newtonsLaw(cbArrayList.get(0), cbArrayList.get(1));
                    //WHAT I WOULD LIKE TO DO ABOVE IS DYNAMICALLY ENTER THE CELESTIALBODY INTO THE NEWTONSLAW METHOD
                    //SO THAT I COULD HAVE >2 BODIES
                    for (int i = 0; i < cbArrayList.size(); i++) {
                        //force in the opposite direction. To be applied on the other body so that they move towards each other, and not in the same direction

                        Vector2D antiGravForce = gravitationalForce.mult(-1);
                        if (i == 1) {
                            Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(gravitationalForce.mult(deltaTime));
                            Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                            cbArrayList.get(i).setCoordinates(newPosition);
                            cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);

                            cbArrayList.get(i).setCenterX(newPosition.getX());
                            cbArrayList.get(i).setCenterY(newPosition.getY());
                        } else {
                            Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(antiGravForce.mult(deltaTime));
                            Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                            cbArrayList.get(i).setCoordinates(newPosition);
                            cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);

                            cbArrayList.get(i).setCenterX(newPosition.getX());
                            cbArrayList.get(i).setCenterY(newPosition.getY());
                        }
                        //Collision handling                    
                        if (cbArrayList.get(0).getBoundsInParent().intersects(cbArrayList.get(1).getBoundsInParent())) { //if distance between 2 centers is as close, or closer than both of their radii, handle collision
                            System.out.println("i got here?");
                            handleCollisions(cbArrayList);
                        }
                    }
                } else if (cbArrayList.size() == 1) {
                    Vector2D newTangentialVelocity = cbArrayList.get(0).getTangentialVelocity();
                    Vector2D newPosition = cbArrayList.get(0).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                    cbArrayList.get(0).setCoordinates(newPosition);
                    cbArrayList.get(0).setTangentialVelocity(newTangentialVelocity);

                    cbArrayList.get(0).setCenterX(newPosition.getX());
                    cbArrayList.get(0).setCenterY(newPosition.getY());
                }
                for (Node node : removeNodeList) {
                    removeFromPane(node);
                }
                for (CelestialBody deadPlanet : deadList) {
                    cbArrayList.remove(deadPlanet);
                }
            }
        }.start();
    }

    /**
     * Will be used for the 'Clear pane ' button in order to delete all
     * CelestialBodies on the screen
     *
     * @param arrayListCB an ArrayList containing all the celestial bodies on
     * the screen
     */
    public void clearPane(ArrayList<CelestialBody> arrayListCB) {
        for (CelestialBody cb : arrayListCB) {
            removeFromPane(cb);
        }
    }

    /**
     * Adds an object to the pane to be visible
     *
     * @param node
     */
    public void addToPane(Node node) { //3 STATICS
        UI.getChildren().add(node);
    }

    /**
     * Removes an object from the pane
     *
     * @param node
     */
    public void removeFromPane(Node node) {
        UI.getChildren().remove(node);
    }

    /**
     * This method deals with the event that a collision happens between 2
     * bodies.
     *
     * @param body1
     * @param body2
     * @return
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

/*TODO FOR GUI: 

1)$###### UPDATE OBJECT TYPE AND CONSTRUCTOR BASED ON CHECKBOX PREFERENCES!!!!####
2) LOGIC FOR ENABLING AND RE-ENABLING TEXTFIELDS AND CHECKBOXES
    i) (DONE)IF ALL CBOXES ARE UNCHECKED, THEN RE-ENABLE THEM ALL
    ii) (DONE)IF A PRESET CHECKBOX IS CHOSEN, THE TEXTFIELDS ARE DISABLED
    
3)(DONE) MAKE CELESTIALBODY OBJECTS ADDABLE TO THE PANE (ONLY CIRCLES WORK, REFER TO INITIALIZE() METHOD FOR MORE INFO)
*OPTIONAL* MAKE OBJECTS TRACE THEIR PATH WHEN MOVING ON-SCREEN
4) ENSURE THAT OBJECTS CANNOT BE ADDED OVER THE BUTTONS, CHECKBOXES AND TEXTFIELDS. (PUT THEM OUT OF FOCUS)
5) (DONE) HOW TO MAKE IT SO THAT THERE'S NO NULLPOINTEREXCEPTION? SO THAT THE CELESTIALBODY OBJECT NAMED 'BODY' POINTS TO THE OBJECTS CREATED IN THE GUI?
6) ADD A GIF TO BACKGROUND
7) EXCEPTION HANDLING FOR TEXTFIELDS
8) PREVENT >2 BODIES FROM GOING ON SCREEN

 */
