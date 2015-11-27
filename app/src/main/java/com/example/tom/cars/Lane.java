package com.example.tom.cars;

import java.util.Random;

/**
 * Description here.
 *
 * @author 630022892
 * @since 27/11/2015
 * @version 1.0
 */
public enum Lane {
    LEFT, MIDDLE, RIGHT;

    private static final Lane[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Lane getRandomLane()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
