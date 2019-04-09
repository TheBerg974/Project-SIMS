package boidsgame;

import java.util.Random;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Mast {

    Circle circle;

    public Mast() //// work on the randomizer
    {
        Random random = new Random();
        circle = new Circle(10);
        circle.setCenterX(random.nextInt(2000));
        circle.setCenterY(random.nextInt(1000));
        circle.setFill(AssetManager.mastSkin(false));
    }

    public Circle getCircle() {
        return circle;
    }
}
