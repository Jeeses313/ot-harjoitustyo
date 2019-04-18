package playertests;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;
import pong.domain.player.Human;

public class HumanTest {

    Human player;

    @Before
    public void setUp() {
        player = new Human(KeyCode.UP, KeyCode.DOWN, 1, 2, 3, Color.BLACK);
    }

    @Test
    public void newHumanCorrectBat() {
        Bat testBat = player.getBat();
        assertEquals(true, (testBat.getxPosition() == 1) && (testBat.getyPosition() == 2) && (testBat.getMovementSpeed() == 3));
    }

    @Test
    public void newHumanCorrectUpKey() {
        assertEquals(KeyCode.UP, player.getUp());
    }

    @Test
    public void newHumanCorrectDownKey() {
        assertEquals(KeyCode.DOWN, player.getDown());
    }

    @Test
    public void moveBatReturnsDirection() {
        Ball testBall = new Ball(5, 1, 2, 3, Color.BLACK);
        int direction = player.moveBat(1, 0, 1, testBall);
        assertEquals(1, direction);
    }

    @Test
    public void moveBatMovesBat() {
        Ball testBall = new Ball(5, 1, 2, 3, Color.BLACK);
        player.moveBat(1, 0, 200, testBall);
        assertEquals(true, Math.abs(player.getBat().getyPosition() - 5) <= 0.000001);
    }

    @Test
    public void moveBatToMovesBat() {
        player.moveBatTo(13);
        assertEquals(true, Math.abs(player.getBat().getyPosition() - 13) <= 0.000001);
    }
    
}
