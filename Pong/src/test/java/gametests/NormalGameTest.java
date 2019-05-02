package gametests;

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pong.domain.actors.Powerup;
import pong.domain.games.NormalGame;
import pong.domain.player.Ai;
import pong.domain.player.Human;
import pong.tools.Configurations;

public class NormalGameTest {

    NormalGame game;
    Configurations config;
    HashMap<KeyCode, Boolean> pressedButtons;

    @Before
    public void setUp() {
        pressedButtons = new HashMap<>();
        config = new Configurations("test.properties");
        game = new NormalGame(true, config);
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
    public void newGamePowerupIsnull() {
        assertEquals(null, game.getPowerup());
    }

    @Test
    public void newGameCorrectPlayerTwo() {
        NormalGame testGame = new NormalGame(false, config);
        assertEquals(Human.class, game.getPlayer2().getClass());
        assertEquals(Ai.class, testGame.getPlayer2().getClass());
    }

    @Test
    public void MoveBatsSetsBatsLastMovement() {
        pressedButtons.put(KeyCode.UP, true);
        pressedButtons.put(KeyCode.S, true);
        game.getPlayer1().getBat().setLastMovement(10);
        game.getPlayer2().getBat().setLastMovement(10);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(0 - game.getPlayer1().getBat().getLastMovement()) <= 0.000001);
        assertEquals(true, Math.abs(0 - game.getPlayer2().getBat().getLastMovement()) <= 0.000001);
    }

