
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
        circle.setCenterX(random.nextInt(720));
        circle.setCenterY(random.nextInt(720));
        circle.setFill(Paint.valueOf("green"));
    }
    
    
    public Circle getCircle()
    {
        return circle;
    }
}
