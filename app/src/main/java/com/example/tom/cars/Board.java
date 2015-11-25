package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Color;
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
    static Paint background;
    static Paint foreground;

    public Board() {}

    static {
        background = new Paint();
        background.setColor(Color.LTGRAY);
        background.setStyle(Paint.Style.FILL);
        background.setAntiAlias(true);

        foreground = new Paint();
        foreground.setColor(Color.BLACK);
        foreground.setStyle(Paint.Style.FILL);
        foreground.setAntiAlias(true);
    }

    public void setDimensions(View view) {
        width = view.getWidth();
        height = view.getHeight();
    }

    public void draw(Canvas c) {
        int midWidth = width/2;
        int lineThickness = 20;

        c.drawRect(0, 0, width, height, background);

        c.drawRect(midWidth, 0, midWidth+lineThickness, height, foreground);
    }
}
