package pong.domain.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;

/**
 * Luokka käsittelee pelaajien yleisiä toimintoja
 */
public abstract class Player {

    private Bat bat;

    /**
     * Luokan konstruktori, jossa pelaajalle luodaan maila
     *
     * @param x Mailan sijainti x-akselilla
     * @param y Mailan sijainti y-akselilla
     * @param movementSpeed Mailan liikkumisnopeus
     * @param color Mailan väri
     *
     * @see pong.domain.actors.Bat
     */
    public Player(int x, int y, int movementSpeed, Color color) {
        this.bat = new Bat(x, y, movementSpeed, color);
    }

    public Bat getBat() {
        return bat;
    }

    public abstract KeyCode getUp();

    public abstract KeyCode getDown();

    /**
     * Likkuttaa mailaa annettuun suuntaan, jos liike ei vie pois sallitulta
     * alueelta
     *
     * @param direction Suunta johon liikutaan, eli positiivinen alas ja
     * negatiivinen ylös
     * @param miny Sallitun liikkumisalueen alaraja
     * @param maxy Sallitun liikkumisalueen yläraja
     * @param ball Pelissä oleva pallo
     * @return Suunta johon liikuttiin, eli positiivinen alas ja negatiivinen
     * ylös
     *
     * @see pong.domain.actors.Bat#move(int, int, int)
     */
    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        this.bat.move(direction, miny, maxy);
        return direction;
    }

    /**
     * Likkuttaa mailan haluttuun kohtaan
     *
     * @param y Mailan uusi sijainti y-akselilla
     *
     * @see pong.domain.actors.Bat#moveTo(int)
     */
    public void moveBatTo(int y) {
        this.bat.moveTo(y);
    }

}
