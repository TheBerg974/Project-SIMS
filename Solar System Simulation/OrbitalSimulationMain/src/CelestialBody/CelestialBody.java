/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CelestialBody;

import javafx.scene.shape.Circle;
import orbitalsimulationmain.Vector2D;
/**
 *
 * @author Francesco
 */
public class CelestialBody extends Circle{
    protected Vector2D tangentialVelocity;
    protected double mass;
    protected double radius;
    public Vector2D coordinates;
    
    public CelestialBody(Vector2D tangentialVelocity, double mass, double radius, Vector2D coordinates) {
        super(coordinates.getX(), coordinates.getY(), radius);
        this.tangentialVelocity = tangentialVelocity;
        this.mass = mass;
        this.radius = radius;
        this.coordinates = coordinates; 
    }
    
    //Getters and Setters

    public Vector2D getTangentialVelocity() {
        return tangentialVelocity;
    }

    public void setTangentialVelocity(Vector2D tangentialVelocity) {
        this.tangentialVelocity = tangentialVelocity;
    }
    
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public Vector2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector2D coordinates) {
        this.coordinates = coordinates;
    }
    
    /* public double applyOrbitEquationX(double oldPosition) {
        double newPosition = Math.acos((2*Math.PI*(phaseShift-initialPhase))/period);
        return newPosition;
    }
    
    public double applyOrbitEquationY(double oldPosition) {
        double newPosition = Math.asin((2*Math.PI*(phaseShift-initialPhase))/period);
        return newPosition;
    } */
    
    /*
    This method updates the x coordinate of the celestial body. 
    */
    /* public double updatePositionX(CelestialBody cb) {
        double oldPositionX = cb.getxCoordinate();
        double newPositionX = applyOrbitEquationX(oldPositionX);  //ADD THE EQUATIONS FOR MOTION 
        return newPositionX;
    } */
    /*
    This method updates the y coordinate of the celestial body. 
    */
    /* public double updatePositionY(CelestialBody cb) {
        double oldPositionY = cb.getxCoordinate();
        double newPositionY = applyOrbitEquationY(oldPositionY);  //ADD THE EQUATIONS FOR MOTION  
        return newPositionY;
    } */
}
