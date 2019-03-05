package orbitalsimulationmain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francesco
 */
public class Vector2D {
    
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    //Getters and setters
    public double getX(){ 
        return x; 
    }
    public double getY(){ 
        return y; 
    }
    
    public void  setX(double value){ 
        x=value; 
    }
    public void  setY(double value){ 
        y=value; 
    }
    
    //addition of 2 vectors
    public Vector2D add(Vector2D otherVector) {
        return new Vector2D(x + otherVector.x, y + otherVector.y);
    }
    //subtraction
    public Vector2D sub(Vector2D otherVector) {
        return new Vector2D(x - otherVector.x, y - otherVector.y);
    }
    //multiplication
    public Vector2D mult(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }
    //dot product
    public double dot(Vector2D otherVector){
        return x*otherVector.x + y*otherVector.y;
    }
    //1200x
    //450y
}
