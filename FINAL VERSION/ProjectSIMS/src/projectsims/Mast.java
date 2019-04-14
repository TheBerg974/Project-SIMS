package projectsims;

import projectsims.AssetManagerBoids;
import java.util.Random;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Mast {

    Circle circle;

    public Mast(double posX, double posY) {
        circle = new Circle(10);        
        circle.setCenterX(posX);
        circle.setCenterY(posY);
        circle.setFill(AssetManagerBoids.mastSkin(false));
    }
    
    public Circle getCircle() {
        return circle;
    }
}
