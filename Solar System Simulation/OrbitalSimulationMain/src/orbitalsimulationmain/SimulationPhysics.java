/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import javafx.scene.shape.Circle;

/**
 *
 * @author Frankie
 */
public class SimulationPhysics {
    /*
     This method applies Newton's law based on the interactions between two bodies, and gives the magnitude of the vector
     */
    public double newtonsLaw(CelestialBody firstBody, CelestialBody otherBody) {
        double distanceX = firstBody.getxCoordinate() - otherBody.getxCoordinate(); //x-coordinate distance between both bodies
        double distanceY = firstBody.getyCoordinate() - otherBody.getyCoordinate(); //y-coordinate distance between both bodies
        double distanceTotal = Math.hypot(distanceX, distanceY); //- firstBody.getRadius() - otherBody.getRadius(); //gets the total distance magnitude between the 2 circles
        double angle = 3;
        double vectorMagnitude = (SimulationConstants.UNIV_GRAV_CONSTANT * firstBody.getMass() * otherBody.getMass())/Math.pow(distanceTotal,2); //Gm1m2/r^2
        return vectorMagnitude; 
    }
    
    /*
    TO DO: 
    1) find the direction that the force is pointing to for each body
    2) apply on the body 1 (Euler's method)
    3) apply opposite direction force on body 2 (E.M.)
    4) 
    */
        
}
