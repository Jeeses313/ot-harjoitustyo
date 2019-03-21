
package pong.player;

import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.actors.Bat;


public abstract class  Player {

    private Bat bat;
    
    public Player(int x, int y) {
        this.bat = new Bat(x, y);
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
    
    public int moveBat(int y, int miny, int maxy, Ball ball) {
        this.bat.move(y, miny, maxy);
        return y;
    }
    
    public void moveBatTo(int y) {
        this.bat.moveTo(y);
    }
    
    
}
