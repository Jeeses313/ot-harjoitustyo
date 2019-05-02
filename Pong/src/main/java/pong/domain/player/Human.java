package pong.domain.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;

/**
 * Luokka käsittelee ihmispelaajien toimintoja
 *
 * @see pong.domain.player.Player
 */
public class Human extends Player {

    private KeyCode up;
    private KeyCode down;

    /**
     * Luokan konstruktori, jossa pelaajalle luodaan maila
     *
     * @param up Näppäin, jolla liikutaan ylös
     * @param down Näppäin, jolla liikutaan alas
     * @param x Mailan sijainti x-akselilla
     * @param y Mailan sijainti y-akselilla
     * @param movementSpeed Mailan liikkumisnopeus
     * @param color Mailan väri
     *
     * @see pong.domain.actors.Bat
     */
    public Human(KeyCode up, KeyCode down, int x, int y, int movementSpeed, Color color) {
        super(x, y, movementSpeed, color);
        this.up = up;
        this.down = down;

    }

    @Override
    public Bat getBat() {
        return super.getBat();
    }

    /**
     * Palauttaa näppäimen, jolla liikutaan ylös
     *
     * @return KeyCode, jonka ollessa painettuna kuuluisi liikkua ylös
     */
    @Override
    public KeyCode getUp() {
        return up;
    }

    /**
     * Palauttaa näppäimen, jolla liikutaan alas
     *
     * @return KeyCode, jonka ollessa painettuna kuuluisi liikkua alas
     */
    @Override
    public KeyCode getDown() {
        return down;
    }

    /**
     * Likkuttaa mailaa pallon suuntaan, jos vaikeustaso on 0 tai pienempi, tai
     * pallon liikkeen suuntan, jos vaikeustaso on 1 tai suurempi, jos liike ei
     * vie pois sallitulta alueelta
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
     * @see pong.domain.player.Player#moveBat(int, int, int,
     * pong.domain.actors.Ball)
     */
    @Override
    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        super.moveBat(direction, miny, maxy, ball);
        return direction;
    }

    /**
     * Likkuttaa mailan haluttuun kohtaan
     *
     * @param y Mailan uusi sijainti y-akselilla
     *
     * @see pong.domain.actors.Bat#moveTo(int)
     * @see pong.domain.player.Player#moveBatTo(int)
     */
    @Override
    public void moveBatTo(int y) {
        super.moveBatTo(y);
    }

}
