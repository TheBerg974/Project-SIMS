package boidsgame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ArenaSetup {
    //size of screen    
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    //Arena inputs
    static double time = 60;
    static int numberOfMasts = 20;
    static double mastRadius = 50;
    static int numberOfSprings = 30;
    static double springiness = 1.2;
    static double springRadius = 50;

    //Predator inputs
    static double maxForcePredator = 0.5;
    static double maxSpeedPredator = 2;
    static double maxForceScaryPredator = 0.5;
    static double maxSpeedScaryPredator = 1;
    static int numberOfPredators = 3;

    //Boid inputs
    //  //radius
    static double separationRadius = 30;//25
    static double cohesionRadius = 60;//50
    static double alignmentRadius = 40;//50
    static double predatorRadius = 100;
    static double playerRadius = 200;
    //  //forces
    static double separtionForce = 1.5;
    static double alignForce = 1.0;
    static double cohesionForce = 1.0;
    static double predatorForce = 5;
    static double playerForce = 10;
    //  //extra
    static double maxForceBoid = 0.05;
    static double maxSpeedBoid = 3;
    static int nbOfBoids = 600;
    static boolean isDifferentMass = true;

    //Player inputs 
    static double gravity = 0.4;
    static double friction = 1;
    static boolean isPlayer1Playing = true;
    static boolean isPlayer2Playing = false;
    static boolean isPlayer3Playing = false;
    static boolean isPlayer4Playing = false;

    //Predator getters and setters
    public static int getNumberOfPredators() {
        return numberOfPredators;
    }

    public static void setNumberOfPredators(int numberOfPredators) {
        ArenaSetup.numberOfPredators = numberOfPredators;
    }

    public static double getMaxForceScaryPredator() {
        return maxForceScaryPredator;
    }

    public static void setMaxForceScaryPredator(double maxForceScaryPredator) {
        ArenaSetup.maxForceScaryPredator = maxForceScaryPredator;
    }

    public static double getMaxSpeedScaryPredator() {
        return maxSpeedScaryPredator;
    }

    public static void setMaxSpeedScaryPredator(double maxSpeedScaryPredator) {
        ArenaSetup.maxSpeedScaryPredator = maxSpeedScaryPredator;
    }

    public static double getMaxForcePredator() {
        return maxForcePredator;
    }

    public static void setMaxForcePredator(double maxForcePredator) {
        ArenaSetup.maxForcePredator = maxForcePredator;
    }

    public static double getMaxSpeedPredator() {
        return maxSpeedPredator;
    }

    public static void setMaxSpeedPredator(double maxSpeedPredator) {
        ArenaSetup.maxSpeedPredator = maxSpeedPredator;
    }

    //Arena getters and setters
    static public void setTime(double playTime) {
        time = playTime;
    }

    static public double getTime() {
        return time;
    }

    public static int getNumberOfMasts() {
        return numberOfMasts;
    }

    public static void setNumberOfMasts(int numberOfMasts) {
        ArenaSetup.numberOfMasts = numberOfMasts;
    }

    public static int getNumberOfSprings() {
        return numberOfSprings;
    }

    public static void setNumberOfSprings(int numberOfSprings) {
        ArenaSetup.numberOfSprings = numberOfSprings;
    }

    public static double getMastRadius() {
        return mastRadius;
    }

    public static void setMastRadius(double mastRadius) {
        ArenaSetup.mastRadius = mastRadius;
    }

    public static double getSpringiness() {
        return springiness;
    }

    public static void setSpringiness(double springiness) {
        ArenaSetup.springiness = springiness;
    }

    public static double getSpringRadius() {
        return springRadius;
    }

    public static void setSpringRadius(double springRadius) {
        ArenaSetup.springRadius = springRadius;
    }

    //Boid getters and settters
    public static double getSeparationRadius() {
        return separationRadius;
    }

    public static void setSeparationRadius(double separationRadius) {
        ArenaSetup.separationRadius = separationRadius;
    }

    public static double getCohesionRadius() {
        return cohesionRadius;
    }

    public static void setCohesionRadius(double cohesionRadius) {
        ArenaSetup.cohesionRadius = cohesionRadius;
    }

    public static double getAlignmentRadius() {
        return alignmentRadius;
    }

    public static void setAlignmentRadius(double alignmentRadius) {
        ArenaSetup.alignmentRadius = alignmentRadius;
    }

    public static double getPredatorRadius() {
        return predatorRadius;
    }

    public static void setPredatorRadius(double predatorRadius) {
        ArenaSetup.predatorRadius = predatorRadius;
    }

    public static double getPlayerRadius() {
        return playerRadius;
    }

    public static void setPlayerRadius(double playerRadius) {
        ArenaSetup.playerRadius = playerRadius;
    }

    public static double getSepartionForce() {
        return separtionForce;
    }

    public static void setSepartionForce(double separtionForce) {
        ArenaSetup.separtionForce = separtionForce;
    }

    public static double getAlignForce() {
        return alignForce;
    }

    public static void setAlignForce(double alignForce) {
        ArenaSetup.alignForce = alignForce;
    }

    public static double getCohesionForce() {
        return cohesionForce;
    }

    public static void setCohesionForce(double cohesionForce) {
        ArenaSetup.cohesionForce = cohesionForce;
    }

    public static double getPredatorForce() {
        return predatorForce;
    }

    public static void setPredatorForce(double predatorForce) {
        ArenaSetup.predatorForce = predatorForce;
    }

    public static double getPlayerForce() {
        return playerForce;
    }

    public static void setPlayerForce(double playerForce) {
        ArenaSetup.playerForce = playerForce;
    }

    public static double getMaxForceBoid() {
        return maxForceBoid;
    }

    public static void setMaxForceBoid(double maxForceBoid) {
        ArenaSetup.maxForceBoid = maxForceBoid;
    }

    public static double getMaxSpeedBoid() {
        return maxSpeedBoid;
    }

    public static void setMaxSpeedBoid(double maxSpeedBoid) {
        ArenaSetup.maxSpeedBoid = maxSpeedBoid;
    }

    public static int getNbOfBoids() {
        return nbOfBoids;
    }

    public static void setNbOfBoids(int nbOfBoids) {
        ArenaSetup.nbOfBoids = nbOfBoids;
    }

    public static boolean isIsDifferentMass() {
        return isDifferentMass;
    }

    public static void setIsDifferentMass(boolean isDifferentMass) {
        ArenaSetup.isDifferentMass = isDifferentMass;
    }

    //Player getters and setters 
    public static double getGravity() {
        return gravity;
    }

    public static void setGravity(double gravity) {
        ArenaSetup.gravity = gravity;
    }

    public static double getFriction() {
        return friction;
    }

    public static void setFriction(double friction) {
        ArenaSetup.friction = friction;
    }

    public static boolean isIsPlayer1Playing() {
        return isPlayer1Playing;
    }

    public static void setIsPlayer1Playing(boolean isPlayer1Playing) {
        ArenaSetup.isPlayer1Playing = isPlayer1Playing;
    }

    public static boolean isIsPlayer2Playing() {
        return isPlayer2Playing;
    }

    public static void setIsPlayer2Playing(boolean isPlayer2Playing) {
        ArenaSetup.isPlayer2Playing = isPlayer2Playing;
    }

    public static boolean isIsPlayer3Playing() {
        return isPlayer3Playing;
    }

    public static void setIsPlayer3Playing(boolean isPlayer3Playing) {
        ArenaSetup.isPlayer3Playing = isPlayer3Playing;
    }

    public static boolean isIsPlayer4Playing() {
        return isPlayer4Playing;
    }

    public static void setIsPlayer4Playing(boolean isPlayer4Playing) {
        ArenaSetup.isPlayer4Playing = isPlayer4Playing;
    }
}
