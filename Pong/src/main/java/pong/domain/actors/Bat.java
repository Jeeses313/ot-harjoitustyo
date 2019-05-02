package pong.domain.actors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import pong.tools.ComponentCreator;

/**
 * Luokka käsittelee pelien mailojen toimintoja
 *
 * @see pong.domain.actors.Collisionable
 * @see pong.domain.games.RallyGame
 * @see pong.domain.games.NormalGame
 */
public class Bat implements Collisionable {

    private int lastMovement;
    private int movementSpeed;
    private double yPosition;
    private double xPosition;
    private Color colour;
    private double size;

    /**
     * Luokan konstruktori
     *
     * @param x Mailan sijainti x-akselilla
     * @param y Mailan sijainti y-akselilla
     * @param movementSpeed Mailan liikkumisnopeus
     * @param colour Mailan väri
     */
    public Bat(int x, int y, int movementSpeed, Color colour) {
        this.lastMovement = 0;
        this.movementSpeed = movementSpeed;
        if (movementSpeed <= 0) {
            this.movementSpeed = 4;
        }
        this.xPosition = x;
        this.yPosition = y;
        this.colour = colour;
        this.size = 1;
    }

    /**
     * Likkuttaa mailaa annettuun suuntaan liikkumisnopuden verran, jos liike ei
     * vie pois sallitulta alueelta, ja laittaa liikkeen suunnan muistiin
     *
     * @param direction Suunta johon liikutaan, eli positiivinen alas ja
     * negatiivinen ylös
     * @param miny Sallitun liikkumisalueen alaraja
     * @param maxy Sallitun liikkumisalueen yläraja
     */
    public void move(int direction, int miny, int maxy) {
        if (direction > 0) {
            if (this.yPosition + 100 <= maxy) {
                this.yPosition += movementSpeed;
            }
        } else if (direction < 0) {
            if (this.yPosition > miny) {
                this.yPosition -= movementSpeed;
            }
        }
        this.lastMovement = direction;

    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(int lastMovement) {
        this.lastMovement = lastMovement;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän 10x(60*size) kokoisen ja
     * colour muuttujan värisen suorakulmion käyttäen ComponentCreator-luokan
     * createRectangle-metodia
     *
     *
     * @return Shape-olio, joka vastaa mailan tietoja
     *
     * @see pong.tools.ComponentCreator#createRectangle(int, int, double,
     * double, javafx.scene.paint.Color)
     */
    @Override
    public Shape getSprite() {
        Rectangle sprite = ComponentCreator.createRectangle(10, 60, this.xPosition, this.yPosition, this.colour);
        sprite.setScaleY(size);
        return sprite;
    }

    /**
     * Bat-oliot eivät käsittele törmäyksiä, joten palauttaa aina false
     *
     * @param other Törmäyksen toinen osapuoli
     * @return false
     */
    @Override
    public boolean collision(Collisionable other) {
        return false;
    }

    /**
     * Liikuttaa mailan haluttuun kohtaan
     *
     * @param y Mailan uusi sijainti y-akselilla
     */
    public void moveTo(int y) {
        this.yPosition = y;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

}
