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

	private static ImagePattern asteroidImage;

	private static ImagePattern michaelBayExplosionImage;

	private static ImagePattern michaelBayPointingImage;

	private static ImagePattern michaelBayGangsterImage;

	private static ImagePattern genericExplosionImage;

	private static ImagePattern usaFlagImage;

	private static Image background;

	private static Image collisionGif;

	private static Image runAwayGif;

	private static String fileURL(String relativePath) {
		return new File(relativePath).toURI().toString();
	}

	/**
	 * Loads all of the assets needed for this program to look presentable
	 */
	public static void preloadAllAssets() {
		background = new Image(fileURL("./assets/gifs/background.gif"));

		backgroundGif = new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));

		//Michael Bay Mode gifs
		collisionGif = new Image(fileURL("./assets/gifs/collision.gif"));
		runAwayGif = new Image(fileURL("./assets/gifs/run_away.gif"));

		//Regular Simulation Images        
		earthImage = new ImagePattern(new Image(fileURL("./assets/images/earth.png")));
		neptuneImage = new ImagePattern(new Image(fileURL("./assets/images/neptune.png")));
		sunImage = new ImagePattern(new Image(fileURL("./assets/images/sun.png")));
		starImage = new ImagePattern(new Image(fileURL("./assets/images/star.png")));
		marsImage = new ImagePattern(new Image(fileURL("./assets/images/mars.png")));
		moonImage = new ImagePattern(new Image(fileURL("./assets/images/moon.png")));
		asteroidImage = new ImagePattern(new Image(fileURL("./assets/images/asteroid.png")));

		//Michael Bay mode images
		michaelBayExplosionImage = new ImagePattern(new Image(fileURL("./assets/images/bay_explosion.png")));
		michaelBayPointingImage = new ImagePattern(new Image(fileURL("./assets/images/bay_pointing.png")));
		michaelBayGangsterImage = new ImagePattern(new Image(fileURL("./assets/images/bay_gangster.png")));
		genericExplosionImage = new ImagePattern(new Image(fileURL("./assets/images/generic_explosion.png")));
		usaFlagImage = new ImagePattern(new Image(fileURL("./assets/images/usa_flag.png")));
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

	public static ImagePattern getAsteroidImage() {
		return asteroidImage;
	}

	public static ImagePattern getMichaelBayExplosionImage() {
		return michaelBayExplosionImage;
	}

	public static ImagePattern getMichaelBayPointingImage() {
		return michaelBayPointingImage;
	}

	public static ImagePattern getMichaelBayGangsterImage() {
		return michaelBayGangsterImage;
	}

	public static ImagePattern getGenericExplosionImage() {
		return genericExplosionImage;
	}

	public static ImagePattern getUsaFlagImage() {
		return usaFlagImage;
	}

	public static Image getCollisionGif() {
		return collisionGif;
	}

	public static Image getRunAwayGif() {
		return runAwayGif;
	}

}
