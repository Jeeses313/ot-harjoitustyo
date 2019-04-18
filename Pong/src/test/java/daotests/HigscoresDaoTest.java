package daotests;

import java.util.ArrayList;
import java.util.Collections;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.dao.HighscoresDao;

public class HigscoresDaoTest {

    HighscoresDao dao;

    @Before
    public void setUp() {
        dao = new HighscoresDao("jdbc:h2:./test");
    }

    @Test
    public void initializedDatabaseHasCorrectValues() {
        dao.init();
        ArrayList<Pair<String, Integer>> testList = dao.getScores();
        Collections.sort(testList, (k, l) -> k.getValue() - l.getValue());
        assertEquals(1, Integer.parseInt(testList.get(0).getValue().toString()));
        assertEquals(10, Integer.parseInt(testList.get(1).getValue().toString()));
        assertEquals(25, Integer.parseInt(testList.get(2).getValue().toString()));
        assertEquals(50, Integer.parseInt(testList.get(3).getValue().toString()));
        assertEquals(100, Integer.parseInt(testList.get(4).getValue().toString()));
    }

    @Test
    public void initializedDatabaseHasCorrectNames() {
        dao.init();
        ArrayList<Pair<String, Integer>> testList = dao.getScores();
        Collections.sort(testList, (k, l) -> k.getValue() - l.getValue());
        assertEquals("Bad", testList.get(0).getKey());
        assertEquals("Beginner", testList.get(1).getKey());
        assertEquals("Normal", testList.get(2).getKey());
        assertEquals("Good", testList.get(3).getKey());
        assertEquals("Pro", testList.get(4).getKey());
    }

    @Test
    public void insertScoreInsertsScore() {
        dao.init();
        dao.insertScore("test", 42);
        ArrayList<Pair<String, Integer>> testList = dao.getScores();
        boolean isIn = false;
        for (Pair testScore : testList) {
            if (testScore.getKey().equals("test") && Integer.parseInt(testScore.getValue().toString()) == 42) {
                isIn = true;
            }
        }
        assertEquals(true, isIn);
    }
    
    @Test
    public void deleteScoreDeletesScore() {
        dao.init();
        dao.insertScore("test", 42);
        dao.deleteScore("test", 42);
        ArrayList<Pair<String, Integer>> testList = dao.getScores();
        boolean isIn = false;
        for (Pair testScore : testList) {
            if (testScore.getKey().equals("test") && Integer.parseInt(testScore.getValue().toString()) == 42) {
                isIn = true;
            }
        }
        assertEquals(false, isIn);
    }
}
