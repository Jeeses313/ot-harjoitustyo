package pong.domain.games;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
import pong.Pong;
import pong.domain.actors.Ball;
import pong.domain.actors.Wall;
import pong.domain.player.Human;
import pong.domain.player.Player;
import pong.tools.Configurations;

/**
 * Luokka käsittelee pallottelupelin toimintoja
 */
public class RallyGame {

    private int points;
    private Player player;
    private Wall wall;
    private Ball ball;
    private boolean pause;
    private KeyCode pauseButton;
    private KeyCode menuButton;
    /**
     * Aika, jona pelin tilaa muutettiin paussin ja käynnissä olemisen välillä
     */
    public long lastPause;
    private double speedUp;
    private boolean gameEnd;

    /**
     * Luokan konstruktori, joka luo pelissä vaaditut oliot, eli mailan ja
     * pallon
     *
     * @param config Pelin asetukset sisältävä Configurations-olio
     *
     * @see pong.domain.actors.Bat
     * @see pong.domain.actors.Ball
     */
    public RallyGame(Configurations config) {
        this.points = 0;
        this.gameEnd = false;
        this.pause = true;
        this.pauseButton = config.getKey("pause", KeyCode.P);
        this.menuButton = config.getKey("menu", KeyCode.M);
        this.speedUp = 1.05;
        this.lastPause = System.currentTimeMillis();
        player = new Human(config.getKey("Player1_Up", KeyCode.W), config.getKey("Player1_Down", KeyCode.S), 10, 160, config.getInt("BatSpeed", 4), config.getColor("Player1_colour"));
        wall = new Wall(790, 0);
        ball = new Ball(10, 400, 200, 6, config.getColor("Ball_colour"));
        ball.randomMovement();
    }

    /**
     * Liikuttaa pelaajien mailoja, jos liikkumisnäppäimiä on painettu
     *
     * @param pressedButtons HashMap, jossa on tieto, onko näppäimiä painettu,
     * eli true, vai ei, eli false
     *
     * @see pong.domain.player.Player#moveBat(int, int, int,
     * pong.domain.actors.Ball)
     */
    public void moveBats(HashMap<KeyCode, Boolean> pressedButtons) {
        player.getBat().setLastMovement(0);
        if (!pause) {
            if (pressedButtons.getOrDefault(player.getDown(), false)) {
                player.moveBat(1, 0, 400, ball);
            }
            if (pressedButtons.getOrDefault(player.getUp(), false)) {
                player.moveBat(-1, 0, 400, ball);
            }
        }
    }

    /**
     * Käsittelee pause- ja menu-näppäimien painamisen<br>
     * Pelin tila muuttuu paussin ja käynnissä olemisen välillä vain, jos
     * viimeisestä tilan muutoksesta on kulunut 0.1s<br>
     * Pelin ollessa paussilla, menu-näppäimen painaminen palauttaa arvon, jonka
     * mukaan siirrytään päävalikkoon<br>
     * Pelin loputtua menu-näppäimen painaminen palauttavat arvon, jonka mukaan
     * siirrytään päävalikkoon<br>
     * Pelin loputtua pause-näppäimen painaminen palauttavat arvon, jonka mukaan
     * siirrytään pisteruutuun
     *
     * @param pressedButtons HashMap, jossa on tieto, onko näppäimiä painettu,
     * eli true, vai ei, eli false
     * @return 0, kun peliä jatketaan<br> 1, kun siirrytään pisteruutuun<br> 2,
     * kun peli menee paussille<br> 3, kun ei tehdä mitään<br> 4, kun palataan
     * päävalikkoon
     */
    public int pauseManagement(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(pauseButton, false) && System.currentTimeMillis() - lastPause >= 100) {
            lastPause = System.currentTimeMillis();
            if (pause) {
                if (this.gameEnd) {
                    return 1;
                } else {
                    pause = false;
                    return 0;
                }
            } else {
                pause = true;
                return 2;
            }
        }
        if (pause && pressedButtons.getOrDefault(menuButton, false)) {
            return 4;
        }
        return 3;

    }

    /**
     * Tarkistaa tapahtuuko törmäyksiä pallon ja mailan tai pallon ja seinän
     * välillä<br>
     * Jos törmäys tapahtuu pallon ja seinän välillä, pallon nopeus kasvatetaan
     * 1.05 kertaiseksi
     *
     * @see pong.domain.actors.Ball#collision(pong.domain.actors.Collisionable)
     * @see pong.domain.actors.Ball#speedUp(double)
     */
    public void collisionManagement() {
        ball.collision(player.getBat());
        if (ball.collision(wall)) {
            ball.speedUp(speedUp);
            this.points++;
        }
    }

    /**
     * Tarkistaa onko pallo kentän ulkopuolella<br>
     * Jos pallo on kentän ulkopuolella, peli loppuu
     *
     * @return 4, kun pallo on kentän ulkopuolella ja peli loppuu<br> 5, kun
     * pallo ei ole kentän ulkopuolella
     *
     * @see pong.domain.actors.Ball#inGoal(int, int)
     */
    public int goalCheck() {
        int action = 4;
        int inGoal = ball.inGoal(0, 800);
        if (inGoal == -1 || inGoal == 1) {
            gameEnd = true;
            pause = true;
            return action;
        }
        return 5;
    }

    /**
     * Liikuttaa palloa, jos peli ei ole paussilla
     *
     * @see pong.domain.actors.Ball#move(int, int)
     */
    public void moveBall() {
        if (!pause) {
            ball.move(0, 400);
        }
    }

    public int getPoints() {
        return points;
    }

    public Player getPlayer1() {
        return player;
    }

    public Wall getWall() {
        return wall;
    }

    public KeyCode getPauseButton() {
        return pauseButton;
    }

    public KeyCode getMenuButton() {
        return menuButton;
    }

    public Ball getBall() {
        return ball;
    }

    public boolean isPaused() {
        return pause;
    }

}
