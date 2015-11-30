package com.example.tom.cars;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * The class to define the drawing of the car.
 *
 * @author 630022892
 * @since 25/11/2015
 * @version 1.0
 */
public class Car extends Character {
    Drawable image;
    Lane lane;

    public Car() {}

    public void setImage(Drawable d) {
        image = d;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    @Override
    public void draw(Canvas c) {
        int startX = board.getLaneCenter(lane);
        final int rawImageWidth = 404;
        final int rawImageHeight = 904;
        final int scaledWidth = 3 * board.laneThickness / 4;
        final int scaledHeight = scaledWidth * rawImageHeight/rawImageWidth;
        s.set(startX, boardHeight-scaledHeight-20);

        image.setBounds((int) s.x-scaledWidth/2, (int) s.y, (int) s.x+scaledWidth/2, boardHeight-20);

        image.draw(c);
    }

    @Override
    public boolean contains(int x, int y) {
        Rect bounds = image.getBounds();

        return bounds.contains(x, y);
    }
}
