
package pong.domain.actors;

import javafx.scene.shape.Shape;

public interface Collisionable {
    public boolean collision(Collisionable other);
    public Shape getSprite();
}
