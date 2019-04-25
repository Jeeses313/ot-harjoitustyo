package pong.domain.actors;

import javafx.scene.shape.Shape;

/**
 * Rajapinta pelissä törmäyksiä käsitteleville luokille
 */
public interface Collisionable {

    /**
     * Tarkistaa taphtuuko törmäys toisen Collisionable-rajapintaan toteuttavan
     * olion kanssa ja käsittelee sen
     *
     * @param other Törmäyksen toinen osapuoli
     * @return boolean-arvo
     */
    public boolean collision(Collisionable other);

    /**
     * Palauttaa oliosta Scene-olioon piirrettävän kuvan
     *
     * @return Shape-olio, joka vastaa olion tietoja
     */
    public Shape getSprite();
}
