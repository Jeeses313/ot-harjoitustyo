package actortests;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pong.domain.actors.Bat;

public class BatTest {
    Bat testBat;

    @Before
    public void setUp() {
        testBat = new Bat(15, 20, 5, Color.BLACK);
    }
    
    @Test
    public void newBatCorrectX() {
        assertEquals(true, Math.abs(15 - testBat.getxPosition()) <= 0.000001);
    }
    
    @Test
    public void newBatCorrectY() {
        assertEquals(true, Math.abs(20 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void newBatCorrectMovementSpeed() {
        assertEquals(5, testBat.getMovementSpeed());
    }
    
    @Test
    public void newBatCorrectLastMovement() {
        assertEquals(0, testBat.getLastMovement());
    }
    
    @Test
    public void moveCorrectYWhenCanMoveUp() {
        testBat.move(-1, -200, 200);
        assertEquals(true, Math.abs(15 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void moveCorrectYWhenCanMoveDown() {
        testBat.move(1, -200, 200);
        assertEquals(true, Math.abs(25 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void moveCorrectYWhenCantMoveUp() {
        testBat.move(-1, 20, 20);
        assertEquals(true, Math.abs(20 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void moveCorrectYWhenCantMoveDown() {
        testBat.move(1, 20, 20);
        assertEquals(true, Math.abs(20 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void moveCorrectLastMovement() {
        testBat.move(5, 20, 20);
        assertEquals(5, testBat.getLastMovement());
    }
    
    @Test
    public void setMovementSpeedCorrectSpeed() {
        testBat.setMovementSpeed(10);
        assertEquals(10, testBat.getMovementSpeed());
    }
    
    @Test
    public void setLastMovementCorrect() {
        testBat.setLastMovement(10);
        assertEquals(10, testBat.getLastMovement());
    }
    
    @Test
    public void getSpriteCorrectX() {
        Shape batSprite = testBat.getSprite();
        assertEquals(true, Math.abs(15 - batSprite.getTranslateX()) <= 0.000001);
    }

    @Test
    public void getSpriteCorrectY() {
        Shape batSprite = testBat.getSprite();
        assertEquals(true, Math.abs(20 - batSprite.getTranslateY()) <= 0.000001);
    }
    
    @Test
    public void collisionReturnsFalse() {
        assertEquals(false, testBat.collision(null));
    }
    
    @Test
    public void moveToCorrectX() {
        testBat.moveTo(0);
        assertEquals(true, Math.abs(15 - testBat.getxPosition()) <= 0.000001);
    }
    
    @Test
    public void moveToCorrectY() {
        testBat.moveTo(0);
        assertEquals(true, Math.abs(0 - testBat.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void setSizeSetsSize() {
        testBat.setSize(0.5);
        assertEquals(true, Math.abs(0.5 - testBat.getSize()) <= 0.000001);
    }
}

