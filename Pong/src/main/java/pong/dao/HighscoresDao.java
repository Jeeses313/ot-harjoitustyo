package pong.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 * Luokka käsittelee tiedostoa, johon pelin pistetiedot on talletettu
 */
public class HighscoresDao {

    private String path;

    /**
     * Luokan kostruktori
     *
     * @param path Pisteet sisältävän tietokannan nimi/polku
     */
    public HighscoresDao(String path) {
        this.path = path;
    }

    /**
     * Palauttaa tiedot pisteistä konstruktoriin annetusta tietokannasta<br>
     * Jos annettua tietokantaa ei ole tai tapahtuu virhe, luodaan uusi
     * tietietokanta ja alustetaan se
     *
     * @return String-Integer Pair-olioita sisältävä ArrayList, johon on
     * talletettu tietokannassa olevat tiedot pisteistä
     *
     * @see pong.dao.HighscoresDao#init()
     * @see pong.dao.HighscoresDao#scoresToList(java.sql.ResultSet)
     */
    public ArrayList<Pair<String, Integer>> getScores() {
        ArrayList<Pair<String, Integer>> scores = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(path, "sa", "");
            PreparedStatement state = connection.prepareStatement("SELECT name, score FROM Scores");
            ResultSet result = state.executeQuery();
            scores = scoresToList(result);
            result.close();
            state.close();
            connection.close();
        } catch (Exception e) {
            this.init();
            scores = this.getScores();
        }
        return scores;
    }

    private ArrayList<Pair<String, Integer>> scoresToList(ResultSet result) throws SQLException {
        ArrayList<Pair<String, Integer>> scores = new ArrayList<>();
        while (result.next()) {
            scores.add(new Pair(result.getString("name"), result.getInt("score")));
        }
        return scores;
    }

    /**
     * Alustaa/palauttaa tietokannan sisällön oletusarvoilla/-arvoihin
     */
    public void init() {
        try (Connection conn = DriverManager.getConnection(path, "sa", "")) {
            conn.prepareStatement("DROP TABLE Scores IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Scores(id serial, name varchar(10), score integer);").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HighscoresDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertScore("Pro", 100);
        insertScore("Good", 50);
        insertScore("Normal", 25);
        insertScore("Beginner", 10);
        insertScore("Bad", 1);
    }

    /**
     * Lisää tietokantaan annetun pistetiedon
     *
     * @param name Pisteet saaneen pelaajan nimi
     * @param score Saatujen pisteiden määrä
     */
    public void insertScore(String name, int score) {
        try (Connection conn = DriverManager.getConnection(path, "sa", "")) {
            PreparedStatement state = conn.prepareStatement("INSERT INTO Scores (name, score) VALUES (?, ?)");
            state.setString(1, name);
            state.setInt(2, score);
            state.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    /**
     * Poistaa tietokannasta annetun pistetiedon<br>
     * Jos samanlaisia pistetietoja on useampia, poistetaan vain yksi
     *
     * @param name Pisteet saaneen pelaajan nimi
     * @param score Saatujen pisteiden määrä
     */
    public void deleteScore(String name, int score) {
        try (Connection conn = DriverManager.getConnection(path, "sa", "")) {
            PreparedStatement state = conn.prepareStatement("DELETE FROM Scores WHERE name = ? AND score = ? LIMIT 1");
            state.setString(1, name);
            state.setInt(2, score);
            state.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
