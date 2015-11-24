package com.example.tom.cars;

import android.content.Context;
import android.graphics.Canvas;
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

    static String tag = "Obstacle View: ";
    public void onDraw(Canvas g) {
        // get the model
        List<Obstacle> obstacles = controller.getModel().obstacles;
        // System.out.println(tag + "onDraw: " + sprites.get(0).v + " : " + sprites.get(0).s);
        for (Obstacle o : obstacles) {
            o.draw(g);
        }
        controller.getModel().board.draw(g);
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
