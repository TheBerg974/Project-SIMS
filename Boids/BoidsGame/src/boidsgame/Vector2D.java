package boidsgame;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Vector2D {
    
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Accessors and Mutators
    public double getX(){ return x; }
    public double getY(){ return y; }
    
    public void  set(Vector2D other) { x = other.x; y=other.y;}
    public void  setX(double value){ x=value; }
    public void  setY(double value){ y=value; }
    
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }
    
    public Vector2D mult(double multiplier) {
        return new Vector2D(x * multiplier, y * multiplier);
    }
    
    public double magnitude()
    {
        return Math.sqrt(this.dot(this));
    }
    
    public double dot(Vector2D other){
        return x*other.x + y*other.y;
    }
    
    public void normalize()
    {
        this.set(this.mult(1.0/this.magnitude()));
    }
    
    public double dist(Vector2D v2) {
        return sqrt(pow(this.x - v2.x, 2) + pow(this.y - v2.y, 2));
    }
    
    void limit(double lim) {
        double mag = mag();
        if (mag != 0 && mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }
    double mag() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
    void div(double val) {
        x /= val;
        y /= val;
    }
}




