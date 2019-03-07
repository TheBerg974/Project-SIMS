/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boidsgame;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Pegasus
 */
public class AssetManager {
    static private Background background;
    //normal yellow bird
    static private ImagePattern yellowBirdDown;
    static private ImagePattern yellowBirdMid;
    static private ImagePattern yellowBirdUp;
    //normal blue bird
    static private ImagePattern blueBirdDown;
    static private ImagePattern blueBirdMid;
    static private ImagePattern blueBirdUp;
    //normal red bird
    static private ImagePattern redBirdDown;
    static private ImagePattern redBirdMid;
    static private ImagePattern redBirdUp;
    //normal predator
    static private ImagePattern predatorDown;
    static private ImagePattern predatorLowMid;
    static private ImagePattern predatorHighMid;
    static private ImagePattern predatorHigh;
    //flipped yellow bird
    static private ImagePattern flippedYellowBirdDown;
    static private ImagePattern flippedYellowBirdMid;
    static private ImagePattern flippedYellowBirdUp;
    //flipped blue bird
    static private ImagePattern flippedBlueBirdDown;
    static private ImagePattern flippedBlueBirdMid;
    static private ImagePattern flippedBlueBirdUp;
    //flipped red bird
    static private ImagePattern flippedRedBirdDown;
    static private ImagePattern flippedRedBirdMid;
    static private ImagePattern flippedRedBirdUp;
    //flipped predator
    static private ImagePattern flippedPredatorDown;
    static private ImagePattern flippedPredatorLowMid;
    static private ImagePattern flippedPredatorHighMid;
    static private ImagePattern flippedPredatorHigh;

    static private ArrayList<ImagePattern> yellowBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> blueBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> redBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> predatorArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedYellowBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedBlueBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedRedBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedPredatorArray = new ArrayList<ImagePattern>();

    static private String fileURL(String relativePath) {
        return new File(relativePath).toURI().toString();
    }

