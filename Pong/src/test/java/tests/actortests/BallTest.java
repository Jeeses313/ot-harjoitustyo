package tests.actortests;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pong.actors.Ball;
import pong.actors.Bat;

public class BallTest {

    Ball testBall;

    @Before
    public void setUp() {
        testBall = new Ball(10, 15, 20, 5);
    }

    @Test
    public void newBallCorrectX() {
        assertEquals(true, Math.abs(15 - testBall.getxPosition()) <= 0.000001);
    }

    @Test
    public void newBallCorrectY() {
        assertEquals(true, Math.abs(20 - testBall.getyPosition()) <= 0.000001);
    }

    @Test
    public void newBallCorrectYMovement() {
        assertEquals(true, Math.abs(0 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void newBallCorrectXMovement() {
        assertEquals(true, Math.abs(0 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void newBallCorrectRadius() {
        assertEquals(10, testBall.getRadius());
    }

    @Test
    public void newBallCorrectSpeed() {
        assertEquals(5, testBall.getSpeed());
    }

    @Test
    public void rightPositionY() {
        int y = (int) testBall.getPosition().getY();
        assertEquals(20, y);
    }

    @Test
    public void rightPositionX() {
        int x = (int) testBall.getPosition().getX();
        assertEquals(15, x);
    }

    @Test
    public void getMovementCorrectXMovement() {
        int xMovement = (int) testBall.getMovement().getX();
        assertEquals(0, xMovement);
    }

    @Test
    public void getMovementCorrectYMovement() {
        int yMovement = (int) testBall.getMovement().getY();
        assertEquals(0, yMovement);
    }

    @Test
    public void setMovementCorrectYMovement() {
        testBall.setMovement(new Point2D(5, 10));
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void setMovementCorrectXMovement() {
        testBall.setMovement(new Point2D(5, 10));
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void speedUpCorrectYMovement() {
        testBall.setMovement(new Point2D(10, 100));
        testBall.speedUp(1.5);
        assertEquals(true, Math.abs(150 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void speedUpCorrectXMovement() {
        testBall.setMovement(new Point2D(10, 100));
        testBall.speedUp(1.5);
        assertEquals(true, Math.abs(15 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void moveCorrectX() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 100);
        assertEquals(true, Math.abs(20 - testBall.getxPosition()) <= 0.000001);
    }

    @Test
    public void moveCorrectY() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 100);
        assertEquals(true, Math.abs(30 - testBall.getyPosition()) <= 0.000001);
    }

    @Test
    public void moveCorrectXMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 100);
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void moveCorrectYMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 100);
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void moveCorrectXMovementWhenOutOfBounds() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 20);
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void moveCorrectYMovementWhenOutOfBounds() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.move(0, 20);
        assertEquals(true, Math.abs(-10 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void moveToCorrectY() {
        testBall.moveTo(20, 30);
        assertEquals(true, Math.abs(30 - testBall.getyPosition()) <= 0.000001);
    }

    @Test
    public void moveToCorrectX() {
        testBall.moveTo(20, 30);
        assertEquals(true, Math.abs(20 - testBall.getxPosition()) <= 0.000001);
    }

    @Test
    public void mirrorYMovementCorrectYMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.mirrorYMovement();
        assertEquals(true, Math.abs(-10 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void mirrorYMovementCorrectXMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.mirrorYMovement();
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void mirrorXMovementCorrectYMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.mirrorXMovement();
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void mirrorXMovementCorrectXMovement() {
        testBall.setMovement(new Point2D(5, 10));
        testBall.mirrorXMovement();
        assertEquals(true, Math.abs(-5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void randomMovementToCorrectYMovement() {
        testBall.randomMovement();
        assertEquals(true, Math.abs(5 - testBall.getYMovement()) <= 0.000001 || Math.abs(-5 - testBall.getYMovement()) <= 0.000001);
    }

    @Test
    public void randomMovementToCorrectXMovement() {
        testBall.randomMovement();
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001 || Math.abs(-5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void inGoalNotIn() {
        assertEquals(0, testBall.inGoal(0, 30));
    }

    @Test
    public void inGoalInRight() {
        assertEquals(1, testBall.inGoal(0, 10));
    }

    @Test
    public void inGoalInLeft() {
        assertEquals(-1, testBall.inGoal(20, 30));
    }

    @Test
    public void getSpriteCorrectX() {
        Shape ballSprite = testBall.getSprite();
        assertEquals(true, Math.abs(15 - ballSprite.getTranslateX()) <= 0.000001);
    }

    @Test
    public void getSpriteCorrectY() {
        Shape ballSprite = testBall.getSprite();
        assertEquals(true, Math.abs(20 - ballSprite.getTranslateY()) <= 0.000001);
    }

    @Test
    public void getSpriteCircleCorrectX() {
        Circle ballSprite = testBall.getSpriteCircle();
        assertEquals(true, Math.abs(15 - ballSprite.getTranslateX()) <= 0.000001);
    }

    @Test
    public void getSpriteCircleCorrectY() {
        Circle ballSprite = testBall.getSpriteCircle();
        assertEquals(true, Math.abs(20 - ballSprite.getTranslateY()) <= 0.000001);
    }

    @Test
    public void noCollisionCorrectXMovement() {
        Bat testbat = new Bat(100, 100, 0);
        testBall.setMovement(new Point2D(5, 10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(5 - testBall.getXMovement()) <= 0.000001);
    }

    @Test
    public void noCollisionCorrectYMovement() {
        Bat testbat = new Bat(100, 100, 0);
        testBall.setMovement(new Point2D(5, 10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }
    
    @Test
    public void collisionCorrectXMovement() {
        Bat testbat = new Bat(15, 20, 0);
        testBall.setMovement(new Point2D(5, 10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(-5 - testBall.getXMovement()) <= 0.000001);
    }
    
    @Test
    public void collisionCorrectYMovementWhenBatMovesSameWay() {
        Bat testbat = new Bat(15, 20, 1);
        testbat.move(1, -10, 100);
        testBall.setMovement(new Point2D(5, 10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }
    
    @Test
    public void collisionCorrectYMovementWhenBatMovesOppositeWay1() {
        Bat testbat = new Bat(15, 20, 1);
        testbat.move(-1, -10, 100);
        testBall.setMovement(new Point2D(5, 10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(-10 - testBall.getYMovement()) <= 0.000001);
    }
    
    @Test
    public void collisionCorrectYMovementWhenBatMovesOppositeWay2() {
        Bat testbat = new Bat(15, 20, 1);
        testbat.move(1, -10, 100);
        testBall.setMovement(new Point2D(5, -10));
        testBall.collision(testbat);
        assertEquals(true, Math.abs(10 - testBall.getYMovement()) <= 0.000001);
    }
}
