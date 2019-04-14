/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetMgr;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author cstuser
 */
public class AssetManager {

    private static Background backgroundGif = null;

    private static ImagePattern earthImage;

    private static ImagePattern neptuneImage;

    private static ImagePattern sunImage;
    
    private static ImagePattern starImage;
    
    private static ImagePattern moonImage;

    private static ImagePattern marsImage;

    private static Image background;

    private static String fileURL(String relativePath) {
        return new File(relativePath).toURI().toString();
    }

    public static void preloadAllAssets() {
        background = new Image(fileURL("./assets/gifs/background.gif"));

        backgroundGif = new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
        System.out.println("ree");
        earthImage = new ImagePattern(new Image(fileURL("./assets/images/earth.png")));

        neptuneImage = new ImagePattern(new Image(fileURL("./assets/images/neptune.png")));

        sunImage = new ImagePattern(new Image(fileURL("./assets/images/sun.png")));
        
        starImage = new ImagePattern(new Image(fileURL("./assets/images/star.png")));

        marsImage = new ImagePattern(new Image(fileURL("./assets/images/mars.png")));
        
        moonImage = new ImagePattern(new Image(fileURL("./assets/images/moon.png")));
        System.out.println("nooo");
    }

    public static Background getBackgroundImage() {
        BackgroundSize backgroundSize = new BackgroundSize(1300, 900, false, false, false, false);
        return new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
    }

    public static ImagePattern getEarthImage() {
        return earthImage;
    }

    public static ImagePattern getNeptuneImage() {
        return neptuneImage;
    }

    public static ImagePattern getSunImage() {
        return sunImage;
    }

    public static ImagePattern getStarImage() {
        return starImage;
    }

    public static ImagePattern getMoonImage() {
        return moonImage;
    }

    public static ImagePattern getMarsImage() {
        return marsImage;
    }

}
