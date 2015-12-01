package com.example.tom.cars;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class Vector2d {
    // of course, also require the methods for adding
    // to these vectors

    public float x, y;

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        // main method for convenient testing
        Vector2d v = new Vector2d(10, 10);
        System.out.println(v.mag());
        v.normalise();
        System.out.println(v.mag());
    }

    public static float sqr(float x) {
        return x * x;
    }

    public boolean equals(Object o) {
        if (o instanceof Vector2d) {
            Vector2d v = (Vector2d) o;
            return x == v.x && y == v.y;
        } else {
            return false;
        }
    }

    public Vector2d copy() {
        return new Vector2d(x, y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + " : " + y;
    }

    public Vector2d add(Vector2d v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public boolean isAtEnd(float w, float h) {
        return x>w || y>h;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public float mag() {
        return (float) Math.sqrt(sqr(x) + sqr(y));
    }

    public float dist(float vx, float vy) {
        return (float) Math.sqrt(sqr(x - vx) + sqr(y - vy));
    }

    public void normalise() {
        double mag = mag();
        x /= mag;
        y /= mag;
    }
}
