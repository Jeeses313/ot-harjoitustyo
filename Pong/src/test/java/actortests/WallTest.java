
package actortests;

import javafx.scene.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.actors.Bat;
import pong.domain.actors.Wall;


public class WallTest {
    
    Wall testWall;

    @Before
    public void setUp() {
        testWall = new Wall(15, 20);
    }
    
    @Test
    public void newBatCorrectX() {
        assertEquals(true, Math.abs(15 - testWall.getxPosition()) <= 0.000001);
    }
    
    @Test
    public void newBatCorrectY() {
        assertEquals(true, Math.abs(20 - testWall.getyPosition()) <= 0.000001);
    }
    
    @Test
    public void getSpriteCorrectX() {
        Shape batSprite = testWall.getSprite();
        assertEquals(true, Math.abs(15 - batSprite.getTranslateX()) <= 0.000001);
    }

    @Test
    public void getSpriteCorrectY() {
        Shape batSprite = testWall.getSprite();
        assertEquals(true, Math.abs(20 - batSprite.getTranslateY()) <= 0.000001);
    }
    
    @Test
    public void collisionReturnsFalse() {
        assertEquals(false, testWall.collision(null));
    }
    
}
