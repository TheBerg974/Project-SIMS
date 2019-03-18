/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalsimulationmain;

/**
 *
 * @author Francesco
 */
public interface SimulationConstants {
    
    /*
    Represents the amount of times the game physics will refresh itself per second.
    */
    int REFRESH_RATE_PHYSICS = 100;
    
    /*
        Represents the amount of times the GUI will refresh itself per second.
    */
    int REFRESH_RATE_GUI = 60;
    
	/*
	Gravitational Constant, also known as 'G', in Newton's Law of Gravitation
	*/
	double UNIV_GRAV_CONSTANT = 6.64 * Math.pow(10,-11);
}
