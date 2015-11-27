package com.example.tom.cars;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class GameModel {
    final Car car;
    CopyOnWriteArrayList<Character> characters;
    final Board board;
    int score;
    int timeElapsed = 0;
    private LaneManager manager;

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

    private boolean gameOver = false;

    /**
     * Initialise characters list and score
     */
    public GameModel() {
        characters = new CopyOnWriteArrayList<>();
        board = new Board();
        car = new Car();
        manager = new LaneManager();
        car.setLane(manager.getLane());
        score = 0;
    }

    public void update(Rect rect, int delay) {
        // check that the drawing rectangle is valid
        if (rect.width() <= 0 || rect.height() <= 0) return;

        if (!gameOver()) {
            for (Character o : characters) {
                o.update(rect, this);
                if (car.contains(o.s.x, o.s.y)) {
                    if (o.type.equals(ObstacleType.BAD)) {
                        gameOver = true;
                        System.out.println("GAME OVER!");
                    } else if (o.type.equals(ObstacleType.GOOD)) {
                        System.out.println("+1 Points!!");
                        o.delete(this);
                    }
                }
            }
            timeElapsed += delay;
            if (timeElapsed >= 1000) {
                if (new Random().nextBoolean()) {
                    characters.add(new BadObstacle(paintGreen));
                } else {
                    characters.add(new GoodObstacle(paintBlue));
                }
                timeElapsed = 0;
            }
            System.out.println(manager.getLane());
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
        } else {
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


}
