package com.example.tom.cars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

import static com.example.tom.cars.Constants.blueScore;
import static com.example.tom.cars.Constants.greenScore;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public abstract class Character {
    ObstacleType type;
    Vector2d s, v;
    final int rad = 45;
    Paint fg;
    static int boardWidth;
    static int boardHeight;
    static Board board;

    public static void setBoard(Board board) {
        Character.board = board;
    }

    public static void setDimensions(View view) {
        boardWidth = view.getWidth();
        boardHeight = view.getHeight();
    }

    static Random random = new Random();

    public Character() {
        s = new Vector2d();
        v = new Vector2d();
    }

    public void spawn() {
        int lane = random.nextInt(3);
        int position = board.getLaneCenter(lane);
        s.set(position,0);
        v.set(0,30);
    }

    public void delete(GameModel model) {
        model.characters.remove(this);
    }

    public int getScore() {
        return fg == GameModel.paintGreen ? greenScore : blueScore;
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

    public void draw(Canvas c) {
        // Decided whether the object is a square or a circle.
    }
}
