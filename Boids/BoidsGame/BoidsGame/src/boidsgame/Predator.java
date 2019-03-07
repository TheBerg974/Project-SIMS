package boidsgame;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Predator {

    Vector2D position;
    Vector2D velocity;
    Vector2D acceleration;
    Rectangle rect;
    double maxForce;
    float maxSpeed;

    public Predator(double posX, double posY) {
        Random random = new Random();
        double randomAngle = random.nextInt(36000) / 100;
        velocity = new Vector2D(Math.cos(randomAngle), Math.sin(randomAngle));//this gives the measure in radiants!!

        acceleration = new Vector2D(0, 0);
        position = new Vector2D(posX, posY);
        maxSpeed = 2;
        maxForce = 0.5;
        
        rect = new Rectangle(40, 40);
        rect.setFill(Paint.valueOf("white"));
        rect.setX(posX);
        rect.setY(posY);
    }
    
    public Vector2D findClosestBoid(ArrayList<Boid> boids)
    {
        double check = position.dist(boids.get(0).position);
        Vector2D checkBoid = boids.get(0).position;
        for(int x = 1; x < boids.size();x++)
        {
            double validate = position.dist(boids.get(x).position);
            if(validate < check)
            {
                checkBoid = boids.get(x).position;
                check = validate;
            }            
        }
        return seek(checkBoid);
    }
    Vector2D seek(Vector2D target) {
        Vector2D desired = target.sub(position);
        desired.normalize();
        desired = desired.mult(maxSpeed);
        desired = desired.sub(velocity);
        desired.limit(maxForce);
        return desired;
    }
    
    public Vector2D separate(ArrayList<Predator> p)
    {
        Vector2D steer = new Vector2D(0, 0);
        int count = 0;

        for (Predator predator : p) {
            double distanceBetweenBoids = position.dist(predator.position);
            if ((distanceBetweenBoids > 0) && (distanceBetweenBoids < 100)) {
                Vector2D difference = position.sub(predator.position);
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
    
    public void updatePredator(ArrayList<Boid> boids, ArrayList<Predator> predator)
    {
        acceleration = acceleration.add(findClosestBoid(boids).mult(1.5));
        acceleration = acceleration.add(separate(predator).mult(2.5));
        
        velocity = velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position = position.add(velocity);
        acceleration = acceleration.mult(0);
        
        rect.setX(position.getX());
        rect.setY(position.getY());
    }
    
    public boolean isGoingLeft()
    {
        if(velocity.getX() < 0)
        {
            return true;
        }
        else
        {
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
    
}
