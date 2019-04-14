/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class MainMenuPane extends Pane{
    
    private Button pendulumSimulation;
    private Button fourierSeriesSimulation;
    private Button boidsSimulation;
    private Button solarSystemSimulation;
    private ProjectSIMS project;
    
    public MainMenuPane() {
        initializePane();
    }
    
    public void initializePane(){
        this.setStyle("-fx-background-color: black;");
        
        File file = new File("./assetsMenu/background.gif");
        Image img = new Image(file.toURI().toString());
        ImageView imgView = new ImageView(img);
        
        File file1 = new File("./assetsMenu/sims.png");
        Image img2 = new Image(file1.toURI().toString());
        ImageView imgView1 = new ImageView(img2);
        imgView1.setFitHeight(175);
        imgView1.setFitWidth(150);
        imgView1.setLayoutX(300);
        imgView1.setLayoutY(10);
        
        Label projectLabel = initializeLabel(150, 100, "Project", Color.WHITE);
        
        
        project = new ProjectSIMS();
        
        fourierSeriesSimulation = initializeButton(100, 250, "Fourier Series");
        boidsSimulation = initializeButton(300, 250, "Boids Simulation");
        solarSystemSimulation = initializeButton(100, 350, "Solar system");
        pendulumSimulation = initializeButton(300, 350, "Pendulum");
        
        pendulumSimulation.setOnAction((ActionEvent e) -> {
            try {
                project.openPendulumSimulation();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        boidsSimulation.setOnAction((ActionEvent e) -> {
            try {
                project.openBoidsSimulation();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        solarSystemSimulation.setOnAction((ActionEvent e) -> {
            try {
                project.openSolarSimulation();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        fourierSeriesSimulation.setOnAction((ActionEvent e) -> {
            ProjectSIMS.changePane(new EpicyclePane(), 950, 500);
        });
        
        this.getChildren().addAll(imgView,imgView1, projectLabel, fourierSeriesSimulation, pendulumSimulation, boidsSimulation, solarSystemSimulation);
    }
    
    private Button initializeButton(double x, double y, String s) {
        Button button = new Button(s);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
    
    private Label initializeLabel(double x, double y, String initialText, Color color) {
        Label label = new Label();
        label.setTextFill(color);
        label.setScaleX(5.2);
        label.setScaleY(5.2);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setText(initialText);
        return label;
    }
}
