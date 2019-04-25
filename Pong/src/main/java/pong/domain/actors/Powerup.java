package pong.domain.actors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import pong.domain.player.Player;
import pong.tools.ComponentCreator;

/**
 * Luokka käsittelee tavallisen pelin lisävoimien toimintoja
 *
 * @see pong.domain.actors.Collisionable
 * @see pong.domain.games.NormalGame
 */
public class Powerup implements Collisionable {

    private int x;
    private int y;
    private boolean shown;
    private int type;
    /**
     * Aika, joka on kulunut activate-metodin kutsumisesta<br>
     * Arvo on -1, jos activate-metodia ei ole kutsuttu
     *
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     */
    public long activationTime;
    /**
     * Aika, joka on kulunut olion luomisesta
     */
    public long spawnTime;
    private Collisionable target;

    /**
     * Luokan konstruktori
     *
     * @param x Lisävoiman sijainti x-akselilla
     * @param y Lisävoiman sijainti y-akselilla
     * @param type Lisävoiman tyyppi
     */
    public Powerup(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.shown = true;
        this.type = type;
        this.activationTime = -1;
        this.spawnTime = System.currentTimeMillis();
    }

    /**
     * Aktivoi lisävoiman ja toimii tyypin mukaan<br>
     * Jos lisävoima ei vaikuta palloon, se vaikuttaa pelaajaan, josta se on
     * liikkumassa pois<br><br>
     * Tyyppi&nbsp;&nbsp;&nbsp;&nbsp;Toiminto&nbsp;&nbsp;&nbsp;&nbsp;Kohde<br>
     * 0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pienentää&nbsp;&nbsp;&nbsp;&nbsp;Maila<br>
     * 1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kasvattaa&nbsp;&nbsp;&nbsp;&nbsp;Maila<br>
     * 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hidastaa&nbsp;&nbsp;&nbsp;&nbsp;Maila<br>
     * 3&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nopeentaa&nbsp;&nbsp;&nbsp;&nbsp;Maila<br>
     * 4&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hidastaa&nbsp;&nbsp;&nbsp;&nbsp;Pallo<br>
     * 5&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nopeentaa&nbsp;&nbsp;&nbsp;&nbsp;Pallo
     *
     * @param player1 Vasen pelaaja
     * @param player2 Oikea pelaaja
     * @param ball Pallo
     *
     * @see pong.domain.actors.Powerup#changeSize(java.lang.Double)
     * @see pong.domain.actors.Powerup#changeSpeed(java.lang.Double)
     */
    public void activate(Player player1, Player player2, Ball ball) {
        this.activationTime = System.currentTimeMillis();
        this.shown = false;
        if (this.type == 0 || this.type == 1) {
            this.setTarget(player1, player2, ball);
            if (type == 0) {
                this.changeSize(0.5);
            } else {
                this.changeSize(1.5);
            }
        } else if (this.type == 2 || this.type == 3) {
            this.setTarget(player1, player2, ball);
            speedChange();
        } else {
            this.target = ball;
            speedChange();
        }
    }

    private void setTarget(Player player1, Player player2, Ball ball) {
        if (ball.getXMovement() > 0) {
            this.target = player1.getBat();
        } else {
            this.target = player2.getBat();
        }
    }

    /**
     * Muuttaa activate-metodissa valitun kohteen kokoa, jos kohde on maila
     *
     * @param size Kerroin, jolla kokoa muutetaan
     *
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     * @see pong.domain.actors.Powerup#changeSpeed(java.lang.Double)
     * @see pong.domain.actors.Bat#setSize(double)
     */
    public void changeSize(Double size) {
        if (this.target.getClass() == Bat.class) {
            Bat targetBat = (Bat) target;
            targetBat.setSize(size);
        }
    }

    private void speedChange() {
        if (type == 2) {
            this.changeSpeed(0.5);
        } else if (type == 3) {
            this.changeSpeed(1.5);
        } else if (type < 5) {
            this.changeSpeed(0.75);
        } else {
            this.changeSpeed(1.25);
        }
    }

    /**
     * Muuttaa activate-metodissa valitun kohteen nopeutta
     *
     * @param speed Kerroin, jolla nopeutta muutetaan
     *
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     * @see pong.domain.actors.Powerup#changeSpeed(java.lang.Double)
     * @see pong.domain.actors.Bat#setMovementSpeed(int)
     * @see pong.domain.actors.Ball#speedUp(double)
     */
    public void changeSpeed(Double speed) {
        if (this.target.getClass() == Bat.class) {
            Bat targetBat = (Bat) target;
            int newSpeed = (int) (targetBat.getMovementSpeed() * speed);
            targetBat.setMovementSpeed(newSpeed);
        } else if (this.target.getClass() == Ball.class) {
            Ball targetBall = (Ball) target;
            targetBall.speedUp(speed);
        }
    }

