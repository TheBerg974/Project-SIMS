package boidsgame;

import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Player {

    //pendulum movement essetial variables 
    double stringLength;
    double angle = Math.PI / 4;
    double angularAcceleration;
    double angularVelocity;
    double originalX;
    double originalY;
    Line string = new Line();
    Mast closestMast;

    //projectile movement essetial variables 
    double normalVelocityX;
    double normalVelocityY;

    //player body
    Circle circle;

    //player number
    int playerNumber;
    //player score
    int score;

    //player inputs (from settings)
    double gravity = ArenaSetup.getGravity() / 24.5;
    double friction = 1 - ArenaSetup.getFriction() / 1000;

    boolean isReal;

    public Player(double posX, double posY, ArrayList<Mast> points, int player) {
        circle = new Circle(25);
        circle.setCenterX(posX);
        circle.setCenterY(posY);
        circle.setFill(AssetManager.playerSwings(playerNumber));
        playerNumber = player;
        score = 0;

        stringLength = Math.sqrt(Math.pow(posX - originalX, 2) + Math.pow(posY - originalY, 2));
        angle = Math.atan2(-1 * (posY - originalY), (posX - originalX)) + Math.PI / 2;

        isReal = true;
    }

    public Player(int player) {
        circle = new Circle(25);
        circle.setCenterX(20);
        circle.setCenterY(300);
        circle.setFill(AssetManager.playerSwings(playerNumber)); 
        playerNumber = player;
        score = 0;
        isReal = false;
    }

    //moves the player in a projectile movtion (when the key is not pressed)
    public void projectileMotion() {
        //vertical displacement
        normalVelocityY += gravity;
        circle.setCenterY(circle.getCenterY() + normalVelocityY);

        //horizontal displacement
        circle.setCenterX(circle.getCenterX() + normalVelocityX);
    }

    //moves the player in a pendulum motion (when key pressed)
    public void pendulumMotion() {
        angularAcceleration = ((-1 * gravity / stringLength) * Math.sin(angle));
        angularVelocity += angularAcceleration;

        //friction
        angularVelocity *= friction;
        angle += angularVelocity;

        //updates the player's location
        Vector2D position = new Vector2D(stringLength * Math.sin(angle), stringLength * Math.cos(angle));
        circle.setCenterX(position.getX() + originalX);
        circle.setCenterY(position.getY() + originalY);

        //updates the string's location
        string.setVisible(true);
        string.setStartX(originalX);
        string.setStartY(originalY);
        string.setEndX(position.getX() + originalX);
        string.setEndY(position.getY() + originalY);
        string.setStrokeWidth(1.5);
        string.toBack();
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
        check.getCircle().setFill(AssetManager.mastSkin(true));
        return check;
    }

    //updates the player's motion
    public void update(boolean isKeyPressed) {
        if (isKeyPressed) {
            pendulumMotion();
            circle.setFill(AssetManager.playerSwings(playerNumber));
        } else {
            projectileMotion();
            circle.setFill(AssetManager.playerGoingUp(playerNumber, normalVelocityY < 0));
        }
        limitVelocity();
    }

    public void switchMovements(ArrayList<Mast> points, double posX, double posY, int phaseChange) {
        switch (phaseChange) {
            //from pendulum to projectile
            case 1:
                double velo = angularVelocity * stringLength;
                normalVelocityX = velo * Math.cos(angle);
                normalVelocityY = -1 * velo * Math.sin(angle);

                string.setVisible(false);
                break;
            //from projectile to pendulum
            case 2:
                closestMast = findClosestMast(points, posX, posY);
                originalX = closestMast.getCircle().getCenterX();
                originalY = closestMast.getCircle().getCenterY();
                stringLength = Math.sqrt(Math.pow(posX - originalX, 2) + Math.pow(posY - originalY, 2));
                angle = Math.atan2(-1 * (posY - originalY), (posX - originalX)) + Math.PI / 2;

                double velo2 = normalVelocityX * Math.cos(angle) + -1 * normalVelocityY * Math.sin(angle);
                angularVelocity = velo2 / stringLength;
                break;

            //no phase change
            default:
                break;
        }
    }

    //limits the player's speed so that they don't ever go too fast 
    public void limitVelocity() {
        if (normalVelocityX > 20) {
            normalVelocityX = 20;
        } else if (normalVelocityX < -20) {
            normalVelocityX = -20;
        }

        if (normalVelocityY > 25) {
            normalVelocityY = 25;
        } else if (normalVelocityY < -25) {
            normalVelocityY = -25;
        }

        if (angularVelocity > 0.2) {
            angularVelocity = 0.2;
        } else if (angularVelocity < -0.2) {
            angularVelocity = -0.2;
        }
    }

    public int getPlayer() {
        return playerNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Circle getCircle() {
        return circle;
    }

    public Line getString() {
        return string;
    }

}
