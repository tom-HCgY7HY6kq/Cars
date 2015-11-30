package com.example.tom.cars;

import java.util.Random;

/**
 * Defines the different possible lanes.
 *
 * @author 630022892
 * @since 27/11/2015
 * @version 1.0
 */
public enum Lane {
    LEFT, MIDDLE, RIGHT;

    private static final Lane[] LANES = values();
    private static final int SIZE = LANES.length;
    private static final Random RANDOM = new Random();

    public static Lane getRandomLane()  {
        return LANES[RANDOM.nextInt(SIZE)];
    }
}