    static public void getAllAssets() {
        //importing yellow bird sprites
        yellowBirdDown = new ImagePattern(new Image(fileURL("./assets/yellowbird-downflap.png")));
        yellowBirdMid = new ImagePattern(new Image(fileURL("./assets/yellowbird-midflap.png")));
        yellowBirdUp = new ImagePattern(new Image(fileURL("./assets/yellowbird-upflap.png")));
        //importing blue bird sprites
        blueBirdDown = new ImagePattern(new Image(fileURL("./assets/bluebird-downflap.png")));
        blueBirdMid = new ImagePattern(new Image(fileURL("./assets/bluebird-midflap.png")));
        blueBirdUp = new ImagePattern(new Image(fileURL("./assets/bluebird-upflap.png")));
        //importing red bird sprites
        redBirdDown = new ImagePattern(new Image(fileURL("./assets/redbird-downflap.png")));
        redBirdMid = new ImagePattern(new Image(fileURL("./assets/redbird-midflap.png")));
        redBirdUp = new ImagePattern(new Image(fileURL("./assets/redbird-upflap.png")));
        //importing predator sprites
        predatorDown = new ImagePattern(new Image(fileURL("./assets/Predator-1.png")));
        predatorLowMid = new ImagePattern(new Image(fileURL("./assets/Predator-2.png")));
        predatorHighMid = new ImagePattern(new Image(fileURL("./assets/Predator-3.png")));
        predatorHigh = new ImagePattern(new Image(fileURL("./assets/Predator-4.png")));
        //importing flipped yellow bird sprites
        flippedYellowBirdDown = new ImagePattern(new Image(fileURL("./assets/Flipped-yellowbird-downflap.png")));
        flippedYellowBirdMid = new ImagePattern(new Image(fileURL("./assets/Flipped-yellowbird-midflap.png")));
        flippedYellowBirdUp = new ImagePattern(new Image(fileURL("./assets/Flipped-yellowbird-upflap.png")));
        //importing blue flipped bird sprites
        flippedBlueBirdDown = new ImagePattern(new Image(fileURL("./assets/Flipped-bluebird-downflap.png")));
        flippedBlueBirdMid = new ImagePattern(new Image(fileURL("./assets/Flipped-bluebird-midflap.png")));
        flippedBlueBirdUp = new ImagePattern(new Image(fileURL("./assets/Flipped-bluebird-upflap.png")));
        //importing red flipped bird sprites
        flippedRedBirdDown = new ImagePattern(new Image(fileURL("./assets/Flipped-redbird-downflap.png")));
        flippedRedBirdMid = new ImagePattern(new Image(fileURL("./assets/Flipped-redbird-midflap.png")));
        flippedRedBirdUp = new ImagePattern(new Image(fileURL("./assets/Flipped-redbird-upflap.png")));
        //importing flipped predators
        flippedPredatorDown = new ImagePattern(new Image(fileURL("./assets/Flipped-Predator-1.png")));
        flippedPredatorLowMid = new ImagePattern(new Image(fileURL("./assets/Flipped-Predator-2.png")));
        flippedPredatorHighMid = new ImagePattern(new Image(fileURL("./assets/Flipped-Predator-3.png")));
        flippedPredatorHigh = new ImagePattern(new Image(fileURL("./assets/Flipped-Predator-4.png")));

        //add sprites to yellow bird arraylist
        yellowBirdArray.add(yellowBirdMid);
        yellowBirdArray.add(yellowBirdUp);
        yellowBirdArray.add(yellowBirdMid);
        yellowBirdArray.add(yellowBirdDown);
        //add sprited to blue bird arraylist
        blueBirdArray.add(blueBirdMid);
        blueBirdArray.add(blueBirdUp);
        blueBirdArray.add(blueBirdMid);
        blueBirdArray.add(blueBirdDown);
        //add sprited to the red bird arraylist
        redBirdArray.add(redBirdMid);
        redBirdArray.add(redBirdUp);
        redBirdArray.add(redBirdMid);
        redBirdArray.add(redBirdDown);
        //add sprited to predator arraylist
        predatorArray.add(predatorLowMid);
        predatorArray.add(predatorHighMid);
        predatorArray.add(predatorHigh);
        predatorArray.add(predatorHighMid);
        predatorArray.add(predatorLowMid);
        predatorArray.add(predatorDown);
        //add sprites to yellow flipped bird arraylist
        flippedYellowBirdArray.add(flippedYellowBirdMid);
        flippedYellowBirdArray.add(flippedYellowBirdUp);
        flippedYellowBirdArray.add(flippedYellowBirdMid);
        flippedYellowBirdArray.add(flippedYellowBirdDown);
        //add sprites to blue flipped bird arraylist
        flippedBlueBirdArray.add(flippedBlueBirdMid);
        flippedBlueBirdArray.add(flippedBlueBirdUp);
        flippedBlueBirdArray.add(flippedBlueBirdMid);
        flippedBlueBirdArray.add(flippedBlueBirdDown);
        //add sprites to the red flipped bird arraylist
        flippedRedBirdArray.add(flippedRedBirdMid);
        flippedRedBirdArray.add(flippedRedBirdUp);
        flippedRedBirdArray.add(flippedRedBirdMid);
        flippedRedBirdArray.add(flippedRedBirdDown);
        //add sprited to flipped predator arraylist
        flippedPredatorArray.add(flippedPredatorLowMid);
        flippedPredatorArray.add(flippedPredatorHighMid);
        flippedPredatorArray.add(flippedPredatorHigh);
        flippedPredatorArray.add(flippedPredatorHighMid);
        flippedPredatorArray.add(flippedPredatorLowMid);
        flippedPredatorArray.add(flippedPredatorDown);

    }

    static public ImagePattern getYellowBirdDown() {
        return yellowBirdDown;
    }

    static public ImagePattern getYellowBirdMid() {
        return yellowBirdMid;
    }

    static public ImagePattern getYellowBirdUp() {
        return yellowBirdUp;
    }

    //animates birds' sprites depending on color
    static public ImagePattern normalBirds(int animation, int spriteNumber) {

        switch (spriteNumber) {
            case 0:
                return yellowBirdArray.get(animation);
            case 1:
                return blueBirdArray.get(animation);
            case 2:
                return redBirdArray.get(animation);
            default:
                return null;
        }
    }

    //animates flipped birds' sprite depending on color
    static public ImagePattern flippedBirds(int animation, int spriteNumber) {
        switch (spriteNumber) {
            case 0:
                return flippedYellowBirdArray.get(animation);
            case 1:
                return flippedBlueBirdArray.get(animation);
            case 2:
                return flippedRedBirdArray.get(animation);
            default:
                return null;
        }
    }

    //animates predator's sprite
    static public ImagePattern normalPredators(int animation) {
        return predatorArray.get(animation);
    }

    //animates flipped predator's sprite

    static public ImagePattern flippedPredators(int animation) {
        return flippedPredatorArray.get(animation);
    }
}
