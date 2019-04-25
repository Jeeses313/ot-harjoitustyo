package pong.ui;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import pong.Pong;
import pong.dao.HighscoresDao;
import pong.tools.ComponentCreator;

/**
 * Luokka sisältää pelin pistetilastoruudun käyttöliittymän ja sen toiminnan
 *
 * @see pong.ui.Screen
 */
public class HighscoreScreen implements Screen {

    private Scene scene;
    private HighscoresDao scoreDao;
    private int currentScore;
    private ArrayList<Pair<String, Integer>> scores;

    /**
     * Luokan konstruktori, joka alustaa näytettävän Scene-olion
     * ComponentCreator-luokan avulla<br>
     * Scenen sisältö riippuu pisteistä, jotka sille kontruktorissa annetaan<br>
     * Jos pisteitä on enemmän kuin pistetilastoissa olevat pisteet, annetaan
     * mahdollisuus uusien pistetietojen tallettamiselle<br>
     * Koska pisteet eivät voi olla negatiivisia, -1 annetaan parametrina, kun
     * halutaan vain näyttää pisteet
     *
     * @param currentScore Pisteet, joilla ruutuun ollaan siirtymässä
     *
     * @see pong.tools.ComponentCreator
     * @see pong.dao.HighscoresDao
     */
    public HighscoreScreen(int currentScore) {
        Pane components = new Pane();
        scoreDao = Pong.getScoreDao();
        scene = new Scene(components, Pong.getStage().getWidth(), Pong.getStage().getHeight());

        Label titleLabel = ComponentCreator.createLabel(280, 10, 50, "Highscores");
        components.getChildren().add(titleLabel);

        this.currentScore = currentScore;
        scores = scoreDao.getScores();
        Collections.sort(scores, (k, l) -> l.getValue() - k.getValue());
        int x = 120;
        int y = 110;
        int shownScores = 0;
        boolean currentScoreShown = false;
        for (Pair score : scores) {
            if (!currentScoreShown && currentScore > Integer.parseInt(score.getValue().toString())) {
                TextField nameField = new TextField("Name");
                nameField.setTranslateX(x);
                nameField.setTranslateY(y + 10);
                nameField.setPrefSize(120, 10);
                Label currentScoreLabel = ComponentCreator.createLabel(x + 135, y, 30, currentScore + "");
                Button submitButton = ComponentCreator.createButton(120, 320, 80, 40, "Submit");
                Label invalidNameLabel = ComponentCreator.createLabel(210, 330, 15, "");
                submitButton.setOnAction(e -> {
                    final int nameLength = nameField.getText().length();
                    if (nameLength > 10) {
                        invalidNameLabel.setText("Your name is " + (nameLength - 10) + " characters too long");
                    } else {
                        scoreDao.insertScore(nameField.getText(), currentScore);
                        HighscoreScreen highscore = new HighscoreScreen(-1);
                        Pong.setScreen(highscore);
                        highscore.start();
                    }
                });
                components.getChildren().addAll(nameField, currentScoreLabel, submitButton, invalidNameLabel);
                shownScores++;
                y += 40;
                currentScoreShown = true;
            }
            if (shownScores < 5) {
                Label nameLabel = ComponentCreator.createLabel(x, y, 30, score.getKey() + " ");
                Label scoreLabel = ComponentCreator.createLabel(x + 130, y, 30, " " + score.getValue());
                components.getChildren().addAll(nameLabel, scoreLabel);
            } else if (shownScores > 5) {
                scoreDao.deleteScore(score.getKey().toString(), Integer.parseInt(score.getValue().toString()));
            }
            y += 40;
            shownScores++;
        }

        Button backButton = ComponentCreator.createButton(700, 310, 100, 60, "Back");
        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            Pong.setScreen(menu);
            menu.start();
        });
        components.getChildren().add(backButton);

        Button resetButton = ComponentCreator.createButton(540, 310, 160, 60, "Reset scores");
        resetButton.setOnAction(e -> {
            scoreDao.init();
            HighscoreScreen highscore = new HighscoreScreen(-1);
            Pong.setScreen(highscore);
            highscore.start();
        });
        components.getChildren().add(resetButton);

    }

    /**
     * Asettaa konstruktorissa alustetun Scene-olion näytettäväksi
     */
    @Override
    public void start() {
        Pong.getStage().setScene(scene);
    }
}
