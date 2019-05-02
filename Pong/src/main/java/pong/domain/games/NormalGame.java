package pong.domain.games;

import java.util.HashMap;
import java.util.Random;
import javafx.scene.input.KeyCode;
import pong.domain.actors.Ball;
import pong.domain.actors.Powerup;
import pong.domain.player.Ai;
import pong.domain.player.Human;
import pong.domain.player.Player;
import pong.tools.Configurations;

/**
 * Luokka käsittelee tavallisen pelin toimintoja
 */
public class NormalGame {

    private int endingPoint;
    private int leftPoints;
    private int rightPoints;
    private boolean twoPlayers;
    private Player player1;
    private Player player2;
    private Ball ball;
    private boolean pause;
    private KeyCode pauseButton;
    private KeyCode menuButton;
    /**
     * Aika, jona pelin tilaa muutettiin paussin ja käynnissä olemisen välillä
     */
    public long lastPause;
    private double speedUp;
    private int powerups;
    private Powerup powerup;

    /**
     * Luokan konstruktori, joka luo pelissä vaaditut oliot, eli mailat ja
     * pallon
     *
     * @param twoPlayers true, jos pelissä on kaksi ihmispelaajaa, ja false, jos
     * vain yksi
     * @param config Pelin asetukset sisältävä Configurations-olio
     *
     * @see pong.domain.actors.Bat
     * @see pong.domain.actors.Ball
     */
    public NormalGame(boolean twoPlayers, Configurations config) {
        this.twoPlayers = twoPlayers;
        this.endingPoint = config.getInt("endingpoint", 5);
        this.leftPoints = 0;
        this.rightPoints = 0;
        this.twoPlayers = twoPlayers;
        this.powerups = config.getInt("powerups", 0);
        this.powerup = null;
        this.pause = true;
        this.pauseButton = config.getKey("pause", KeyCode.P);
        this.menuButton = config.getKey("menu", KeyCode.M);
        this.speedUp = config.getDouble("speedUp", 1);
        this.lastPause = System.currentTimeMillis();
        player1 = new Human(config.getKey("Player1_Up", KeyCode.W), config.getKey("Player1_Down", KeyCode.S), 10, 160, config.getInt("BatSpeed", 4), config.getColor("Player1_colour"));
        if (twoPlayers) {
            player2 = new Human(config.getKey("Player2_Up", KeyCode.UP), config.getKey("Player2_Down", KeyCode.DOWN), 770, 160, config.getInt("BatSpeed", 4), config.getColor("Player2_colour"));
        } else {
            player2 = new Ai(770, 160, config.getInt("difficulty", 1), config.getColor("Player2_colour"));
        }
        ball = new Ball(10, 400, 200, config.getInt("BallSpeed", 8), config.getColor("Ball_colour"));
        ball.randomMovement();
    }

    /**
     * Liikuttaa tekoälypelaajan mailaa ja ihmispelaajien mailoja, jos
     * liikkumisnäppäimiä on painettu
     *
     * @param pressedButtons HashMap, jossa on tieto, onko näppäimiä painettu,
     * eli true, vai ei, eli false
     *
     * @see pong.domain.player.Player#moveBat(int, int, int,
     * pong.domain.actors.Ball)
     */
    public void moveBats(HashMap<KeyCode, Boolean> pressedButtons) {
        player1.getBat().setLastMovement(0);
        player2.getBat().setLastMovement(0);
        if (!pause) {
            this.moveBat1(pressedButtons);
            this.moveBat2(pressedButtons);
        }
    }

