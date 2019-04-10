
package boidsgame;

import javafx.scene.shape.Rectangle;

public class Spike {
    Rectangle rectangle;
    
    public Spike(double posX, double posY, int location)
    {
        rectangle = new Rectangle();
        rectangle.setWidth(30);
        rectangle.setHeight(30);
        rectangle.setX(posX);
        rectangle.setY(posY);
        rectangle.setFill(AssetManager.spikeSkin(location));
    }
    
        
    public Rectangle getRectangle() {
        return rectangle;
    }
}
