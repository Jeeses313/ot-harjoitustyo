package pong.games;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
import pong.Pong;
import pong.actors.Ball;
import pong.player.Ai;
import pong.player.Human;
import pong.player.Player;
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
    private long lastPause;
    private double speedUp;

    public NormalGame(boolean twoPlayers) {
        Configurations config = Pong.getConfig();
        this.twoPlayers = twoPlayers;
        this.endingPoint = config.getEndingPoint();
        this.leftPoints = 0;
        this.rightPoints = 0;
        this.twoPlayers = twoPlayers;

        this.pause = true;
        this.pauseButton = config.getPauseButton();
        this.speedUp = config.getSpeedUp();
        this.lastPause = System.currentTimeMillis();
        player1 = new Human(config.getPlayer1Up(), config.getPlayer1Down(), 10, 160, config.getBatSpeed());
        if (twoPlayers) {
            player2 = new Human(config.getPlayer2Up(), config.getPlayer2Down(), 770, 160, config.getBatSpeed());
        } else {
            player2 = new Ai(780, 160, config.getDifficulty());
        }
        ball = new Ball(10, 400, 200, config.getBallSpeed());
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
            lastPause = System.currentTimeMillis();
            if (pause) {
                if (leftPoints < endingPoint && rightPoints < endingPoint) {
                    pause = false;
                    return 0;
                } else {
                    return 1;
                }

            } else {
                pause = true;
                return 2;
            }
        }
        return 3;

    }

    public void collisionManagement() {
        if (ball.collision(player1.getBat())) {
            ball.speedUp(speedUp);

        } else if (ball.collision(player2.getBat())) {
            ball.speedUp(speedUp);
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

    public Ball getBall() {
        return ball;
    }

}
