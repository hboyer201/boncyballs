package BouncingBalls;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Ball {
    private double loc_x;
    private double loc_y;
    private double deltaX;
    private double deltaY;
    private double velocity_x;
    private double velocity_y;
    public Circle _circle;

    public Ball(){
        deltaX = 5 * Math.random();
        deltaY = 5 * Math.random();
        loc_x = Math.random() * 400;
        loc_y = Math.random() * 100;
        double radius = Math.random()*15;
        _circle = new Circle(radius, Color.BLUE);
        _circle.relocate(loc_x, loc_y);
    }


    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

}
