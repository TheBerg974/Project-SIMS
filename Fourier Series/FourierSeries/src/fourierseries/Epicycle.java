/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourierseries;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author berge
 */
public class Epicycle {

    //Epicycle class(circle, theta(phase), frequency, coordinate fo the phasor)
    private Circle circle;
    private double theta, frequency;

    //Constructor
    public Epicycle(double centerX, double centerY, double radius, double theta, double frequency) {
        this.circle = new Circle(centerX, centerY, radius);
        this.theta = theta;
        this.frequency = frequency;
    }

    //Drawing the epicycle on the pane
    public void drawEpicycle(GraphicsContext gc) {
        double topX = this.circle.getCenterX() - this.circle.getRadius();
        double topY = this.circle.getCenterY() - this.circle.getRadius();
        double diameter = this.circle.getRadius() * 2;
        gc.setStroke(Color.rgb(200, 200, 200, 0.5));
        gc.strokeOval(topX, topY, diameter, diameter);
    }

    //Drawing the phasor
    public void drawPhasor(GraphicsContext gc) {
        double initialX = this.circle.getCenterX();
        double initialY = this.circle.getCenterY();
        double finalX = this.circle.getCenterX() + computePhasorX(this.theta);
        double finalY = this.circle.getCenterY() - computePhasorY(this.theta);
        gc.setStroke(Color.WHITE);
        gc.strokeLine(initialX, initialY, finalX, finalY);
    }

    //Computing all the rotating Phasors
    public void rotatePhasor(double dt, GraphicsContext gc) {
        double freq = this.getFrequency();
        double newTheta = this.theta + (dt * freq);
        this.theta = newTheta;
    }

    //Calculating x coordinate of the phasor
    public double computePhasorX(double theta) {
        double x = (this.circle.getRadius() * Math.cos(theta));
        return x;
    }

    //Calculating y coordinate of the phasor
    public double computePhasorY(double theta) {
        double y = (this.circle.getRadius() * Math.sin(theta));
        return y;
    }

    //Getters and Setters
    public void setCircle(double centerX, double centerY) {
        this.circle = new Circle(centerX, centerY, this.circle.getRadius());
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPhasorX() {
        return this.circle.getCenterX() + computePhasorX(this.theta);
    }

    public double getPhasorY() {
        return this.circle.getCenterY() - computePhasorY(this.theta);
    }

    public Circle getCircle() {
        return circle;
    }

    public double getTheta() {
        return theta;
    }

    public double getFrequency() {
        return frequency;
    }
}
