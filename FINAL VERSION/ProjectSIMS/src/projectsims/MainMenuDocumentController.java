/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

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
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frankie
 */
public class MainMenuDocumentController implements Initializable {

	Font MICHAEL_BAY_MODE = new Font("Times New Roman", 14);
	Font simulationFont = new Font("Times new Roman", 12);

	@FXML
	private Label labelMainMenu;

	@FXML
	private Button buttonBlankScenario;

	@FXML
	private Button buttonEarthSun;

	@FXML
	private Button buttonEarthMoon;

	@FXML
	private Button buttonMichaelBayMode;

	@FXML
	private ImageView leftImage;

	@FXML
	private ImageView rightImage;

	@FXML
	private ImageView backgroundImage;

	@FXML
	public void handleButtonBlankScenarioAction(ActionEvent ae) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentSolar.fxml"));
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
		ProjectSIMS.changePane(new MainMenuPane(), 500, 500);
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		AssetManagerSolar.preloadAllAssets();
		//buttonMichaelBayMode.setStyle("-fx-background-color: #FFA500; ");	

		Label label = new Label();
		label.setFont(simulationFont);
		label.setText("Blank Scenario");
		buttonBlankScenario.setText(label.getText());
		buttonBlankScenario.setFont(simulationFont);

		Label label1 = new Label();
		label1.setFont(simulationFont);
		label1.setText("Earth-Moon System");
		buttonEarthMoon.setText(label1.getText());
		buttonEarthMoon.setFont(simulationFont);

		Label label2 = new Label();
		label2.setFont(simulationFont);
		label2.setText("Earth-Sun System");
		buttonEarthSun.setText(label2.getText());
		buttonEarthSun.setFont(simulationFont);
	}

}
