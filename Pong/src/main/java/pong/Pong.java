
package pong;

import pong.screen.Menu;
import pong.screen.Screen;
import javafx.application.Application;
import javafx.stage.Stage;


public class Pong extends Application{
    public static Stage stage;
    public static Screen screen;
    
    public static void main(String[] args) { 
        launch(Pong.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        setScreen(new Menu());
        stage.setTitle("Pong");
        stage.setHeight(400);
        stage.setWidth(800);
        stage.setResizable(false);
        screen.start();
        stage.show();
    }
    
    
    public static Stage getStage() {
        return stage;
    }
    
    public static void setScreen(Screen newScreen) {
        screen = newScreen;
    }
  
}
