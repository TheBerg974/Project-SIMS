
package projectsims;

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

    /**
     * Create a point according to an epicycle
     * @param epi the epicycle
     * @param gc the given graphics context
     */
    public Point(Epicycle epi, GraphicsContext gc) {
        this.x = this.xGap;
        this.y = epi.getPhasorY();
        drawPoint(gc);
    }
    
    /**
     * Create a point at a specific coordinate
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the point on a given canvas
     * @param gc the graphics context
     */
    public void drawPoint(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(this.x - 1, this.y - 1, 2, 2);
    }
    
    /**
     * Translates the points on a canvas
     * @param gc the graphics context
     */
    public void translatePoint(GraphicsContext gc) {
        double newX = this.x + this.velocityX;
        this.setX(newX);
    }
    
    /**
     * Connects two points together with a line
     * @param gc the graphics context
     * @param previousPoint The point to connect to
     */
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
