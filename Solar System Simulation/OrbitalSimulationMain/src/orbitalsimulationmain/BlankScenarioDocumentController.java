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
import javafx.event.ActionEvent;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author cstuser
 */
public class BlankScenarioDocumentController implements Initializable {

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
	private Button buttonPlay;
	@FXML
	private Button buttonTime100;
	@FXML
	private Button buttonTime500;
	@FXML
	private Button buttonCreateObject;
	@FXML
	private Button buttonClearScreen;

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
	private Label labelpopUpInfo;

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

	@FXML
	private void handleMouseClickAction(MouseEvent me) {
		try {
			if (cbArrayList.size() < 2) {
				CelestialBody cb = new CelestialBody(new Vector2D(velocityX, velocityY), mass, radius, new Vector2D(me.getSceneX(), me.getSceneY()));
				if (!presetSelected) {
					switch (objectType) {
						case "planet":
							cb.setFill(AssetManager.getNeptuneImage());
							break;
						case "star":
							cb.setFill(AssetManager.getSunImage()); //GET GENERIC STAR IMG
							break;
						case "asteroid":
							cb.setFill(AssetManager.getMarsImage()); //GET ASTEROID IMAGE
							break;
					}
				} else if (presetSelected) {
					switch (presetType) {
						case "earth":
							cb.setFill(AssetManager.getEarthImage());
							break;
						case "sun":
							cb.setFill(AssetManager.getSunImage());
							break;
						case "phi":
							cb.setFill(AssetManager.getMarsImage()); //GET PHI ORIONIS IMAGE
							break;
					}
				}
				cb.toBack();
				cbArrayList.add(cb);
				addToPane(cb);
			}
		} catch (NullPointerException npe) {
			Stage popUpWindow = new Stage();
			popUpWindow.setHeight(300);
			popUpWindow.setWidth(500);
			popUpWindow.setTitle("You cannot do that!");
			popUpWindow.setResizable(false);

			labelpopUpInfo = new Label();
			labelpopUpInfo.setText("Be sure to select an object or preset checkbox in order to create an object!");

			StackPane window = new StackPane();
			window.getChildren().add(labelpopUpInfo);

			Scene scene = new Scene(window, 400, 300);
			popUpWindow.setScene(scene);
			popUpWindow.show();
		}
	}

	@FXML
	private void handleCreateObjectButton(ActionEvent ae) {
		try {
			mass = Double.parseDouble(textFieldMass.getText());
			radius = Double.parseDouble(textFieldRadius.getText());
			velocityX = Double.parseDouble(textFieldVelocityX.getText());
			velocityY = Double.parseDouble(textFieldVelocityY.getText());
		} catch (Exception ime) { //FIND A LESS GENERAL EXCEPTION
			Stage popUpWindow = new Stage();
			popUpWindow.setHeight(300);
			popUpWindow.setWidth(400);
			popUpWindow.setTitle("Newton is crying! :\'(");
			popUpWindow.setResizable(false);

			labelpopUpInfo = new Label();
			labelpopUpInfo.setText("YOU MUST ONLY ENTER NUMBERS INTO THE TEXTFIELDS! \n\t\t\t THIS DISPLEASES THE MACHINE!");

			StackPane window = new StackPane();
			window.getChildren().add(labelpopUpInfo);

			Scene scene = new Scene(window, 400, 300);
			popUpWindow.setScene(scene);
			popUpWindow.show();
		}
	}

