package com.example.tom.cars;

/**
 * Description here.
 *
 * @author 630022892
 * @since 27/11/2015
 * @version 1.0
 */
public class LaneManager {
    private Lane lane;

    public LaneManager() {
        this.lane = Lane.LEFT;
    }

    public void goLeft() {
        switch (lane) {
            case LEFT:
                break;
            case MIDDLE:
                lane = Lane.LEFT;
                break;
            case RIGHT:
                lane = Lane.MIDDLE;
                break;
        }
    }

    public void goRight() {
        switch (lane) {
            case LEFT:
                lane = Lane.MIDDLE;
                break;
            case MIDDLE:
                lane = Lane.RIGHT;
                break;
            case RIGHT:
                break;
        }
    }

    public Lane getLane() {
        return lane;
    }
}
