package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.example.tom.cars.Constants.*;

import java.util.Random;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public abstract class Obstacle {
    Vector2d s, v;
    float rad;
    Paint fg;


    public Obstacle(Paint fg) {
        this();
        this.fg = fg;
    }

    static Random random = new Random();

    public Obstacle() {
        s = new Vector2d();
        v = new Vector2d();
        reSpawn();
    }

    public void reSpawn() {
        int position = 500 + random.nextInt(3)*200;
        rad = 45;
        s.set(position,0);
        v.set(0,15);
    }

    public void delete(GameModel model) {
        model.obstacles.remove(this);
    }

    public int getScore() {
        return fg == GameModel.paintGreen ? greenScore : blueScore;
    }

    public void update(Rect rect, GameModel gameModel) {
        s.add(v);
        if (s.needsWrapping(rect.width(), rect.height())) {
            System.out.println("wrapped");
            this.delete(gameModel);
        }
    }

    public boolean contains(float x, float y) {
        return s.dist(x,y) < rad;
    }

    public void draw(Canvas c) {
        // Decided whether the object is a square or a circle.
    }
}
