/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourierseries;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author berge
 */
public class FourierSeries extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        EpicyclePanel root = new EpicyclePanel();

        
        Scene scene = new Scene(root, 1200, 450);
        
        primaryStage.setTitle("FOURIER SERIES DEMONSTRATION");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}