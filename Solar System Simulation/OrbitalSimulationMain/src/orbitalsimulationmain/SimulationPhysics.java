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
	This method handles the application of Newton's Law of Universal Gravitation to the celestial bodies' velocity and position vectors.
	*/
	public Vector2D applyGravitationalForce(CelestialBody cb) {
		CelestialBody otherBody = new CelestialBody(0,0,0,0,0);
		cb.setTangentialVelocity(newtonsLaw(cb.getMass(),otherBody.getMass(), ));
		Vector2D position = new Vector2D(,);
		return;
	}

	/*
	 This method applies Newton's law based on the interactions between two bodies
	 */
	public double newtonsLaw(double mass1, double mass2, double distanceX, double distanceY, CelestialBody firstBody, CelestialBody otherBody) {
		distanceX = firstBody.getxCoordinate() - otherBody.getxCoordinate();
		distanceY = firstBody.getyCoordinate() - otherBody.getyCoordinate();
		double distanceTotal = Math.hypot(distanceX, distanceY) - firstBody.getRadius() - otherBody.getRadius(); //gets the total distance magnitude between the 2 circles
		double vectorScalar = (SimulationConstants.UNIV_GRAV_CONSTANT * firstBody.getMass() * otherBody.getMass())/distanceTotal;
		return vectorScalar; //TO DO
	} 
}
