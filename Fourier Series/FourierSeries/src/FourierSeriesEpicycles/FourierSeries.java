/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesEpicycles;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author berge
 */
public class FourierSeries extends Application {

    private static Scene scene;
    private static Stage window;
    private static Pane pane;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        pane = new EpicyclePane();

        scene = new Scene(pane, 950, 500);

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
    
    /**
     * 
     * @param p
     * @param width
     * @param height 
     */
    public static void changePane(Pane p, double width, double height) {
        pane = p;
        scene = new Scene(pane, width, height);
        window.setScene(scene);
        p.requestFocus();
        window.show();
    }

}
