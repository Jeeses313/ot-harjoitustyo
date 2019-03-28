
package pong.screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import pong.Pong;
import tools.ComponentCreator;


public class Menu implements Screen{
    private Scene menu; 

    public Menu() {
        Pane components = new Pane();
        menu = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        Label title = ComponentCreator.createLabel(320, 20, 70, "Pong");
        
        Button singleplayerButton = ComponentCreator.createButton(350, 180, 100, 40, "1 Player");
        singleplayerButton.setOnAction(e -> {
            NormalGame game = new NormalGame(false);
            Pong.setScreen(game);
            game.start();
        });
        
        Button twoplayerButton = ComponentCreator.createButton(350, 220, 100, 40, "2 Player");
        twoplayerButton.setOnAction(e -> {
            NormalGame game = new NormalGame(true);
            Pong.setScreen(game);
            game.start();
        });
        
        Button optionsButton = ComponentCreator.createButton(350, 260, 100, 40, "Options");
        optionsButton.setOnAction(e -> {
            Options options = new Options();
            Pong.setScreen(options);
            options.start();
        });
        
        Button exitButton = ComponentCreator.createButton(350, 300, 100, 40, "Exit");
        exitButton.setOnAction(e -> {
            Pong.getStage().close();
        });
        
        optionsButton.setDisable(true);
        components.getChildren().addAll(title, singleplayerButton, twoplayerButton, optionsButton, exitButton);
    }
    
    
    
    @Override
    public void start() {
        Pong.getStage().setScene(menu);
    }

  
    
}