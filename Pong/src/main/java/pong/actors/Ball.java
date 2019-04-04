package pong.actors;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import pong.tools.ComponentCreator;

public class Ball implements Collisionable {

    private double yMovement;
    private double xMovement;
    private double xPosition;
    private double yPosition;
    private int radius;
    private int speed;

    public Ball(int radius, int x, int y, int speed) {
        this.speed = speed;
        this.radius = radius;
        this.yMovement = 0;
        this.xMovement = 0;
        this.moveTo(x, y);
    }

    @Override
    public Shape getSprite() {
        return ComponentCreator.createCircle(radius, this.xPosition, this.getyPosition());
    }

    public Circle getSpriteCircle() {
        return ComponentCreator.createCircle(radius, this.xPosition, this.getyPosition());
    }

    public Point2D getPosition() {
        return new Point2D(xPosition, yPosition);
    }

    public Point2D getMovement() {
        return new Point2D(xMovement, yMovement);
    }

    public void setMovement(Point2D movement) {
        this.yMovement = movement.getY();
        this.xMovement = movement.getX();
    }

    public void speedUp(double multiplier) {
        this.setMovement(new Point2D(this.xMovement * multiplier, this.yMovement * multiplier));
    }

    public void move(int miny, int maxy) {
        this.moveTo(this.xPosition + this.xMovement, this.yPosition + this.yMovement);
        if (this.yPosition + 35 >= maxy || this.yPosition <= miny) {
            this.mirrorYMovement();
        }

    }

    public void moveTo(double x, double y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public void mirrorYMovement() {
        this.yMovement *= -1;
    }

    public void mirrorXMovement() {
        this.xMovement *= -1;
    }

    public double getYMovement() {
        return this.yMovement;
    }

    public double getXMovement() {
        return this.xMovement;
    }

    public double getxPosition() {
        return xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public int getRadius() {
        return radius;
    }

    public int getSpeed() {
        return speed;
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
        Circle thisSprite = this.getSpriteCircle();
        Shape collisionArea = Shape.intersect(thisSprite, other.getSprite());
        if (collisionArea.getBoundsInLocal().getWidth() != -1) {
            if (other.getClass() == Bat.class) {
                Bat bat = (Bat) other;
                if ((this.xMovement > 0 && bat.getxPosition() > thisSprite.getCenterX() - 20) || (this.xMovement < 0 && bat.getxPosition() < thisSprite.getCenterX() + 20)) {
                    this.mirrorXMovement();
                }
                this.checkChangeYDirection(bat);
            }
            return true;
        }
        return false;
    }

    public void checkChangeYDirection(Bat bat) {
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

    public int inGoal(int leftGoal, int rightGoal) {
        if (this.xPosition <= leftGoal) {
            return -1;
        } else if (this.xPosition >= rightGoal) {
            return 1;
        }
        return 0;
    }
}
