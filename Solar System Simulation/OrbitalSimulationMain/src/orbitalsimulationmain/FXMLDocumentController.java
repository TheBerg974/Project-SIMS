/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        //goes back to the program's main menu.
        System.exit(0); //for now, it's gonna exit from the entire program
    }
    
    @FXML
    private void handleMouseClickAction(MouseEvent me) {
        CelestialBody cb = new CelestialBody(0,0,10,me.getSceneX(),me.getSceneY());
        addToPane(cb.getCircle());
        if(me.getSceneY() > 700) {
            removeFromPane(cb.getCircle());
        }
        //onClick, add a planet based on what the textfields and check marks say. Disable other checkboxes (arraylist)
        //if large objects >2, pop up window saying "you can't add more!", and prevent them from doing so (disable textfields and checkboxes)
    }
     
    @FXML
    private void handleTextFieldMassAction(ActionEvent ae) {
        CelestialBody cb = new CelestialBody(0, 0, 0, 0, 0);
        cb.setMass(Double.parseDouble(textFieldMass.getText()));
    }
    
    @FXML
    private void handleTextFieldRadiusAction(ActionEvent ae) {
        CelestialBody cb = new CelestialBody(0, 0, Double.parseDouble(textFieldRadius.getText()), 0, 0);
    }
    
    @FXML
    private void handleTextFieldVelocityAction(ActionEvent ae) {
        CelestialBody cb = new CelestialBody(Double.parseDouble(textFieldVelocity.getText()), 0, 0, 0, 0);

    }
    
    @FXML
    private void onCheckBoxAction (ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        
        for (CheckBox cbox: objectArrayList) {
            if (cbox.isSelected()) {
                selected = !selected;
            }
            else if (!cbox.isSelected()) {
                cbox.setDisable(true);
            }
            else if (!cbox.isSelected() && selected==false) {
                cbox.setDisable(false);
            }
            //TODO: IF ALL CBOXES ARE UNCHECKED, THEN RE-ENABLE THEM ALL
        }
        for (CheckBox cb: presetArrayList) {
            if (cb.isSelected()) {
                selected = !selected;
                for (TextField tf: textfieldArrayList) {
                    tf.setDisable(true);
                }
            }
            if (!cb.isSelected()) {
                cb.setDisable(true);
            }
            else if (!cb.isSelected() && selected==false) {
                cb.setDisable(false);
                for (TextField tf: textfieldArrayList) {
                    tf.setDisable(false);
                }
            }
            //TODO: IF ALL CBOXES ARE UNCHECKED, THEN RE-ENABLE THEM ALL
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        objectArrayList.add(checkBoxPlanet);
        objectArrayList.add(checkBoxStar);
        objectArrayList.add(checkBoxAsteroid);
        
        presetArrayList.add(checkBoxEarth);
        presetArrayList.add(checkBoxSun);
        presetArrayList.add(checkBoxPhiOrionis);

        textfieldArrayList.add(textFieldMass);
        textfieldArrayList.add(textFieldRadius);
        textfieldArrayList.add(textFieldVelocity);
        
        Circle c = new Circle(200,200,20);
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
