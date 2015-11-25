package com.example.tom.cars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
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

    static String tag = "Character View: ";
    public void onDraw(Canvas g) {
        // get the model
        GameModel model = controller.getModel();

        // Set dimensions for characters
        model.board.setDimensions(this);
        Character.setDimensions(this);

        Character.board = model.board;

        model.board.draw(g);
        List<Character> characters = model.characters;
        // System.out.println(tag + "onDraw: " + sprites.get(0).v + " : " + sprites.get(0).s);

        for (Character o : characters) {
            o.draw(g);
        }

        Drawable d = getResources().getDrawable(R.drawable.car, null);
        model.car.setImage(d);
        model.car.draw(g);

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
