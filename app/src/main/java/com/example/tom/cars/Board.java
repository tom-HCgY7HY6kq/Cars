package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Description here.
 *
 * @author 630022892
 * @since 24/11/2015
 * @version 1.0
 */
public class Board {
    int width;
    int height;
    Paint background;
    Paint foreground;

    public Board() {}

    public void setDimensions(View view) {
        width = view.getWidth();
        height = view.getHeight();
    }

    public void draw(Canvas c) {
        int midWidth = width/2;
        int lineThickness = 20;

        c.drawRect(midWidth, 0, midWidth+lineThickness, height, GameModel.paintBlue);
    }
}
