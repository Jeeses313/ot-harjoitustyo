package pong.ui;

import pong.domain.games.NormalGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pong.Pong;
import pong.domain.games.RallyGame;
import pong.tools.ComponentCreator;

/**
 * Luokka sisältää pelin valikkoruudun käyttöliittymän ja sen toiminnan
 *
 * @see pong.ui.Screen
 */
public class Menu implements Screen {

    private Scene menu;

    /**
     * Luokan konstruktori, joka alustaa näytettävän Scene-olion
     * ComponentCreator-luokan avulla
     *
     * @see pong.tools.ComponentCreator
     */
    public Menu() {
        Pane components = new Pane();
        menu = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        Label title = ComponentCreator.createLabel(320, 20, 70, "Pong");

        Button singleplayerButton = ComponentCreator.createButton(300, 160, 100, 40, "1 Player");
        singleplayerButton.setOnAction(e -> {
            NormalGameScreen game = new NormalGameScreen(new NormalGame(false, Pong.getConfig()));
            Pong.setScreen(game);
            game.start();
        });

        Button twoplayerButton = ComponentCreator.createButton(400, 160, 100, 40, "2 Player");
        twoplayerButton.setOnAction(e -> {
            NormalGameScreen game = new NormalGameScreen(new NormalGame(true, Pong.getConfig()));
            Pong.setScreen(game);
            game.start();
        });

        Button rallyButton = ComponentCreator.createButton(300, 200, 100, 40, "Rally game");
        rallyButton.setOnAction(e -> {
            RallyGameScreen game = new RallyGameScreen(new RallyGame(Pong.getConfig()));
            Pong.setScreen(game);
            game.start();
        });

        Button highscoresButton = ComponentCreator.createButton(400, 200, 100, 40, "Highscores");
        highscoresButton.setOnAction(e -> {
            HighscoreScreen scoreScreen = new HighscoreScreen(-1);
            Pong.setScreen(scoreScreen);
            scoreScreen.start();
        });

        Button optionsButton = ComponentCreator.createButton(300, 240, 100, 40, "Options");
        optionsButton.setOnAction(e -> {
            Options options = new Options();
            Pong.setScreen(options);
            options.start();
        });

        Button exitButton = ComponentCreator.createButton(400, 240, 100, 40, "Exit");
        exitButton.setOnAction(e -> {
            Pong.getStage().close();
        });

        components.getChildren().addAll(title, singleplayerButton, twoplayerButton, rallyButton, optionsButton, exitButton, highscoresButton);
    }

    /**
     * Asettaa konstruktorissa alustetun Scene-olion näytettäväksi
     */
    @Override
    public void start() {
        Pong.getStage().setScene(menu);
    }

}
