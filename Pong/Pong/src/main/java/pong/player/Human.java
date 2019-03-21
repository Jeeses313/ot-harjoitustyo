
package pong.player;

import javafx.scene.input.KeyCode;
import pong.actors.Ball;
import pong.actors.Bat;


public class Human extends Player{
    private KeyCode up;
    private KeyCode down;
    
    public Human(KeyCode up, KeyCode down, int x, int y) {
        super(x,y);
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
    public int moveBat(int y, int miny, int maxy, Ball ball) {
        super.moveBat(y, miny, maxy, ball); 
        return y;
    }

    @Override
    public void moveBatTo(int y) {
        super.moveBatTo(y);
    }

    
    
    
    
}
