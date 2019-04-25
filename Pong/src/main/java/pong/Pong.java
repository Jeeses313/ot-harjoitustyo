package pong;

import pong.ui.Menu;
import pong.ui.Screen;
import javafx.application.Application;
import javafx.stage.Stage;
import pong.dao.HighscoresDao;
import pong.tools.Configurations;

/**
 * Ohjelman pääluokka
 */
public class Pong extends Application {

    private static Stage stage;
    private static Screen screen;
    private static Configurations config;
    private static HighscoresDao scoreDao;

    /**
     * Ohjelman aloittava metodi<br>
     * Luo asetuksia käsittelevän Configurations olion ja pistetietoja
     * käsittelevän HighScoreDao olion
     *
     * @param args args
     *
     * @see pong.dao.HighscoresDao
     * @see pong.tools.Configurations
     */
    public static void main(String[] args) {
        config = new Configurations("config.properties");
        scoreDao = new HighscoresDao("jdbc:h2:./highscores");
        launch(Pong.class);
    }

    /**
     * Käynnistää käyttöliittymän
     *
     * @param stage Käyttöliittymän perusta
     * @throws Exception Exception
     */
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
