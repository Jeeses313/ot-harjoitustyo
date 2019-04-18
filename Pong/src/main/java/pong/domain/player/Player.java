package pong.domain.player;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import pong.domain.actors.Ball;
import pong.domain.actors.Bat;

public abstract class Player {

    private Bat bat;

    public Player(int x, int y, int movementSpeed, Color color) {
        this.bat = new Bat(x, y, movementSpeed, color);
    }

    public Bat getBat() {
        return bat;
    }

    public abstract KeyCode getUp();

    public abstract KeyCode getDown();

    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        this.bat.move(direction, miny, maxy);
        return direction;
    }

    public void moveBatTo(int y) {
        this.bat.moveTo(y);
    }

}
