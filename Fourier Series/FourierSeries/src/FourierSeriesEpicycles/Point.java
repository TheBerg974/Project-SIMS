/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FourierSeriesEpicycles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author berge
 */
public class Point {

    private double x, y;
    final private double velocityX = 1;
    final private double xGap = 450;

    public Point(Epicycle epi, GraphicsContext gc) {
        this.x = this.xGap;
        this.y = epi.getPhasorY();
        drawPoint(gc);
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void drawPoint(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(this.x - 1, this.y - 1, 2, 2);
    }

    public void translatePoint(GraphicsContext gc) {
        double newX = this.x + this.velocityX;
        this.setX(newX);
    }
    
    public void connectPoint(GraphicsContext gc, Point previousPoint) {
        gc.setStroke(Color.WHITE);
        gc.strokeLine(this.x, this.y, previousPoint.getX(), previousPoint.getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVelocityX() {
        return velocityX;
    }
}
