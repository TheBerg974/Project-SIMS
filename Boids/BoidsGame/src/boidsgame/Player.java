
package boidsgame;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Player {
   Circle circle; 
    
    public Player()
    {
        circle = new Circle(20);
        circle.setCenterX(200);
        circle.setCenterY(200);
        circle.setFill(Paint.valueOf("blue"));
        
    }
    
    public Circle getCircle()
    {
        return circle;
    }
}
