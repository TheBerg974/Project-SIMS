/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import CelestialBody.CelestialBody;
import java.util.ArrayList;

/**
 *
 * @author Frankie
 */
public class SimulationPhysics {
	
	/**
	 * This method applies Newton's law based on the interactions between two bodies, and gives the magnitude of the vector
	 * @param firstBody
	 * @param otherBody
	 * @return 
	 */ 
    public static Vector2D newtonsLaw(CelestialBody firstBody, CelestialBody otherBody) {
        double distanceX = firstBody.getCoordinates().getX() - otherBody.getCoordinates().getX(); //x-coordinate distance between both bodies
        double distanceY = firstBody.getCoordinates().getY() - otherBody.getCoordinates().getY(); //y-coordinate distance between both bodies
        double distanceTotal = Math.hypot(distanceX, distanceY); //- firstBody.getRadius() - otherBody.getRadius(); //gets the total distance magnitude between the 2 circles
        double angle = Math.atan2(distanceY, distanceX); //TODO
        double vectorMagnitude = (SimulationConstants.UNIV_GRAV_CONSTANT * firstBody.getMass() * otherBody.getMass())/Math.pow(distanceTotal,2); //Gm1m2/r^2
        Vector2D gravAccel = new Vector2D(vectorMagnitude*Math.cos(angle), vectorMagnitude*Math.sin(angle));
        return gravAccel; 
    }
			
	
    /*
    TO DO: 
    1) (DONE) find the direction that the force is pointing to for each body
    2) (DONE) apply on the body 1 (Euler's method) 
    3) (DONE) apply opposite direction force on body 2 (E.M.) 
    4) COLLISION AND THEIR CONDITIONS
    */
    
    
}
