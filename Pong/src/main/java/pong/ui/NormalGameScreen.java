package pong.ui;

import pong.domain.games.NormalGame;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import pong.Pong;
import pong.domain.actors.Powerup;
import pong.tools.ComponentCreator;

public class NormalGameScreen implements Screen {

    private HashMap<KeyCode, Boolean> pressedButtons;
    
    private Pane components;
    private Scene field;
    private Label pauseText;
    private Label pointsText;
    private NormalGame game;
    private Shape ballSprite;
    private Shape player1Sprite;
    private Shape player2Sprite;
    private Shape powerupSprite;

    public NormalGameScreen(NormalGame game) {
        this.game = game;
        this.pressedButtons = new HashMap<>();
        components = new Pane();
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
        powerupSprite = new Rectangle();
        components.getChildren().addAll(player1Sprite, player2Sprite, ballSprite, pauseText, pointsText, powerupSprite);
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
                    if (goalCheck == 0 || goalCheck == 1) {
                        if (goalCheck == 1) {
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
                game.powerupManagement();
                Powerup powerup = game.getPowerup();
                components.getChildren().remove(powerupSprite);
                if(powerup != null) {
                    powerupSprite = powerup.getSprite();
                    components.getChildren().add(powerupSprite);
                }
                game.moveBall();
                player1Sprite.setTranslateY(game.getPlayer1().getBat().getyPosition());
                player1Sprite.setScaleY(game.getPlayer1().getBat().getSize());
                player2Sprite.setTranslateY(game.getPlayer2().getBat().getyPosition());
                player2Sprite.setScaleY(game.getPlayer2().getBat().getSize());
                ballSprite.setTranslateY(game.getBall().getyPosition());
                ballSprite.setTranslateX(game.getBall().getxPosition());
            }
        }.start();
        Pong.getStage().setScene(field);
    }

}
