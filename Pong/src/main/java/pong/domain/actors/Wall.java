package pong.domain.actors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import pong.tools.ComponentCreator;

/**
 * Luokka käsittelee pallottelupelin seinän toimintoja
 *
 * @see pong.domain.actors.Collisionable
 * @see pong.domain.games.RallyGame
 */
public class Wall implements Collisionable {

    private int xPosition;
    private int yPosition;

    /**
     * Luokan konstruktori
     *
     * @param xposition Olion sijainti x-akselilla
     * @param yposition Olion sijainti y-akselilla
     */
    public Wall(int xposition, int yposition) {
        this.xPosition = xposition;
        this.yPosition = yposition;
    }

    /**
     * Wall-oliot eivät käsittele törmäyksiä, joten palauttaa aina true
     *
     * @param other Törmäyksen toinen osapuoli
     * @return true
     */
    @Override
    public boolean collision(Collisionable other) {
        return true;
    }

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän 20x420 kokoisen mustan
     * suorakulmion käyttäen ComponentCreator-luokan createRectangle-metodia
     *
     * @return Shape-olio, joka vastaa seinän tietoja
     *
     * @see pong.tools.ComponentCreator#createRectangle(int, int, double,
     * double, javafx.scene.paint.Color)
     */
    @Override
    public Shape getSprite() {
        return ComponentCreator.createRectangle(20, 420, this.xPosition, this.yPosition, Color.BLACK);
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

}
