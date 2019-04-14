/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author berge
 */
public class MainMenuPane extends Pane{
    
    private Button pendulumSimulation;
    private Button fourierSeriesSimulation;
    private Button boidsSimulation;
    private Button solarSystemSimulation;
    
    public MainMenuPane() {
        
    }
    
    public void initializePane(){
        
    }
    
    private Button initializeButton(double x, double y, String s) {
        Button button = new Button(s);
        button.setScaleX(2);
        button.setScaleY(1.5);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }
}
