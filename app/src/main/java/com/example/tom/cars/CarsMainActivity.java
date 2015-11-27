package com.example.tom.cars;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description here.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class CarsMainActivity extends Activity {

    ObstacleView view;
    GameModel model;
    // keep a reference to the running thread so
    // we can kill it from a lifecycle method
    GameThread runner;
    static String tag = "Bubble: ";
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    SwipeGestureDetector swipey;
    LaneManager manager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameModel();
        view = new ObstacleView(this);
        setContentView(view);
        System.out.println(tag + model);
        System.out.println(tag + view);

        // Setup view to detect swipes.
        manager = model.getLaneManager();
        SwipeGestureDetector swipey = new SwipeGestureDetector(manager);
        gestureDetector = new GestureDetector(this, swipey);
        gestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        view.setOnTouchListener(gestureListener);
    }

    public GameModel getModel() {
        return model;
    }

    public void onResume() {
        super.onResume();
        System.out.println("Bubble: onResume: ");
        rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        System.out.println("Bubble: onResume: " + rect);
        runner = new GameThread();
        runner.start();
    }

    public void onPause() {
        super.onPause();
        runner.running = false;
        try {
            runner.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Rect rect;

    class GameThread extends Thread {
        // have
        boolean running = true;

        public void run() {
            System.out.println(tag + "Running thread ...");
            while (running) {
                try {
                    rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                    // System.out.println("Bubble Thread: " + rect);
                    getModel().update(rect, Constants.delay);
                    view.postInvalidate();
                    Thread.sleep(Constants.delay);
                } catch (Exception e) {
                    System.out.println("BubbleThread: " + e);
                    e.printStackTrace();
                }
            }
        }
    }
}
