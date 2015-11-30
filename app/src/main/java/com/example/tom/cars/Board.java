package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * A class that defines the drawing of the game board.
 *
 * @author 630022892
 * @since 24/11/2015
 * @version 1.0
 */
public class Board {
    static Paint background;
    static Paint foreground;

    // Define the paint colours
    static {
        background = new Paint();
        background.setColor(Color.LTGRAY);
        background.setStyle(Paint.Style.FILL);
        background.setAntiAlias(true);

        foreground = new Paint();
        foreground.setColor(Color.DKGRAY);
        foreground.setStyle(Paint.Style.FILL);
        foreground.setAntiAlias(true);
    }

    final int lineThickness;
    final int laneThickness;
    int width;
    int height;

    public Board() {
        lineThickness = 20;
        laneThickness = 200;
    }

    public void setDimensions(View view) {
        width = view.getWidth();
        height = view.getHeight();
    }

    public void draw(Canvas c) {
        int midWidth = width/2;

        // Draw the background
        c.drawRect(0, 0, width, height, background);

        // Draw lane lines
        int midL = midWidth-laneThickness/2;
        int midR = midWidth+laneThickness/2;
        int left = midWidth-3*laneThickness/2 - lineThickness;
        int right = midWidth+3*laneThickness/2 + lineThickness;
        c.drawRect(0, 0, left, height, foreground);
        c.drawRect(midL, 0, midL+lineThickness, height, foreground);
        c.drawRect(midR, 0, midR+lineThickness, height, foreground);
        c.drawRect(right, 0, width, height, foreground);
    }

    /**
     * @param lane The required lane to find the center of.
     * @return The pixel which is the center of the give Lane
     */
    public int getLaneCenter(Lane lane) {
        int val;
        switch (lane) {
            case LEFT:
                val = (width/2) - (laneThickness + lineThickness);
                return val;
            case MIDDLE:
                val = (width/2);
                return val;
            case RIGHT:
                val = (width/2) + (laneThickness + lineThickness);
                return val;
            default:
                return 0;
        }
    }
}
