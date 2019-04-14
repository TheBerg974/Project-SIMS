/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frankie
 */
public class MainMenuDocumentController implements Initializable {

    @FXML
    private Label labelMainMenu;

    @FXML
    private Button buttonBlankScenario;

    @FXML
    private Button buttonEarthSun;

    @FXML
    private Button buttonEarthMoon;
    

    @FXML
    public void handleButtonBlankScenarioAction(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("OrbitalSimulator");
            scene.getRoot().requestFocus();
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleButtonEarthSunAction(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EarthSunDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Orbital Simulator (Earth-Sun system)");
            scene.getRoot().requestFocus();
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleButtonEarthMoonAction(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EarthMoonDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Orbital Simulator (Earth-Moon system)");
            scene.getRoot().requestFocus();
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleButtonMichaelBayModeAction(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MichaelBayMode.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("MICHAEL BAY MODE!!!");
            scene.getRoot().requestFocus();
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
