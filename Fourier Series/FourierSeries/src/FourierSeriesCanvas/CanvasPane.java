/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesCanvas;

import FourierSeriesEpicycles.Epicycle;
import FourierSeriesEpicycles.Point;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    boolean clicked = false;
    private boolean paused = true;

    public CanvasPane() {
        initializePane();
        setOnMouseClicked(e -> {
            clicked = !clicked;
            if (!clicked) {
                clear(gc);
                DiscreteFourierTransform dft = new DiscreteFourierTransform(points);
                epicycles = dft.discreteFourierTransform();
                points.removeAll(points);
                epicycles.get(0).setCircle(475, 400);
                loop();
            }
        });

        setOnMouseMoved(e -> {
            if (clicked) {
                double posX = e.getX();
                double posY = e.getY();
                gc.setFill(Color.WHITE);
                gc.fillOval(posX - 1, posY - 1, 2, 2);
                points.add(new Point(posX - 475, posY - 400));
            }
        });

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
                if (deltaTime > 33.2) {
                    initialTime = currentTime;
                    clear(gc);
                    drawEpicycles(Math.PI*2/epicycles.size());
                    drawPoints();
                }
            }
        });
    }

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

    private void computeEpicyclePositions(ArrayList<Epicycle> epicycles) {
        for (int i = 1; i < epicycles.size(); i++) {
            Epicycle previousEpi = epicycles.get(i - 1);
            Epicycle currentEpi = epicycles.get(i);
            currentEpi.setCircle(previousEpi.getPhasorX(), previousEpi.getPhasorY());
        }
    }

    private void initializePane() {
        this.setStyle("-fx-background-color: black;");
        canvas = new Canvas(950, 800);
        gc = canvas.getGraphicsContext2D();
        points = new ArrayList<>();
        path = new ArrayList<>();
        executor = Executors.newSingleThreadExecutor();
        this.getChildren().addAll(canvas);
    }

    private void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, 950, 800);
    }

}
