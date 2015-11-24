package com.example.tom.cars;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
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
    CopyOnWriteArrayList<Obstacle> obstacles;
    int nSprites = 1;
    int score;
    int timeElapsed = 0;

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

    public void update(Rect rect, int delay) {
        // check that the drawing rectangle is valid
        if (rect.width() <= 0 || rect.height() <= 0) return;

        if (!gameOver()) {
            for (Obstacle o : obstacles) o.update(rect, this);
            timeElapsed += delay;
            if (timeElapsed >= 1500) {
                if (new Random().nextBoolean()) {
                    obstacles.add(new BadObstacle(paintGreen));
                } else {
                    obstacles.add(new GoodObstacle(paintBlue));
                }
                timeElapsed = 0;
            }

        }
    }

    /**
     * @return Boolean to show whether the game is over.
     */
    public boolean gameOver() {
        return timeElapsed < 0;
    }

    /**
     * Initialise obstacles list and score
     */
    public GameModel() {
        obstacles = new CopyOnWriteArrayList<>();
        score = 0;
    }
}
