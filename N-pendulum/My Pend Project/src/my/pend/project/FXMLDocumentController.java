/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pend.project;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author PRINTOUT
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    AnchorPane anglesPane;

    @FXML
    Button pauseButton;

    @FXML
    Button resumeButton;

    @FXML
    Button resetButton;

    @FXML
    CheckBox showTrace;

    @FXML
    CheckBox hidePendulums;

    @FXML
    TextField length1Input;
    @FXML
    TextField length2Input;
    @FXML
    TextField length3Input;
    @FXML
    TextField mass1Input;
    @FXML
    TextField mass2Input;
    @FXML
    TextField mass3Input;

    @FXML
    Text angle1Text;
    @FXML
    Text angle2Text;
    @FXML
    Text angle3Text;

    @FXML
    Text angularVelocity1Text;
    @FXML
    Text angularVelocity2Text;
    @FXML
    Text angularVelocity3Text;

    @FXML
    Text angularAcceleration1Text;
    @FXML
    Text angularAcceleration2Text;
    @FXML
    Text angularAcceleration3Text;

    @FXML
    TextField gravityInput;

    @FXML
    Text message;

    @FXML
    RadioButton amount1;
    @FXML
    RadioButton amount2;
    @FXML
    RadioButton amount3;

    @FXML
    Button showGraphsButton;

    @FXML
    ScatterChart<?, ?> anglesScatterChart;

    @FXML
    CategoryAxis x;

    @FXML
    NumberAxis y;

    @FXML
    AnchorPane anglesGraphPane;

    //private double lastFrameTime = 0.0;
    private boolean dragging = false;

    private Point2D screenOrigin = new Point2D(500, 300);

    private int pendNum = 2;

    private double scale = 0.00025;
    private double gravity = 9.81 * scale;

    private double angle1 = (pi() / 3) % (2 * pi());
    private double angle2 = (pi() / 4) % (2 * pi());
    private double angle3 = (pi() / 1) % (2 * pi());
    private double[] angleArray = {angle1, angle2, angle3};

    private double initialAngle1 = angle1;
    private double initialAngle2 = angle2;
    private double initialAngle3 = angle3;

    private Circle p1 = null;
    private Circle p2 = null;
    private Circle p3 = null;
    private Circle[] pendulumArray = {p1, p2, p3};
    private Circle point = null;

    private double L1 = 140;
    private double L2 = 120;
    private double L3 = 100;
    private double[] lengthArray = {L1, L2, L3};

    private double m1 = 20;
    private double m2 = 20;
    private double m3 = 30;
    private double[] massArray = {m1, m2, m3};

    private double vel1 = 0;
    private double vel2 = 0;
    private double vel3 = 0;
    private double tempVel1 = 0;

    private double initialVel1 = vel1;
    private double initialVel2 = vel2;
    private double initialVel3 = vel3;

    private double acc1 = 0;
    private double acc2 = 0;
    private double acc3 = 0;
    private double tempAcc1 = 0;

    private Line line1 = null;
    private Line line2 = null;
    private Line line3 = null;

    private double initialX1 = 0;
    private double initialY1 = 0;
    private double initialX2 = 0;
    private double initialY2 = 0;
    private double initialX3 = 0;
    private double initialY3 = 0;

    private AnimationTimer a = null;

    private boolean graphsOpen = false;

    XYChart.Series anglesSeries = null;

    private ArrayList<Circle> traces = new ArrayList<Circle>();
    private boolean isPlaying = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //lastFrameTime = 0.0f;
        //long initialTime = System.nanoTime();
        pauseButton.setOnMouseClicked(pauseClicked);
        resumeButton.setOnMouseClicked(resumeClicked);

        gravityInput.setText("9.81");
        length1Input.setText("" + L1);
        length2Input.setText("" + L2);
        length3Input.setText("" + L3);

        mass1Input.setText("" + m1);
        mass2Input.setText("" + m2);
        mass3Input.setText("" + m3);

        amount2.setSelected(true);

        showGraphsButton.setOnMouseClicked(showGraphsButtonPressed);

        point = new Circle(screenOrigin.getX(), screenOrigin.getY(), 2);
        addToPane(point);

        p1 = new Circle(L1 * sin(angle1) + screenOrigin.getX(), L1 * cos(angle1) + screenOrigin.getY(), m1);
        line1 = new Line(screenOrigin.getX(), screenOrigin.getY(), p1.getCenterX(), p1.getCenterY());

        addToPane(p1);
        addToPane(line1);

        initialX1 = p1.getCenterX();
        initialY1 = p1.getCenterY();

        if (pendNum == 2) {

            double x2 = p1.getCenterX() + L2 * sin(angle2);
            double y2 = p1.getCenterY() + L2 * cos(angle2);
            p2 = new Circle(x2, y2, m2);
            line2 = new Line(p1.getCenterX(), p1.getCenterY(), p2.getCenterX(), p2.getCenterY());

            addToPane(p2);
            addToPane(line2);

            initialX2 = p2.getCenterX();
            initialY2 = p2.getCenterY();

        }

        if (pendNum == 3) {
            // SET UP 3RD PENDULUM CIRCLE
            // SET VALUES FOR INITIAL X AND Y
        }

        a = new AnimationTimer() {
            @Override
            public void handle(long now) {

                checkBoxes();
                disableButtons();
                updateAngles();

                // DRAGGING FUNCTION
                p1.setOnMouseDragged(circle1Clicked);

                resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        angle1 = initialAngle1;
                        angle2 = initialAngle2;
                        angle3 = initialAngle3;

                        p1.setCenterX(initialX1);
                        p1.setCenterY(initialY1);
                        line1.setEndX(p1.getCenterX());
                        line1.setEndY(p1.getCenterY());
                        vel1 = initialVel1;
                        if (pendNum == 2) {
                            p2.setCenterX(initialX2);
                            p2.setCenterY(initialY2);
                            line2.setStartX(p1.getCenterX());
                            line2.setStartY(p1.getCenterY());
                            line2.setEndX(p2.getCenterX());
                            line2.setEndY(p2.getCenterY());
                            vel2 = initialVel2;
                        }
                        if (pendNum == 3) {
                            // DO THE SAME FOR 3RD PENDULUM
                        }

                    }
                });

                length1Input.setOnKeyPressed(enterLengths);
                length2Input.setOnKeyPressed(enterLengths);
                length3Input.setOnKeyPressed(enterLengths);

                mass1Input.setOnKeyPressed(enterMasses);
                mass2Input.setOnKeyPressed(enterMasses);
                mass3Input.setOnKeyPressed(enterMasses);

                amount1.setOnMouseClicked(amountButtonPressed);
                amount2.setOnMouseClicked(amountButtonPressed);
                amount3.setOnMouseClicked(amountButtonPressed);

                gravityInput.setOnKeyPressed(enterGravity);

                if (pendNum == 1) {

                    acc1 = -gravity * sin(angle1);
                    vel1 += acc1;
                    angle1 += vel1;

                    Point2D newPos1 = new Point2D(L1 * sin(angle1) + screenOrigin.getX(), L1 * cos(angle1) + screenOrigin.getY());
                    setPosition(p1, newPos1);
                    line1.setEndX(p1.getCenterX());
                    line1.setEndY(p1.getCenterY());

                } else if (pendNum == 2) {

                    p2.setOnMouseDragged(circle2Clicked);

                    double tempGravity = (gravity / scale) / 39.24; // arbitrary number

                    double dif = angle1 - angle2;
                    double a1n1 = -tempGravity * (2 * m1 + m2) * sin(angle1);
                    double a1n2 = -m2 * tempGravity * sin(angle1 - 2 * angle2);
                    double a1n3 = -2 * sin(dif) * m2 * (square(vel2) * L2 + square(vel1) * L1 * cos(dif));
                    double a1d = L1 * (2 * m1 + m2 - m2 * cos(2 * angle1 - 2 * angle2));
                    acc1 = (a1n1 + a1n2 + a1n3) / a1d;

                    double a2n = 2 * sin(dif) * (square(vel1) * L1 * (m1 + m2) + tempGravity * (m1 + m2) * cos(angle1) + (square(vel2) * L2 * m2 * cos(dif)));
                    double a2d = L2 * (2 * m1 + m2 - (m2 * cos(2 * angle1 - 2 * angle2)));
                    acc2 = a2n / a2d;

                    vel1 += acc1;
                    vel2 += acc2;
                    angle1 += vel1;
                    angle2 += vel2;

                    Point2D newPos1 = new Point2D(L1 * sin(angle1) + screenOrigin.getX(), L1 * cos(angle1) + screenOrigin.getY());
                    Point2D newPos2 = new Point2D(newPos1.getX() + L2 * sin(angle2), newPos1.getY() + L2 * cos(angle2));

                    setPosition(p1, newPos1);
                    setPosition(p2, newPos2);
                    line1.setEndX(p1.getCenterX());
                    line1.setEndY(p1.getCenterY());
                    line2.setStartX(p1.getCenterX());
                    line2.setStartY(p1.getCenterY());
                    line2.setEndX(p2.getCenterX());
                    line2.setEndY(p2.getCenterY());

                }

                if (hidePendulums.isSelected()) {
                    hidePendulums();
                } else {
                    showPendulums();
                }

                if (graphsOpen) {

                    try {
                        anglesSeries.getData().add(new XYChart.Data(angle1, angle2));
                        anglesScatterChart.getData().add(anglesSeries);
                    } catch(Exception e) {
                        
                    }

                }
                
                

            }

        };
        a.start();

    }

    public void trace() {
        if (pendNum == 1) {
            Circle p = new Circle(p1.getCenterX(), p1.getCenterY(), 1, Color.DARKRED);
            addToPane(p);
            traces.add(p);

        } else if (pendNum == 2) {
            Circle p = new Circle(p1.getCenterX(), p1.getCenterY(), 1, Color.DARKRED);
            addToPane(p);
            Circle q = new Circle(p2.getCenterX(), p2.getCenterY(), 1, Color.DARKCYAN);
            addToPane(q);
            traces.add(p);
            traces.add(q);
        }

    }

    EventHandler<MouseEvent> showGraphsButtonPressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AnglesGraph.fxml"));
