package boidsgame;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.shape.Rectangle;

public class Boid {

    Vector2D position;
    Vector2D velocity;
    Vector2D acceleration;

    //boid body
    Rectangle rect;

    //type of boid (influences the mass and color of the boid)
    int boidType;
    //mass of the boid
    int mass; // mass makes it so that boids adhere slower or faster to a group /////////////////////////////////////////////////////////////////////////////

    
    // radius inputs 
    double separationRadius = ArenaSetup.getSeparationRadius();
    double cohesionRadius = ArenaSetup.getCohesionRadius();
    double alignmentRadius = ArenaSetup.getAlignmentRadius();
    double predatorRadius = ArenaSetup.getPredatorRadius();
    double playerRadius = ArenaSetup.getPlayerRadius();

    //force inputs
    double separtionForce = ArenaSetup.getSepartionForce();
    double alignForce = ArenaSetup.getAlignForce();
    double cohesionForce = ArenaSetup.getCohesionForce();
    double predatorForce = ArenaSetup.getPredatorForce();
    double playerForce = ArenaSetup.getPlayerForce();

    //extra inputs 
    double maxForce = ArenaSetup.getMaxForceBoid(); // Maximum steering force
    double maxSpeed = ArenaSetup.getMaxSpeedBoid();

    Boid(double posX, double posY) {
        Random random = new Random();
        double randomAngle = random.nextInt(36000) / 100;
        velocity = new Vector2D(Math.cos(randomAngle), Math.sin(randomAngle));

        acceleration = new Vector2D(0, 0);
        position = new Vector2D(posX, posY);

        //randomly selects the boid type
        boidType = random.nextInt((2) + 1);
        //selects the mass according to player input
        if (ArenaSetup.isDifferentMass()) {
            mass = boidType + 1;
        } else {
            mass = 1;
        }

        rect = new Rectangle(20, 20);
        rect.setX(posX);
        rect.setY(posY);
    }

    //changes the acceleration based on the force and the mass of the boid
    public void applyForce(Vector2D force) {
        force.div(mass);
        acceleration = acceleration.add(force);
    }

    //combines all the individual boid movements to create a flocking behaviour
    public void flock(ArrayList<Boid> boid, ArrayList<Predator> predator, ArrayList<Player> player) {
        Vector2D sep = separation(boid);
        Vector2D align = alignment(boid);
        Vector2D coh = cohesion(boid);
        Vector2D pre = avoidPredator(predator);
        Vector2D play = avoidPlayer(player);

        //applies the player input forces
        sep = sep.mult(separtionForce);
        align = align.mult(alignForce);
        coh = coh.mult(cohesionForce);
        pre = pre.mult(predatorForce);
        play = play.mult(playerForce);

        //apply forces to acceleration
        applyForce(sep);
        applyForce(align);
        applyForce(coh);
        applyForce(play);
        applyForce(pre);

    }

    //Aligment of the boids in the flock
    public Vector2D alignment(ArrayList<Boid> boids) {
        Vector2D sum = new Vector2D(0, 0);
        int count = 0;
        for (Boid other : boids) {
            double distanceBetweenBoids = position.dist(other.position);
            if ((distanceBetweenBoids > 0) && (distanceBetweenBoids < alignmentRadius)) {
                sum = sum.add(other.velocity);
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            sum.normalize();
            sum = sum.mult(maxSpeed);
            Vector2D steer = sum.sub(velocity);
            steer.limit(maxForce);
            return steer;
        } else {
            return new Vector2D(0, 0);
        }
    }

    //Cohesion of the flock
    public Vector2D cohesion(ArrayList<Boid> boids) {
        Vector2D sum = new Vector2D(0, 0);
        int count = 0;
        for (Boid other : boids) {
            double d = position.dist(other.position);
            if ((d > 0) && (d < cohesionRadius)) {
                sum = sum.add(other.position); // Add position
                count++;
            }
        }
        if (count > 0) {
            sum.div(count);
            return seek(sum);  // Steer towards the position
        } else {
            return new Vector2D(0, 0);
        }
    }

    //Separation between boids
    public Vector2D separation(ArrayList<Boid> boids) {
        Vector2D steer = new Vector2D(0, 0);
        int count = 0;

        for (Boid other : boids) {
            double distanceBetweenBoids = position.dist(other.position);
            if ((distanceBetweenBoids > 0) && (distanceBetweenBoids < separationRadius)) {
                Vector2D difference = position.sub(other.position);
                difference.normalize();
                difference.div(distanceBetweenBoids);
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

    //Avoid predators 
    public Vector2D avoidPredator(ArrayList<Predator> predator) {
        Vector2D steer = new Vector2D(0, 0);
        int count = 0;

        for (Predator other : predator) {
            double distanceBetweenBoids = position.dist(other.position);
            if ((distanceBetweenBoids > 0) && (distanceBetweenBoids < predatorRadius)) {
                Vector2D difference = position.sub(other.position);
                difference.normalize();
                difference.div(distanceBetweenBoids);
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

    //Avoid players 
    public Vector2D avoidPlayer(ArrayList<Player> other) {
        Vector2D steer = new Vector2D(0, 0);
        int count = 0;

        for (Player player : other) {
            Vector2D positionPlayer = new Vector2D(player.getCircle().getCenterX(), player.getCircle().getCenterY());
            double distanceBetweenBoids = position.dist(positionPlayer);
            if ((distanceBetweenBoids > 0) && (distanceBetweenBoids < playerRadius)) {
                Vector2D difference = position.sub(positionPlayer);
                difference.normalize();
                difference.div(distanceBetweenBoids);
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

    //update the boid's movement (speed & acceleration)
    public void updateBoids() {
        velocity = velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.mult(0);
    }

    //moves the whole flock and updates the boid's position
    public void run(ArrayList<Boid> boids, ArrayList<Predator> predator, ArrayList<Player> player) {
        flock(boids, predator, player);
        updateBoids();
        rect.setX(position.getX());
        rect.setY(position.getY());
    }

    //if the boid goes to the left, return true
    public boolean gointLeft() {
        if (velocity.getX() < 0) {
            return true;
        } else {
            return false;
        }
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

    public int getBoidType() {
        return boidType;
    }
}
