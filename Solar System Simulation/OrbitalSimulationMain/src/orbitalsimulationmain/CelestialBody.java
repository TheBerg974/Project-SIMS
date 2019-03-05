/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import javafx.scene.shape.Circle;
/**
 *
 * @author Francesco
 */
public class CelestialBody {
    protected double tangentialVelocity;
    protected double mass;
    protected double radius;
    protected double xCoordinate;
    protected double yCoordinate;       
    protected Circle circle;
    
    public CelestialBody(double tangentialVelocity, double mass, double radius, double xCoordinate, double yCoordinate) {
//        this.tangentialVelocity = tangentialVelocity;
//        this.mass = mass;
//        this.radius = radius;

        circle = new Circle(radius);  // radius      
        circle.setCenterX(xCoordinate); // xCoordinate
        circle.setCenterY(yCoordinate); // yCoordinate
        circle.setFill(javafx.scene.paint.Color.RED);
    }
    
    //Getters and Setters
    public double getTangentialVelocity() {
        return tangentialVelocity;
    }

    public void setTangentialVelocity(double tangentialVelocity) {
        this.tangentialVelocity = tangentialVelocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }
    
    public Circle getCircle(){
        return circle;
    }
    
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
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
