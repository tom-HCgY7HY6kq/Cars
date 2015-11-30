package com.example.tom.cars;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Model for the game. Holds all of the game logic.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class GameModel {
    static Paint paintBlue, paintGreen;

    static {
        paintBlue = new Paint();
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.FILL);
        paintBlue.setAntiAlias(true);

        paintGreen = new Paint();
        paintGreen.setColor(Color.GREEN);
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setAntiAlias(true);
    }

    final Board board;
    final Car car;
    CopyOnWriteArrayList<Character> obstacles;
    int score;
    int timeElapsed = 0;
    private LaneManager manager;
    private boolean gameOver = false;


    /**
     * Initialise characters list and score
     */
    public GameModel() {
        obstacles = new CopyOnWriteArrayList<>();
        board = new Board();
        car = new Car();
        manager = new LaneManager();
        car.setLane(manager.getLane());
        score = 0;
    }

    /**
     * Game logic to update the gamestate.
     *
     * @param rect  Drawable area.
     * @param delay Time delay since last update.
     */
    public void update(Rect rect, int delay) {
        // check that the drawing rectangle is valid
        if (rect.width() <= 0 || rect.width() <= 0) {
            return;
        }

        if (!gameOver()) {
            for (Character o : obstacles) {
                o.update(rect, this);
                if (car.contains(o.s.x, o.s.y)) {
                    if (o.type.equals(ObstacleType.BAD)) {
                        gameOver = true;
                    } else if (o.type.equals(ObstacleType.GOOD)) {
                        score += 1;
                        o.delete(this);
                    }
                }
            }
            timeElapsed += delay;
            if (timeElapsed >= Constants.delayBetweenCars) {
                addRandomObstacle();
                timeElapsed = 0;
            }
            updateCarLane();
        } else {
        }
    }

    /**
     * Adds a random obstacle to the ArrayList of obstacles.
     */
    public void addRandomObstacle() {
        if (new Random().nextBoolean()) {
            obstacles.add(new BadObstacle(paintGreen));
        } else {
            obstacles.add(new GoodObstacle(paintBlue));
        }
    }

    /**
     * Updates the car lane according to the lane stored in the LaneManager
     */
    public void updateCarLane() {
        switch (manager.getLane()) {
            case LEFT:
                car.setLane(Lane.LEFT);
                break;
            case MIDDLE:
                car.setLane(Lane.MIDDLE);
                break;
            case RIGHT:
                car.setLane(Lane.RIGHT);
                break;
        }
    }


    /**
     * @return Boolean to show whether the game is over.
     */
    public boolean gameOver() {
        return gameOver;
    }

    public LaneManager getLaneManager() {
        return manager;
    }

    public int getScore() {
        return score;
    }
}
