package com.example.tom.cars;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class GameModel {
    ArrayList<Obstacle> obstacles;
    int nSprites = 1;
    int score;
    int timeRemaining = 600000;

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
            for (Obstacle o : obstacles) o.update(rect);
            timeRemaining -= delay;
        }
    }

    public boolean gameOver() {
        return timeRemaining <= 0;
    }

    public GameModel() {
        System.out.println("Bubble GameModel: GameModel()");
        initSprites();
        score = 0;
        System.out.println("Bubble GameModel:  finished in ()");
    }

    public void click(float x, float y) {
        for (Obstacle o : obstacles) {
            if (o.contains(x, y)) {
                score += o.getScore();
                o.reSpawn();
                return;
            }
        }
    }

    void initSprites() {
        obstacles = new ArrayList<>();
        for (int i = 0; i < nSprites; i++) {
            Paint p = i % 2 == 0 ? paintBlue : paintGreen;
            obstacles.add(new Obstacle(p));
        }
    }
}
