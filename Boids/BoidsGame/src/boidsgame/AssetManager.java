/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boidsgame;

import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Screen;

/**
 *
 * @author Pegasus
 */
public class AssetManager {

    //screen area
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    //background    
    static protected Image background;
    static private Image menuBackground;
    //information menu background
    static private ImagePattern menuBack;
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
    //normal scary predator
    static private ImagePattern scaryPredatorDown;
    static private ImagePattern scaryPredatorLowMid;
    static private ImagePattern scaryPredatorHighMid;
    static private ImagePattern scaryPredatorHigh;
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
    // flipped scary predator 
    static private ImagePattern flippedScaryPredatorDown;
    static private ImagePattern flippedScaryPredatorLowMid;
    static private ImagePattern flippedScaryPredatorHighMid;
    static private ImagePattern flippedScaryPredatorHigh;

    //mast
    static private ImagePattern mast;
    static private ImagePattern closestMast;

    //spring
    static private ImagePattern springs;
    static private ImagePattern springBounced;

    //spikes
    static private ImagePattern spike;
    static private ImagePattern spikeFlipped;
    static private ImagePattern spikeUpwards;

    //player 1
    static private ImagePattern player1Swing;
    static private ImagePattern player1GoingUp;
    static private ImagePattern player1GoingDown;
    //player 2
    static private ImagePattern player2Swing;
    static private ImagePattern player2GoingUp;
    static private ImagePattern player2GoingDown;
    //player 3
    static private ImagePattern player3Swing;
    static private ImagePattern player3GoingUp;
    static private ImagePattern player3GoingDown;
    //player 3
    static private ImagePattern player4Swing;
    static private ImagePattern player4GoingUp;
    static private ImagePattern player4GoingDown;

    //line orb-player
    static private ImagePattern orbLine1;
    static private ImagePattern orbLine2;
    static private ImagePattern orbLine3;
    static private ImagePattern orbLine4;

    //**// BUTTONS //**// 
    //redw bird
    static private Image buttonRedBirdDown;
    static private Image buttonRedBirdMid;
    static private Image buttonRedBirdUp;
    // predator
    static private Image buttonPredatorDown;
    static private Image buttonPredatorLowMid;
    static private Image buttonPredatorHighMid;
    static private Image buttonPredatorHigh;
    //scroll
    static private Image scroll;


    //arraylist for animating sprites
    static private ArrayList<ImagePattern> yellowBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> blueBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> redBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> predatorArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> scaryPredatorArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedYellowBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedBlueBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedRedBirdArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedPredatorArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> flippedScaryPredatorArray = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> player1Orb = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> player2Orb = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> player3Orb = new ArrayList<ImagePattern>();
    static private ArrayList<ImagePattern> player4Orb = new ArrayList<ImagePattern>();

    static private ArrayList<Image> buttonBoid = new ArrayList<>();
    static private ArrayList<Image> buttonPredator = new ArrayList<>();
    static private ArrayList<Image> buttonPlayer = new ArrayList<>();
    static private ArrayList<Image> buttonArena = new ArrayList<>();

    static private String fileURL(String relativePath) {
        return new File(relativePath).toURI().toString();
    }

