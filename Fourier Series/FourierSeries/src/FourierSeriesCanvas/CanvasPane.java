/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesCanvas;

import FourierSeriesEpicycles.Point;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class CanvasPane extends Pane {

    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Point> points;
    boolean clicked = false;

    public CanvasPane() {
        initializePane();
        setOnMouseClicked(e -> {
            clicked = !clicked;
        });

        setOnMouseMoved(e -> {
            if (clicked) {
                double posX = e.getX();
                double posY = e.getY();
                gc.setFill(Color.WHITE);
                gc.fillOval(posX - 1, posY - 1, 2, 2);
            }
        });

    }

    private void initializePane() {
        this.setStyle("-fx-background-color: black;");
        canvas = new Canvas(950, 800);
        gc = canvas.getGraphicsContext2D();
        points = new ArrayList<>();

        this.getChildren().addAll(canvas);
    }

}
