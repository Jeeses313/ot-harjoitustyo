
package pong.screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pong.Pong;

public class Options implements Screen{
    private Scene options;
    public Options() {
        Pane components = new Pane();
        options = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        
        Label ballSpeedLabel = new Label("Ball speed");
        
        Label ballSpeedUpLabel = new Label("Ball speed up");
        
        Label powerupsLabel = new Label("Power-up");
        
        Button powerupButton = new Button("Off");
        
        Button resetButton = new Button("Reset to default");
        
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            Pong.setScreen(menu);
            menu.start();
        });
        
        components.getChildren().addAll(backButton);
    }

    @Override
    public void start() {
        Pong.getStage().setScene(options);
    }
    
}
