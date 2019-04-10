/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boidsgame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Pegasus
 */
public class BoidsGame extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ArenaWindow.fxml"));
        Scene scene = new Scene(root,1000,1000);
        
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();        
//        Scene scene = new Scene(root,screenBounds.getWidth()-10,screenBounds.getHeight()-40);
        stage.setScene(scene);
        //stage.setMaximized(true);              
        scene.getRoot().requestFocus();
        stage.show();
        stage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
