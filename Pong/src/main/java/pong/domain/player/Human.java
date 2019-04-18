package pong.domain.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;

public class Human extends Player {

    private KeyCode up;
    private KeyCode down;

    public Human(KeyCode up, KeyCode down, int x, int y, int movementSpeed, Color color) {
        super(x, y, movementSpeed, color);
        this.up = up;
        this.down = down;

    }

    @Override
    public Bat getBat() {
        return super.getBat();
    }

    @Override
    public KeyCode getUp() {
        return up;
    }

    @Override
    public KeyCode getDown() {
        return down;
    }

    @Override
    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        super.moveBat(direction, miny, maxy, ball);
        return direction;
    }

    @Override
    public void moveBatTo(int y) {
        super.moveBatTo(y);
    }

}