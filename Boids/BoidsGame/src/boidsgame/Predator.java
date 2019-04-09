package boidsgame;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class Predator {

    //gets the screen size in order to place the elements of the game
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    Vector2D position;
    Vector2D velocity;
    Vector2D acceleration;
    Rectangle rect;

    //player inputs
    double maxForce;// Maximum steering force
    double maxSpeed;
    
    public Predator(double posX, double posY, boolean isScary) {
        Random random = new Random();
        double randomAngle = random.nextInt(36000) / 100;
        velocity = new Vector2D(Math.cos(randomAngle), Math.sin(randomAngle));//this gives the measure in radiants!!

        acceleration = new Vector2D(0, 0);
        position = new Vector2D(posX, posY);

        rect = new Rectangle(45, 40);
        rect.setX(posX);
        rect.setY(posY);
        
        if(isScary)
        {
            maxForce = ArenaSetup.getMaxForceScaryPredator();
            maxSpeed = ArenaSetup.getMaxSpeedScaryPredator();
        }
        else
        {
            maxForce = ArenaSetup.getMaxForcePredator();
            maxSpeed = ArenaSetup.getMaxSpeedPredator(); 
        }
    }

    //**// Common Methods //**//
    //keeps the predators apart
    public Vector2D separate(ArrayList<Predator> p) {
        Vector2D steer = new Vector2D(0, 0);
        int count = 0;
        for (Predator predator : p) {
            double distanceBetweenPredators = position.dist(predator.position);
            if ((distanceBetweenPredators > 0) && (distanceBetweenPredators < 70)) {
                Vector2D difference = position.sub(predator.position);
                difference.normalize();
                difference.div(distanceBetweenPredators);
                steer = steer.add(difference);
                count++;
            }
        }
        //average
        if (count > 0) {
            steer.div(count);
        }

        if (steer.mag() > 0) {
            steer.normalize();
            steer = steer.mult(maxSpeed);
            steer = steer.sub(velocity);
            steer.limit(maxForce);
        }
        return steer;
    }

    //steer towards a desired point
    Vector2D seek(Vector2D target) {
        Vector2D desired = target.sub(position);
        desired.normalize();
        desired = desired.mult(maxSpeed);
        desired = desired.sub(velocity);
        desired.limit(maxForce);
        return desired;
    }

    //animates all the predators
    public void animation(boolean isNormalPredator, int animationPredator) {
        if (isNormalPredator) {
            if (velocity.getX() < 0) {
                rect.setFill(AssetManager.flippedPredators(animationPredator));
            } else {
                rect.setFill(AssetManager.normalPredators(animationPredator));
            }
        } else {
            if (velocity.getX() < 0) {
                rect.setFill(AssetManager.flippedScaryPredators(animationPredator));
            } else {
                rect.setFill(AssetManager.normalScaryPredators(animationPredator));
            }
        }
    }

    //**// Normal Predator //**//
    //finds the closest boid to the predator's upper left corner
    public Vector2D findClosestBoid(ArrayList<Boid> boids) {
        double check = position.dist(boids.get(0).position);
        Vector2D checkBoid = boids.get(0).position;
        for (int x = 1; x < boids.size(); x++) {
            double validate = position.dist(boids.get(x).position);
            if (validate < check) {
                checkBoid = boids.get(x).position;
                check = validate;
            }
        }
        return seek(checkBoid);
    }

    //updates the predator's location 
    public void updatePredator(ArrayList<Boid> boids, ArrayList<Predator> predator) {
        acceleration = acceleration.add(findClosestBoid(boids).mult(1.5));
        acceleration = acceleration.add(separate(predator).mult(2.5));

        velocity = velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.mult(0);

        rect.setX(position.getX());
        rect.setY(position.getY());
    }

    //**// Scary Predators //**//
    //finds the player closest to the scary predator's left corner
    public Vector2D findClosestPlayer(ArrayList<Player> player) {
        double check = position.dist(new Vector2D(player.get(0).getCircle().getCenterX(), player.get(0).getCircle().getCenterY()));
        Vector2D checkPlayer = new Vector2D(player.get(0).getCircle().getCenterX(), player.get(0).getCircle().getCenterY());
        for (int x = 1; x < player.size(); x++) {
            double validate = position.dist(new Vector2D(player.get(x).getCircle().getCenterX(), player.get(x).getCircle().getCenterY()));
            if (validate < check) {
                checkPlayer = new Vector2D(player.get(x).getCircle().getCenterX(), player.get(x).getCircle().getCenterY());
                check = validate;
            }
        }
        return seek(checkPlayer);
    }

    //updates the scary predator's location so that it doesn't spawn-kill the player
    public void updateScaryPredator(ArrayList<Player> player, ArrayList<Predator> scaryPredator) {
        //making sure the scary predator stays between the square created by orbs
        if ((position.getX() < 180) || (position.getX() > screenWidth - 300)) {
            acceleration = acceleration.add(seek(new Vector2D(500, 500)).mult(10000));
        } else {
            acceleration = acceleration.add(findClosestPlayer(player).mult(1.5));
        }

        acceleration = acceleration.add(separate(scaryPredator).mult(2.5));

        velocity = velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.mult(0);

        rect.setX(position.getX());
        rect.setY(position.getY());
    }

    //chnages the maximum speed
    public void setMaxSpeed(float speed) {
        maxSpeed = speed;
    }

    public Rectangle getRectangle() {
        return rect;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

}
