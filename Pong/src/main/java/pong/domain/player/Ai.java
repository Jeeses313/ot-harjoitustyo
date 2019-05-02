package pong.domain.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;

/**
 * Luokka käsittelee tekoälypelaajien toimintoja
 *
 * @see pong.domain.player.Player
 */
public class Ai extends Player {

    private int difficulty;

    /**
     * Luokan konstruktori, jossa pelaajalle luodaan maila
     *
     * @param x Mailan sijainti x-akselilla
     * @param y Mailan sijainti y-akselilla
     * @param difficulty Tekoälyn vaikeustaso
     * @param color Mailan väri
     *
     * @see pong.domain.actors.Bat
     */
    public Ai(int x, int y, int difficulty, Color color) {
        super(x, y, 0, color);
        if (difficulty <= 0) {
            super.getBat().setMovementSpeed(5);
        } else if (difficulty == 1) {
            super.getBat().setMovementSpeed(3);
        } else {
            super.getBat().setMovementSpeed(5);
        }

        this.difficulty = difficulty;
    }

    @Override
    public Bat getBat() {
        return super.getBat();
    }

    /**
     * Likkuttaa mailaa pallon suuntaan, jos vaikeustaso on 0 tai pienempi, tai
     * pallon liikkeen suuntan, jos vaikeustaso on 1 tai suurempi, jos liike ei
     * vie pois sallitulta alueelta
     *
     * @param direction Perityn metodin parametri, jota ei käytetä
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
        if (this.difficulty <= 0) {
            if (ball.getSprite().getTranslateY() > super.getBat().getSprite().getTranslateY()) {
                return super.moveBat(1, miny, maxy, ball);
            } else if (ball.getSprite().getTranslateY() < super.getBat().getSprite().getTranslateY()) {
                return super.moveBat(-1, miny, maxy, ball);
            }
        } else {
            if (ball.getYMovement() > 0) {
                return super.moveBat(1, miny, maxy, ball);
            } else if (ball.getYMovement() < 0) {
                return super.moveBat(-1, miny, maxy, ball);
            }
        }
        return 0;
    }

    /**
     * Ai ei käytä näppäimiä liikkumiseen
     *
     * @return null
     */
    @Override
    public KeyCode getDown() {
        return null;
    }

    /**
     * Ai ei käytä näppäimiä liikkumiseen
     *
     * @return null
     */
    @Override
    public KeyCode getUp() {
        return null;
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
