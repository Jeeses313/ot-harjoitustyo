package pong.actors;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball implements Collisionable {

    private Circle sprite;
    private Point2D movement;
    private int speed;

    public Ball(int radius, int x, int y, int speed) {
        this.sprite = new Circle(radius);
        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
        this.speed = speed;
        this.movement = new Point2D(0, 0);
    }

    public Shape getSprite() {
        return this.sprite;
    }

    public void setSprite(Circle ball, int x, int y) {
        this.sprite = ball;
        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
        this.movement = new Point2D(0, 0);
    }

    public Point2D getMovement() {
        return movement;
    }

    public void setMovement(Point2D movement) {
        this.movement = movement;
    }

    public void speedUp(int multiplier) {
        this.movement = new Point2D(this.movement.getX() * multiplier, this.movement.getY() * multiplier);
    }

    public void move(int miny, int maxy) {
        this.sprite.setTranslateX(this.sprite.getTranslateX() + this.movement.getX());
        this.sprite.setTranslateY(this.sprite.getTranslateY() + this.movement.getY());
        if (this.sprite.getTranslateY() + 35 >= maxy || this.sprite.getTranslateY() <= 0) {
            this.mirrorYMovement();
        }

    }

    public void moveTo(int x, int y) {
        this.sprite.setTranslateX(x);
        this.sprite.setTranslateY(y);
    }

    public void mirrorYMovement() {
        this.movement = new Point2D(this.movement.getX(), this.movement.getY() * -1);
    }

    public void mirrorXMovement() {
        this.setMovement(new Point2D(-1 * this.movement.getX(), this.movement.getY()));
    }

    public double getYMovement() {
        return this.movement.getY();
    }

    public double getXMovement() {
        return this.movement.getX();
    }

    public void setSize(int size) {
        this.sprite.setTranslateX(size);
        this.sprite.setTranslateY(size);
    }

    public void randomMovement() {
        int newMovement = new Random().nextInt(4);
        int x = speed;
        int y = speed;
        if (newMovement == 0) {
            x *= -1;
        } else if (newMovement == 1) {
            y *= -1;
        }
        if (x == 2) {
            x *= -1;
            y *= -1;
        }
        this.setMovement(new Point2D(x, y));
    }

    @Override
    public boolean collision(Collisionable other) {
        Shape collisionArea = Shape.intersect(this.sprite, other.getSprite());
        if (collisionArea.getBoundsInLocal().getWidth() != -1) {
            if (other.getClass() == Bat.class) {
                Bat bat = (Bat) other;
                if((this.movement.getX() > 0 && bat.getSprite().getTranslateX() > this.sprite.getCenterX() - 20) || (this.movement.getX() < 0 && bat.getSprite().getTranslateX() < this.sprite.getCenterX() + 20)) {
                    this.mirrorXMovement();
                }
                if (this.getYMovement() > 0) {
                    if (bat.getLastMovement() < 0) {
                        this.mirrorYMovement();
                    }
                } else {
                    if (bat.getLastMovement() > 0) {
                        this.mirrorYMovement();
                    }
                }
            }
            return true;
        }
        return false;
    }

    public int inGoal(int leftGoal, int rightGoal) {
        if (this.sprite.getTranslateX() <= leftGoal) {
            return -1;
        } else if (this.sprite.getTranslateX() >= rightGoal) {
            return 1;
        }
        return 0;
    }
}