    private void moveBat1(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(player1.getDown(), false)) {
            player1.moveBat(1, 0, 400, ball);
        }
        if (pressedButtons.getOrDefault(player1.getUp(), false)) {
            player1.moveBat(-1, 0, 400, ball);
        }
    }

    private void moveBat2(HashMap<KeyCode, Boolean> pressedButtons) {
        if (twoPlayers) {
            if (pressedButtons.getOrDefault(player2.getDown(), false)) {
                player2.moveBat(1, 0, 400, ball);
            }
            if (pressedButtons.getOrDefault(player2.getUp(), false)) {
                player2.moveBat(-1, 0, 400, ball);
            }
        } else {
            player2.moveBat(0, 0, 400, ball);
        }
    }

    /**
     * Käsittelee pause- ja menu-näppäimien painamisen<br>
     * Pelin tila muuttuu paussin ja käynnissä olemisen välillä vain, jos
     * viimeisestä tilan muutoksesta on kulunut 0.1s<br>
     * Tilan muutumisen yhteydessä muutetaan myös lisävoiman aktivointi- jai
     * ilmestymisaikoja<br>
     * Pelin ollessa paussilla, menu-näppäimen painaminen palauttaa arvon, jonka
     * mukaan siirrytään päävalikkoon<br>
     * Pelin loputtua menu- tai pause-näppäimen painaminen palauttavat arvon,
     * jonka mukaan siirrytään päävalikkoon
     *
     * @param pressedButtons HashMap, jossa on tieto, onko näppäimiä painettu,
     * eli true, vai ei, eli false
     * @return 0, kun peliä jatketaan<br> 1, kun palataan päävalikkoon<br> 2,
     * kun peli menee paussille<br> 3, kun ei tehdä mitään
     *
     * @see pong.domain.actors.Powerup#activationTime
     * @see pong.domain.actors.Powerup#spawnTime
     */
    public int pauseManagement(HashMap<KeyCode, Boolean> pressedButtons) {
        if (pressedButtons.getOrDefault(pauseButton, false) && System.currentTimeMillis() - lastPause >= 100) {
            powerupPauseTimeManagement();
            lastPause = System.currentTimeMillis();
            if (pause) {
                return unpause();
            } else {
                return pause();
            }
        }
        if (pause && pressedButtons.getOrDefault(menuButton, false)) {
            return 1;
        }
        return 3;

    }

    private int unpause() {
        if (leftPoints < endingPoint && rightPoints < endingPoint) {
            pause = false;
            return 0;
        } else {
            return 1;
        }
    }

    private int pause() {
        pause = true;
        return 2;
    }

    private void powerupPauseTimeManagement() {
        if (pause && this.powerup != null) {
            if (powerup.activationTime != -1) {
                this.powerup.activationTime += System.currentTimeMillis() - lastPause;
            }
            this.powerup.spawnTime += System.currentTimeMillis() - lastPause;
        }
    }

    /**
     * Tarkistaa tapahtuuko törmäyksiä pallon ja mailan tai pallon ja lisävoiman
     * välillä<br>
     * Jos törmäys tapahtuu pallon ja pelaajan välillä, pallon nopeutta
     * kasvatetaan asetusten mukaisesti<br>
     * Jos törmäys tapahtuu pallon ja lisävoiman välillä, lisävoima aktivoidaan
     *
     * @see pong.domain.actors.Ball#collision(pong.domain.actors.Collisionable)
     * @see pong.domain.actors.Ball#speedUp(double)
     * @see
     * pong.domain.actors.Powerup#collision(pong.domain.actors.Collisionable)
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     */
    public void collisionManagement() {
        if (ball.collision(player1.getBat())) {
            ball.speedUp(speedUp);

        } else if (ball.collision(player2.getBat())) {
            ball.speedUp(speedUp);
        }
        if (powerup != null) {
            if (powerup.collision(ball)) {
                powerup.activate(player1, player2, ball);
            }
        }
    }

    /**
     * Tarkistaa onko pallo maalissa<br>
     * Jos pallo on maalissa, peli palautetaan alkuperäiseen tilaan<br>
     * Jos pallo on vasemmassa maalissa, oikean pelaajan pisteitä kasvatetaan
     * yhdellä<br>
     * Jos pallo on oikeassa maalissa, vasemman pelaajan pisteitä kasvatetaan
     * yhdellä<br>
     *
     * @return 0, kun oikea ihmispelaaja voittaa<br> 1, kun tekoäly voittaa<br>
     * 2, kun vasen pelaaja voittaa<br> 3, kun toinen pelaajista saa pisteen,
     * mutta peli ei lopu<br> 4 tai 5, kun pallo ei ole maalissa
     *
     * @see pong.domain.actors.Ball#inGoal(int, int)
     * @see pong.domain.games.NormalGame#resetField()
     */
    public int goalCheck() {
        int action = 4;
        int inGoal = ball.inGoal(0, 800);
        if (inGoal == -1 || inGoal == 1) {
            if (inGoal == -1) {
                rightPoints++;
            } else {
                leftPoints++;
            }
            pause = true;
            action = checkPoints();

            this.resetField();
            return action;
        }
        return 5;
    }

    private int checkPoints() {
        if (rightPoints == endingPoint) {
            if (twoPlayers) {
                return 0;
            } else {
                return 1;
            }
        } else if (leftPoints == endingPoint) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Palauttaa kentän alkuperäiseen tilaan
     *
     * @see pong.domain.actors.Ball#moveTo(double, double)
     * @see pong.domain.actors.Ball#randomMovement()
     * @see pong.domain.actors.Bat#moveTo(int)
     * @see pong.domain.actors.Powerup#deactivate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     */
    public void resetField() {
        ball.moveTo(400, 200);
        ball.randomMovement();
        player1.moveBatTo(160);
        player2.moveBatTo(160);
        if (this.powerup != null) {
            this.powerup.deactivate(player1, player2, ball);
            this.powerup = null;
        }
    }

    /**
     * Käsittelee lisävoimien luomisen ja poistamisen Jos lisävoimat ovat päällä
     * ja kentällä ei ole lisävoimaa, aktivoituna tai ei, on mahdollista, että
     * satunnaiseen paikkaan luodaan satunnaisella tyypillä lisävoima<br>
     * Jos kentällä on lisävoima, aktivoituna tai ei, tarkistetaan kuuluisiko
     * sen kadota ja lopettaa vaikuttaminen
     *
     * @see pong.domain.actors.Powerup#despawn(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     */
    public void powerupManagement() {
        if (this.powerups < 1 || pause) {
            return;
        }
        if (powerup == null) {
            if (new Random(System.currentTimeMillis()).nextInt(10000) < 100) {
                spawnPowerup();
            }
        } else {
            powerup = powerup.despawn(player1, player2, ball);
        }
    }

    private void spawnPowerup() {
        Random rng = new Random(System.currentTimeMillis());
        powerup = new Powerup(rng.nextInt(720) + 40, rng.nextInt(340) + 20, rng.nextInt(6));
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

    public int getLeftPoints() {
        return leftPoints;
    }

    public int getRightPoints() {
        return rightPoints;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
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

    public Powerup getPowerup() {
        return powerup;
    }

    public boolean isPaused() {
        return pause;
    }

}
