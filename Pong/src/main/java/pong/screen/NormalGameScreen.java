package pong.screen;

import games.NormalGame;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import pong.Pong;
import tools.ComponentCreator;

public class NormalGameScreen implements Screen {

    private HashMap<KeyCode, Boolean> pressedButtons;

    private Scene field;
    private Label pauseText;
    private Label pointsText;
    private NormalGame game;
    private Shape ballSprite;
    private Shape player1Sprite;
    private Shape player2Sprite;

    public NormalGameScreen(NormalGame game) {
        this.game = game;
        this.pressedButtons = new HashMap<>();
        Pane components = new Pane();
        field = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        pauseText = ComponentCreator.createLabel(230, 100, 40, "Press " + game.getPauseButton().toString() + " to start");
        pointsText = ComponentCreator.createLabel(370, 10, game.getLeftPoints() + " - " + game.getRightPoints());
        field.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        field.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        player1Sprite = game.getPlayer1().getBat().getSprite();
        player2Sprite = game.getPlayer2().getBat().getSprite();
        ballSprite = game.getBall().getSprite();
        components.getChildren().addAll(player1Sprite, player2Sprite, ballSprite, pauseText, pointsText);
    }

    @Override
    public void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.moveBats(pressedButtons);
                int pauseManagement = game.pauseManagement(pressedButtons);
                if (pauseManagement == 0) {
                    pauseText.setTranslateX(120);
                    pauseText.setText("Paused. Press " + game.getPauseButton().toString() + " to unpause");
                    pauseText.setVisible(false);
                } else if (pauseManagement == 1) {
                    Menu menu = new Menu();
                    Pong.setScreen(menu);
                    menu.start();
                    stop();
                } else if (pauseManagement == 2) {
                    pauseText.setVisible(true);
                }
                game.collisionManagement();
                int goalCheck = game.goalCheck();
                if (goalCheck < 5) {
                    pauseText.setVisible(true);
                    if (goalCheck == 0 || goalCheck == 1) {
                        if (goalCheck == 0) {
                            pauseText.setText("Player 2 won. Press " + game.getPauseButton().toString() + " to return to menu");
                        } else if (goalCheck == 1) {
                            pauseText.setText("You lost. Press " + game.getPauseButton().toString() + " to return to menu");
                        }
                        pauseText.setFont(new Font(30));
                        pauseText.setTranslateX(120);
                    } else if (goalCheck == 2) {
                        pauseText.setText("Player 1 won. Press " + game.getPauseButton().toString() + " to return to menu");
                        pauseText.setFont(new Font(30));
                        pauseText.setTranslateX(120);
                    } else if (goalCheck == 3) {
                        pauseText.setTranslateX(230);
                        pauseText.setText("Press " + game.getPauseButton().toString() + " to start");
                    }
                    pointsText.setText(game.getLeftPoints() + " - " + game.getRightPoints());
                }
                game.moveBall();
                player1Sprite.setTranslateY(game.getPlayer1().getBat().getyPosition());
                player2Sprite.setTranslateY(game.getPlayer2().getBat().getyPosition());
                ballSprite.setTranslateY(game.getBall().getyPosition());
                ballSprite.setTranslateX(game.getBall().getxPosition());
            }
        }.start();
        Pong.getStage().setScene(field);
    }

}
