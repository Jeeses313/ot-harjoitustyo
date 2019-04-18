package pong.domain.actors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import pong.tools.ComponentCreator;

public class Wall implements Collisionable {

    private int xPosition;
    private int yPosition;

    public Wall(int xposition, int yposition) {
        this.xPosition = xposition;
        this.yPosition = yposition;
    }

    @Override
    public boolean collision(Collisionable other) {
        return true;
    }

    @Override
    public Shape getSprite() {
        return ComponentCreator.createRectangle(20, 420, this.xPosition, this.yPosition, Color.BLACK);
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

}
