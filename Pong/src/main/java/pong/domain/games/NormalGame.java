package pong.domain.games;

import java.util.HashMap;
import java.util.Random;
import javafx.scene.input.KeyCode;
import pong.domain.actors.Ball;
import pong.domain.actors.Powerup;
import pong.domain.player.Ai;
import pong.domain.player.Human;
import pong.domain.player.Player;
import pong.tools.Configurations;

public class NormalGame {

    private int endingPoint;
    private int leftPoints;
    private int rightPoints;
    private boolean twoPlayers;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean pause;
    private KeyCode pauseButton;
    private KeyCode menuButton;
    private long lastPause;
    private double speedUp;
    private int powerups;
    private Powerup powerup;

    public NormalGame(boolean twoPlayers, Configurations config) {
        this.twoPlayers = twoPlayers;
        this.endingPoint = config.getInt("endingpoint", 5);
        this.leftPoints = 0;
        this.rightPoints = 0;
        this.twoPlayers = twoPlayers;
        this.powerups = config.getInt("powerups", 0);
        this.powerup = null;
        this.pause = true;
        this.pauseButton = config.getKey("pause", KeyCode.P);
        this.menuButton = config.getKey("menu", KeyCode.M);
        this.speedUp = config.getDouble("speedUp", 1);
        this.lastPause = System.currentTimeMillis();
        player1 = new Human(config.getKey("Player1_Up", KeyCode.W), config.getKey("Player1_Down", KeyCode.S), 10, 160, config.getInt("BatSpeed", 4), config.getColor("Player1_colour"));
        if (twoPlayers) {
            player2 = new Human(config.getKey("Player2_Up", KeyCode.UP), config.getKey("Player2_Down", KeyCode.DOWN), 770, 160, config.getInt("BatSpeed", 4), config.getColor("Player2_colour"));
        } else {
            player2 = new Ai(770, 160, config.getInt("difficulty", 1), config.getColor("Player2_colour"));
        }
        ball = new Ball(10, 400, 200, config.getInt("BallSpeed", 8), config.getColor("Ball_colour"));
        ball.randomMovement();
    }

    public void moveBats(HashMap<KeyCode, Boolean> pressedButtons) {
        player1.getBat().setLastMovement(0);
        player2.getBat().setLastMovement(0);
        if (!pause) {
            this.moveBat1(pressedButtons);
            this.moveBat2(pressedButtons);
        }
    }

    public void moveBat1(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(player1.getDown(), false)) {
            player1.moveBat(1, 0, 400, ball);
        }
        if (pressedButtons.getOrDefault(player1.getUp(), false)) {
            player1.moveBat(-1, 0, 400, ball);
        }
    }

    public void moveBat2(HashMap<KeyCode, Boolean> pressedButtons) {
        if (twoPlayers) {
            if (pressedButtons.getOrDefault(player2.getDown(), false)) {
                player2.moveBat(1, 0, 400, ball);
            }
            if (pressedButtons.getOrDefault(player2.getUp(), false)) {
                player2.moveBat(-1, 0, 400, ball);
            }
        } else {
            player2.moveBat(0, 0, 400, ball);
        }
    }

    public int pauseManagement(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(pauseButton, false) && System.currentTimeMillis() - lastPause >= 100) {
            powerupPauseTimeManagement();
            lastPause = System.currentTimeMillis();
            if (pause) {
                return unpause();
            } else {
                return pause();
            }
        }
        if (pause && pressedButtons.getOrDefault(menuButton, false)) {
            return 1;
        }
        return 3;

    }

    private int unpause() {
        if (leftPoints < endingPoint && rightPoints < endingPoint) {
            pause = false;
            return 0;
        } else {
            return 1;
        }
    }

    private int pause() {
        pause = true;
        return 2;
    }

    private void powerupPauseTimeManagement() {
        if (pause && this.powerup != null) {
            if (powerup.activationTime != -1) {
                this.powerup.activationTime += System.currentTimeMillis() - lastPause;
            }
            this.powerup.spawnTime += System.currentTimeMillis() - lastPause;
        }
    }

    public void collisionManagement() {
        if (ball.collision(player1.getBat())) {
            ball.speedUp(speedUp);

        } else if (ball.collision(player2.getBat())) {
            ball.speedUp(speedUp);
        }
        if (powerup != null) {
            if (powerup.collision(ball)) {
                powerup.activate(player1, player2, ball);
            }
        }

    }

    public int goalCheck() {
        int action = 4;
        int inGoal = ball.inGoal(0, 800);
        if (inGoal == -1 || inGoal == 1) {
            if (inGoal == -1) {
                rightPoints++;
            } else {
                leftPoints++;
            }
            pause = true;
            action = checkPoints();

            this.resetField();
            return action;
        }
        return 5;
    }

    public int checkPoints() {
        if (rightPoints == endingPoint) {
            if (twoPlayers) {
                return 0;
            } else {
                return 1;
            }
        } else if (leftPoints == endingPoint) {
            return 2;
        } else {
            return 3;
        }
    }

    public void resetField() {
        ball.moveTo(400, 200);
        ball.randomMovement();
        player1.moveBatTo(160);
        player2.moveBatTo(160);
        if (this.powerup != null) {
            this.powerup.deactivate(player1, player2, ball);
            this.powerup = null;
        }
    }

    public void powerupManagement() {
        if (this.powerups < 1 || pause) {
            return;
        }
        if (powerup == null) {
            if (new Random(System.currentTimeMillis()).nextInt(10000) < 100) {
                spawnPowerup();
            }
        } else {
            powerup = powerup.despawn(player1, player2, ball);
        }
    }

    public void spawnPowerup() {
        Random rng = new Random(System.currentTimeMillis());
        powerup = new Powerup(rng.nextInt(720) + 40, rng.nextInt(340) + 20, rng.nextInt(6));
    }

    public void moveBall() {
        if (!pause) {
            ball.move(0, 400);
        }
    }

    public int getLeftPoints() {
        return leftPoints;
    }

    public int getRightPoints() {
        return rightPoints;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public KeyCode getPauseButton() {
        return pauseButton;
    }

    public KeyCode getMenuButton() {
        return menuButton;
    }

    public Ball getBall() {
        return ball;
    }

    public Powerup getPowerup() {
        return powerup;
    }

}
