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
        
        Label batSpeedLabel = ComponentCreator.createLabel(0, 0, "Bat speed");
        RadioButton slowBatSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Slow");
        slowBatSpeedButton.getStyleClass().remove("radio-button");
        slowBatSpeedButton.getStyleClass().add("toggle-button");
        RadioButton normalBatSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Normal");
        normalBatSpeedButton.getStyleClass().remove("radio-button");
        normalBatSpeedButton.getStyleClass().add("toggle-button");
        RadioButton fastBatSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Fast");
        fastBatSpeedButton.getStyleClass().remove("radio-button");
        fastBatSpeedButton.getStyleClass().add("toggle-button");
        ToggleGroup batSpeedButtons = new ToggleGroup();
        batSpeedButtons.getToggles().addAll(slowBatSpeedButton, normalBatSpeedButton, fastBatSpeedButton);
        int batSpeed = configs.getBatSpeed();
        if (batSpeed < 4) {
            slowBatSpeedButton.fire();
        } else if (batSpeed == 4) {
            normalBatSpeedButton.fire();
        } else {
            fastBatSpeedButton.fire();
        }
        components.getChildren().addAll(batSpeedLabel, slowBatSpeedButton, normalBatSpeedButton, fastBatSpeedButton);
        
        Label ballSpeedLabel = ComponentCreator.createLabel(0, 0, "Ball speed");
        RadioButton slowSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Slow");
        slowSpeedButton.getStyleClass().remove("radio-button");
        slowSpeedButton.getStyleClass().add("toggle-button");
        RadioButton normalSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Normal");
        normalSpeedButton.getStyleClass().remove("radio-button");
        normalSpeedButton.getStyleClass().add("toggle-button");
        RadioButton fastSpeedButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Fast");
        fastSpeedButton.getStyleClass().remove("radio-button");
        fastSpeedButton.getStyleClass().add("toggle-button");
        ToggleGroup ballSpeedButtons = new ToggleGroup();
        ballSpeedButtons.getToggles().addAll(slowSpeedButton, normalSpeedButton, fastSpeedButton);
        int ballSpeed = configs.getBallSpeed();
        if (ballSpeed < 8) {
            slowSpeedButton.fire();
        } else if (ballSpeed == 8) {
            normalSpeedButton.fire();
        } else {
            fastSpeedButton.fire();
        }
        components.getChildren().addAll(ballSpeedLabel, slowSpeedButton, normalSpeedButton, fastSpeedButton);

        Label ballSpeedUpLabel = ComponentCreator.createLabel(0, 0, "Ball speed up");
        RadioButton speedUpOffButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "None");
        speedUpOffButton.getStyleClass().remove("radio-button");
        speedUpOffButton.getStyleClass().add("toggle-button");
        RadioButton normalSpeedUpButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Normal");
        normalSpeedUpButton.getStyleClass().remove("radio-button");
        normalSpeedUpButton.getStyleClass().add("toggle-button");
        RadioButton bigSpeedUpButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Big");
        bigSpeedUpButton.getStyleClass().remove("radio-button");
        bigSpeedUpButton.getStyleClass().add("toggle-button");
        ToggleGroup ballSpeedUpButtons = new ToggleGroup();
        ballSpeedUpButtons.getToggles().addAll(speedUpOffButton, normalSpeedUpButton, bigSpeedUpButton);
        double ballSpeedUp = configs.getSpeedUp();
        if (ballSpeedUp < 1) {
            speedUpOffButton.fire();
        } else if (ballSpeedUp == 1) {
            normalSpeedUpButton.fire();
        } else {
            bigSpeedUpButton.fire();
        }
        components.getChildren().addAll(ballSpeedUpLabel, speedUpOffButton, normalSpeedUpButton, bigSpeedUpButton);

        Label endingpointLabel = ComponentCreator.createLabel(0, 0, "Ending point");
        RadioButton onePointButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "1");
        onePointButton.getStyleClass().remove("radio-button");
        onePointButton.getStyleClass().add("toggle-button");
        RadioButton threePointButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "3");
        threePointButton.getStyleClass().remove("radio-button");
        threePointButton.getStyleClass().add("toggle-button");
        RadioButton fivePointButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "5");
        fivePointButton.getStyleClass().remove("radio-button");
        fivePointButton.getStyleClass().add("toggle-button");
        ToggleGroup endingpointButtons = new ToggleGroup();
        endingpointButtons.getToggles().addAll(onePointButton, threePointButton, fivePointButton);
        int endingpoint = configs.getEndingPoint();
        if (endingpoint < 3) {
            onePointButton.fire();
        } else if (endingpoint == 3) {
            threePointButton.fire();
        } else {
            fivePointButton.fire();
        }
        components.getChildren().addAll(endingpointLabel,onePointButton,threePointButton,fivePointButton);

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
        components.getChildren().add(resetButton);

        Button backButton = ComponentCreator.createButton(0, 0, 0, 0, "Back");
        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            Pong.setScreen(menu);
            menu.start();
        });
        components.getChildren().add(backButton);

        Label difficultyLabel = ComponentCreator.createLabel(0, 0, "Ai difficulty");
        RadioButton easyDifficultyButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Easy");
        easyDifficultyButton.getStyleClass().remove("radio-button");
        easyDifficultyButton.getStyleClass().add("toggle-button");
        RadioButton normalDifficultyButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Normal");
        normalDifficultyButton.getStyleClass().remove("radio-button");
        normalDifficultyButton.getStyleClass().add("toggle-button");
        RadioButton hardDifficultyButton = ComponentCreator.createRadioButton(0, 0, 0, 0, "Hard");
        hardDifficultyButton.getStyleClass().remove("radio-button");
        hardDifficultyButton.getStyleClass().add("toggle-button");
        ToggleGroup difficultyButtons = new ToggleGroup();
        difficultyButtons.getToggles().addAll(easyDifficultyButton, normalDifficultyButton, hardDifficultyButton);
        int difficulty = configs.getDifficulty();
        if (difficulty <= 0) {
            easyDifficultyButton.fire();
        } else if (difficulty == 1) {
            normalDifficultyButton.fire();
        } else {
            hardDifficultyButton.fire();
        }
        components.getChildren().addAll(difficultyLabel,easyDifficultyButton,normalDifficultyButton,hardDifficultyButton);

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
        components.getChildren().addAll(pauseLabel,pauseButton);

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
        components.getChildren().addAll(player1upLabel,player1upButton);

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
        components.getChildren().addAll(player1downLabel,player1downButton);

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
        components.getChildren().addAll(player2upLabel,player2upButton);

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
        components.getChildren().addAll(player2downLabel,player2downButton);

    }

    @Override
    public void start() {
        Pong.getStage().setScene(options);
    }

}
