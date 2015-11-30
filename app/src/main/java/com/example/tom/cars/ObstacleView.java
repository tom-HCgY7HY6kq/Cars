package com.example.tom.cars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Class to hold the view of the game.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class ObstacleView extends View {
    CarsMainActivity controller;
    Board board;
    Car car;


    public ObstacleView(Context context) {
        super(context);
        this.controller = (CarsMainActivity) context;
    }

    public ObstacleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.controller = (CarsMainActivity) context;
    }

    public ObstacleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.controller = (CarsMainActivity) context;
    }

    public void onDraw(Canvas g) {
        // get the model
        GameModel model = controller.getModel();
        this.board = model.board;
        this.car = model.car;
        List<Character> obstacles = model.obstacles;

        // Set dimensions for characters
        board.setDimensions(this);
        Character.setDimensions(this);

        Character.board = board;

        // Draw the board and characters.
        board.draw(g);
        for (Character o : obstacles) {
            o.draw(g);
        }
        Drawable d = ContextCompat.getDrawable(controller, R.drawable.car);
        car.setImage(d);
        car.draw(g);

    }
}
