package pong.ui;

import pong.games.NormalGame;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import pong.Pong;
import pong.games.RallyGame;
import pong.tools.ComponentCreator;

public class RallyGameScreen implements Screen {

    private HashMap<KeyCode, Boolean> pressedButtons;

    private Scene field;
    private Label pauseText;
    private Label pointsText;
    private RallyGame game;
    private Shape ballSprite;
    private Shape player1Sprite;
    private Shape wall;

    public RallyGameScreen(RallyGame game) {
        this.game = game;
        this.pressedButtons = new HashMap<>();
        Pane components = new Pane();
        field = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        pauseText = ComponentCreator.createLabel(230, 100, 40, "Press " + game.getPauseButton().toString() + " to start");
        pointsText = ComponentCreator.createLabel(370, 10, game.getPoints() + "");
        field.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        field.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        player1Sprite = game.getPlayer1().getBat().getSprite();
        wall = game.getWall().getSprite();
        ballSprite = game.getBall().getSprite();
        components.getChildren().addAll(player1Sprite, wall, ballSprite, pauseText, pointsText);
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
                    pauseText.setText("Paused. Press " + game.getPauseButton().toString() + " to unpause \nor " + game.getMenuButton().toString() + " to return to menu");
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
                    pauseText.setText("You lost. Press " + game.getPauseButton().toString() + " to return to menu");
                    pauseText.setFont(new Font(30));
                    pauseText.setTranslateX(120);
                }

                pointsText.setText(game.getPoints() + "");
                game.moveBall();
                player1Sprite.setTranslateY(game.getPlayer1().getBat().getyPosition());
                ballSprite.setTranslateY(game.getBall().getyPosition());
                ballSprite.setTranslateX(game.getBall().getxPosition());
            }
        }.start();
        Pong.getStage().setScene(field);
    }

}
