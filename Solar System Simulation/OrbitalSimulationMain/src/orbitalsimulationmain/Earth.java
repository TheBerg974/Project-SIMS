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
public class Earth extends Planet{
	
	public Earth(double tangentialVelocity, double mass, double radius, double xCoordinate, double yCoordinate) {
		super(tangentialVelocity, 1/330000, radius, xCoordinate, yCoordinate);
	}

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

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
}
