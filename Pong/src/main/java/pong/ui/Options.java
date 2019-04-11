package pong.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
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

        Label gameSettingsLabel = ComponentCreator.createLabel(470, 0, 30, "Game settings");
        Label controlSettingsLabel = ComponentCreator.createLabel(70, 0, 30, "Control settings");
        Label warningLabel = ComponentCreator.createLabel(420, 240, 15, "Warning! Ai is balanced for normal speeds and \nmight not work as intented on lower or higher speeds.\nIf you want to configure specific values, edit \nconfig.properties file.");
        components.getChildren().addAll(gameSettingsLabel, controlSettingsLabel, warningLabel);

        Label batSpeedLabel = ComponentCreator.createLabel(400, 50, 20, "Bat speed");
        RadioButton slowBatSpeedButton = ComponentCreator.createRadioButton(530, 50, 60, 5, "Slow");
        slowBatSpeedButton.getStyleClass().remove("radio-button");
        slowBatSpeedButton.getStyleClass().add("toggle-button");
        RadioButton normalBatSpeedButton = ComponentCreator.createRadioButton(590, 50, 80, 5, "Normal");
        normalBatSpeedButton.getStyleClass().remove("radio-button");
        normalBatSpeedButton.getStyleClass().add("toggle-button");
        RadioButton fastBatSpeedButton = ComponentCreator.createRadioButton(670, 50, 60, 5, "Fast");
        fastBatSpeedButton.getStyleClass().remove("radio-button");
        fastBatSpeedButton.getStyleClass().add("toggle-button");
        ToggleGroup batSpeedButtons = new ToggleGroup();
        batSpeedButtons.getToggles().addAll(slowBatSpeedButton, normalBatSpeedButton, fastBatSpeedButton);
        int batSpeed = configs.getInt("BatSpeed", 4);
        if (batSpeed < 4) {
            slowBatSpeedButton.fire();
        } else if (batSpeed == 4) {
            normalBatSpeedButton.fire();
        } else {
            fastBatSpeedButton.fire();
        }
        slowBatSpeedButton.setOnAction(e -> {
            configs.setInt("BatSpeed", 2);
        });
        normalBatSpeedButton.setOnAction(e -> {
            configs.setInt("BatSpeed", 4);
        });
        fastBatSpeedButton.setOnAction(e -> {
            configs.setInt("BatSpeed", 6);
        });
        components.getChildren().addAll(batSpeedLabel, slowBatSpeedButton, normalBatSpeedButton, fastBatSpeedButton);

        Label ballSpeedLabel = ComponentCreator.createLabel(400, 80, 20, "Ball speed");
        RadioButton slowSpeedButton = ComponentCreator.createRadioButton(530, 80, 60, 5, "Slow");
        slowSpeedButton.getStyleClass().remove("radio-button");
        slowSpeedButton.getStyleClass().add("toggle-button");
        RadioButton normalSpeedButton = ComponentCreator.createRadioButton(590, 80, 80, 5, "Normal");
        normalSpeedButton.getStyleClass().remove("radio-button");
        normalSpeedButton.getStyleClass().add("toggle-button");
        RadioButton fastSpeedButton = ComponentCreator.createRadioButton(670, 80, 60, 5, "Fast");
        fastSpeedButton.getStyleClass().remove("radio-button");
        fastSpeedButton.getStyleClass().add("toggle-button");
        ToggleGroup ballSpeedButtons = new ToggleGroup();
        ballSpeedButtons.getToggles().addAll(slowSpeedButton, normalSpeedButton, fastSpeedButton);
        int ballSpeed = configs.getInt("BallSpeed", 8);
        if (ballSpeed < 8) {
            slowSpeedButton.fire();
        } else if (ballSpeed == 8) {
            normalSpeedButton.fire();
        } else {
            fastSpeedButton.fire();
        }
        slowSpeedButton.setOnAction(e -> {
            configs.setInt("BallSpeed", 6);
        });
        normalSpeedButton.setOnAction(e -> {
            configs.setInt("BallSpeed", 8);
        });
        fastSpeedButton.setOnAction(e -> {
            configs.setInt("BallSpeed", 10);
        });
        components.getChildren().addAll(ballSpeedLabel, slowSpeedButton, normalSpeedButton, fastSpeedButton);

        Label ballSpeedUpLabel = ComponentCreator.createLabel(400, 110, 20, "Ball speed up");
        RadioButton speedUpOffButton = ComponentCreator.createRadioButton(530, 110, 60, 5, "None");
        speedUpOffButton.getStyleClass().remove("radio-button");
        speedUpOffButton.getStyleClass().add("toggle-button");
        RadioButton normalSpeedUpButton = ComponentCreator.createRadioButton(590, 110, 80, 5, "Normal");
        normalSpeedUpButton.getStyleClass().remove("radio-button");
        normalSpeedUpButton.getStyleClass().add("toggle-button");
        RadioButton bigSpeedUpButton = ComponentCreator.createRadioButton(670, 110, 60, 5, "Big");
        bigSpeedUpButton.getStyleClass().remove("radio-button");
        bigSpeedUpButton.getStyleClass().add("toggle-button");
        ToggleGroup ballSpeedUpButtons = new ToggleGroup();
        ballSpeedUpButtons.getToggles().addAll(speedUpOffButton, normalSpeedUpButton, bigSpeedUpButton);
        double ballSpeedUp = configs.getDouble("speedUp", 1);
        if (ballSpeedUp <= 1) {
            speedUpOffButton.fire();
        } else if (ballSpeedUp < 1.1) {
            normalSpeedUpButton.fire();
        } else {
            bigSpeedUpButton.fire();
        }
        speedUpOffButton.setOnAction(e -> {
            configs.setDouble("speedUp", 1);
        });
        normalSpeedUpButton.setOnAction(e -> {
            configs.setDouble("speedUp", 1.01);
        });
        bigSpeedUpButton.setOnAction(e -> {
            configs.setDouble("speedUp", 1.1);
        });
        components.getChildren().addAll(ballSpeedUpLabel, speedUpOffButton, normalSpeedUpButton, bigSpeedUpButton);

        Label endingpointLabel = ComponentCreator.createLabel(400, 140, 20, "Ending point");
        RadioButton onePointButton = ComponentCreator.createRadioButton(530, 140, 60, 5, "1");
        onePointButton.getStyleClass().remove("radio-button");
        onePointButton.getStyleClass().add("toggle-button");
        RadioButton threePointButton = ComponentCreator.createRadioButton(590, 140, 80, 5, "3");
        threePointButton.getStyleClass().remove("radio-button");
        threePointButton.getStyleClass().add("toggle-button");
        RadioButton fivePointButton = ComponentCreator.createRadioButton(670, 140, 60, 5, "5");
        fivePointButton.getStyleClass().remove("radio-button");
        fivePointButton.getStyleClass().add("toggle-button");
        ToggleGroup endingpointButtons = new ToggleGroup();
        endingpointButtons.getToggles().addAll(onePointButton, threePointButton, fivePointButton);
        int endingpoint = configs.getInt("endingpoint", 5);
        if (endingpoint < 3) {
            onePointButton.fire();
        } else if (endingpoint == 3) {
            threePointButton.fire();
        } else {
            fivePointButton.fire();
        }
        onePointButton.setOnAction(e -> {
            configs.setInt("endingpoint", 1);
        });
        threePointButton.setOnAction(e -> {
            configs.setInt("endingpoint", 3);
        });
        fivePointButton.setOnAction(e -> {
            configs.setInt("endingpoint", 5);
        });
        components.getChildren().addAll(endingpointLabel, onePointButton, threePointButton, fivePointButton);

        Label powerupsLabel = ComponentCreator.createLabel(400, 170, 20, "Power-up");
        RadioButton powerupButtonOff = ComponentCreator.createRadioButton(530, 170, 100, 5, "Off");
        powerupButtonOff.getStyleClass().remove("radio-button");
        powerupButtonOff.getStyleClass().add("toggle-button");
        RadioButton powerupButtonOn = ComponentCreator.createRadioButton(630, 170, 100, 5, "On");
        powerupButtonOn.getStyleClass().remove("radio-button");
        powerupButtonOn.getStyleClass().add("toggle-button");
        ToggleGroup powerupButtons = new ToggleGroup();
        powerupButtons.getToggles().addAll(powerupButtonOff, powerupButtonOn);
        int powerups = configs.getInt("powerups", 0);
        if (powerups <= 0) {
            powerupButtonOff.fire();
        } else {
            powerupButtonOn.fire();
        }
        powerupButtonOff.setOnAction(e -> {
            configs.setInt("powerups", 0);
        });
        powerupButtonOn.setOnAction(e -> {
            configs.setInt("powerups", 1);
        });
        components.getChildren().addAll(powerupsLabel, powerupButtonOff, powerupButtonOn);
        powerupButtonOff.setDisable(true);
        powerupButtonOn.setDisable(true);

        Button resetButton = ComponentCreator.createButton(580, 330, 140, 40, "Reset to default");
        resetButton.setOnAction(e -> {
            configs.resetConfigs();
            Options options = new Options();
            Pong.setScreen(options);
            options.start();
        });
        components.getChildren().add(resetButton);

        Button backButton = ComponentCreator.createButton(720, 330, 80, 40, "Back");
        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            Pong.setScreen(menu);
            menu.start();
        });
        components.getChildren().add(backButton);

        Label difficultyLabel = ComponentCreator.createLabel(400, 200, 20, "Ai difficulty");
        RadioButton easyDifficultyButton = ComponentCreator.createRadioButton(530, 200, 60, 5, "Easy");
        easyDifficultyButton.getStyleClass().remove("radio-button");
        easyDifficultyButton.getStyleClass().add("toggle-button");
        RadioButton normalDifficultyButton = ComponentCreator.createRadioButton(590, 200, 80, 5, "Normal");
        normalDifficultyButton.getStyleClass().remove("radio-button");
        normalDifficultyButton.getStyleClass().add("toggle-button");
        RadioButton hardDifficultyButton = ComponentCreator.createRadioButton(670, 200, 60, 5, "Hard");
        hardDifficultyButton.getStyleClass().remove("radio-button");
        hardDifficultyButton.getStyleClass().add("toggle-button");
        ToggleGroup difficultyButtons = new ToggleGroup();
        difficultyButtons.getToggles().addAll(easyDifficultyButton, normalDifficultyButton, hardDifficultyButton);
        int difficulty = configs.getInt("difficulty", 1);
        if (difficulty <= 0) {
            easyDifficultyButton.fire();
        } else if (difficulty == 1) {
            normalDifficultyButton.fire();
        } else {
            hardDifficultyButton.fire();
        }
        easyDifficultyButton.setOnAction(e -> {
            configs.setInt("difficulty", 0);
        });
        normalDifficultyButton.setOnAction(e -> {
            configs.setInt("difficulty", 1);
        });
        hardDifficultyButton.setOnAction(e -> {
            configs.setInt("difficulty", 2);
        });
        components.getChildren().addAll(difficultyLabel, easyDifficultyButton, normalDifficultyButton, hardDifficultyButton);

        Label pauseLabel = ComponentCreator.createLabel(10, 170, 20, "Pause button");
        Button pauseButton = ComponentCreator.createButton(200, 170, 90, 5, configs.getKey("pause", KeyCode.P).toString());
        pauseButton.setOnAction(e -> {
            pauseButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("pause", key.getCode());
                pauseButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });
        components.getChildren().addAll(pauseLabel, pauseButton);

        Label menuLabel = ComponentCreator.createLabel(10, 200, 20, "Menu button");
        Button menuButton = ComponentCreator.createButton(200, 200, 90, 5, configs.getKey("menu", KeyCode.M).toString());
        menuButton.setOnAction(e -> {
            menuButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("menu", key.getCode());
                menuButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });
        components.getChildren().addAll(menuLabel, menuButton);

        Label player1upLabel = ComponentCreator.createLabel(10, 50, 20, "Player1 up button");
        Button player1upButton = ComponentCreator.createButton(200, 50, 90, 5, configs.getKey("Player1_Up", KeyCode.W).toString());
        player1upButton.setOnAction(e -> {
            player1upButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("Player1_Up", key.getCode());
                player1upButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });
        components.getChildren().addAll(player1upLabel, player1upButton);

        Label player1downLabel = ComponentCreator.createLabel(10, 80, 20, "Player1 down button");
        Button player1downButton = ComponentCreator.createButton(200, 80, 90, 5, configs.getKey("Player1_Down", KeyCode.S).toString());
        player1downButton.setOnAction(e -> {
            player1downButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("Player1_Down", key.getCode());
                player1downButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });
        components.getChildren().addAll(player1downLabel, player1downButton);

        Label player2upLabel = ComponentCreator.createLabel(10, 110, 20, "Player2 up button");
        Button player2upButton = ComponentCreator.createButton(200, 110, 90, 5, configs.getKey("Player2_Up", KeyCode.UP).toString());
        player2upButton.setOnAction(e -> {
            player2upButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("Player2_Up", key.getCode());
                player2upButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });
        components.getChildren().addAll(player2upLabel, player2upButton);

        Label player2downLabel = ComponentCreator.createLabel(10, 140, 20, "Player2 up button");
        Button player2downButton = ComponentCreator.createButton(200, 140, 90, 5, configs.getKey("Player2_Down", KeyCode.DOWN).toString());
        player2downButton.setOnAction(e -> {
            player2downButton.setText("");
            this.options.setOnKeyPressed(key -> {
                configs.setKey("Player2_Down", key.getCode());
                player2downButton.setText(key.getCode().toString());
                this.options.setOnKeyPressed(k -> {
                });
            });
        });

        components.getChildren().addAll(player2downLabel, player2downButton);

    }

    @Override
    public void start() {
        Pong.getStage().setScene(options);
    }

}
