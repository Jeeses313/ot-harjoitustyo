package pong.domain.actors;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import pong.domain.player.Player;
import pong.tools.ComponentCreator;

public class Powerup implements Collisionable {

    private int x;
    private int y;
    private boolean shown;
    private int type;
    private long activationTime;
    private long spawnTime;
    private Collisionable target;

    public Powerup(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.shown = true;
        this.type = type;
        this.activationTime = -1;
        this.spawnTime = System.currentTimeMillis();
    }

    public void activate(Player player1, Player player2, Ball ball) {
        this.activationTime = System.currentTimeMillis();
        this.shown = false;
        if (this.type == 0 || this.type == 1) {
            this.setTarget(player1, player2, ball);
            if (type == 0) {
                this.changeSize(0.5);
            } else {
                this.changeSize(1.5);
            }
        } else if (this.type == 2 || this.type == 3) {
            this.setTarget(player1, player2, ball);
            speedChange();
        } else {
            this.target = ball;
        }
    }

    private void setTarget(Player player1, Player player2, Ball ball) {
        if (ball.getXMovement() > 0) {
            this.target = player1.getBat();
        } else {
            this.target = player2.getBat();
        }
    }

    public void changeSize(Double size) {
        if (this.target.getClass() == Bat.class) {
            Bat targetBat = (Bat) target;
            targetBat.setSize(size);
        }
    }

    private void speedChange() {
        if (type == 2) {
            this.changeSpeed(0.5);
        } else if (type == 3) {
            this.changeSpeed(1.5);
        } else if (type < 5) {
            this.changeSpeed(0.75);
        } else {
            this.changeSpeed(1.25);
        }
    }

    public void changeSpeed(Double speed) {
        if (this.target.getClass() == Bat.class) {
            Bat targetBat = (Bat) target;
            int newSpeed = (int) (targetBat.getMovementSpeed() * speed);
            targetBat.setMovementSpeed(newSpeed);
        } else if (this.target.getClass() == Ball.class) {
            Ball targetBall = (Ball) target;
            targetBall.speedUp(speed);
        }
    }

    public void deactivate(Player player1, Player player2, Ball ball) {
        if (activationTime == -1) {
            return;
        }
        if (this.type == 0 || this.type == 1) {
            this.changeSize(1.0);
        } else if (this.type == 2 || this.type == 3) {
            if (this.type == 2) {
                this.changeSpeed(1 / 0.5);
            } else {
                this.changeSpeed(1 / 1.5);
            }
        } else {
            if (this.type < 5) {
                this.changeSpeed(1 / 0.75);
            } else {
                this.changeSpeed(1 / 1.25);
            }
        }
    }

    public Powerup despawn(Player player1, Player player2, Ball ball) {
        if (activationTime == -1) {
            if (Math.abs(System.currentTimeMillis() - this.spawnTime) > 15000) {
                return null;
            }
            return this;
        }
        if (Math.abs(System.currentTimeMillis() - this.activationTime) > 10000) {
            this.deactivate(player1, player2, ball);
            return null;
        }
        return this;
    }

    @Override
    public Shape getSprite() {
        if (shown) {
            if (this.type == 0 || this.type == 2) {
                return ComponentCreator.createDiamond(this.x, this.y, Color.RED);
            } else if (this.type == 1 || this.type == 3) {
                return ComponentCreator.createDiamond(this.x, this.y, Color.BLUE);
            } else {
                return ComponentCreator.createDiamond(this.x, this.y, Color.GREEN);
            }
        }
        return new Rectangle();

    }

    @Override
    public boolean collision(Collisionable other) {
        Shape thisSprite = this.getSprite();
        Shape collisionArea = Shape.intersect(thisSprite, other.getSprite());
        if (collisionArea.getBoundsInLocal().getWidth() != -1) {
            return true;
        }
        return false;
    }

}
