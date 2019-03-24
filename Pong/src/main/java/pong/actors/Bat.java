
package pong.actors;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Bat implements Collisionable{
    private Rectangle sprite;
    private int lastMovement; 
    private int movementSpeed;
    
    public Bat(int x, int y, int movementSpeed) {
        this.sprite = new Rectangle(10, 60);
        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
        this.lastMovement = 0;
        this.movementSpeed = movementSpeed;
    }
    
    public void move(int direction, int miny, int maxy) {
        if(direction > 0) {
            if(this.sprite.getTranslateY() + 100 <= maxy) {
                this.sprite.setTranslateY(this.sprite.getTranslateY() + movementSpeed);
            }
        } else if(direction < 0) {
            if(this.sprite.getTranslateY() > 0) {
                this.sprite.setTranslateY(this.sprite.getTranslateY() - movementSpeed);    
            }
        }
        this.lastMovement = direction;
        
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    
    

    public int getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(int lastMovement) {
        this.lastMovement = lastMovement;
    }
    
    

    @Override
    public Shape getSprite() {
        return sprite;
    }
    
    public void setSize(int size) {
        this.sprite.setScaleY(size);
    }

    @Override
    public boolean collision(Collisionable other) {
        return true; 
    }
    
    public void moveTo(int y) {
        this.sprite.setTranslateY(y);
    }
    
    
}
