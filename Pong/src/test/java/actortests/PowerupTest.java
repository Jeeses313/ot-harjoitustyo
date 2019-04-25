package actortests;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;
import pong.domain.actors.Powerup;
import pong.domain.player.Human;

public class PowerupTest {

    Powerup power;
    Human p1;
    Human p2;
    Ball ball;

    @Before
    public void setUp() {
        power = new Powerup(0, 1, 2);
        p1 = new Human(KeyCode.UP, KeyCode.DOWN, 0, 0, 10, Color.BLACK);
        p2 = new Human(KeyCode.UP, KeyCode.DOWN, 0, 0, 10, Color.BLACK);
        ball = new Ball(0, 0, 0, 10, Color.BLACK);

    }

    @Test
    public void getSpriteCorrectX() {
        assertEquals(true, Math.abs(0 - power.getSprite().getTranslateX()) <= 0.000001);
    }

    @Test
    public void getSpriteCorrectY() {
        assertEquals(true, Math.abs(1 - power.getSprite().getTranslateY()) <= 0.000001);
    }

    @Test
    public void getSpriteCorrectColor() {
        Powerup power2 = new Powerup(0, 1, 1);
        Powerup power3 = new Powerup(0, 1, 4);
        assertEquals(Color.RED.toString(), power.getSprite().getFill().toString());
        assertEquals(Color.BLUE.toString(), power2.getSprite().getFill().toString());
        assertEquals(Color.GREEN.toString(), power3.getSprite().getFill().toString());
    }

    @Test
    public void collisionReturnsTrueWhenBallHits() {
        Ball testBall = new Ball(100, 0, 1, 0, Color.BLACK);
        assertEquals(true, power.collision(testBall));
    }

    @Test
    public void collisionReturnsFalseWhenNoBallHit() {
        Ball testBall = new Ball(1, 100, 100, 0, Color.BLACK);
        assertEquals(false, power.collision(testBall));
    }

    @Test
    public void despawnReturnSelfWhenJustSpawned() {
        assertEquals(power, power.despawn(null, null, null));
    }

    @Test
    public void despawnReturnNullWhenNotActivatedForFifteenSeconds() throws InterruptedException {
        power.spawnTime = System.currentTimeMillis() - 15100;
        assertEquals(null, power.despawn(null, null, null));
    }

    @Test
    public void activateShrinksBatThatBallIsMovingAwayFrom() {
        Powerup powerup = new Powerup(0, 0, 0);
        ball.setMovement(new Point2D(1, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(0.5 - p1.getBat().getSize()) <= 0.000001);
        assertEquals(true, Math.abs(1.0 - p2.getBat().getSize()) <= 0.000001);
    }

    @Test
    public void activateEnlargesBatThatBallIsMovingAwayFrom() {
        Powerup powerup = new Powerup(0, 0, 1);
        ball.setMovement(new Point2D(-1, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(1.0 - p1.getBat().getSize()) <= 0.000001);
        assertEquals(true, Math.abs(1.5 - p2.getBat().getSize()) <= 0.000001);
    }

    @Test
    public void activateSlowsDownBatThatBallIsMovingAwayFrom() {
        ball.setMovement(new Point2D(1, 0));
        power.activate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 0.5 - p1.getBat().getMovementSpeed()) <= 0.000001);
        assertEquals(true, Math.abs(10 * 1.0 - p2.getBat().getMovementSpeed()) <= 0.000001);
    }

    @Test
    public void activateSpeedUpBatThatBallIsMovingAwayFrom() {
        Powerup powerup = new Powerup(0, 0, 3);
        ball.setMovement(new Point2D(1, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.5 - p1.getBat().getMovementSpeed()) <= 0.000001);
        assertEquals(true, Math.abs(10 * 1.0 - p2.getBat().getMovementSpeed()) <= 0.000001);
    }
    
    @Test
    public void activateSlowsDownBall() {
        Powerup powerup = new Powerup(0, 0, 4);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 0.75 - ball.getXMovement()) <= 0.000001);
    }
    
    @Test
    public void activateSpeedUpBall() {
        Powerup powerup = new Powerup(0, 0, 5);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.25 - ball.getXMovement()) <= 0.000001);
    }
    
    @Test
    public void getSpriteReturnsNewRectangleAfterActivation() {
        Powerup powerup = new Powerup(0, 0, 5);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(true, Math.abs(0 - powerup.getSprite().getTranslateX()) <= 0.000001);
    }
    
    @Test
    public void despawnReturnSelfWhenActivatedButTenSecondsHaveNotPassed() {
        Powerup powerup = new Powerup(0, 0, 5);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        assertEquals(powerup, powerup.despawn(p1, p2, ball));
    }
    
    @Test
    public void despawnReturnNullWhenActivatedAndTenSecondsHavePassed() {
        Powerup powerup = new Powerup(0, 0, 5);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        powerup.activationTime = 10100;
        assertEquals(null, powerup.despawn(p1, p2, ball));
    }
    
    @Test
    public void deactivateDoesNothingIfNotActivated() {
        Powerup powerup = new Powerup(0, 0, 0);
        ball.setMovement(new Point2D(1, 0));
        powerup.deactivate(p1, p2, ball);
        assertEquals(-1, powerup.activationTime);
    }
    
    @Test
    public void deactivateRestoresShrunkBatSize() {
        Powerup powerup = new Powerup(0, 0, 0);
        ball.setMovement(new Point2D(1, 0));
        powerup.activate(p1, p2, ball);
        powerup.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(1.0 - p1.getBat().getSize()) <= 0.000001);
        assertEquals(true, Math.abs(1.0 - p2.getBat().getSize()) <= 0.000001);
    }

    @Test
    public void deactivateRestoresEnlargedBat() {
        Powerup powerup = new Powerup(0, 0, 1);
        ball.setMovement(new Point2D(-1, 0));
        powerup.activate(p1, p2, ball);
        powerup.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(1.0 - p1.getBat().getSize()) <= 0.000001);
        assertEquals(true, Math.abs(1.0 - p2.getBat().getSize()) <= 0.000001);
    }

    @Test
    public void deactivateRestoresSlownDownBat() {
        ball.setMovement(new Point2D(1, 0));
        power.activate(p1, p2, ball);
        power.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.0 - p1.getBat().getMovementSpeed()) <= 0.000001);
        assertEquals(true, Math.abs(10 * 1.0 - p2.getBat().getMovementSpeed()) <= 0.000001);
    }

    @Test
    public void deactivateRestoresSpedUpBat() {
        Powerup powerup = new Powerup(0, 0, 3);
        ball.setMovement(new Point2D(1, 0));
        powerup.activate(p1, p2, ball);
        powerup.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.0 - p1.getBat().getMovementSpeed()) <= 0.000001);
        assertEquals(true, Math.abs(10 * 1.0 - p2.getBat().getMovementSpeed()) <= 0.000001);
    }
    
    @Test
    public void deactivateRestoresSlownDownBall() {
        Powerup powerup = new Powerup(0, 0, 4);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        powerup.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.0 - ball.getXMovement()) <= 0.000001);
    }
    
    @Test
    public void deactivateRestoresSpedUpBall() {
        Powerup powerup = new Powerup(0, 0, 5);
        ball.setMovement(new Point2D(10, 0));
        powerup.activate(p1, p2, ball);
        powerup.deactivate(p1, p2, ball);
        assertEquals(true, Math.abs(10 * 1.0 - ball.getXMovement()) <= 0.000001);
    }
    
}
