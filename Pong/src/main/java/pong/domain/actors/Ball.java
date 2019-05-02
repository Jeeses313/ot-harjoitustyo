package pong.domain.actors;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import pong.tools.ComponentCreator;

/**
 * Luokka käsittelee pelien pallon toimintoja
 *
 * @see pong.domain.actors.Collisionable
 * @see pong.domain.games.RallyGame
 * @see pong.domain.games.NormalGame
 */
public class Ball implements Collisionable {

    private double yMovement;
    private double xMovement;
    private double xPosition;
    private double yPosition;
    private int radius;
    private int speed;
    private Color colour;

    /**
     * Luokan konstruktori
     *
     * @param radius Pallon säde
     * @param x Pallon sijainti x-akselilla
     * @param y Pallon sijainti y-akselilla
     * @param speed Pallon nopeus
     * @param colour Pallon väri
     */
    public Ball(int radius, int x, int y, int speed, Color colour) {
        this.speed = speed;
        if (speed <= 0) {
            this.speed = 8;
        }
        this.radius = radius;
        this.yMovement = 0;
        this.xMovement = 0;
        this.moveTo(x, y);
        this.colour = colour;
    }

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän radius muuttujan säteisen ja
     * colour muuttujan värisen ympyrän käyttäen ComponentCreator-luokan
     * createCircle-metodia
     *
     * @return Shape-olio, joka vastaa pallon tietoja
     *
     * @see pong.tools.ComponentCreator#createCircle(int, double, double,
     * javafx.scene.paint.Color)
     * @see pong.domain.actors.Ball#getSpriteCircle()
     */
    @Override
    public Shape getSprite() {
        return ComponentCreator.createCircle(radius, this.xPosition, this.getyPosition(), colour);
    }

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän radius muuttujan säteisen ja
     * colour muuttujan värisen ympyrän käyttäen ComponentCreator-luokan
     * createCircle-metodia
     *
     * @return Circle-olio, joka vastaa pallon tietoja
     *
     * @see pong.tools.ComponentCreator#createCircle(int, double, double,
     * javafx.scene.paint.Color)
     * @see pong.domain.actors.Ball#getSprite()
     */
    public Circle getSpriteCircle() {
        return ComponentCreator.createCircle(radius, this.xPosition, this.getyPosition(), colour);
    }

    /**
     * Palauttaa pallon sijainnin
     *
     * @return Point2D-olio, jonka x ja y vastaavat pallon sijaintia
     */
    public Point2D getPosition() {
        return new Point2D(xPosition, yPosition);
    }

    /**
     * Palauttaa pallon liikkeen
     *
     * @return Point2D-olio, jonka x ja y vastaavat pallon liikettä
     */
    public Point2D getMovement() {
        return new Point2D(xMovement, yMovement);
    }

    /**
     * Asettaa x ja y suuntaiset liikkeet vastaamaan annettuja arvoja
     *
     * @param movement Point2D-olio, jonka x ja y arvot asetetaan pallon
     * xMovementiksi ja yMovementiksi
     */
    public void setMovement(Point2D movement) {
        this.yMovement = movement.getY();
        this.xMovement = movement.getX();
    }

    /**
     * Muuttaa pallon nopeutta annetun kertoimen verran käyttäen
     * setMovement-metodia
     *
     * @param multiplier Kerroin, jolla nopeutta muutetaan
     *
     * @see pong.domain.actors.Ball#setMovement(javafx.geometry.Point2D)
     */
    public void speedUp(double multiplier) {
        this.setMovement(new Point2D(this.xMovement * multiplier, this.yMovement * multiplier));
    }

    /**
     * Liikuttaa palloa x ja y nopeuksien verran suuntiinsa käyttäen
     * moveTo-metodia<br>
     * Jos pallo liikkuisi sallitun alueen ulkopuolelle, y-akselinsuuntainen
     * nopeus käännetään vastakkaiseksi käyttäen mirrorYMovement-metodia
     *
     * @param miny Sallitun liikkumisalueen alaraja
     * @param maxy Sallitun liikkumisalueen yläraja
     *
     * @see pong.domain.actors.Ball#moveTo(double, double)
     * @see pong.domain.actors.Ball#mirrorYMovement()
     */
    public void move(int miny, int maxy) {
        this.moveTo(this.xPosition + this.xMovement, this.yPosition + this.yMovement);
        if (this.yPosition + 35 >= maxy || this.yPosition <= miny) {
            this.mirrorYMovement();
        }

    }

    /**
     * Liikuttaa pallon haluttuun kohtaan
     *
     * @param x Pallon uusi sijainti x-akselilla
     * @param y Pallon uusi sijainti y-akselilla
     */
    public void moveTo(double x, double y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    /**
     * Kääntää pallon y-akselin suuntaisen liikken vastakkaiseksi
     */
    public void mirrorYMovement() {
        this.yMovement *= -1;
    }

    /**
     * Kääntää pallon x-akselin suuntaisen liikken vastakkaiseksi
     */
    public void mirrorXMovement() {
        this.xMovement *= -1;
    }

    public double getYMovement() {
        return this.yMovement;
    }

    public double getXMovement() {
        return this.xMovement;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public int getRadius() {
        return radius;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * Asettaa pallon likkeen satunnaiseksi nopeuden mukaan käyttäen
     * setMovement-metodia
     *
     * @see pong.domain.actors.Ball#setMovement(javafx.geometry.Point2D)
     */
    public void randomMovement() {
        int newMovement = new Random(System.currentTimeMillis()).nextInt(4);
        int x = speed;
        int y = speed;
        if (newMovement == 0) {
            x *= -1;
        } else if (newMovement == 1) {
            y *= -1;
        }
        if (x == 2) {
            x *= -1;
            y *= -1;
        }
        this.setMovement(new Point2D(x, y));
    }

    /**
     * Tarkistaa taphtuuko törmäys toisen Collisionable-rajapintaan toteuttavan
     * olion kanssa ja käsittelee sen<br>
     * Tarkistus tapahtuu Collisionable-rajapintaa toteuttavien olioiden
     * getSprite-metodilla, jolloin Shape-olioiden intersect-metodilla voi
     * tarkistaa tapahtuuko törmäys<br>
     * Bat ja Wall olioihin törmätessä pallon x-akselinsuuntainen nopeus
     * käännetään vastakkaiseksi käyttäen mirrorXMovement-metodia<br>
     * Jos mailan y-akselin suuntainen likke on pallon y-akselin suuntaista
     * liikettä vastaan, pallon y-akselinsuuntainen nopeus käännetään
     * vastakkaiseksi käyttäen mirrorYMovement-metodia
     *
     * @param other Törmäyksen toinen osapuoli
     * @return true, kun törmäys tapahtuu, ja false muuten
     *
     * @see pong.domain.actors.Ball#mirrorXMovement()
     * @see pong.domain.actors.Ball#mirrorYMovement()
     * @see
     * pong.domain.actors.Ball#checkChangeYDirection(pong.domain.actors.Bat)
     * @see pong.domain.actors.Ball#getSprite()
     * @see pong.domain.actors.Ball#getSpriteCircle()
     * @see pong.domain.actors.Wall#getSprite()
     * @see pong.domain.actors.Bat#getSprite()
     */
    @Override
    public boolean collision(Collisionable other) {
        Circle thisSprite = this.getSpriteCircle();
        Shape collisionArea = Shape.intersect(thisSprite, other.getSprite());
        if (collisionArea.getBoundsInLocal().getWidth() != -1) {
            if (other.getClass() == Bat.class) {
                Bat bat = (Bat) other;
                if ((this.xMovement > 0 && bat.getxPosition() > thisSprite.getCenterX() - 20) || (this.xMovement < 0 && bat.getxPosition() < thisSprite.getCenterX() + 20)) {
                    this.mirrorXMovement();
                }
                this.checkChangeYDirection(bat);
            } else if (other.getClass() == Wall.class) {
                Wall wall = (Wall) other;
                if ((this.xMovement > 0 && wall.getxPosition() > thisSprite.getCenterX() - 20) || (this.xMovement < 0 && wall.getxPosition() < thisSprite.getCenterX() + 20)) {
                    this.mirrorXMovement();
                }
            }
            return true;
        }
        return false;
    }

    private void checkChangeYDirection(Bat bat) {
        if (this.getYMovement() > 0) {
            if (bat.getLastMovement() < 0) {
                this.mirrorYMovement();
            }
        } else {
            if (bat.getLastMovement() > 0) {
                this.mirrorYMovement();
            }
        }
    }

    /**
     * Tarkistaa onko pallo maalissa
     *
     * @param leftGoal Vasemman maalin sijainti x-akselilla
     * @param rightGoal Oikean maalin sijainti x-akselilla
     * @return -1, jos pallo on vasemmassa maalissa, 1, kun oikeassa, ja 0, kun
     * ei kummassakaan
     */
    public int inGoal(int leftGoal, int rightGoal) {
        if (this.xPosition <= leftGoal) {
            return -1;
        } else if (this.xPosition >= rightGoal) {
            return 1;
        }
        return 0;
    }
}
