package boidsgame;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.shape.Rectangle;

public class Boid { ///THIS SHALT BE FLAPPY BIRB

    Vector2D position;
    Vector2D velocity;
    Vector2D acceleration;
    Rectangle rect;
    int boidType;
    float separationRadius = 30.0f;//25
    float cohesionRadius = 60;//50
    float alignmentRadius = 40;//50
    float predatorRadius = 100;
    double separtionForce = 1.5;
    double alignForce = 1.0;
    double cohesionForce = 1.0;
    double predatorForce = 5;
    double maxForce;
    float maxSpeed;

    Boid(double posX, double posY) {
        Random random = new Random();
        double randomAngle = random.nextInt(36000) / 100;
        velocity = new Vector2D(Math.cos(randomAngle), Math.sin(randomAngle));//this gives the measure in radiants!!

        acceleration = new Vector2D(0, 0);
        position = new Vector2D(posX, posY);
        maxSpeed = 3;
        maxForce = 0.05;

        boidType = random.nextInt((2) + 1);

        rect = new Rectangle(20, 20);
        rect.setFill(AssetManager.getYellowBirdDown());
        rect.setX(posX);
        rect.setY(posY);
    }

    public void applyForce(Vector2D force) ///we could add mass if we wanted, like boids of different size!
    {
        acceleration = acceleration.add(force);
    }

    public void flock(ArrayList<Boid> boid, ArrayList<Predator> predator) {
        Vector2D sep = separation(boid);
        Vector2D align = alignment(boid);
        Vector2D coh = cohesion(boid);
        Vector2D pre = avoidPredator(predator);

        sep = sep.mult(separtionForce);
        align = align.mult(alignForce);
        coh = coh.mult(cohesionForce);
        pre = pre.mult(predatorForce);

        //apply forces to acceleration
        applyForce(sep);
        applyForce(align);
        applyForce(coh);
        applyForce(pre);

    }

    //Aligment of the boids
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

    //Avoid predators and obstacles
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

    Vector2D seek(Vector2D target) {
        Vector2D desired = target.sub(position);
        desired.normalize();
        desired = desired.mult(maxSpeed);
        desired = desired.sub(velocity);
        desired.limit(maxForce);
        return desired;
    }

    public void updateBoids() {
        velocity = velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.mult(0);
    }

    public void run(ArrayList<Boid> boids, ArrayList<Predator> predator) {
        flock(boids, predator);
        updateBoids();
        rect.setX(position.getX());
        rect.setY(position.getY());
    }

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