    //pre-loads all assets
    static public void getAllGameAssets() {
        //importing background
        background = new Image(fileURL("./assets/Background, Mast, Spring and Spike/backgroundWaterfall.gif"));
        menuBackground = new Image(fileURL("./assets/Background, Mast, Spring and Spike/mBackground.png"));
        //importing menu rectangle background
        menuBack = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Scroll.png")));

        //importing yellow bird sprites
        yellowBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/yellowbird-downflap.png")));
        yellowBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/yellowbird-midflap.png")));
        yellowBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/yellowbird-upflap.png")));
        //importing blue bird sprites
        blueBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/bluebird-downflap.png")));
        blueBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/bluebird-midflap.png")));
        blueBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/bluebird-upflap.png")));
        //importing red bird sprites
        redBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/redbird-downflap.png")));
        redBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/redbird-midflap.png")));
        redBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/redbird-upflap.png")));
        //importing predator sprites
        predatorDown = new ImagePattern(new Image(fileURL("./assets/Predator/Predator-1.png")));
        predatorLowMid = new ImagePattern(new Image(fileURL("./assets/Predator/Predator-2.png")));
        predatorHighMid = new ImagePattern(new Image(fileURL("./assets/Predator/Predator-3.png")));
        predatorHigh = new ImagePattern(new Image(fileURL("./assets/Predator/Predator-4.png")));
        //importing Scary predator sprites
        scaryPredatorDown = new ImagePattern(new Image(fileURL("./assets/Predator/ScaryPredator-1.png")));
        scaryPredatorLowMid = new ImagePattern(new Image(fileURL("./assets/Predator/ScaryPredator-2.png")));
        scaryPredatorHighMid = new ImagePattern(new Image(fileURL("./assets/Predator/ScaryPredator-3.png")));
        scaryPredatorHigh = new ImagePattern(new Image(fileURL("./assets/Predator/ScaryPredator-4.png")));

        //importing flipped yellow bird sprites
        flippedYellowBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-yellowbird-downflap.png")));
        flippedYellowBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-yellowbird-midflap.png")));
        flippedYellowBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-yellowbird-upflap.png")));
        //importing blue flipped bird sprites
        flippedBlueBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-bluebird-downflap.png")));
        flippedBlueBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-bluebird-midflap.png")));
        flippedBlueBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-bluebird-upflap.png")));
        //importing red flipped bird sprites
        flippedRedBirdDown = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-redbird-downflap.png")));
        flippedRedBirdMid = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-redbird-midflap.png")));
        flippedRedBirdUp = new ImagePattern(new Image(fileURL("./assets/Boids/Flipped-redbird-upflap.png")));
        //importing flipped predators
        flippedPredatorDown = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-Predator-1.png")));
        flippedPredatorLowMid = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-Predator-2.png")));
        flippedPredatorHighMid = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-Predator-3.png")));
        flippedPredatorHigh = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-Predator-4.png")));
        //importing flipped predators
        flippedScaryPredatorDown = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-ScaryPredator-1.png")));
        flippedScaryPredatorLowMid = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-ScaryPredator-2.png")));
        flippedScaryPredatorHighMid = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-ScaryPredator-3.png")));
        flippedScaryPredatorHigh = new ImagePattern(new Image(fileURL("./assets/Predator/Flipped-ScaryPredator-4.png")));

        //importing mast
        mast = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/log.png")));
        closestMast = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/closest log.png")));

        //importing springs
        springs = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Spring.png")));
        springBounced = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Spring - boing.png")));

        //importing spikes
        spike = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Spike.png")));
        spikeFlipped = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Spike - Flipped.png")));
        spikeUpwards = new ImagePattern(new Image(fileURL("./assets/Background, Mast, Spring and Spike/Spike - UpFlip.png")));

        //importing player 1
        player1GoingUp = new ImagePattern(new Image(fileURL("./assets/Player-1/Up.png")));
        player1GoingDown = new ImagePattern(new Image(fileURL("./assets/Player-1/Down.png")));
        player1Swing = new ImagePattern(new Image(fileURL("./assets/Player-1/Swing.png")));
        //importing player 2
        player2GoingUp = new ImagePattern(new Image(fileURL("./assets/Player-2/Up.png")));
        player2GoingDown = new ImagePattern(new Image(fileURL("./assets/Player-2/Down.png")));
        player2Swing = new ImagePattern(new Image(fileURL("./assets/Player-2/Swing.png")));
        //importing player 3
        player3GoingUp = new ImagePattern(new Image(fileURL("./assets/Player-3/Up.png")));
        player3GoingDown = new ImagePattern(new Image(fileURL("./assets/Player-3/Down.png")));
        player3Swing = new ImagePattern(new Image(fileURL("./assets/Player-3/Swing.png")));
        //importing player 4
        player4GoingUp = new ImagePattern(new Image(fileURL("./assets/Player-4/Up.png")));
        player4GoingDown = new ImagePattern(new Image(fileURL("./assets/Player-4/Down.png")));
        player4Swing = new ImagePattern(new Image(fileURL("./assets/Player-4/Swing.png")));

        //importing orb-player line
        orbLine1 = new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 Line.png")));
        orbLine2 = new ImagePattern(new Image(fileURL("./assets/Player-2/P-2 Line.png")));
        orbLine3 = new ImagePattern(new Image(fileURL("./assets/Player-3/P-3 Line.png")));
        orbLine4 = new ImagePattern(new Image(fileURL("./assets/Player-4/P-4 Line.png")));

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
        //add sprited to scary predator arraylist
        scaryPredatorArray.add(scaryPredatorLowMid);
        scaryPredatorArray.add(scaryPredatorHighMid);
        scaryPredatorArray.add(scaryPredatorHigh);
        scaryPredatorArray.add(scaryPredatorHighMid);
        scaryPredatorArray.add(scaryPredatorLowMid);
        scaryPredatorArray.add(scaryPredatorDown);

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
        //add sprites to flipped predator arraylist
        flippedPredatorArray.add(flippedPredatorLowMid);
        flippedPredatorArray.add(flippedPredatorHighMid);
        flippedPredatorArray.add(flippedPredatorHigh);
        flippedPredatorArray.add(flippedPredatorHighMid);
        flippedPredatorArray.add(flippedPredatorLowMid);
        flippedPredatorArray.add(flippedPredatorDown);
        //add sprites to flipped scary predator arraylist
        flippedScaryPredatorArray.add(flippedScaryPredatorLowMid);
        flippedScaryPredatorArray.add(flippedScaryPredatorHighMid);
        flippedScaryPredatorArray.add(flippedScaryPredatorHigh);
        flippedScaryPredatorArray.add(flippedScaryPredatorHighMid);
        flippedScaryPredatorArray.add(flippedScaryPredatorLowMid);
        flippedScaryPredatorArray.add(flippedScaryPredatorDown);

        //add sprites to player 1 orb Arraylist
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-1.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-2.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-3.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-4.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-5.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-6.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-7.png"))));
        player1Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-1/P-1 A-8.png"))));

        //add sprites to player 2 orb Arraylist
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-1.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-2.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-3.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-4.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-5.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-6.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-7.png"))));
        player2Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-2/P-1 A-8.png"))));

        //add sprites to player 3 orb Arraylist
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-1.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-2.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-3.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-4.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-5.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-6.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-7.png"))));
        player3Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-3/P-1 A-8.png"))));

        //add sprites to player 4 orb Arraylist
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-1.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-2.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-3.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-4.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-5.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-6.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-7.png"))));
        player4Orb.add(new ImagePattern(new Image(fileURL("./assets/Player-4/P-1 A-8.png"))));

        //**// BUTTONS //**//
        //boids
        buttonRedBirdDown = new Image(fileURL("./assets/Button/redbird-downflap.png"));
        buttonRedBirdMid = new Image(fileURL("./assets/Button/redbird-midflap.png"));
        buttonRedBirdUp = new Image(fileURL("./assets/Button/redbird-upflap.png"));
        //predator
        buttonPredatorDown = new Image(fileURL("./assets/Button/Flipped-Predator-1.png"));
        buttonPredatorLowMid = new Image(fileURL("./assets/Button/Flipped-Predator-2.png"));
        buttonPredatorHighMid = new Image(fileURL("./assets/Button/Flipped-Predator-3.png"));
        buttonPredatorHigh = new Image(fileURL("./assets/Button/Flipped-Predator-4.png"));


        //add sprites to red bird arraylist
        buttonBoid.add(buttonRedBirdMid);
        buttonBoid.add(buttonRedBirdUp);
        buttonBoid.add(buttonRedBirdMid);
        buttonBoid.add(buttonRedBirdDown);
        //add sprited to predator arraylist
        buttonPredator.add(buttonPredatorLowMid);
        buttonPredator.add(buttonPredatorHighMid);
        buttonPredator.add(buttonPredatorHigh);
        buttonPredator.add(buttonPredatorHighMid);
        buttonPredator.add(buttonPredatorLowMid);
        buttonPredator.add(buttonPredatorDown);
        //add sprites to player arraylist
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-1.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-2.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-3.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-4.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-5.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-6.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-7.png")));
        buttonPlayer.add(new Image(fileURL("./assets/Button/P-1 A-8.png")));
        //add sprites to the arena arraylist
        buttonArena.add(new Image(fileURL("./assets/Button/Spike1.png")));
        buttonArena.add(new Image(fileURL("./assets/Button/Spike2.png")));
        //scroll
        scroll = new Image(fileURL("./assets/Button/scroll2.png"));

    }

