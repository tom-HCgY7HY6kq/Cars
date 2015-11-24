package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Description here.
 *
 * @author 630022892
 * @since 24/11/2015
 * @version 1.0
 */
public class BadObstacle extends Obstacle {

    public BadObstacle(Paint fg) {
        this();
        this.fg = fg;
    }

    public BadObstacle() {
        s = new Vector2d();
        v = new Vector2d();
        super.reSpawn();
    }

    /**
     * Bad obstacles are square.
     *
     * @param c Canvas to draw the obstacle on
     */
    @Override
    public void draw(Canvas c) {
        c.drawRect(s.x, s.y, s.x+60, s.y+60, fg);
    }
}