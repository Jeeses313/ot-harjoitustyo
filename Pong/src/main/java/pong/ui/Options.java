package pong.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pong.Pong;
import pong.tools.ComponentCreator;
import pong.tools.Configurations;

/**
 * Luokka sisältää pelin asetusruudun käyttöliittymän ja sen toiminnan
 *
 * @see pong.ui.Screen
 */
public class Options implements Screen {

    private Scene options;
    private Configurations configs;

    /**
     * Luokan konstruktori, joka alustaa näytettävän Scene-olion
     * ComponentCreator-luokan avulla ja hakee asetuksia käsittelevän
     * Configurations-olion ohjelman pääluokalta Pong<br>
     * Scenen sisältö riippuu Configurations-olion tiedoista ja mahdollistaa
     * tietojen muuttamisen
     *
     * @see pong.tools.ComponentCreator
     * @see pong.Pong
     * @see pong.tools.Configurations
     */
    public Options() {
        this.configs = Pong.getConfig();
        Pane components = new Pane();
        options = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());

        Label gameSettingsLabel = ComponentCreator.createLabel(470, 0, 30, "Game settings");
        Label controlSettingsLabel = ComponentCreator.createLabel(70, 0, 30, "Control settings");
        Label colourSettingsLabel = ComponentCreator.createLabel(70, 230, 30, "Colour settings");
        Label warningLabel = ComponentCreator.createLabel(420, 240, 15, "Warning! Ai is balanced for normal speeds and \nmight not work as intented on lower or higher speeds.\nIf you want to configure specific values, edit \nconfig.properties file.");
        components.getChildren().addAll(gameSettingsLabel, controlSettingsLabel, warningLabel, colourSettingsLabel);

        Label batSpeedLabel = ComponentCreator.createLabel(400, 50, 20, "Bat speed");
        RadioButton slowBatSpeedButton = ComponentCreator.createRadioButton(530, 50, 60, 5, "Slow");
        RadioButton normalBatSpeedButton = ComponentCreator.createRadioButton(590, 50, 80, 5, "Normal");
        RadioButton fastBatSpeedButton = ComponentCreator.createRadioButton(670, 50, 60, 5, "Fast");
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
        RadioButton normalSpeedButton = ComponentCreator.createRadioButton(590, 80, 80, 5, "Normal");
        RadioButton fastSpeedButton = ComponentCreator.createRadioButton(670, 80, 60, 5, "Fast");
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
        RadioButton normalSpeedUpButton = ComponentCreator.createRadioButton(590, 110, 80, 5, "Normal");
        RadioButton bigSpeedUpButton = ComponentCreator.createRadioButton(670, 110, 60, 5, "Big");
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
        RadioButton threePointButton = ComponentCreator.createRadioButton(590, 140, 80, 5, "3");
        RadioButton fivePointButton = ComponentCreator.createRadioButton(670, 140, 60, 5, "5");
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
        RadioButton powerupButtonOn = ComponentCreator.createRadioButton(630, 170, 100, 5, "On");
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
        RadioButton normalDifficultyButton = ComponentCreator.createRadioButton(590, 200, 80, 5, "Normal");
        RadioButton hardDifficultyButton = ComponentCreator.createRadioButton(670, 200, 60, 5, "Hard");
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

        Label player1ColourLabel = ComponentCreator.createLabel(10, 270, 20, "Player1");
        ColorPicker player1ColorPicker = ComponentCreator.createColourPicker(120, 275, 30, 30, configs.getColor("Player1_colour"));
        player1ColorPicker.setOnAction(e -> {
            configs.setColor("Player1_colour", player1ColorPicker.getValue());
        });
        components.getChildren().addAll(player1ColourLabel, player1ColorPicker);

        Label player2ColourLabel = ComponentCreator.createLabel(10, 300, 20, "Player2/Ai");
        ColorPicker player2ColorPicker = ComponentCreator.createColourPicker(120, 305, 30, 30, configs.getColor("Player2_colour"));
        player2ColorPicker.setOnAction(e -> {
            configs.setColor("Player2_colour", player2ColorPicker.getValue());
        });
        components.getChildren().addAll(player2ColourLabel, player2ColorPicker);

        Label ballColourLabel = ComponentCreator.createLabel(10, 330, 20, "Ball");
        ColorPicker ballColorPicker = ComponentCreator.createColourPicker(120, 335, 30, 30, configs.getColor("Ball_colour"));
        ballColorPicker.setOnAction(e -> {
            configs.setColor("Ball_colour", ballColorPicker.getValue());
        });
        components.getChildren().addAll(ballColourLabel, ballColorPicker);

    }

    /**
     * Asettaa konstruktorissa alustetun Scene-olion näytettäväksi
     */
    @Override
    public void start() {
        Pong.getStage().setScene(options);
    }

}