	@FXML
	private void onObjectCheckBoxAction(ActionEvent ae) {
		//iterates through the object type arraylist. Checks which check box is selected, then disables all other check boxes in the arraylist so they can't be added.
		int counter = 0;
		for (CheckBox cbox : objectArrayList) {
			if (!cbox.isSelected()) {
				cbox.setDisable(true);
				counter++;
			}
			//if any checkbox is selected, then we disable the other arraylist. This is done to prevent the user from choosing two conflicting object types (ex: they choose a Sun and a Planet checkbox).
			if (cbox.isSelected()) {
				presetSelected = false; //refer to handleMouseAction for this variable's use
				for (CheckBox cb : presetArrayList) {
					cb.setDisable(true);
				}
			}
			//If all 3 checkboxes are disabled, re-enable them all
			if (counter == 3) {
				for (CheckBox cb : objectArrayList) {
					cb.setDisable(false);
				}
				for (CheckBox c : presetArrayList) {
					c.setDisable(false);
				}
			}
		}
		//Depending on the checkbox chosen, the look of the celestial body will be different
		if (checkBoxPlanet.isSelected()) {
			objectType = "planet";
		} else if (checkBoxStar.isSelected()) {
			objectType = "star";
		} else if (checkBoxAsteroid.isSelected()) {
			objectType = "asteroid";
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

			//If any checkbox is currently selected, presetSelected becomes true. Refer to handleMouseClickAction to see why it is used
			//when any preset checkbox is selected, the object checkboxes become disabled temporarily
			if (cbox.isSelected()) {
				presetSelected = true;
				for (CheckBox cb : objectArrayList) {
					cb.setDisable(true);
				}
			}

			//If all 3 checkboxes are unselected, re-enable all textfields and checkboxes
			if (counter == 3) {
				for (CheckBox cb : presetArrayList) {
					cb.setDisable(false);
				}
				for (CheckBox cb : objectArrayList) {
					cb.setDisable(false);
				}
				for (TextField tf : textfieldArrayList) {
					tf.setDisable(false);
				}
			}
		}
		//Depending on the checkbox chosen, the parameters of the celestial body will be different
		if (checkBoxEarth.isSelected()) {
			presetType = "earth";
			textFieldMass.setText("0.009");
			textFieldRadius.setText("5");
		} else if (checkBoxSun.isSelected()) {
			presetType = "sun";
			textFieldMass.setText("100");
			textFieldRadius.setText("100");
		} else if (checkBoxPhiOrionis.isSelected()) {
			presetType = "phi";
			textFieldMass.setText("180");
			textFieldRadius.setText("200");
		}
	}
	
	@FXML
	private void handleClearScreenButtonAction(ActionEvent ae) {
		clearPane(cbArrayList);
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//This will ensure that the GUI is *never* covered up!
		UI.toBack();

		//Preloads all assets for project
		AssetManager.preloadAllAssets();

		//Creates a background size to input into the getBackgroundImage method
		BackgroundSize backgroundSize = new BackgroundSize(1218, 690, false, false, false, false);
		Background background = AssetManager.getBackgroundImage(backgroundSize);
		//set background gif
		UI.setBackground(background);
		
		//fills in button color
		buttonCreateObject.setStyle("-fx-background-color: #00ff00; ");
		
		//Sets the blue rectangle to the back of the pane, behind all components of the UI pne.
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

		//Time value when simulation begins
		long initialTime = System.nanoTime();

		new AnimationTimer() {
			@Override
			public void handle(long instantTime) {

				//Calculates time values (current time, change in time, and the time value of the previous step)
				double currentTime = (instantTime - initialTime) / 1000000000.0;
				double deltaTime = currentTime - previousTimeStep;
				previousTimeStep = currentTime;

				if (cbArrayList.size() == 2) {
					Vector2D gravitationalForce = SimulationPhysics.newtonsLaw(cbArrayList.get(0), cbArrayList.get(1));

					for (int i = 0; i < cbArrayList.size(); i++) {
						//force in the opposite direction. To be applied on the other body so that they move towards each other, and not in the same direction
						
						Vector2D antiGravForce = SimulationPhysics.newtonsLawOtherBody(cbArrayList.get(1), cbArrayList.get(0));
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
						//Collision handling, if the distance between both bodies is less than or equal to the sum of their radii, then they are colliding, and it must be handled.                     
						if (Math.hypot((cbArrayList.get(1).getCenterX() - cbArrayList.get(0).getCenterX()),(cbArrayList.get(1).getCenterY() - cbArrayList.get(0).getCenterY())) <=(cbArrayList.get(0).getRadius() + cbArrayList.get(1).getRadius())) {
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
	 * Will be used for the 'Clear pane ' button.
	 * Removes all CelestialBodies on the screen.
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
	 * This method deals with the event that a collision happens between 2 bodies
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

/*TODO FOR GUI: 
1) SHOW VECTORS OF MOTION OF PLANETS
6) ADD A GIF TO BACKGROUND (REEEEE)
9) LIMIT RADIUS TO >=3
11) MAKE SURE TO ADD INSTRUCTIONS TO THE GUI. FILL IN TEXTFIELDS, CLICK ON OBJECT/PRESET TYPE, CLICK GREEN BUTTON, THEN PUT ON GUI.
13) CLEAN UP CODE!!!
15) BE ABLE TO ADD MORE ASTEROIDS!
!!!!!!!!!!!CREATE 2 NEW SCENARIOS!!
 */
