package boidsgame;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Spring {

    Rectangle rectangle;
    double springiness = ArenaSetup.getSpringiness();

    Line top;
    Line right;
    Line bottom;
    Line left;

    public Spring(double posX, double posY) {
        rectangle = new Rectangle();
        rectangle.setWidth(100);
        rectangle.setHeight(20);
        rectangle.setX(posX);
        rectangle.setY(posY);        
        rectangle.setFill(AssetManager.springSkin(true));
        

        top = new Line();
        top.setStartX(posX);
        top.setStartY(posY);
        top.setEndX(posX + rectangle.getWidth());
        top.setEndY(posY);

        bottom = new Line();
        bottom.setStartX(posX);
        bottom.setStartY(posY + rectangle.getHeight());
        bottom.setEndX(posX + rectangle.getWidth());
        bottom.setEndY(posY + rectangle.getHeight());

        right = new Line();
        right.setStartX(posX + rectangle.getWidth());
        right.setStartY(posY);
        right.setEndX(posX + rectangle.getWidth());
        right.setEndY(posY + rectangle.getHeight());

        left = new Line();
        left.setStartX(posX);
        left.setStartY(posY);
        left.setEndX(posX);
        left.setEndY(posY + rectangle.getHeight());
    }

    //simulates the spring's bouncing function
    public void boingg(Player player) {
        if (player.getCircle().getBoundsInParent().intersects(right.getBoundsInParent())) {

            if ((int) player.normalVelocityX == 0) {
                player.normalVelocityX = 4;
            } else {
                player.normalVelocityX = Math.abs(player.normalVelocityX) * springiness;
            }

        } else if (player.getCircle().getBoundsInParent().intersects(left.getBoundsInParent())) {
            if ((int) player.normalVelocityX == 0) {
                player.normalVelocityX = -4;
            } else {
                player.normalVelocityX = Math.abs(player.normalVelocityX) * -springiness;
            }
        } else {
            player.normalVelocityX *= springiness;
        }

        player.normalVelocityY *= -springiness;
        player.angularVelocity *= -springiness;
        
        rectangle.setFill(AssetManager.springSkin(false));

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Line getLine() {
        return top;
    }
}
