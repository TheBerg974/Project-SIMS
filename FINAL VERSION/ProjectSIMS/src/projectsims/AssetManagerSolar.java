/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectsims;

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
public class AssetManagerSolar {

	private static Background backgroundGif = null;
	
	private static Background usaBackground = null;

	private static ImagePattern earthImage;

	private static ImagePattern neptuneImage;

	private static ImagePattern sunImage;

	private static ImagePattern starImage;

	private static ImagePattern moonImage;

	private static ImagePattern marsImage;

	private static ImagePattern asteroidImage;

	private static ImagePattern michaelBayExplosionImage;

	private static Image michaelBayPointingImage;

	private static ImagePattern michaelBayGangsterImage;

	private static ImagePattern genericExplosionImage;

	private static Image usaFlagImage;

	private static Image background;

	private static Image collisionGif;

	private static Image runAwayGif;

	private static String fileURL(String relativePath) {
		return new File(relativePath).toURI().toString();
	}

	/**
	 * Loads all of the assetsSolar needed for this program to look presentable
	 */
	public static void preloadAllAssets() {
		background = new Image(fileURL("./assetsSolar/gifs/background.gif"));

		backgroundGif = new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));

		//Michael Bay Mode gifs
		collisionGif = new Image(fileURL("./assetsSolar/gifs/collision.gif"));
		runAwayGif = new Image(fileURL("./assetsSolar/gifs/run_away.gif"));

		//Regular Simulation Images        
		earthImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/earth.png")));
		neptuneImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/neptune.png")));
		sunImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/sun.png")));
		starImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/star.png")));
		marsImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/mars.png")));
		moonImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/moon.png")));
		asteroidImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/asteroid.png")));

		//Michael Bay mode images
		michaelBayExplosionImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/bay_explosion.jpg")));
		michaelBayPointingImage = new Image(fileURL("./assetsSolar/images/bay_pointing.jpg"));
		michaelBayGangsterImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/bay_gangster.jpg")));
		genericExplosionImage = new ImagePattern(new Image(fileURL("./assetsSolar/images/generic_explosion.png")));
		usaFlagImage = new Image(fileURL("./assetsSolar/images/usa_flag.jpg"));
		usaBackground = new Background(new BackgroundImage(usaFlagImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT));
	}

	public static Background getBackgroundImage() {
		BackgroundSize backgroundSize = new BackgroundSize(1300, 900, false, false, false, false);
		return new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
	}

	public static Background getUsaBackground() {
		BackgroundSize backgroundSize = new BackgroundSize(250, 30, false, false, false, false);
        return new Background(new BackgroundImage(usaFlagImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize));
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

	public static Image getMichaelBayPointingImage() {
		return michaelBayPointingImage;
	}

	public static ImagePattern getMichaelBayGangsterImage() {
		return michaelBayGangsterImage;
	}

	public static ImagePattern getGenericExplosionImage() {
		return genericExplosionImage;
	}

	public static Image getUsaFlagImage() {
		return usaFlagImage;
	}

	public static Image getCollisionGif() {
		return collisionGif;
	}

	public static Image getRunAwayGif() {
		return runAwayGif;
	}

}
