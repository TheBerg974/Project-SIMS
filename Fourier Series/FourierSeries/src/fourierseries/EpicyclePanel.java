/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourierseries;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author berge
 */
public class EpicyclePanel extends Pane {

    private Executor executor;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Epicycle> epicycles = new ArrayList<>();
    private ArrayList<Point> points = new ArrayList<>();
    private final boolean paused = true;

    //Pane Constructor 
    public EpicyclePanel() {
        initializePane();

        Epicycle epi1 = new Epicycle(200, 200, 70, 0, 0.001);
        Epicycle epi2 = new Epicycle(270, 200, 35, Math.PI/2, 0.005);
        Epicycle epi3 = new Epicycle(305, 200, 17.5, Math.PI, 0.01);
        
        epi1.drawPhasor(gc);
        epi1.drawEpicycle(gc);

        epicycles.add(epi1);
        epicycles.add(epi2);
        epicycles.add(epi3);

        loop();
    }

    //Initializing Pane(BackGround Color, GraphicsContex, Thread, Canvas)
    private void initializePane() {
        this.setStyle("-fx-background-color: black;");
        executor = Executors.newSingleThreadExecutor();
        canvas = new Canvas(1200, 450);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
    }

    public void drawEpicycles(double dt) {
        computeEpicyclePositions(epicycles);
        for (int i = 0; i < epicycles.size(); i++) {
            epicycles.get(i).drawEpicycle(gc);
            epicycles.get(i).rotatePhasor(dt, gc);
        }
    }

    public void computeEpicyclePositions(ArrayList<Epicycle> epicycles) {
        for (int i = 1; i < epicycles.size(); i++) {
            Epicycle previousEpi = epicycles.get(i - 1);
            Epicycle currentEpi = epicycles.get(i);
            currentEpi.setCircle(previousEpi.getPhasorX(), previousEpi.getPhasorY());
        }
    }

    public void drawPoints() {
        Epicycle epi = epicycles.get(epicycles.size() - 1);
        Point point = new Point(epi, gc);
        points.add(point);
        point.drawPoint(gc);

        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getX() > 1200) {
                points.remove(i);
            } else {
                Point previousPoint = points.get(i - 1);
                points.get(i).translatePoint(gc);
                points.get(i).drawPoint(gc);
                if (i > 1) {
                    points.get(i).connectPoint(gc, previousPoint);
                }
            }

        }

        Point lastPoint = points.get(points.size() - 1);
        gc.strokeLine(epi.getPhasorX(), epi.getPhasorY(), lastPoint.getX(), lastPoint.getY());
    }

    //Loop for calculating phasors position
    private void loop() {
        executor.execute(() -> {
            double initialTime = System.nanoTime();
            double currentTime;
            double deltaTime;
            while (paused) {
                currentTime = System.nanoTime();
                deltaTime = (currentTime - initialTime) / 1000000;
                //Will update every 1/60 seconds (60 frames per second)
                if (deltaTime >= 16.6) {
                    initialTime = currentTime;
                    clear(gc);
                    drawEpicycles(deltaTime);
                    drawPoints();
                }
            }
        });
    }

    private void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, 1200, 450);
    }

}
