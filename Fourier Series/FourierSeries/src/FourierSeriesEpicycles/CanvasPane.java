/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesEpicycles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class CanvasPane extends Pane {

    private ExecutorService executor;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Point> points;
    private ArrayList<Point> path;
    private ArrayList<Epicycle> epicycles;
    private ArrayList<Epicycle> ogEpicycles;
    private boolean clicked = false;
    private boolean paused = true;
    private Button back;

    /**
     * This method is called when the Pane is created
     */
    public CanvasPane() {
        //initializing the pane
        initializePane();
        
        //Starts drawing when mouse is clicked 
        setOnMouseClicked(e -> {
            clicked = !clicked;
            clear(gc);
            paused = false;
            
            //Starts the discrete fourier transform on the set of drawn points
            if (!clicked) {
                clear(gc);
                paused = true;
                DiscreteFourierTransform dft = new DiscreteFourierTransform(points);
                epicycles = dft.discreteFourierTransform();
                ogEpicycles = epicycles;
                points.removeAll(points);
                path.removeAll(path);
                epicycles.get(0).setCircle(475, 400);
                loop();
            }
        });
        
        //Draws points where the mouse is 
        setOnMouseMoved(e -> {
            if (clicked) {
                double posX = e.getX();
                double posY = e.getY();
                gc.setFill(Color.WHITE);
                gc.fillOval(posX - 1, posY - 1, 2, 2);
                Point newPoint = new Point(posX - 475, posY - 400);
                points.add(newPoint);
            }
        });

    }

    /**
     * Loop for the the epicycle animation
     */
    private void loop() {
        executor.execute(() -> {
            double initialTime = System.nanoTime();
            double currentTime;
            double deltaTime;
            double interval = Math.PI * 2 / epicycles.size();
            while (paused) {
                currentTime = System.nanoTime();
                deltaTime = (currentTime - initialTime) / 1000000;
                //Will update every 1/60 seconds (60 frames per second)
                if (deltaTime > 33.2) {
                    initialTime = currentTime;
                    clear(gc);
                    drawEpicycles(interval);
                    drawPoints();
                }
            }
            clear(gc);
        });
    }
    
    /**
     * Draws the points according to the last epicycles phase
     */
    private void drawPoints() {
        Epicycle epi = epicycles.get(epicycles.size() - 1);
        Point point = new Point(epi.getPhasorX(), epi.getPhasorY());
        path.add(point);
        point.drawPoint(gc);
        for (int i = 0; i < path.size(); i++) {
            path.get(i).drawPoint(gc);
            if (i > 1) {
                Point previousPoint = path.get(i - 1);
                path.get(i).connectPoint(gc, previousPoint);
            }
        }

    }
    
    /**
     * Draws all the Epicycle of the DFT
     * @param dt the sampling frequency to rotate the phases of the epicycles
     */
    public void drawEpicycles(double dt) {

        for (int i = 0; i < epicycles.size(); i++) {
            epicycles.get(i).rotatePhasor(dt, gc);
        }
        computeEpicyclePositions(epicycles);
        for (int i = 0; i < epicycles.size(); i++) {
            epicycles.get(i).drawEpicycle(gc);
            epicycles.get(i).drawPhasor(gc);
        }
    }

    /**
     * Computes all the position of the epicycles
     * @param epicycles the List of Epicycles
     */
    private void computeEpicyclePositions(List<Epicycle> epicycles) {
        for (int i = 1; i < epicycles.size(); i++) {
            Epicycle previousEpi = epicycles.get(i - 1);
            Epicycle currentEpi = epicycles.get(i);
            currentEpi.setCircle(previousEpi.getPhasorX(), previousEpi.getPhasorY());
        }
    }

    /**
     * Method to initialize all the setting of the pane
     */
    private void initializePane() {
        this.setStyle("-fx-background-color: black;");
        canvas = new Canvas(950, 800);
        gc = canvas.getGraphicsContext2D();
        points = new ArrayList<>();
        path = new ArrayList<>();
        executor = Executors.newSingleThreadExecutor();
        back = initializeButton(900, 300, "Back");
        back.setOnAction((ActionEvent e) -> {
            FourierSeries.changePane(new EpicyclePane(), 950, 500);
        });
        this.getChildren().addAll(canvas, back);
    }
    
    /**
     * Initialize a button at a given position
     * @param x the x coordinate
     * @param y the y coordinate
     * @param s The message displayed on the button
     * @return the initialized button
     */
    private Button initializeButton(double x, double y, String s) {
        Button button = new Button(s);
        button.setScaleX(2);
        button.setScaleY(1.5);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    /**
     * Clear the pane of all graphics context
     * @param gc the graphics context
     */
    private void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, 950, 800);
    }

}
