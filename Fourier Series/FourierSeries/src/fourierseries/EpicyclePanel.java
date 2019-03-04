/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourierseries;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class EpicyclePanel extends Pane {

    private ExecutorService executor;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Epicycle> epicycles;
    private ArrayList<Point> points;
    private boolean paused;
    private Slider nCircles;
    private Slider freqMult;
    private Label nCirclesLabel;
    private Label freqLabel;
    private ComboBox wavePatterns;

    //Pane Constructor 
    public EpicyclePanel() {
        initializePane();

        nCirclesLabel.setText(5 + " Epicycle(s)");
        freqLabel.setText("Speed times " + (int)freqMult.getValue());

        epicycles = SquareWave(5, 1);

        loop();
    }

    //Initializing Pane(BackGround Color, GraphicsContext, Thread, Canvas)
    private void initializePane() {
        this.setStyle("-fx-background-color: black;");

        executor = Executors.newSingleThreadExecutor();

        canvas = new Canvas(1200, 450);

        gc = canvas.getGraphicsContext2D();

        epicycles = new ArrayList<>();
        points = new ArrayList<>();

        paused = true;

        wavePatterns = new ComboBox<String>();
        wavePatterns.getItems().addAll(
                "Square-Wave",
                "Sawtooth-Wave",
                "Triangle-Wave"
        );
        wavePatterns.setPromptText("Please select one");
        wavePatterns.setValue("Square-Wave");
        wavePatterns.setLayoutX(275);
        wavePatterns.setLayoutY(350);
        wavePatterns.valueProperty().addListener((arg0, arg1, arg2) -> {
            paused = false;
            epicycles.removeAll(epicycles);
            points.removeAll(points);
            epicycles = generateSeries(wavePatterns.getValue().toString(), (int) nCircles.getValue(), (int)freqMult.getValue());
            paused = true;
            loop();
        });

        nCirclesLabel = new Label();
        nCirclesLabel.setTextFill(Color.WHITE);
        nCirclesLabel.setLayoutX(175);
        nCirclesLabel.setLayoutY(350);
        
        freqLabel = new Label();
        freqLabel.setTextFill(Color.WHITE);
        freqLabel.setLayoutX(175);
        freqLabel.setLayoutY(400);

        nCircles = new Slider();
        nCircles.setLayoutX(10);
        nCircles.setLayoutY(350);
        nCircles.setMin(1);
        nCircles.setMax(100);
        nCircles.setShowTickLabels(true);
        nCircles.setShowTickMarks(true);
        nCircles.setMinorTickCount(1);
        nCircles.setBlockIncrement(1);
        nCircles.setValue(5);

        freqMult = new Slider();
        freqMult = new Slider();
        freqMult.setLayoutX(10);
        freqMult.setLayoutY(400);
        freqMult.setMin(1);
        freqMult.setMax(5);
        freqMult.setShowTickLabels(true);
        freqMult.setShowTickMarks(true);
        freqMult.setMinorTickCount(1);
        freqMult.setBlockIncrement(1);
        freqMult.setValue(1);

        nCircles.valueProperty().addListener((ObservableValue<? extends Number> arg0, Number arg1, Number arg2) -> {
            paused = false;
            epicycles.removeAll(epicycles);
            points.removeAll(points);
            epicycles = generateSeries(wavePatterns.getValue().toString(), (int) nCircles.getValue(), (int)freqMult.getValue());
            nCirclesLabel.setText((int) nCircles.getValue() + " Epicycle(s)");
            paused = true;
            loop();
        });

        freqMult.valueProperty().addListener((ObservableValue<? extends Number> arg0, Number arg1, Number arg2) -> {
            paused = false;
            epicycles.removeAll(epicycles);
            points.removeAll(points);
            epicycles = generateSeries(wavePatterns.getValue().toString(), (int) nCircles.getValue(), (int)freqMult.getValue());
            freqLabel.setText("Speed times " + (int)freqMult.getValue());
            paused = true;
            loop();
        });

        this.getChildren().add(canvas);
        this.getChildren().add(nCircles);
        this.getChildren().add(nCirclesLabel);
        this.getChildren().add(wavePatterns);
        this.getChildren().add(freqMult);
        this.getChildren().add(freqLabel);
    }

    public void drawEpicycles(double dt) {
        computeEpicyclePositions(epicycles);
        for (int i = 0; i < epicycles.size(); i++) {
            epicycles.get(i).rotatePhasor(dt, gc);
            epicycles.get(i).drawEpicycle(gc);
            epicycles.get(i).drawPhasor(gc);
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
                if (i > 1 && previousPoint.getY() > 0) {
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
                if (deltaTime >= 28.5) {
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

    public ArrayList<Epicycle> SquareWave(int n, int freqMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        int j = 0;
        for (int i = 0; i < n; i++) {
            j = (i * 2) + 1;
            double radius = 75 * (4 / (Math.PI * j));
            epicycles.add(new Epicycle(x, 200, radius, 0, (j / 1000.0)*freqMult));
            x += radius;
        }
        return epicycles;
    }

    public ArrayList<Epicycle> SawtoothWave(int n, int freqMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        for (int i = 1; i < n + 1; i++) {
            double radius = 75 * (4 / (Math.PI * i));
            epicycles.add(new Epicycle(x, 200, radius, 0, (i / 1000.0)*freqMult));
            x += radius;
        }
        return epicycles;
    }

    public ArrayList<Epicycle> TriangleWave(int n, int freqMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        for (int i = 0; i < n; i++) {
            int j = (i * 2) + 1;
            double radius = 75 * (8 / Math.pow(Math.PI, 2)) * ((Math.pow(-1, (j - 1) / 2)) / Math.pow(j, 2));
            epicycles.add(new Epicycle(x, 200, radius, 0, (j / 500.0)*freqMult));
            x += radius;
        }
        return epicycles;
    }

    public ArrayList<Epicycle> generateSeries(String type, int n, int freqMult) {
        if (type.equals("Square-Wave")) {
            return SquareWave(n, freqMult);
        } else if (type.equals("Sawtooth-Wave")) {
            return SawtoothWave(n, freqMult);
        } else {
            return TriangleWave(n, freqMult);
        }
    }

}