    @Test
    public void MoveBatsDoesNotMoveBatsIfPaused() {
        pressedButtons.put(KeyCode.UP, true);
        pressedButtons.put(KeyCode.S, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(160 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
        assertEquals(true, Math.abs(160 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
    }

    @Test
    public void MoveBatsMovesBatsIfUnpaused() {
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        pressedButtons.put(KeyCode.UP, true);
        pressedButtons.put(KeyCode.S, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(164 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
        assertEquals(true, Math.abs(156 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
        pressedButtons.put(KeyCode.UP, false);
        pressedButtons.put(KeyCode.S, false);
        pressedButtons.put(KeyCode.DOWN, true);
        pressedButtons.put(KeyCode.W, true);
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(160 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
        assertEquals(true, Math.abs(160 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
    }

    @Test
    public void MoveBatsMovesAiBatIfUnpaused() {
        game = new NormalGame(false, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        game.getBall().setMovement(new Point2D(-1, -1));
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(157 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
        game.getBall().setMovement(new Point2D(1, 1));
        game.moveBats(pressedButtons);
        assertEquals(true, Math.abs(160 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
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
    public void pauseManagementReturnOneWhenPausedAndMenuButtonPressed() {
        pressedButtons.put(KeyCode.P, true);
        pressedButtons.put(KeyCode.M, true);
        int pause = game.pauseManagement(pressedButtons);
        assertEquals(1, pause);
    }

    @Test
    public void pauseManagementReturnsOneWhenUnpausedAfterGameEnd() {
        for (int i = 0; i < 7; i++) {
            game.getBall().moveTo(-10, 0);
            game.goalCheck();
        }
        game.lastPause = System.currentTimeMillis() - 110;
        pressedButtons.put(KeyCode.P, true);
        pressedButtons.put(KeyCode.M, true);
        int pause = game.pauseManagement(pressedButtons);
        assertEquals(1, pause);
    }

    @Test
    public void goalCheckIncreasesRightPointsWhenBallIsInLeftGoal() {
        for (int i = 0; i < 7; i++) {
            game.getBall().moveTo(-10, 0);
            game.goalCheck();
        }
        assertEquals(7, game.getRightPoints());
        assertEquals(0, game.getLeftPoints());
    }

    @Test
    public void goalCheckIncreasesLeftPointsWhenBallIsInRightGoal() {
        for (int i = 0; i < 7; i++) {
            game.getBall().moveTo(900, 0);
            game.goalCheck();
        }
        assertEquals(0, game.getRightPoints());
        assertEquals(7, game.getLeftPoints());
    }
    
    @Test
    public void goalCheckReturn0WhenPlayer2WinsGame() {
        int goal = 1;
        for (int i = 0; i < 5; i++) {
            game.getBall().moveTo(-10, 0);
            goal = game.goalCheck();
        }
        assertEquals(0, goal);
    }
    
    @Test
    public void goalCheckReturn1WhenAiWinsGame() {
        game = new NormalGame(false, config);
        int goal = 0;
        for (int i = 0; i < 5; i++) {
            game.getBall().moveTo(-10, 0);
            goal = game.goalCheck();
        }
        assertEquals(1, goal);
    }

    @Test
    public void goalCheckReturnTwoWhenPlayer1WinsGame() {
        int goal = 1;
        for (int i = 0; i < 5; i++) {
            game.getBall().moveTo(900, 0);
            goal = game.goalCheck();
        }
        assertEquals(2, goal);
    }

    @Test
    public void goalCheckReturnFiveAndDoesNotIncreasePointsWhenBallNotInGoal() {
        game.getBall().moveTo(400, 0);
        int goal = game.goalCheck();
        assertEquals(0, game.getRightPoints());
        assertEquals(0, game.getLeftPoints());
        assertEquals(5, goal);
    }

    @Test
    public void resetFieldResetsField() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        game.getPlayer1().getBat().moveTo(0);
        game.getPlayer2().getBat().moveTo(0);
        game.getBall().moveTo(0, 0);
        game.powerupManagement();
        game.resetField();
        assertEquals(true, Math.abs(160 - game.getPlayer1().getBat().getyPosition()) <= 0.000001);
        assertEquals(true, Math.abs(160 - game.getPlayer2().getBat().getyPosition()) <= 0.000001);
        assertEquals(true, Math.abs(400 - game.getBall().getxPosition()) <= 0.000001);
        assertEquals(true, Math.abs(200 - game.getBall().getyPosition()) <= 0.000001);
        assertEquals(null, game.getPowerup());
    }

    @Test
    public void powerupManagementSpawnsPowerupWhenSettingIsOnAndThereAreNone() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        assertEquals(Powerup.class, game.getPowerup().getClass());
    }

    @Test
    public void powerupManagementDoesNotSpawnPowerupWhenSettingIsOff() {
        config.setInt("powerups", 0);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        for (int i = 0; i < 10000; i++) {
            game.powerupManagement();
        }
        assertEquals(null, game.getPowerup());
    }

    @Test
    public void pauseManagementChangesSpawnedPowerupsSpawnTime() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        long lastPauseStart = System.currentTimeMillis() - 110;
        long powerupRealSpawnTime = game.getPowerup().spawnTime;
        game.lastPause = lastPauseStart;
        game.pauseManagement(pressedButtons);
        long lastPauseEnd = System.currentTimeMillis() - 110;
        game.lastPause = lastPauseEnd;
        game.pauseManagement(pressedButtons);
        long powerupNewSpawnTime = game.getPowerup().spawnTime;
        assertEquals(true, Math.abs((powerupNewSpawnTime - powerupNewSpawnTime) - (lastPauseEnd - lastPauseStart)) <= 0.000001);
    }

    @Test
    public void pauseManagementDoesNotChangeSpawnedPowerupsActivationTimeWhenNotActivated() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        long lastPauseStart = System.currentTimeMillis() - 110;
        game.lastPause = lastPauseStart;
        game.pauseManagement(pressedButtons);
        long lastPauseEnd = System.currentTimeMillis() - 110;
        game.lastPause = lastPauseEnd;
        game.pauseManagement(pressedButtons);
        assertEquals(-1, game.getPowerup().activationTime);
    }

    @Test
    public void pauseManagementChangesSpawnedPowerupsActivationTimeWhenActivated() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        game.getPowerup().activate(game.getPlayer1(), game.getPlayer2(), game.getBall());
        long activationTimeReal = game.getPowerup().activationTime;
        long lastPauseStart = System.currentTimeMillis() - 110;
        game.lastPause = lastPauseStart;
        game.pauseManagement(pressedButtons);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        long lastPauseEnd = System.currentTimeMillis();
        long activationTimeNew = game.getPowerup().activationTime;
        assertEquals(true, Math.abs((activationTimeNew - activationTimeReal) - (lastPauseEnd - lastPauseStart)) <= 0.000001);
    }

    @Test
    public void collisionManagementSpeedUpBallWhenCollisionWithPlayer1Bat() {
        config.setDouble("speedUp", 10);
        game = new NormalGame(true, config);
        game.getBall().setMovement(new Point2D(-10, -10));
        game.getBall().moveTo(10, 160);
        game.collisionManagement();
        assertEquals(true, Math.abs(100 - game.getBall().getXMovement()) <= 0.000001);
        assertEquals(true, Math.abs(-100 - game.getBall().getYMovement()) <= 0.000001);
    }

    @Test
    public void collisionManagementSpeedUpBallWhenCollisionWithPlayer2Bat() {
        config.setDouble("speedUp", 10);
        game = new NormalGame(true, config);
        game.getBall().setMovement(new Point2D(10, 10));
        game.getBall().moveTo(770, 160);
        game.collisionManagement();
        assertEquals(true, Math.abs(-100 - game.getBall().getXMovement()) <= 0.000001);
        assertEquals(true, Math.abs(100 - game.getBall().getYMovement()) <= 0.000001);
    }

    @Test
    public void collisionManagementActivatesPowerupWhenCollision() {
        config.setInt("powerups", 1);
        game = new NormalGame(true, config);
        pressedButtons.put(KeyCode.P, true);
        game.lastPause = System.currentTimeMillis() - 110;
        game.pauseManagement(pressedButtons);
        while (game.getPowerup() == null) {
            game.powerupManagement();
        }
        Shape powerup = game.getPowerup().getSprite();
        game.getBall().moveTo(powerup.getTranslateX(), powerup.getTranslateY());
        game.collisionManagement();
        assertEquals(true, -1 != game.getPowerup().activationTime);
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
        assertEquals(true, Math.abs((400 + 5*10) - game.getBall().getxPosition()) <= 0.000001);
        assertEquals(true, Math.abs((200 - 5*10) - game.getBall().getyPosition()) <= 0.000001);       
    }

}
