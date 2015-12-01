package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

/**
 * Defines a character in the game.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public abstract class Character {
    static int boardWidth;
    static int boardHeight;
    static Board board;
    static int rad;
    ObstacleType type;
    Vector2d s, v;
    Paint paint;

    public Character() {
        s = new Vector2d();
        v = new Vector2d();
    }

    public static void setDimensions(View view) {
        boardWidth = view.getWidth();
        boardHeight = view.getHeight();
        rad = boardWidth / 20;
    }

    /**
     * Spawns a character in a random lane with a downward minVelocity of 10.
     */
    public void spawn() {
        Random r = new Random();
        final int downVelocity = Constants.minVelocity + r.nextInt(30);

        Lane lane = Lane.getRandomLane();
        int position = board.getLaneCenter(lane);
        s.set(position,0);
        v.set(0, downVelocity);
    }

    /**
     * Removes this character from the game model.
     *
     * @param model Current model
     */
    public void delete(GameModel model) {
        model.obstacles.remove(this);
    }

    public void update(Rect rect, GameModel gameModel) {
        s.add(v);
        if (s.isAtEnd(rect.width(), rect.height())) {
            this.delete(gameModel);
        }
    }

    public boolean contains(float x, float y) {
        int xN = Math.round(x);
        int yN = Math.round(y);

        return contains(xN, yN);
    }

    public boolean contains(int x, int y) {
        return s.dist(x,y) < rad;
    }

    /**
     * Drawing is decided with each type of character.
     *
     * @param c Canvas to draw on
     */
    public void draw(Canvas c) {
    }
}
