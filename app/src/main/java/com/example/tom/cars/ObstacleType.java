package com.example.tom.cars;

/**
 * Description here.
 *
 * @author 630022892
 * @since 25/11/2015
 * @version 1.0
 */
public enum ObstacleType {
    GOOD(1), BAD(0);

    private int score;

    ObstacleType(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
