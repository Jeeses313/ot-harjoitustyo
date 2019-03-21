
package pong.screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import pong.Pong;


public class Menu implements Screen{
    private Scene menu; 

    public Menu() {
        Pane components = new Pane();
        menu = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());
        Label title = new Label("Pong");
        title.setFont(new Font(70));
        title.setTranslateX(320);
        title.setTranslateY(20);
        
        Button singleplayer = new Button("1 Player");
        singleplayer.setTranslateX(350);
        singleplayer.setTranslateY(180);
        singleplayer.setPrefSize(100, 40);
        singleplayer.setOnAction(e -> {
            
        });
        
        Button twoplayer = new Button("2 Player");
        twoplayer.setTranslateX(350);
        twoplayer.setTranslateY(220);
        twoplayer.setPrefSize(100, 40);
        twoplayer.setOnAction(e -> {
            NormalGame game = new NormalGame(true);
            Pong.setScreen(game);
            game.start();
        });
        
        Button options = new Button("Options");
        options.setTranslateX(350);
        options.setTranslateY(260);
        options.setPrefSize(100, 40);
        options.setOnAction(e -> {
            
        });
        
        Button exit = new Button("Exit");
        exit.setTranslateX(350);
        exit.setTranslateY(300);
        exit.setPrefSize(100, 40);
        exit.setOnAction(e -> {
            Pong.getStage().close();
        });
        
        
        singleplayer.setDisable(true);
        options.setDisable(true);
        
        components.getChildren().addAll(title, singleplayer, twoplayer, options, exit);
    }
    
    
    
    @Override
    public void start() {
        Pong.getStage().setScene(menu);
    }

  
    
}