    //returns the background image according to the size of the screen
    static public Background getBackgroundImage(double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    //returns the menu background according to the size
    static public Background getMenuBackgroundImage(double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(menuBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    //returns the information image (scroll)
    static public ImagePattern getInfoMenuBackground() {
        return menuBack;
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

    //animates scary predator's sprite
    static public ImagePattern normalScaryPredators(int animation) {
        return scaryPredatorArray.get(animation);
    }

    //animates flipped predator's sprite
    static public ImagePattern flippedPredators(int animation) {
        return flippedPredatorArray.get(animation);
    }

    //animates flipped scary predator's sprite
    static public ImagePattern flippedScaryPredators(int animation) {
        return flippedScaryPredatorArray.get(animation);
    }

    //return mast skin
    static public ImagePattern mastSkin(boolean isClosest) {
        if (isClosest) {
            return closestMast;
        } else {
            return mast;
        }

    }

    //returns spring skin depending on if the player is bouncing or not 
    static public ImagePattern springSkin(boolean isIldle) {
        if (isIldle) {
            return springs;
        } else {
            return springBounced;
        }
    }

    //returns spike skin 
    static public ImagePattern spikeSkin(int location) {
        switch (location) {
            //spike is on the left
            case 1:
                return spike;
            //spike is on the right    
            case 2:
                return spikeFlipped;
            //spike is on the bottom    
            case 3:
                return spikeUpwards;
            default:
                break;
        }
        return null;
    }

    //return player movement when going up
    static public ImagePattern playerGoingUp(int player, boolean isGoingUp) {
        switch (player) {
            case 1:
                if (isGoingUp) {
                    return player1GoingUp;
                } else {
                    return player1GoingDown;
                }
            case 2:
                if (isGoingUp) {
                    return player2GoingUp;
                } else {
                    return player2GoingDown;
                }
            case 3:
                if (isGoingUp) {
                    return player3GoingUp;
                } else {
                    return player3GoingDown;
                }
            case 4:
                if (isGoingUp) {
                    return player4GoingUp;
                } else {
                    return player4GoingDown;
                }
        }
        return null;
    }

    //returns the sprite for the swinging player 
    static public ImagePattern playerSwings(int player) {
        switch (player) {
            case 1:
                return player1Swing;
            case 2:
                return player2Swing;
            case 3:
                return player3Swing;
            case 4:
                return player4Swing;
        }
        return null;
    }

    //returns the player's orb sprite according to sprite and animation location
    static public ImagePattern playerOrbSprites(int player, int animation) {
        switch (player) {
            case 1:
                return player1Orb.get(animation);
            case 2:
                return player2Orb.get(animation);
            case 3:
                return player3Orb.get(animation);
            case 4:
                return player4Orb.get(animation);
        }
        return null;
    }

    //returns the skin of the line according to the player
    static public ImagePattern OrbPlayerLine(int player) {
        switch (player) {
            case 1:
                return orbLine1;
            case 2:
                return orbLine2;
            case 3:
                return orbLine3;
            case 4:
                return orbLine4;
        }
        return null;
    }

    //**//BUTTONS
    //boid button
    static public Background getButtonBoid(int animation, double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(buttonBoid.get(animation), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    //predator button
    static public Background getButtonPredator(int animation, double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(buttonPredator.get(animation), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    //player button
    static public Background getButtonPlayer(int animation, double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(buttonPlayer.get(animation), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }
    
    //arena button
    static public Background getButtonArena(int animation, double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(buttonArena.get(animation), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }
    
    //how to playe button
    static public Background getButtonDefault(double width, double height) {
        final BackgroundSize backgroundSize = new BackgroundSize(width, height, false, false, false, false);
        return new Background(new BackgroundImage(scroll, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }
}
