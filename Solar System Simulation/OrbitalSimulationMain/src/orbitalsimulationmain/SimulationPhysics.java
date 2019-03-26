/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import CelestialBody.CelestialBody;
import javafx.scene.shape.Circle;

/**
 *
 * @author Frankie
 */
public class SimulationPhysics {
    /*
     This method applies Newton's law based on the interactions between two bodies, and gives the magnitude of the vector
     */
    public static Vector2D newtonsLaw(CelestialBody firstBody, CelestialBody otherBody) {
        double distanceX = firstBody.getCoordinates().getX() - otherBody.getCoordinates().getX(); //x-coordinate distance between both bodies
        double distanceY = firstBody.getCoordinates().getY() - otherBody.getCoordinates().getY(); //y-coordinate distance between both bodies
        double distanceTotal = Math.hypot(distanceX, distanceY); //- firstBody.getRadius() - otherBody.getRadius(); //gets the total distance magnitude between the 2 circles
        double angle = Math.atan2(distanceY, distanceX); //TODO
        double vectorMagnitude = (SimulationConstants.UNIV_GRAV_CONSTANT * firstBody.getMass() * otherBody.getMass())/Math.pow(distanceTotal,2); //Gm1m2/r^2
        Vector2D gravAccel = new Vector2D(vectorMagnitude*Math.cos(angle), vectorMagnitude*Math.sin(angle));
        //System.out.println(gravAccel.getX());
        //System.out.println(gravAccel.getY());
        return gravAccel; 
    }
	
	public static Vector2D handleCollisions(CelestialBody body1, CelestialBody body2) {
		//get center position vectors for each object
		Vector2D center1 = new Vector2D(body1.getCoordinates().getX(), body1.getCoordinates().getY());
		Vector2D center2 = new Vector2D(body2.getCoordinates().getX(), body2.getCoordinates().getY());
		
		//find normal and tangent vectors
		Vector2D normalVector = center1.sub(center2);
		double normalVectorX = normalVector.getX();
		double normalVectorY = normalVector.getY();
		
		Vector2D tangentVector = new Vector2D(normalVectorX, -1*normalVectorY);
		
		//update velocities according to tangent and normal vectors
		Vector2D vec1Tan = null;//original velocity vector.mult(tangentVector);
		Vector2D vec2Tan = null;//orig vel vect.mult(tangentVector);
		Vector2D vec1Norm = null;//original velocity vector.mult(normalVector);
		Vector2D vec2Norm = null;//original velocity vector.mult(normalVector);
		
		//updated velocities
		Vector2D v1Updated = vec1Tan.add(vec1Norm).sub(vec2Norm);
		Vector2D v2Updated = vec2Tan.add(vec2Norm).sub(vec1Norm);
		return (v1Updated && v2Updated);
	}
	
	public static Vector2D getNormalVector() {
		
	}
    
    /*
    TO DO: 
    1) (DONE) find the direction that the force is pointing to for each body
    2) (DONE) apply on the body 1 (Euler's method) 
    3) (DONE) apply opposite direction force on body 2 (E.M.) 
    4) 
    */
    
        
}
