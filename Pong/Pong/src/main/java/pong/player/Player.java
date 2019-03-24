
package pong.player;

import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.actors.Bat;


public abstract class  Player {

    private Bat bat;
    
    public Player(int x, int y, int movementSpeed) {
        this.bat = new Bat(x, y, movementSpeed);
    }

    public Bat getBat() {
        return bat;
    }
    
   public KeyCode getUp() {
        return KeyCode.WINDOWS;
    }

    public KeyCode getDown() {
        return KeyCode.WINDOWS;
    }
    
    public int moveBat(int direction, int miny, int maxy, Ball ball) {
        this.bat.move(direction, miny, maxy);
        return direction;
    }
    
    public void moveBatTo(int y) {
        this.bat.moveTo(y);
    }
    
    
}
