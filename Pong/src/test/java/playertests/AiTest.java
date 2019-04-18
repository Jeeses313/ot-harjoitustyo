package playertests;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;
import pong.domain.player.Ai;

public class AiTest {

    Ai easy;
    Ai normal;
    Ai hard;

    @Before
    public void setUp() {
        easy = new Ai(1, 2, 0, Color.BLACK);
        normal = new Ai(1, 2, 1, Color.BLACK);
        hard = new Ai(1, 2, 2, Color.BLACK);
    }

    @Test
    public void newAiEasyCorrectBat() {
        Bat testBat = easy.getBat();
        assertEquals(true, (testBat.getxPosition() == 1) && (testBat.getyPosition() == 2) && (testBat.getMovementSpeed() == 5));
    }

    @Test
    public void newAiNormalCorrectBat() {
        Bat testBat = normal.getBat();
        assertEquals(true, (testBat.getxPosition() == 1) && (testBat.getyPosition() == 2) && (testBat.getMovementSpeed() == 3));
    }

    @Test
    public void newAiHardCorrectBat() {
        Bat testBat = easy.getBat();
        assertEquals(true, (testBat.getxPosition() == 1) && (testBat.getyPosition() == 2) && (testBat.getMovementSpeed() == 5));
    }

    @Test
    public void newAiCorrectUpKey() {
        assertEquals(null, easy.getUp());
    }

    @Test
    public void newAiCorrectDownKey() {
        assertEquals(null, easy.getDown());
    }

    @Test
    public void moveBatToMovesBat() {
        easy.moveBatTo(13);
        assertEquals(true, Math.abs(easy.getBat().getyPosition() - 13) <= 0.000001);
    }

    @Test
    public void moveBatEasyCorrectDirectionWhenBallLowerThanBat() {
        Ball testBall = new Ball(1, 0, 10, 0, Color.BLACK);
        int direction = easy.moveBat(0, 0, 10, testBall);
        assertEquals(1, direction);
    }

    @Test
    public void moveBatEasyCorrectDirectionWhenBallHigherThanBat() {
        Ball testBall = new Ball(1, 0, -10, 0, Color.BLACK);
        int direction = easy.moveBat(0, 0, 10, testBall);
        assertEquals(-1, direction);
    }

    @Test
    public void moveBatNormalCorrectDirectionWhenBallMovesDown() {
        Ball testBall = new Ball(1, 0, 10, 0, Color.BLACK);
        testBall.setMovement(new Point2D(0, 10));
        int direction = normal.moveBat(0, 0, 10, testBall);
        assertEquals(1, direction);
    }

    @Test
    public void moveBatNormalCorrectDirectionWhenBallMovesUp() {
        Ball testBall = new Ball(1, 0, -10, 0, Color.BLACK);
        testBall.setMovement(new Point2D(0, -10));
        int direction = normal.moveBat(0, 0, 10, testBall);
        assertEquals(-1, direction);
    }

    @Test
    public void moveBatHardCorrectDirectionWhenBallMovesDown() {
        Ball testBall = new Ball(1, 0, 10, 0, Color.BLACK);
        testBall.setMovement(new Point2D(0, 10));
        int direction = hard.moveBat(0, 0, 10, testBall);
        assertEquals(1, direction);
    }

    @Test
    public void moveBatHardCorrectDirectionWhenBallMovesUp() {
        Ball testBall = new Ball(1, 0, -10, 0, Color.BLACK);
        testBall.setMovement(new Point2D(0, -10));
        int direction = hard.moveBat(0, 0, 10, testBall);
        assertEquals(-1, direction);
    }

}
