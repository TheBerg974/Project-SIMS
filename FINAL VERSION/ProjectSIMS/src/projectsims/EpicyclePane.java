/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class EpicyclePane extends Pane {

    private ExecutorService executor;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<Epicycle> epicycles;
    private ArrayList<Point> points;
    private boolean paused;
    private Slider nCircles;
    private Slider freqMult;
    private Slider ampMult;
    private Label nCirclesLabel;
    private Label freqLabel;
    private Label ampLabel;
    private ComboBox wavePatterns;
    private Image seriesImg;
    private ImageView imgView;
    private Button backButton;
    private Button canvasButton;

    //Pane Constructor 
    public EpicyclePane() {
        initializePane();
        epicycles = SquareWave(1, 1, 1);
        loop();
    }

    //Initializing Pane(BackGround Color, GraphicsContext, Thread, Canvas)
    private void initializePane() {
        this.setStyle("-fx-background-color: black;");

        executor = Executors.newSingleThreadExecutor();

        canvas = new Canvas(1200, 500);

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

        File file = new File("./assetsFourier/" + wavePatterns.getValue().toString() + ".png");
        seriesImg = new Image(file.toURI().toString());
        imgView = new ImageView(seriesImg);
        imgView.setLayoutX(425);
        imgView.setLayoutY(350);
        imgView.setFitWidth(500);
        imgView.setFitWidth(300);

        nCircles = initializeSlider(10, 350, 1, 100, 1);
        freqMult = initializeSlider(10, 400, 1, 5, 1);
        ampMult = initializeSlider(10, 450, 0.1, 1, 0.1);
        nCirclesLabel = initializeLabel(165, 350, ((int) nCircles.getValue() + " Epicycle(s)"), Color.WHITE);
        freqLabel = initializeLabel(165, 400, ("Speed times " + (int) freqMult.getValue()), Color.WHITE);
        ampLabel = initializeLabel(165, 450, ("Amplitude times " + ampMult.getValue()), Color.WHITE);
        backButton = initializeButton(785, 355, "Back to Menu");
        canvasButton = initializeButton(767, 410, "Canvas");

        nCircles.valueProperty().addListener((ObservableValue<? extends Number> arg0, Number arg1, Number arg2) -> {
            nCirclesLabel.setText((int) nCircles.getValue() + " Epicycle(s)");
            resetEpycicles();
        });

        freqMult.valueProperty().addListener((ObservableValue<? extends Number> arg0, Number arg1, Number arg2) -> {
            freqLabel.setText("Speed times " + (int) freqMult.getValue());
            resetEpycicles();
        });

        ampMult.valueProperty().addListener((ObservableValue<? extends Number> arg0, Number arg1, Number arg2) -> {
            ampLabel.setText("Amplitude times " + String.format("%.1f", ampMult.getValue()));
            resetEpycicles();
        });

        wavePatterns.valueProperty().addListener((arg0, arg1, arg2) -> {
            resetEpycicles();
        });

        canvasButton.setOnAction((ActionEvent e) -> {
            ProjectSIMS.changePane(new CanvasPane(), 975, 800);
        });
        
        backButton.setOnAction((ActionEvent e) -> {
            ProjectSIMS.changePane(new MainMenuPane(), 500, 500);
        });

        this.getChildren().addAll(canvas, nCircles, nCirclesLabel, freqMult, freqLabel,
                ampMult, ampLabel, wavePatterns, imgView, backButton, canvasButton);
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

    private void drawPoints() {
        Epicycle epi = epicycles.get(epicycles.size() - 1);
        Point point = new Point(epi, gc);
        points.add(point);
        point.drawPoint(gc);

        for (int i = 1; i < points.size(); i++) {
            if (points.get(i).getX() > 950) {
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
                if (deltaTime > 33.2) {
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

    private ArrayList<Epicycle> SquareWave(int n, int freqMult, double ampMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        int j = 0;
        for (int i = 0; i < n; i++) {
            j = (i * 2) + 1;
            double radius = 75 * (4 / (Math.PI * j)) * ampMult;
            epicycles.add(new Epicycle(x, 200, radius, 0, (j / 1000.0) * freqMult));
            x += radius;
        }
        return epicycles;
    }

    private ArrayList<Epicycle> SawtoothWave(int n, int freqMult, double ampMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        for (int i = 1; i < n + 1; i++) {
            double radius = 75 * (4 / (Math.PI * i)) * ampMult;
            epicycles.add(new Epicycle(x, 200, radius, 0, (i / 1000.0) * freqMult));
            x += radius;
        }
        return epicycles;
    }

    private ArrayList<Epicycle> TriangleWave(int n, int freqMult, double ampMult) {
        ArrayList<Epicycle> epicycles = new ArrayList<>();
        int x = 200;
        for (int i = 0; i < n; i++) {
            int j = (i * 2) + 1;
            double radius = 75 * (8 / Math.pow(Math.PI, 2)) * ((Math.pow(-1, (j - 1) / 2)) / Math.pow(j, 2)) * ampMult;
            epicycles.add(new Epicycle(x, 200, radius, 0, (j / 500.0) * freqMult));
            x += radius;
        }
        return epicycles;
    }

    private ArrayList<Epicycle> generateSeries(String type, int n, int freqMult, double ampMult) {
        if (type.equals("Square-Wave")) {
            return SquareWave(n, freqMult, ampMult);
        } else if (type.equals("Sawtooth-Wave")) {
            return SawtoothWave(n, freqMult, ampMult);
        } else {
            return TriangleWave(n, freqMult, ampMult);
        }
    }

    private Slider initializeSlider(double x, double y, double min, double max, double inc) {
        Slider slider = new Slider();
        slider.setLayoutX(x);
        slider.setLayoutY(y);
        slider.setMin(min);
        slider.setMax(max);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(1);
        slider.setBlockIncrement(inc);
        slider.setValue(1);
        return slider;
    }

    private Label initializeLabel(double x, double y, String initialText, Color color) {
        Label label = new Label();
        label.setTextFill(color);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setText(initialText);
        return label;
    }

    private Button initializeButton(double x, double y, String s) {
        Button button = new Button(s);
        button.setScaleX(2);
        button.setScaleY(1.5);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    private void resetEpycicles() {
        paused = false;
        epicycles.removeAll(epicycles);
        points.removeAll(points);
        epicycles = generateSeries(wavePatterns.getValue().toString(), (int) nCircles.getValue(), (int) freqMult.getValue(), ampMult.getValue());
        File file = new File("./assetsFourier/" + wavePatterns.getValue().toString() + ".png");
        seriesImg = new Image(file.toURI().toString());
        imgView.setImage(seriesImg);
        paused = true;
        loop();
    }

}
