package gametests;

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.games.NormalGame;
import pong.domain.games.RallyGame;
import pong.tools.Configurations;

public class RallyGameTest {

    RallyGame game;
    Configurations config;
    HashMap<KeyCode, Boolean> pressedButtons;

    @Before
    public void setUp() {
        pressedButtons = new HashMap<>();
        config = new Configurations("test.properties");
        game = new RallyGame(config);
    }

    @Test
    public void newGameCorrectPauseButton() {
        assertEquals(KeyCode.P, game.getPauseButton());
    }

    @Test
    public void newGameCorrectMenuButton() {
        assertEquals(KeyCode.M, game.getMenuButton());
    }

    @Test
    public void moveBatsSetsBatsLastMovement() {
        pressedButtons.put(KeyCode.S, true);
        game.getPlayer1().getBat().setLastMovement(10);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(0 - game.getPlayer1().getBat().getLastMovement()) <= 0.000001);
    }

    @Test
    public void moveBatsDoesNotMoveBatsIfPaused() {
        pressedButtons.put(KeyCode.S, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(160 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
    }

    @Test
    public void moveBatsMovesBatsIfUnpaused() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        pressedButtons.put(KeyCode.S, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(164 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
        pressedButtons.put(KeyCode.S, false);
        pressedButtons.put(KeyCode.W, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(160 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
    }

    @Test
    public void pauseManagementUnpausesWhenPauseButtonPressedAndEnoughTimePassed() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        assertEquals(false, game.isPaused());
    }

    @Test
    public void pauseManagementPausesWhenPauseButtonPressedAndEnoughTimePassed() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        assertEquals(true, game.isPaused());
    }

    @Test
    public void pauseManagementReturnThreeWhenNothingIsDone() {
        pressedButtons.put(KeyCode.P, true);
        int pause = game.pauseManagement(pressedButtons);
        assertEquals(3, pause);
    }

    @Test
    public void pauseManagementReturnFourWhenPausedAndMenuButtonPressed() {
        pressedButtons.put(KeyCode.P, true);
        pressedButtons.put(KeyCode.M, true);
        int pause = game.pauseManagement(pressedButtons);
        assertEquals(4, pause);
    }

    @Test
    public void pauseManagementReturnsOneWhenUnpausedAfterGameEnd() {
        game.getBall().moveTo(-10, 0);
        game.goalCheck();
        game.lastPause = System.currentTimeMillis() - 110;
        pressedButtons.put(KeyCode.P, true);
        pressedButtons.put(KeyCode.M, true);
        int pause = game.pauseManagement(pressedButtons);
        assertEquals(1, pause);
    }

    @Test
    public void collisionManagementSpeedUpBallWhenCollisionWithWall() {
        game.getBall().setMovement(new Point2D(10, 10));
        game.getBall().moveTo(790, 0);
        game.collisionManagement();
        assertEquals(true, Math.abs(-10 * 1.05 - game.getBall().getXMovement()) <= 0.000001);
        assertEquals(true, Math.abs(10 * 1.05 - game.getBall().getYMovement()) <= 0.000001);
    }

    @Test
    public void collisionManagementIncreasesPointsWhenCollisionWithWall() {
        for (int i = 0; i < 10; i++) {
            game.getBall().setMovement(new Point2D(10, 10));
            game.getBall().moveTo(790, 0);
            game.collisionManagement();
        }
        assertEquals(10, game.getPoints());
    }

    @Test
    public void collisionManagementDoesNotSpeedUpBallWhenCollisionWithPlayer() {
        config.setDouble("speedUp", 10);
        game = new RallyGame(config);
        game.getBall().setMovement(new Point2D(10, 10));
        game.getBall().moveTo(10, 160);
        game.collisionManagement();
        assertEquals(true, Math.abs(-10 - game.getBall().getXMovement()) <= 0.000001);
        assertEquals(true, Math.abs(10 - game.getBall().getYMovement()) <= 0.000001);
    }

    @Test
    public void goalCheckReturnFiveWhenBallOnField() {
        game.getBall().moveTo(400, 0);
        int goal = game.goalCheck();
        assertEquals(5, goal);
    }

    @Test
    public void goalCheckReturnFourWhenBallNotOnField() {
        game.getBall().moveTo(-10, 0);
        int goal = game.goalCheck();
        assertEquals(4, goal);
    }

    @Test
    public void moveBallDoesNotMoveBallWhenPaused() {
        for (int i = 0; i < 100; i++) {
            game.moveBall();
        }
        assertEquals(true, Math.abs(400 - game.getBall().getxPosition()) <= 0.000001);
        assertEquals(true, Math.abs(200 - game.getBall().getyPosition()) <= 0.000001);
    }

    @Test
    public void moveBallMovesBallWhenNotPaused() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        game.getBall().setMovement(new Point2D(10, -10));
        for (int i = 0; i < 5; i++) {
            game.moveBall();
        }
        assertEquals(true, Math.abs((400 + 5 * 10) - game.getBall().getxPosition()) <= 0.000001);
        assertEquals(true, Math.abs((200 - 5 * 10) - game.getBall().getyPosition()) <= 0.000001);
    }
    
    @Test
    public void moveBatsDoesNotMoveWall() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        pressedButtons.put(KeyCode.S, true);
        pressedButtons.put(KeyCode.W, true);
        pressedButtons.put(KeyCode.UP, true);
        pressedButtons.put(KeyCode.DOWN, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(790 - game.getWall().getxPosition()) <= 0.000001);
        assertEquals(true, Math.abs(0 - game.getWall().getyPosition()) <= 0.000001);
    }
}
