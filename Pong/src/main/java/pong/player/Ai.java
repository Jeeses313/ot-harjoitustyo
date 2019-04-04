package pong.player;

import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.actors.Bat;

public class Ai extends Player {

    private int difficulty;

    public Ai(int x, int y, int difficulty) {
        super(x, y, 0);
        if (difficulty >= 0) {
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

    @Override
    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        if (this.difficulty == 0) {
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

    @Override
    public KeyCode getDown() {
        return super.getDown();
    }

    @Override
    public KeyCode getUp() {
        return super.getUp();
    }

    @Override
    public void moveBatTo(int y) {
        super.moveBatTo(y);
    }

}