//                Parent root1 = (Parent) fxmlLoader.load();
//                Stage stage = new Stage();
//                stage.setTitle("Graphs Window");
//                stage.setScene(new Scene(root1));
//                stage.show();

                message.setText("Graphs loaded.");

                
                NumberAxis xAxis = new NumberAxis(-6 * Math.abs(angle1), 6 * Math.abs(angle1), 4.0);
                xAxis.setLabel("Angle 1");

                NumberAxis yAxis = new NumberAxis(-6 * Math.abs(angle2), 6 * Math.abs(angle2), 4.0);
                yAxis.setLabel("Angle 2");

                anglesScatterChart = new ScatterChart(xAxis, yAxis);
                anglesScatterChart.setTitle("Angle 2 vs. Angle 1");
                anglesScatterChart.setPrefSize(1000, 1000);

                anglesSeries = new XYChart.Series();
                anglesSeries.setName("Angle 2 vs. Angle 1");

                anglesScatterChart.getData().add(anglesSeries);

                Group root = new Group(anglesScatterChart);
                Scene scene = new Scene(root, 1000, 1000);
                Stage stage = new Stage();
                stage.setTitle("Graphs Window");
                stage.setScene(scene);
                stage.show();
                graphsOpen = true;

            } catch (Exception e) {
                message.setText("Cannot load graphs window.");
            }

        }
    };

    EventHandler<MouseEvent> circle1Clicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            Point2D diff = new Point2D(screenOrigin.getX() - event.getSceneX(), screenOrigin.getY() - event.getSceneY());
            angle1 = Math.atan2(-1 * diff.getY(), diff.getX()) - pi() / 2;
            vel1 = 0;
            p1.setCenterX(L1 * sin(angle1) + screenOrigin.getX());
            p1.setCenterY(L1 * cos(angle1) + screenOrigin.getY());
            line1.setEndX(p1.getCenterX());
            line1.setEndY(p1.getCenterY());
            if (pendNum == 2) {
                vel2 = 0;
                p2.setCenterX(p1.getCenterX() + L2 * sin(angle2));
                p2.setCenterY(p1.getCenterY() + L2 * cos(angle2));
                line2.setStartX(p1.getCenterX());
                line2.setStartY(p1.getCenterY());
                line2.setEndX(p2.getCenterX());
                line2.setEndY(p2.getCenterY());

            }
            updateAngles();
        }
    };

    EventHandler<KeyEvent> enterLengths = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                updateLengths();
            }
        }

    };

    EventHandler<KeyEvent> enterMasses = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                updateMasses();
            }
        }

    };

    EventHandler<KeyEvent> enterGravity = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                updateGravity();
            }
        }

    };

    public void showPendulums() {
        point.setVisible(true);
        p1.setVisible(true);
        line1.setVisible(true);
        if (pendNum == 2) {
            p2.setVisible(true);
            line2.setVisible(true);
        } else if (pendNum == 3) {
            p2.setVisible(true);
            line2.setVisible(true);
            p3.setVisible(true);
            line3.setVisible(true);
        }
    }

    public void updateLengths() {
        try {

//            L1 = Double.parseDouble( length1Input.getText() );
//            if(pendNum == 2) {
//                L2 = Double.parseDouble( length2Input.getText() );
//            }
//            else if(pendNum == 3) {
//                L2 = Double.parseDouble( length2Input.getText() );
//                L3 = Double.parseDouble( length3Input.getText() );
//            }
            if (!length1Input.getText().equalsIgnoreCase("")) {
                L1 = Double.parseDouble(length1Input.getText());
            }
            if (!length2Input.getText().equalsIgnoreCase("") && pendNum == 2) {
                L2 = Double.parseDouble(length2Input.getText());
            }
            if (!length3Input.getText().equalsIgnoreCase("") && pendNum == 3) {
                L2 = Double.parseDouble(mass2Input.getText());
                L3 = Double.parseDouble(mass3Input.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Please enter valid length values.");
        }

    }

    public void updateGravity() {
        try {
            if (!gravityInput.getText().equalsIgnoreCase("")) {
                gravity = Double.parseDouble(gravityInput.getText()) * scale;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Please enter valid gravity value.");
        }

    }

    public void updateMasses() {
        try {

            if (!mass1Input.getText().equalsIgnoreCase("")) {
                m1 = Double.parseDouble(mass1Input.getText());
                p1.setRadius(m1);
            }
            if (!mass2Input.getText().equalsIgnoreCase("") && pendNum == 2) {
                m2 = Double.parseDouble(mass2Input.getText());
                p2.setRadius(m2);
            }
            if (!mass3Input.getText().equalsIgnoreCase("") && pendNum == 3) {
                m2 = Double.parseDouble(mass2Input.getText());
                p2.setRadius(m2);
                m3 = Double.parseDouble(mass3Input.getText());
                p3.setRadius(m3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Please enter valid mass values.");
        }
    }

    public void updateAngles() {

        angle1Text.setText("Angle 1: " + (double) Math.round(((angle1 * 180 / pi()) * 10000d) / 10000d) % 360.0000 + "°");
        angularVelocity1Text.setText("Angular Velocity 1: " + (double) Math.round((vel1 * 180 / pi()) * 10000d) / 10000d + " rad/s");
        angularAcceleration1Text.setText("Angular Acceleration 1: " + (double) Math.round((acc1 * 180 / pi()) * 10000d) / 10000d + " rad/s2");

        angle2Text.setText("Angle 2: ");
        angularVelocity2Text.setText("Angular Velocity 2: ");
        angularAcceleration2Text.setText("");

        angle3Text.setText("Angle 3: ");
        angularVelocity3Text.setText("Angular Velocity 3: ");
        angularAcceleration3Text.setText("");

        if (pendNum == 2) {
            angle2Text.setText("Angle 2: " + (double) Math.round(((angle2 * 180 / pi()) * 10000d) / 10000d) % 360.0000 + "°");
            angularVelocity2Text.setText("Angular Velocity 2: " + (double) Math.round((vel2 * 180 / pi()) * 10000d) / 10000d + " rad/s");
            angularAcceleration2Text.setText("Angular Acceleration 2: " + (double) Math.round((acc2 * 180 / pi()) * 10000d) / 10000d + " rad/s2");
        }
        if (pendNum == 3) {
            angle2Text.setText("Angle 2: " + (double) Math.round(((angle2 * 180 / pi()) * 10000d) / 10000d) % 360.0000 + "°");
            angularVelocity2Text.setText("Angular Velocity 2: " + (double) Math.round((vel2 * 180 / pi()) * 10000d) / 10000d + " rad/s");
            angularAcceleration2Text.setText("Angular Acceleration 2: " + (double) Math.round((acc2 * 180 / pi()) * 10000d) / 10000d + " rad/s2");

            angle3Text.setText("Angle 3: " + (double) Math.round(((angle3 * 180 / pi()) * 10000d) / 10000d) % 360.0000 + "°");
            angularVelocity3Text.setText("Angular Velocity 3: " + (double) Math.round((vel3 * 180 / pi()) * 10000d) / 10000d + " rad/s");
            angularAcceleration3Text.setText("Angular Acceleration 3: " + (double) Math.round((acc3 * 180 / pi()) * 10000d) / 10000d + " rad/s2");
        }
    }

    public void updateNumberOfPendulums() {
        if (amount1.isSelected()) {
            pendNum = 1;
            p2.setVisible(false);
            line2.setVisible(false);
        } else if (amount2.isSelected()) {
            pendNum = 2;
        } else if (amount3.isSelected()) {
            pendNum = 3;
        }
    }

    public void fade() {

    }

    EventHandler<MouseEvent> amountButtonPressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            updateNumberOfPendulums();
        }
    };

    EventHandler<MouseEvent> circle2Clicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            Point2D diff = new Point2D(screenOrigin.getX() - event.getSceneX(), screenOrigin.getY() - event.getSceneY());
            angle2 = Math.atan2(-1 * diff.getY(), diff.getX()) - pi() / 2;
            vel2 = 0;
            p2.setCenterX(L2 * sin(angle2) + p1.getCenterX());
            p2.setCenterY(L2 * cos(angle2) + p1.getCenterY());
            line2.setStartX(p1.getCenterX());
            line2.setStartY(p1.getCenterY());
            line2.setEndX(p2.getCenterX());
            line2.setEndY(p2.getCenterY());

            updateAngles();

        }
    };

    public void checkBoxes() {

        if (showTrace.isSelected()) {
            trace();
        } else {
            for (Circle c : traces) {
                c.setVisible(false);
            }
        }

    }

    public void disableButtons() {

//        if(isPlaying) {
//            resumeButton.setDisable(true);
//            pauseButton.setDisable(false);
//        }
//        else {
//            resumeButton.setDisable(false);
//            pauseButton.setDisable(true);
//        }
    }

    public void hidePendulums() {

        point.setVisible(false);
        if (pendNum == 1) {
            p1.setVisible(false);
            line1.setVisible(false);
        } else if (pendNum == 2) {
            p1.setVisible(false);
            line1.setVisible(false);
            p2.setVisible(false);
            line2.setVisible(false);
        } else if (pendNum == 3) {
            // DO SAME FOR PEND 3
        }
    }

    EventHandler<MouseEvent> pauseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            a.stop();
            isPlaying = false;
        }
    };

    EventHandler<MouseEvent> resumeClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            a.start();
            isPlaying = true;
        }
    };

    public void setPosition(Circle c, Point2D p) {
        c.setCenterX(p.getX());
        c.setCenterY(p.getY());
    }

    public double sin(double d) {
        return Math.sin(d);
    }

    public double cos(double d) {
        return Math.cos(d);
    }

    public double pi() {
        return Math.PI;
    }

    public double sqrt(double d) {
        return Math.sqrt(d);
    }

    public double square(double d) {
        return d * d;
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void addToAnglesPane(Node node) {
        anglesPane.getChildren().add(node);
    }

    public double distance(Point2D p1, Point2D p2) {
        return Math.abs(sqrt(square(p2.getX() - p1.getX()) + square(p2.getY() - p1.getY())));
    }

}
