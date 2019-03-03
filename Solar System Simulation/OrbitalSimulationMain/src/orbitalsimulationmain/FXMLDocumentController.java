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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    ArrayList<CheckBox> objectArrayList = new ArrayList<>();
    
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
        System.exit(0);
    }
    
    @FXML
    private void handleMouseClickAction(MouseEvent me) {
        
        //onClick, add a planet based on what the textfields and check marks say. Disable other checkboxes (arraylist)
        //if large objects >2, pop up window saying "you can't add more!", and prevent them from doing so (disable textfields and checkboxes)
        
    }
    
    @FXML
    private void onCheckBoxAction (ActionEvent ae) {
        //iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
        for (CheckBox cbox: objectArrayList) {
            int selectedCounter = 0;
            if (cbox.isSelected()) {
                selectedCounter++;
            }
            else if (!cbox.isSelected()) {
                cbox.setDisable(true);
            }
            else if (!cbox.isSelected() && selectedCounter == 0) {
                cbox.setDisable(false);
            }
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        objectArrayList.add(checkBoxPlanet);
        objectArrayList.add(checkBoxStar);
        objectArrayList.add(checkBoxAsteroid);
        objectArrayList.add(checkBoxEarth);
        objectArrayList.add(checkBoxSun);
        objectArrayList.add(checkBoxPhiOrionis);

        // LOAD SHIT
        //set background of region
    }    
    
}
