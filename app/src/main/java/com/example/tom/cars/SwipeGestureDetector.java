package com.example.tom.cars;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * A detector to detect swipes on the screen.
 *
 * @author 630022892
 * @since 27/11/2015
 * @version 1.0
 */
class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
    private LaneManager manager;

    SwipeGestureDetector(LaneManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                             float velocityX, float velocityY) {

        switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
            case 1:
                Log.d("Gesture Lane", "up");
                return true;
            case 2:
                Log.d("Gesture Lane", "left");
                manager.goLeft();
                return true;
            case 3:
                Log.d("Gesture Lane", "down");
                return true;
            case 4:
                Log.d("Gesture Lane", "right");
                manager.goRight();
                return true;
        }
        return false;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    /**
     * Calculates the slope of a swipe give the start and end coordinates of
     * the swipe.
     *
     * @param x1 Start x coordinate.
     * @param y1 Start y coordinate.
     * @param x2 End x coordinate.
     * @param y2 End y coordinate.
     * @return Slope of the swipe.
     */
    private int getSlope(float x1, float y1, float x2, float y2) {

        Double angle = Math.toDegrees(Math.atan2(y1 - y2, x2 - x1));
        if (angle > 45 && angle <= 135)
            // up
            return 1;
        if (angle >= 135 && angle < 180 || angle < -135 && angle > -180)
            // left
            return 2;
        if (angle < -45 && angle>= -135)
            // down
            return 3;
        if (angle > -45 && angle <= 45)
            // right
            return 4;
        return 0;
    }
}
