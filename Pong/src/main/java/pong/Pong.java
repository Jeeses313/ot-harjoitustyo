package pong;

import pong.ui.Menu;
import pong.ui.Screen;
import javafx.application.Application;
import javafx.stage.Stage;
import pong.dao.HighscoresDao;
import pong.tools.Configurations;

public class Pong extends Application {

    public static Stage stage;
    public static Screen screen;
    public static Configurations config;
    public static HighscoresDao scoreDao;

    public static void main(String[] args) {
        config = new Configurations("config.properties");
        scoreDao = new HighscoresDao("jdbc:h2:./highscores");
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

    public static Configurations getConfig() {
        return config;
    }

    public static HighscoresDao getScoreDao() {
        return scoreDao;
    }

    
    
    
}
