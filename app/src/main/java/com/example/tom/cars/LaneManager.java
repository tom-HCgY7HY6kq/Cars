package com.example.tom.cars;

/**
 * Keeps track of which lane the car should be in.
 *
 * @author 630022892
 * @since 27/11/2015
 * @version 1.0
 */
public class LaneManager {
    private Lane lane;

    /**
     * Starting lane for a new car is the left lane.
     */
    public LaneManager() {
        this.lane = Lane.LEFT;
    }

    /**
     * Moves the car one lane to the right.
     */
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

    /**
     * Moves the car one lane to the right.
     */
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
