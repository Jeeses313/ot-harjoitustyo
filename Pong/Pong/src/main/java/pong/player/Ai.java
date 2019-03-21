
package pong.player;

import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.actors.Bat;


public class Ai extends Player {

    public Ai(int x, int y) {
        super(x, y);
    }

    @Override
    public Bat getBat() {
        return super.getBat();
    }

    @Override
    public int moveBat(int y, int miny, int maxy, Ball ball) {
        return super.moveBat(y, miny, maxy, ball);  
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
