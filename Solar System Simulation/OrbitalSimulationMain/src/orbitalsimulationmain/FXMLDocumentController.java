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
import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    ArrayList<CheckBox> objectArrayList = new ArrayList<>();
    ArrayList<CheckBox> presetArrayList = new ArrayList<>();

    ArrayList<TextField> textfieldArrayList = new ArrayList<>();
    
    //ArrayList<LargeBody> largeBodyArrayList = new ArrayList<>();

    boolean selected = false;

    @FXML
    private AnchorPane panel;
    @FXML
    private AnchorPane UI;

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
    private void handleMouseClickAction(MouseEvent me) {
        //CelestialBody cb = new CelestialBody(handleTextFieldVelocityAction(ae),handleTextFieldMassAction(ae),handleTextFieldRadiusAction(ae),me.getSceneX(),me.getSceneY());
        CelestialBody cb = new CelestialBody(new Vector2D(15,15), mass, radius, new Vector2D(me.getSceneX(), me.getSceneY()));
        addToPane(cb);
        if (me.getSceneY() > 700) {
            removeFromPane(cb);
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
    private void onObjectCheckBoxAction(ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        int counter = 0;
        for (CheckBox cbox : objectArrayList) {
            if (cbox.isSelected()) {
                //objectArrayList.remove(cbox);
                
            }
            else if (!cbox.isSelected()) {
                cbox.setDisable(true);
                counter++;
            }
            
            if(counter ==3) {
                for(CheckBox cb: objectArrayList) {
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
            }
            else if (!cbox.isSelected()) {
                cbox.setDisable(true);
                counter++;
            }
            //If all 3 checkboxes are unselected, re-enable them, and re-enable the textfields
            if(counter ==3) {
                for(CheckBox cb: presetArrayList) {
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
        
        //if any space object on the pane intersects any component, then delete that object
            
        //if (cb.getBoundsInLocal().intersects()){ 

        CelestialBody body1 = new CelestialBody(new Vector2D(0,100),700,15,new Vector2D(300,300));
        CelestialBody body2 = new CelestialBody(new Vector2D(0,-100),30,10,new Vector2D(900,90));
		
        ArrayList<CelestialBody> cbArrayList = new ArrayList<>();
        cbArrayList.add(body1);
        cbArrayList.add(body2);
        
        //Time value when simulation begins
        long initialTime = System.nanoTime();
        
        addToPane(body1);
        addToPane(body2);
        new AnimationTimer() {
            @Override
            public void handle(long instantTime) {

                Vector2D gravitationalForce = SimulationPhysics.newtonsLaw(body1, body2);
                                
                //Calculates time values (current time, change in time, and the time value of the previous step)
                double currentTime = (instantTime - initialTime) / 1000000000.0;
                double deltaTime = currentTime - previousTimeStep;
                previousTimeStep = currentTime;
                
                //for (CelestialBody celb : cbArrayList) {   
                for (int i = 0; i < cbArrayList.size(); i++) {
                    //force in the opposite direction. To be applied on the other body so that they move towards each other, and not in the same direction
                    int j = i+1;
					if (j >= cbArrayList.size()) {
						j = 0;
					}
					//Vector2D gravitationalForce = SimulationPhysics.newtonsLaw(cbArrayList.get(i), cbArrayList.get(j));
					//WHAT I WOULD LIKE TO DO ABOVE IS DYNAMICALLY ENTER THE CELESTIALBODY INTO THE NEWTONSLAW METHOD
					//SO THAT I COULD HAVE >2 BODIES
					Vector2D antiGravForce = gravitationalForce.mult(-1); 
					/*
					Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(gravitationalForce.mult(-1* deltaTime));
					Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
					cbArrayList.get(i).setCoordinates(newPosition);
					cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);

					cbArrayList.get(i).setCenterX(newPosition.getX());
					cbArrayList.get(i).setCenterY(newPosition.getY());
					*/
					
					if (i == 1) {
                        Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(gravitationalForce.mult(deltaTime));
                        Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                        cbArrayList.get(i).setCoordinates(newPosition);
                        cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);
                    
                        cbArrayList.get(i).setCenterX(newPosition.getX());
                        cbArrayList.get(i).setCenterY(newPosition.getY());
                    }
                    else {
						Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(antiGravForce.mult(deltaTime));
                        Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                        cbArrayList.get(i).setCoordinates(newPosition);
                        cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);
                    
                        cbArrayList.get(i).setCenterX(newPosition.getX());
                        cbArrayList.get(i).setCenterY(newPosition.getY());
                    } 
                }
				//Collision handling
				double distanceX = body1.getCoordinates().getX() - body2.getCoordinates().getX(); //x-coordinate distance between both bodies
				double distanceY = body1.getCoordinates().getY() - body2.getCoordinates().getY(); //y-coordinate distance between both bodies
				double distanceTotal = Math.hypot(distanceX, distanceY);
				if (distanceTotal <= (body1.getRadius() + body2.getRadius())) { //if distance between 2 centers is as close, or closer than both of their radii, handle collision
					
				}
            }   
        }.start();

        //Circle c = new Circle(200, 200, 20);
        
       // addToPane(c);
        // LOAD SHIT
        //set background of region
    }

    public void addToPane(Node node) {
        UI.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        UI.getChildren().remove(node);
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
6) MAKE THE MATH UPDATE MORE OFTEN THAN THE GUI (60 VS 30 FPS FOR EXAMPLE)
*/