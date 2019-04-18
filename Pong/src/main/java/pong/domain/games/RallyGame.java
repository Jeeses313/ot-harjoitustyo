package pong.domain.games;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
import pong.Pong;
import pong.domain.actors.Ball;
import pong.domain.actors.Wall;
import pong.domain.player.Human;
import pong.domain.player.Player;
import pong.tools.Configurations;

public class RallyGame {

    private int points;
    private Player player;
    private Wall wall;
    private Ball ball;
    private boolean pause;
    private KeyCode pauseButton;
    private KeyCode menuButton;
    private long lastPause;
    private double speedUp;
    private boolean gameEnd;

    public RallyGame(Configurations config) {
        this.points = 0;
        this.gameEnd = false;
        this.pause = true;
        this.pauseButton = config.getKey("pause", KeyCode.P);
        this.menuButton = config.getKey("menu", KeyCode.M);
        this.speedUp = 1.05;
        this.lastPause = System.currentTimeMillis();
        player = new Human(config.getKey("Player1_Up", KeyCode.W), config.getKey("Player1_Down", KeyCode.S), 10, 160, config.getInt("BatSpeed", 4), config.getColor("Player1_colour"));
        wall = new Wall(790, 0);
        ball = new Ball(10, 400, 200, 6, config.getColor("Ball_colour"));
        ball.randomMovement();
    }

    public void moveBats(HashMap<KeyCode, Boolean> pressedButtons) {
        player.getBat().setLastMovement(0);
        if (!pause) {
            if (pressedButtons.getOrDefault(player.getDown(), false)) {
                player.moveBat(1, 0, 400, ball);
            }
            if (pressedButtons.getOrDefault(player.getUp(), false)) {
                player.moveBat(-1, 0, 400, ball);
            }
        }
    }

    public int pauseManagement(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(pauseButton, false) && System.currentTimeMillis() - lastPause >= 100) {
            lastPause = System.currentTimeMillis();
            if (pause) {
                if (this.gameEnd) {
                    return 1;
                } else {
                    pause = false;
                    return 0;
                }
            } else {
                pause = true;
                return 2;
            }
        }
        if (pause && pressedButtons.getOrDefault(menuButton, false)) {
            return 4;
        }
        return 3;

    }

    public void collisionManagement() {
        ball.collision(player.getBat());
        if (ball.collision(wall)) {
            ball.speedUp(speedUp);
            this.points++;
        }
    }

    public int goalCheck() {
        int action = 4;
        int inGoal = ball.inGoal(0, 800);
        if (inGoal == -1 || inGoal == 1) {
            gameEnd = true;
            pause = true;
            return action;
        }
        return 5;
    }

    public void moveBall() {
        if (!pause) {
            ball.move(0, 400);
        }
    }

    public int getPoints() {
        return points;
    }

    public Player getPlayer1() {
        return player;
    }

    public Wall getWall() {
        return wall;
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

}
