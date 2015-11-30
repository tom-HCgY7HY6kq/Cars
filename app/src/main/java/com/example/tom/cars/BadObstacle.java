package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * An obstacle that the player is trying to avoid.
 * Square shaped.
 *
 * @author 630022892
 * @since 24/11/2015
 * @version 1.0
 */
public class BadObstacle extends Character {

    public BadObstacle(Paint fg) {
        this();
        this.fg = fg;
    }

    public BadObstacle() {
        s = new Vector2d();
        v = new Vector2d();
        this.type = ObstacleType.BAD;
        super.spawn();
    }

    /**
     * Bad characters are square. Square is drawn around a central vector
     * of s.
     *
     * @param c Canvas to draw the obstacle on
     */
    @Override
    public void draw(Canvas c) {
        c.drawRect(s.x-rad, s.y-rad, s.x+rad, s.y+rad, fg);
    }
}
