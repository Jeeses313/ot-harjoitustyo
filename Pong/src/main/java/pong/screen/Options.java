package pong.screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import pong.Pong;
import pong.tools.ComponentCreator;
import pong.tools.Configurations;

public class Options implements Screen {

    private Scene options;
    private Configurations configs;

    public Options() {
        this.configs = Pong.getConfig();
        Pane components = new Pane();
        options = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());

        Label ballSpeedLabel = ComponentCreator.createLabel(0, 0, "Ball speed");

        Label ballSpeedUpLabel = ComponentCreator.createLabel(0, 0, "Ball speed up");

        Label powerupsLabel = ComponentCreator.createLabel(0, 0, "Power-up");
        RadioButton powerupButtonOff = ComponentCreator.createRadioButton(0, 0, 0, 0, "Off");
        powerupButtonOff.getStyleClass().remove("radio-button");
        powerupButtonOff.getStyleClass().add("toggle-button");
        RadioButton powerupButtonOn = ComponentCreator.createRadioButton(0, 0, 0, 0, "On");
        powerupButtonOn.getStyleClass().remove("radio-button");
        powerupButtonOn.getStyleClass().add("toggle-button");
        ToggleGroup powerupButtons = new ToggleGroup();
        powerupButtons.getToggles().addAll(powerupButtonOff, powerupButtonOn);
        int powerups = configs.getPowerups();
        if (powerups <= 0) {
            powerupButtonOff.fire();
        } else {
            powerupButtonOn.fire();
        }

        Button resetButton = ComponentCreator.createButton(0, 0, 0, 0, "Reset to default");
        resetButton.setOnAction(e -> {
            configs.resetConfigs();
            Options options = new Options();
            Pong.setScreen(options);
            options.start();
        });

        Button backButton = ComponentCreator.createButton(0, 0, 0, 0, "Back");
        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            Pong.setScreen(menu);
            menu.start();
        });

        Label difficultyLabel = ComponentCreator.createLabel(0, 0, "Ai difficulty");
        RadioButton easyDifficulty = ComponentCreator.createRadioButton(0, 0, 0, 0, "Easy");
        easyDifficulty.getStyleClass().remove("radio-button");
        easyDifficulty.getStyleClass().add("toggle-button");
        RadioButton normalDifficulty = ComponentCreator.createRadioButton(0, 0, 0, 0, "Normal");
        normalDifficulty.getStyleClass().remove("radio-button");
        normalDifficulty.getStyleClass().add("toggle-button");
        RadioButton hardDifficulty = ComponentCreator.createRadioButton(0, 0, 0, 0, "Hard");
        hardDifficulty.getStyleClass().remove("radio-button");
        hardDifficulty.getStyleClass().add("toggle-button");
        ToggleGroup difficultyButtons = new ToggleGroup();
        difficultyButtons.getToggles().addAll(easyDifficulty, normalDifficulty, hardDifficulty);
        int difficulty = configs.getDifficulty();
        if (difficulty <= 0) {
            easyDifficulty.fire();
        } else if (difficulty == 1) {
            normalDifficulty.fire();
        } else {
            hardDifficulty.fire();
        }

        Label pauseLabel = ComponentCreator.createLabel(0, 0, "Pause button");
        Button pauseButton = ComponentCreator.createButton(0, 0, 0, 0, configs.getPauseButton().toString());
        pauseButton.setOnAction(e -> {
            pauseButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setPauseButton(key.getCode());
                pauseButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        Label player1upLabel = ComponentCreator.createLabel(0, 0, "Player1 up button");
        Button player1upButton = ComponentCreator.createButton(0, 0, 0, 0, configs.getPlayer1Up().toString());
        player1upButton.setOnAction(e -> {
            player1upButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setPlayer1Up(key.getCode());
                player1upButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        Label player1downLabel = ComponentCreator.createLabel(0, 0, "Player1 down button");
        Button player1downButton = ComponentCreator.createButton(0, 0, 0, 0, configs.getPlayer1Down().toString());
        player1downButton.setOnAction(e -> {
            player1downButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setPlayer1Down(key.getCode());
                player1downButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        Label player2upLabel = ComponentCreator.createLabel(0, 0, "Player2 up button");
        Button player2upButton = ComponentCreator.createButton(0, 0, 0, 0, configs.getPlayer2Up().toString());
        player2upButton.setOnAction(e -> {
            player2upButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setPlayer2Up(key.getCode());
                player2upButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        Label player2downLabel = ComponentCreator.createLabel(0, 0, "Player2 up button");
        Button player2downButton = ComponentCreator.createButton(0, 0, 0, 0, configs.getPlayer2Down().toString());
        player2downButton.setOnAction(e -> {
            player2downButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setPlayer2Down(key.getCode());
                player2downButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        components.getChildren().addAll(backButton, easyDifficulty, normalDifficulty, hardDifficulty, difficultyLabel, resetButton, pauseButton,pauseLabel,player1downLabel,player1downButton,player1upLabel,player1upButton, player2downLabel,player2downButton,player2upLabel,player2upButton);
    }

    @Override
    public void start() {
        Pong.getStage().setScene(options);
    }

}
