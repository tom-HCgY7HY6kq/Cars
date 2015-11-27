package com.example.tom.cars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class ObstacleView extends View {
    CarsMainActivity controller;
    Board board;
    Car car;


    static String tag = "Character View: ";
    public void onDraw(Canvas g) {
        // get the model
        GameModel model = controller.getModel();
        this.board = model.board;
        this.car = model.car;

        // Set dimensions for characters
        board.setDimensions(this);
        Character.setDimensions(this);

        Character.board = board;

        board.draw(g);
        List<Character> characters = model.characters;
        // System.out.println(tag + "onDraw: " + sprites.get(0).v + " : " + sprites.get(0).s);

        for (Character o : characters) {
            o.draw(g);
        }


        Drawable d = ContextCompat.getDrawable(controller, R.drawable.car);
        car.setImage(d);
        car.draw(g);

    }

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
}
