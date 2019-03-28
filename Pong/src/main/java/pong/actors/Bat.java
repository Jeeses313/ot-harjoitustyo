
package pong.actors;

import javafx.scene.shape.Shape;
import tools.ComponentCreator;

public class Bat implements Collisionable{
    private int lastMovement; 
    private int movementSpeed;
    private double yPosition;
    private double xPosition;
    
    public Bat(int x, int y, int movementSpeed) {

        this.lastMovement = 0;
        this.movementSpeed = movementSpeed;
        this.xPosition = x;
        this.yPosition = y;
    }
    
    public void move(int direction, int miny, int maxy) {
        if(direction > 0) {
            if(this.yPosition + 100 <= maxy) {
                this.yPosition += movementSpeed;
            }
        } else if(direction < 0) {
            if(this.yPosition > miny) {
                this.yPosition -= movementSpeed;  
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

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }
    
    
        
    @Override
    public Shape getSprite() {
        return ComponentCreator.createRectangle(10, 60, this.xPosition, this.yPosition);
    }

    @Override
    public boolean collision(Collisionable other) {
        return true; 
    }
    
    public void moveTo(int y) {
        this.yPosition = y;
    }
    
    
}
