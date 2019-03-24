package pong.screen;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import pong.player.Ai;
import pong.actors.Ball;
import pong.player.Human;
import pong.player.Player;
import pong.Pong;
import tools.ComponentCreator;

public class NormalGame implements Screen {

    private int endingPoint;
    private int leftPoints;
    private int rightPoints;
    private Scene field;
    private boolean twoPlayers;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean pause;
    private KeyCode pauseButton;
    private long lastPause;
    private Map<KeyCode, Boolean> pressedButtons;
    private int speedUp;
    private Label pauseText;
    private Label pointsText;

    public NormalGame(boolean twoPlayers) {
        this.endingPoint = 5;
        this.leftPoints = 0;
        this.rightPoints = 0;
        this.twoPlayers = twoPlayers;
        this.pressedButtons = new HashMap<>();
        this.pause = true;
        this.pauseButton = KeyCode.P;
        this.speedUp = 1;
        this.lastPause = System.currentTimeMillis();
        Pane components = new Pane();
        field = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        
        player1 = new Human(KeyCode.W, KeyCode.S, 10, 160, 4);
        if (twoPlayers) {
            player2 = new Human(KeyCode.UP, KeyCode.DOWN, 770, 160, 4);
        } else {
            player2 = new Ai(780, 160, 1);
        }
        
        ball = new Ball(10, 400, 200, 8);
        ball.randomMovement();
        
        pauseText = ComponentCreator.createLabel(230, 100, 40, "Press " + this.pauseButton.toString() + " to start");

        pointsText = ComponentCreator.createLabel(370, 10, leftPoints + " - " + rightPoints);


        field.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });

        field.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        components.getChildren().addAll(player1.getBat().getSprite(), player2.getBat().getSprite(), ball.getSprite(), pauseText, pointsText);
    }

    @Override
    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                player1.getBat().setLastMovement(0);
                player2.getBat().setLastMovement(0);
                if (!pause) {
                    if (pressedButtons.getOrDefault(player1.getDown(), false) ) {
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
                if (pressedButtons.getOrDefault(pauseButton, false)) {
                    if (System.currentTimeMillis() - lastPause >= 100) {
                        if (pause) {
                            if (leftPoints < endingPoint && rightPoints < endingPoint) {
                                pauseText.setTranslateX(120);
                                pauseText.setText("Paused. Press " + pauseButton.toString() + " to unpause");
                                pause = false;
                                pauseText.setVisible(false);
                            } else {
                                Menu menu = new Menu();
                                Pong.setScreen(menu);
                                menu.start();
                                stop();
                            }

                        } else {
                            pause = true;
                            pauseText.setVisible(true);
                        }
                        lastPause = System.currentTimeMillis();
                    }
                }

                if (ball.collision(player1.getBat())) {
                    ball.speedUp(speedUp);

                } else if (ball.collision(player2.getBat())) {
                    ball.speedUp(speedUp);
                }

                int inGoal = ball.inGoal(0, 800);
                if (inGoal == -1 || inGoal == 1) {
                    if (inGoal == -1) {
                        rightPoints++;
                    } else {
                        leftPoints++;
                    }
                    pauseText.setVisible(true);
                    pause = true;
                    if (rightPoints == endingPoint) {
                        if (twoPlayers) {
                            pauseText.setText("Player 2 won. Press " + pauseButton.toString() + " to return to menu");
                        } else {
                            pauseText.setText("You lost. Press " + pauseButton.toString() + " to return to menu");
                        }
                        pauseText.setFont(new Font(30));
                        pauseText.setTranslateX(120);
                    } else if (leftPoints == endingPoint) {
                        pauseText.setText("Player 1 won. Press " + pauseButton.toString() + " to return to menu");
                        pauseText.setFont(new Font(30));
                        pauseText.setTranslateX(120);
                    } else {
                        pauseText.setTranslateX(230);
                        pauseText.setText("Press " + pauseButton.toString() + " to start");
                    }
                    ball.moveTo(400, 200);
                    ball.randomMovement();
                    pointsText.setText(leftPoints + " - " + rightPoints);
                    player1.moveBatTo(160);
                    player2.moveBatTo(160);
                }

                if (!pause) {
                    ball.move(0, 400);
                }
            }
        }.start();
        Pong.getStage().setScene(field);
    }

}
