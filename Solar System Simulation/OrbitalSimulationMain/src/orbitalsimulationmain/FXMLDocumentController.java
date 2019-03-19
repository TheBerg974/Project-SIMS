/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

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
import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    ArrayList<CheckBox> objectArrayList = new ArrayList<>();
    ArrayList<CheckBox> presetArrayList = new ArrayList<>();

    ArrayList<TextField> textfieldArrayList = new ArrayList<>();
    
    ArrayList<LargeBody> largeBodyArrayList = new ArrayList<>();

    boolean selected = false;

    @FXML
    private AnchorPane panel;
    @FXML
    private AnchorPane UI;

    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonTime100;
    @FXML
    private Button buttonTime500;

    @FXML
    private Label labelMass;
    @FXML
    private Label labelRadius;
    @FXML
    private Label labelVelocity;
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
    private TextField textFieldVelocity;

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
    
    //The following 7 variables are used in the initialize method
    private double mass= 0;
    private double radius = 0;
    private double velocity = 0;
    //This value is used to find the time at which the object was on its previous step. 
    //For example: If object A is at step x at time y, and it takes 3 seconds per step, then previousTimeStep is time y-3, for step x-1
    private double previousTimeStep = 0.0;   
    
    private CelestialBody body; //Unfuck this variable. Make it point to the bodies one can create on the GUI 
    private Vector2D velocityVector;
    private Vector2D positionVector;
    
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        //TODO: Will send the user back to the program's main menu.
        System.exit(0); //for now, it's going to exit from the entire program
    }

    @FXML
    private void handleMouseClickAction(MouseEvent me) {
        //CelestialBody cb = new CelestialBody(handleTextFieldVelocityAction(ae),handleTextFieldMassAction(ae),handleTextFieldRadiusAction(ae),me.getSceneX(),me.getSceneY());
        CelestialBody cb = new CelestialBody(0, mass, radius, me.getSceneX(), me.getSceneY());
        addToPane(cb.getCircle());
        if (me.getSceneY() > 700) {
            removeFromPane(cb.getCircle());
        } //this line SHOULD remove any circles below 700 pixels, but IT DOESN'T WORK
        
        //onClick, add a planet based on what the textfields and check marks say. Disable other checkboxes (arraylist)
        //if large objects >2, pop up window saying "you can't add more!", and prevent them from doing so (disable textfields and checkboxes)
    }

    @FXML
    private void handleTextFieldMassAction(ActionEvent ae) {
        mass = Double.parseDouble(textFieldMass.getText());
    }

    @FXML
    private void handleTextFieldRadiusAction(ActionEvent ae) {
        radius = Double.parseDouble(textFieldRadius.getText());
    }

    @FXML
    private void handleTextFieldVelocityAction(ActionEvent ae) {
        velocity = Double.parseDouble(textFieldVelocity.getText());
    }

    @FXML
    private void onCheckBoxAction(ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        for (CheckBox cbox : objectArrayList) {
            if (cbox.isSelected()) {
                selected = !selected;
            } else if (!cbox.isSelected()) {
                cbox.setDisable(true);
            } else if (!cbox.isSelected() && selected == false) {
                cbox.setDisable(false);
            }
        }
        for (CheckBox cb : presetArrayList) {
            if (cb.isSelected()) {
                selected = !selected;
                for (TextField tf : textfieldArrayList) {
                    tf.setDisable(true);
                }
            }
            if (!cb.isSelected()) {
                cb.setDisable(true);
            } else if (!cb.isSelected() && selected == false) {
                cb.setDisable(false);
                for (TextField tf : textfieldArrayList) {
                    tf.setDisable(false);
                }
            }            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        textfieldArrayList.add(textFieldVelocity);
        
        for (CheckBox cb : objectArrayList) {
            cb.requestFocus();
            CelestialBody obj;
            //if any space object on the pane intersects any component, then delete that object
            
            //if (cb.getBoundsInLocal().intersects()){ 
                
            //}
        }
        
        for (CheckBox cb : objectArrayList) {
           cb.requestFocus();
        }
        
        for (TextField tf : textfieldArrayList) {
           tf.requestFocus();
        }
	/*
        //Positions of the CelestialBody
        double bodyPosX = body.getxCoordinate();
	double bodyPosY = body.getyCoordinate();
        
        //Instantiating a position and velocity vector for a CelestialBody
        positionVector = new Vector2D(bodyPosX, bodyPosY, 0);
        velocityVector = new Vector2D(0,0,0);
        
        //Time value when simulation begins
        long initialTime = System.nanoTime();
        Vector2D gravitationalForce = new Vector2D(122,122,0); //TODO!!!!!!
        
        new AnimationTimer() {
            @Override
            public void handle(long instantTime) {
                //Calculates time values (current time, change in time, and the time value of the previous step)
                double currentTime = (instantTime - initialTime) / 1000000000.0;
                double deltaTime = currentTime - previousTimeStep;
                previousTimeStep = currentTime;
                
                //Changing the velocity of the object in real-time using Euler's Method of Integration
                Vector2D acceleration = gravitationalForce.mult(deltaTime);
                velocityVector = velocityVector.add(acceleration);
                positionVector = positionVector.add(velocityVector.mult(deltaTime));
                
                //The following code updates the position of the Celestial Bodies
                body.setxCoordinate(positionVector.getX());
                body.setyCoordinate(positionVector.getY());
            }   
        }.start();
        */
        Circle c = new Circle(200, 200, 20);
        
        addToPane(c);
        // LOAD SHIT
        //set background of region
    }

    public void addToPane(Node node) {
        UI.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        UI.getChildren().add(node);
    }
}

/*TODO FOR GUI: 

1) UPDATE OBJECT TYPE AND CONSTRUCTOR BASED ON CHECKBOX PREFERENCES
2) LOGIC FOR ENABLING AND RE-ENABLING TEXTFIELDS AND CHECKBOXES
    i) IF ALL CBOXES ARE UNCHECKED, THEN RE-ENABLE THEM ALL
    ii) IF A PRESET CHECKBOX IS CHOSEN, THE TEXTFIELDS ARE DISABLED
3) MAKE CELESTIALBODY OBJECTS ADDABLE TO THE PANE (ONLY CIRCLES WORK, REFER TO INITIALIZE() METHOD FOR MORE INFO)
*OPTIONAL* MAKE OBJECTS TRACE THEIR PATH WHEN MOVING ON-SCREEN
4) ENSURE THAT OBJECTS CANNOT BE ADDED OVER THE BUTTONS, CHECKBOXES AND TEXTFIELDS. (PUT THEM OUT OF FOCUS)
5) HOW TO MAKE IT SO THAT THERE'S NO NULLPOINTEREXCEPTION? SO THAT THE CELESTIALBODY OBJECT NAMED 'BODY' POINTS TO THE OBJECTS CREATED IN THE GUI?
*/