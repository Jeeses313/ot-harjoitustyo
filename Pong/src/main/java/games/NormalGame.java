package games;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.player.Ai;
import pong.player.Human;
import pong.player.Player;

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
    private int speedUp;

    public NormalGame(boolean twoPlayers) {
        this.twoPlayers = twoPlayers;
        this.endingPoint = 5;
        this.leftPoints = 0;
        this.rightPoints = 0;
        this.twoPlayers = twoPlayers;

        this.pause = true;
        this.pauseButton = KeyCode.P;
        this.speedUp = 1;
        this.lastPause = System.currentTimeMillis();

        player1 = new Human(KeyCode.W, KeyCode.S, 10, 160, 4);
        if (twoPlayers) {
            player2 = new Human(KeyCode.UP, KeyCode.DOWN, 770, 160, 4);
        } else {
            player2 = new Ai(780, 160, 1);
        }

        ball = new Ball(10, 400, 200, 8);
        ball.randomMovement();
    }

    public void moveBats(HashMap<KeyCode, Boolean> pressedButtons) {
        player1.getBat().setLastMovement(0);
        player2.getBat().setLastMovement(0);
        if (!pause) {
            if (pressedButtons.getOrDefault(player1.getDown(), false)) {
                player1.moveBat(1, 0, 400, ball);
            }
            if (pressedButtons.getOrDefault(player1.getUp(), false)) {
                player1.moveBat(-1, 0, 400, ball);
            }
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
    }

    public int pauseManagement(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(pauseButton, false)) {
            if (System.currentTimeMillis() - lastPause >= 100) {
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
            if (rightPoints == endingPoint) {
                if (twoPlayers) {
                    action = 0;

                } else {
                    action = 1;
                }

            } else if (leftPoints == endingPoint) {
                action = 2;
            } else {
                action = 3;
            }
            ball.moveTo(400, 200);
            ball.randomMovement();

            player1.moveBatTo(160);
            player2.moveBatTo(160);
            return action;
        }
        return 5;
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
