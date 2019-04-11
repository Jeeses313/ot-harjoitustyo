
package pong.ui;

import pong.games.NormalGame;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pong.Pong;
import pong.games.RallyGame;
import pong.tools.ComponentCreator;


public class Menu implements Screen{
    private Scene menu; 

    public Menu() {
        Pane components = new Pane();
        menu = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        Label title = ComponentCreator.createLabel(320, 20, 70, "Pong");
        
        
        Button singleplayerButton = ComponentCreator.createButton(350, 160, 100, 40, "1 Player");
        singleplayerButton.setOnAction(e -> {
            NormalGameScreen game = new NormalGameScreen(new NormalGame(false));
            Pong.setScreen(game);
            game.start();
        });
        
        Button twoplayerButton = ComponentCreator.createButton(350, 200, 100, 40, "2 Player");
        twoplayerButton.setOnAction(e -> {
            NormalGameScreen game = new NormalGameScreen(new NormalGame(true));
            Pong.setScreen(game);
            game.start();
        });
        
        Button rallyButton = ComponentCreator.createButton(350, 240, 100, 40, "Rally game");
        rallyButton.setOnAction(e -> {
            RallyGameScreen game = new RallyGameScreen(new RallyGame());
            Pong.setScreen(game);
            game.start();
        });
        
        Button optionsButton = ComponentCreator.createButton(350, 280, 100, 40, "Options");
        optionsButton.setOnAction(e -> {
            Options options = new Options();
            Pong.setScreen(options);
            options.start();
        });
        
        Button exitButton = ComponentCreator.createButton(350, 320, 100, 40, "Exit");
        exitButton.setOnAction(e -> {
            Pong.getStage().close();
        });
        
        components.getChildren().addAll(title, singleplayerButton, twoplayerButton, rallyButton, optionsButton, exitButton);
    }
    
    
    
    @Override
    public void start() {
        Pong.getStage().setScene(menu);
    }

  
    
}
