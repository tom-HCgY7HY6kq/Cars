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
public class Obstacle {
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
        rad = minRadius + random.nextInt(randSize);
        s.set(0,0);
        v.set(velocityScale * (float) random.nextGaussian(),
                7);
    }

    public int getScore() {
        return fg == GameModel.paintGreen ? greenScore : blueScore;
    }

    public void update(Rect rect) {
        s.add(v);
        s.wrap(rect.width(), rect.height());
    }

    public boolean contains(float x, float y) {
        return s.dist(x,y) < rad;
    }

    public void draw(Canvas c) {
        c.drawCircle(s.x, s.y, rad, fg);
    }
}
