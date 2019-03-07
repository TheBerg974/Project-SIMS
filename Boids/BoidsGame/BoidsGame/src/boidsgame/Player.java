package boidsgame;

import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Player {

    double stringLength;
    double angle = Math.PI / 4;
    double angularAcceleration;
    double angularVelocity;
    double gravity = 0.4;
    double originalX;
    double originalY;
    Circle circle;
    Line string;

    

    public Player(double posX, double posY, ArrayList<Mast> points) {
        circle = new Circle(20);
        attachToClosestMast(points, posX, posY);
        circle.setFill(Paint.valueOf("blue"));
        
        //create the player
        originalX = circle.getCenterX();
        originalY = circle.getCenterY();
        stringLength = Math.sqrt(Math.pow(posX - originalX, 2) + Math.pow(posY - originalY, 2));
        
        //calculate the starting angle
        angle = Math.atan2(-1 * (posY - originalY), (posX - originalX)) + Math.PI / 2;
        
        //create the string's starting point
        string = new Line();
        string.setStartX(originalX);
        string.setStartY(originalY);

    }

    //moves the player in a pendulum motion
    public void pendulumMotion() {
        angularAcceleration = (-1 * gravity / stringLength) * Math.sin(angle);
        angularVelocity += angularAcceleration;
        //friction
        //angularVelocity *= friction;        
        angle += angularVelocity;

        //updates the player's location
        Vector2D position = new Vector2D(stringLength * Math.sin(angle), stringLength * Math.cos(angle));
        circle.setCenterX(position.getX() + originalX);
        circle.setCenterY(position.getY() + originalY);

        //updates the string's endpoint's location
        string.setEndX(position.getX() + originalX);
        string.setEndY(position.getY() + originalY);
    }

    //finds the mast that is closes to the player
    public Mast findClosestMast(ArrayList<Mast> points, double posX, double posY) {
        Mast check = points.get(0);
        double position = Math.sqrt(Math.pow(points.get(0).getCircle().getCenterX() - posX, 2) + Math.pow(points.get(0).getCircle().getCenterY() - posY, 2));
        for (int x = 1; x < points.size(); x++) {
            double validate = Math.sqrt(Math.pow(points.get(x).getCircle().getCenterX() - posX, 2) + Math.pow(points.get(x).getCircle().getCenterY() - posY, 2));
            if (validate < position) {
                check = points.get(x);
                position = validate;
            }
        }
        return check;
    }

    //centers the player's swinging on the closest mast
    public void attachToClosestMast(ArrayList<Mast> points, double posX, double posY) {
        Mast mast = findClosestMast(points, posX, posY);
        circle.setCenterX(mast.getCircle().getCenterX());
        circle.setCenterY(mast.getCircle().getCenterY());
        mast.getCircle().setFill(Paint.valueOf("purple"));
    }

    //updates the player's motion
    public void update() {

        pendulumMotion();
    }

    public Circle getCircle() {
        return circle;
    }

    public Line getString() {
        return string;
    }

}
