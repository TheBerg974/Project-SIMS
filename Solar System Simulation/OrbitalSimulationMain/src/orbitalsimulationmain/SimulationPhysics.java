/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

import CelestialBody.CelestialBody;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Frankie
 */
public class SimulationPhysics {

    /**
     * Gravitational Constant, also known as 'G', in Newton's Law of
     * Gravitation, with a scalar to make objects move faster
     */
    static final double UNIV_GRAV_CONSTANT = 6.64 * 10000;

    /**
     * This method applies Newton's law based on the interactions between two
     * bodies, and gives the magnitude of the vector
     *
     * @param firstBody
     * @param otherBody
     * @return
     */
    public static Vector2D newtonsLaw(CelestialBody firstBody, CelestialBody otherBody) {
        double distanceX = firstBody.getCoordinates().getX() - otherBody.getCoordinates().getX(); //x-coordinate distance between both bodies
        double distanceY = firstBody.getCoordinates().getY() - otherBody.getCoordinates().getY(); //y-coordinate distance between both bodies
        double distanceTotal = Math.hypot(distanceX, distanceY); //gets the total distance magnitude between the 2 circles
        double angle = Math.atan2(distanceY, distanceX); 
        double vectorMagnitude = (UNIV_GRAV_CONSTANT * firstBody.getMass() * otherBody.getMass()) / (Math.pow(distanceTotal, 2) * otherBody.getMass()); //Gm1m2/r^2 * m1
        Vector2D gravAccel = new Vector2D(vectorMagnitude * Math.cos(angle), vectorMagnitude * Math.sin(angle));
        System.out.println(gravAccel.getX());
        return gravAccel;
    }
    
    /**
     * This method will sum all gravitational forces present on the screen
     * @param gravitationalForces 
     */
    public static Vector2D summationOfForce(ArrayList<Vector2D> gravForces) {
        Vector2D sigmaGrav = new Vector2D(0,0);
        for (Vector2D vec: gravForces) {
            sigmaGrav = sigmaGrav.add(vec);
        }
        return sigmaGrav;
    }
    
    //Time value when simulation begins
    long initialTime = System.nanoTime();
    
    /**
     * This method is used to implement the 
     * @param instantTime
     * @param cbArrayList
     * @param removeNodeList
     * @param deadList
     * @param UI
     * @param gravForcesList
     * @param currentTime
     * @param deltaTime
     * @param previousTimeStep 
     */
    public static void startSimulation(long instantTime, ArrayList<CelestialBody> cbArrayList, ArrayList<Node> removeNodeList, ArrayList<CelestialBody> deadList, AnchorPane UI, ArrayList<Vector2D> gravForcesList, double currentTime, double deltaTime, double previousTimeStep) {

        if (cbArrayList.size() >= 2) {
            Vector2D gravitationalForce = newtonsLaw(cbArrayList.get(0), cbArrayList.get(1));
            
            for (int i = 0; i < cbArrayList.size(); i++) {
                //force in the opposite direction. To be applied on the other body so that they move towards each other, and not in the same direction
                Vector2D antiGravForce = newtonsLaw(cbArrayList.get(1), cbArrayList.get(0));

                if (i == 1) {
                    Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(gravitationalForce.mult(deltaTime));
                    Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                    cbArrayList.get(i).setCoordinates(newPosition);
                    cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);

                    cbArrayList.get(i).setCenterX(newPosition.getX());
                    cbArrayList.get(i).setCenterY(newPosition.getY());
                } else {
                    Vector2D newTangentialVelocity = cbArrayList.get(i).getTangentialVelocity().add(antiGravForce.mult(deltaTime));
                    Vector2D newPosition = cbArrayList.get(i).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
                    cbArrayList.get(i).setCoordinates(newPosition);
                    cbArrayList.get(i).setTangentialVelocity(newTangentialVelocity);

                    cbArrayList.get(i).setCenterX(newPosition.getX());
                    cbArrayList.get(i).setCenterY(newPosition.getY());
                }
                //Collision handling: if the distance between both bodies is less than or equal to the sum of their radii, then they are colliding, and it must be handled.                     
                for (CelestialBody c1 : cbArrayList) {
                    for (CelestialBody c2 : cbArrayList) {
                        if (!c1.equals(c2)) {
                            if (Math.hypot((c2.getCenterX() - c1.getCenterX()), (c2.getCenterY() - c1.getCenterY())) <= (c1.getRadius() + c2.getRadius())) {
                                handleCollisions(c1, c2, removeNodeList, deadList);
                            }
                        }
                    }
                }
            }
        } else if (cbArrayList.size() == 1) {
            Vector2D newTangentialVelocity = cbArrayList.get(0).getTangentialVelocity();
            Vector2D newPosition = cbArrayList.get(0).getCoordinates().add(newTangentialVelocity.mult(deltaTime));
            cbArrayList.get(0).setCoordinates(newPosition);
            cbArrayList.get(0).setTangentialVelocity(newTangentialVelocity);

            cbArrayList.get(0).setCenterX(newPosition.getX());
            cbArrayList.get(0).setCenterY(newPosition.getY());
        } else if (cbArrayList.size() > 2) {
            //apply physics, but SUM UP ALL THE FORCES, and apply them on each asteroid!!!
            SimulationPhysics.summationOfForce(gravForcesList);
        }
        for (Node node : removeNodeList) {
            removeFromPane(node, UI);
        }
        for (CelestialBody deadPlanet : deadList) {
            cbArrayList.remove(deadPlanet);
        }
    }

    public static void handleCollisions(CelestialBody body1, CelestialBody body2, ArrayList<Node> removeNodeList, ArrayList<CelestialBody> deadList) { //remove static?
        double massRatio = (body1.getMass() / body2.getMass());
        double massRatio2 = (body2.getMass() / body1.getMass());

        if ((massRatio > 0.5) && (massRatio <= 1) || (massRatio2 > 0.5) && (massRatio2 <= 1)) { //if 1st body is larger or equal to 50% of the other body's mass, they both annihilate
            removeNodeList.add(body1);
            deadList.add(body1);

            removeNodeList.add(body2);
            deadList.add(body2);
        } else if (massRatio < 0.5) { //if body 1 is smaller than body 2, and they collide, body 1 breaks up, and is absorbed by body 2 
            removeNodeList.add(body1);
            body2.setMass(body2.getMass() + body1.getMass());
            deadList.add(body1);
        } else if (massRatio2 < 0.5) { //if body 2 is smaller than body 1, and they collide, body 1 breaks up, and is absorbed by body 2
            removeNodeList.add(body2);
            body1.setMass(body1.getMass() + body2.getMass());
            deadList.add(body2);
        } else if ((massRatio == 0.5) || (massRatio2 == 0.5)) {
            removeNodeList.add(body1);
            deadList.add(body1);

            removeNodeList.add(body2);
            deadList.add(body2);
        }
    }

    public static void removeFromPane(Node node, AnchorPane UI) {
        UI.getChildren().remove(node);
    }
}
