/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

/**
 *
 * @author cstuser
 */
public class Sun extends Star{
    
    public Sun(double tangentialVelocity, double mass, double radius, double xCoordinate, double yCoordinate) {
        super(tangentialVelocity, 1, radius, xCoordinate, yCoordinate);
    }
    
}