    /**
     * Kumoaa activate-metodin tekemät muutokset<br>
     * Jos activate-metodia ei ole kutsuttu, metodi ei tee mitään
     *
     * @param player1 Vasen pelaaja
     * @param player2 Oikea pelaaja
     * @param ball Pallo
     *
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     * @see pong.domain.actors.Powerup#changeSpeed(java.lang.Double)
     * @see pong.domain.actors.Powerup#changeSize(java.lang.Double)
     */
    public void deactivate(Player player1, Player player2, Ball ball) {
        if (activationTime == -1) {
            return;
        }
        if (this.type == 0 || this.type == 1) {
            this.changeSize(1.0);
        } else if (this.type == 2 || this.type == 3) {
            if (this.type == 2) {
                this.changeSpeed(1 / 0.5);
            } else {
                this.changeSpeed(1 / 1.5);
            }
        } else {
            if (this.type < 5) {
                this.changeSpeed(1 / 0.75);
            } else {
                this.changeSpeed(1 / 1.25);
            }
        }
    }

    /**
     * Tarkistaa, miten paljon aikaa on kulunut olion luomisesta ja
     * aktivoinnista ja toimii sen mukaan<br>
     * Jos aktivoinnista on kulunut 15s, kutsutaan metodia deactivate ja
     * palautetaan null<br>
     * Jos aktivointia ei ole tapahtunut ja on kulunut 10s, palautetaan null<br>
     * Muuten palautetaan olio itse
     *
     * @param player1 Vasen pelaaja
     * @param player2 Oikea pelaaja
     * @param ball Pallo
     * @return Jos aktivoinnista on kulunut 15s tai jos aktivointia ei ole
     * tapahtunut ja on kulunut 10s, palautetaan null. Muuten palautetaan olio
     * itse
     *
     * @see pong.domain.actors.Powerup#activate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     * @see pong.domain.actors.Powerup#deactivate(pong.domain.player.Player,
     * pong.domain.player.Player, pong.domain.actors.Ball)
     */
    public Powerup despawn(Player player1, Player player2, Ball ball) {
        if (activationTime == -1) {
            if (Math.abs(System.currentTimeMillis() - this.spawnTime) > 15000) {
                return null;
            }
            return this;
        }
        if (Math.abs(System.currentTimeMillis() - this.activationTime) > 10000) {
            this.deactivate(player1, player2, ball);
            return null;
        }
        return this;
    }

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän 45 astetta sivulle käännetyn
     * ja 20x20 kokoisen ruudun käyttäen ComponentCreator-luokan
     * createDiamond-metodia<br>
     * Jos tyyppi on 0 tai 2, ruudun väri on punainen<br>
     * Jos tyyppi on 1 tai 3, ruudun väri on sininen<br>
     * Mulloin ruudun väri on vihreä
     *
     * @return Shape-olio, joka vastaa lisävoiman tietoja
     *
     * @see pong.tools.ComponentCreator#createDiamond(double, double,
     * javafx.scene.paint.Color)
     */
    @Override
    public Shape getSprite() {
        if (shown) {
            if (this.type == 0 || this.type == 2) {
                return ComponentCreator.createDiamond(this.x, this.y, Color.RED);
            } else if (this.type == 1 || this.type == 3) {
                return ComponentCreator.createDiamond(this.x, this.y, Color.BLUE);
            } else {
                return ComponentCreator.createDiamond(this.x, this.y, Color.GREEN);
            }
        }
        return new Rectangle();

    }

    /**
     * Tarkistaa taphtuuko törmäys toisen Collisionable-rajapintaan toteuttavan
     * olion kanssa ja käsittelee sen<br>
     * Tarkistus tapahtuu Collisionable-rajapintaa toteuttavien olioiden
     * getSprite-metodilla, jolloin Shape-olioiden intersect-metodilla voi
     * tarkistaa tapahtuuko törmäys<br>
     *
     * @param other Törmäyksen toinen osapuoli
     * @return true, kun törmäys tapahtuu, ja false muuten
     */
    @Override
    public boolean collision(Collisionable other) {
        Shape thisSprite = this.getSprite();
        Shape collisionArea = Shape.intersect(thisSprite, other.getSprite());
        if (collisionArea.getBoundsInLocal().getWidth() != -1) {
            return true;
        }
        return false;
    }

}
