package com.example.tom.cars;

import android.graphics.Canvas;

/**
 * Description here.
 *
 * @author 630022892
 * @since 24/11/2015
 * @version 1.0
 */
public class Board {
    Vector2d s;

    public Board() {
        s = new Vector2d();
        s.set(200, 200);
    }

    public void draw(Canvas c) {
        c.drawRect(s.x, 0, s.x+60, s.y+1000, GameModel.paintBlue);
    }
}
