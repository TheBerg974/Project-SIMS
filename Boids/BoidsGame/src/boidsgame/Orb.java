
package boidsgame;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Orb {
    
    Circle circle;
    Line line = new Line();
    
    public Orb(double posX, double posY)
    {
        circle = new Circle(45);
        circle.setCenterX(posX);
        circle.setCenterY(posY);
        circle.setFill(AssetManager.playerOrbSprites(1,0));
    }
    
    //connects the orb to its player via a line
    public void connectToPlayer(Player player)
    {
        line.setVisible(true);
        line.setStartX(circle.getCenterX());
        line.setStartY(circle.getCenterY());
        line.setEndX(player.getCircle().getCenterX());
        line.setEndY(player.getCircle().getCenterY());
        line.setStrokeWidth(2);
        line.setStroke(AssetManager.OrbPlayerLine(player.getPlayer()));
        line.toBack();        
    }
    
    public Circle getCircle()
    {
        return circle;
    }
    public Line getLine()
    {
        return line;
    }
    
}
