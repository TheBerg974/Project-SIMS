/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author berge
 */
public class ProjectSIMS extends Application {
    
    public static Scene scene;
    public static Parent root;
    public static Stage window;
    
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        Parent root = new MainMenuPane();
        
        scene = new Scene(root, 500, 500);
        
        window.setTitle("FOURIER SERIES DEMONSTRATION");
        window.setScene(scene);
        window.show();

        window.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void openBoidsSimulation() throws Exception {
        root = FXMLLoader.load(getClass().getResource("ArenaWindow.fxml"));
        scene = new Scene(root, 1000, 1000);
        window.setScene(scene);
        root.requestFocus();
        window.show();
    }
    
    public void openPendulumSimulation() throws Exception {
        root = FXMLLoader.load(getClass().getResource("FXMLDocumentPendulum.fxml"));
        scene = new Scene(root, 1200, 900);
        window.setScene(scene);
        root.requestFocus();
        window.show();
    }
    
     public static void changePane(Pane p, double width, double height) {
        root = p;
        scene = new Scene(root, width, height);
        window.setScene(scene);
        p.requestFocus();
        window.show();
    }
     
    public void openSolarSimulation() throws Exception {
        root = FXMLLoader.load(getClass().getResource("MainMenuDocument.fxml"));
        scene = new Scene(root, 600, 400);
        window.setScene(scene);
        root.requestFocus();
        window.show();
    }
    
}
