package BouncingBalls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Application {

    public static Circle circle;
    public static Pane canvas;
    public final double standardGravity = 0.5;
    public double gravity;
    public double sideGravity;
    private double friction_bounce = 1.3;
    private double friction_roll = 1.009;
    public ArrayList<Ball> balls;



    @Override
    public void start(final Stage primaryStage) {

        canvas = new Pane();
        canvas.setFocusTraversable(true);

        final Scene scene = new Scene(canvas, 800, 600);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        startGame(3);


        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {



            @Override
            public void handle(final ActionEvent t) {
                final Bounds bounds = canvas.getBoundsInLocal();
                for (int k = 0; k < balls.size(); k++){

                    balls.get(k)._circle.setLayoutX(balls.get(k)._circle.getLayoutX() + (balls.get(k).getDeltaX()));
                    balls.get(k)._circle.setLayoutY(balls.get(k)._circle.getLayoutY() + (balls.get(k).getDeltaY()));



                    final boolean atRightBorder = balls.get(k)._circle.getLayoutX() >= (bounds.getMaxX() - balls.get(k)._circle.getRadius());
                    final boolean atLeftBorder = balls.get(k)._circle.getLayoutX() <= (bounds.getMinX() + balls.get(k)._circle.getRadius());
                    final boolean atBottomBorder = balls.get(k)._circle.getLayoutY() >= (bounds.getMaxY() - balls.get(k)._circle.getRadius());
                    final boolean atTopBorder = balls.get(k)._circle.getLayoutY() <= (bounds.getMinY() + balls.get(k)._circle.getRadius());


                    if (atRightBorder || atLeftBorder) {
                        balls.get(k).setDeltaX((balls.get(k).getDeltaX()*-1)/friction_bounce);
                    }
                    else if (atBottomBorder || atTopBorder) {
                        balls.get(k).setDeltaY((balls.get(k).getDeltaY() * -1) / friction_bounce);
                    }
                    else{
                        balls.get(k).setDeltaY(balls.get(k).getDeltaY()+gravity);
                        balls.get(k).setDeltaX(balls.get(k).getDeltaX()+sideGravity);
                    }

                }

            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }




    public void startGame(int numBalls) {

        gravity = standardGravity;
        sideGravity = 0;
        balls = new ArrayList<Ball>();

        while (balls.size() < numBalls) {
            Ball temp = new Ball();
            balls.add(temp);
            canvas.getChildren().add(temp._circle);
        }



    }

    public void changeBallNum(int change) {
        if (change < 1) {
            if (balls.size() == 1) {
                return;
            } else {
                canvas.getChildren().remove(balls.get(balls.size() -1)._circle);
                balls.remove(balls.size()-1);
            }
        } else {
            if (balls.size() == 25) {
                return;
            } else {
                Ball t = new Ball();
                canvas.getChildren().add(t._circle);
                balls.add(t);
            }
        }
    }

    private class KeyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent keyEvent) {

            switch (keyEvent.getCode()) {
                case LEFT:
                    sideGravity = -1 * standardGravity;
                    gravity = 0;
                    break;
                case RIGHT:
                    gravity = 0;
                    sideGravity = standardGravity;
                    break;
                case UP:
                    sideGravity = 0;
                    gravity = -1 * standardGravity;
                    break;
                case DOWN:
                    sideGravity = 0;
                    gravity = standardGravity;
                    break;
                case A:
                    changeBallNum(1);
                    break;
                case R:
                    changeBallNum(-1);
                    break;
                default:
                    break;

            }
        }
    }


    public static void main(final String[] args) {
        launch(args);
    }
}


