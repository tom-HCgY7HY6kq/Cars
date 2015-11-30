package com.example.tom.cars;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * Main controller activity for the Cars game.
 *
 * @author 630022892
 * @since 19/11/2015
 * @version 1.0
 */
public class CarsMainActivity extends Activity {

    ObstacleView view;
    GameModel model;
    GameThread runner;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new GameModel();
        setContentView(R.layout.content_cars_main);

        view = (ObstacleView) findViewById(R.id.game);
//        setContentView(view);

        // Setup view to detect swipes.
        LaneManager manager = model.getLaneManager();
        SwipeGestureDetector swipey = new SwipeGestureDetector(manager);
        final GestureDetector gestureDetector =
                new GestureDetector(this, swipey);
        OnTouchListener gestureListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        view.setOnTouchListener(gestureListener);
    }

    /**
     * @return The current Game Model
     */
    public GameModel getModel() {
        return model;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Bubble: onResume: ");
        view.measure(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        rect = new Rect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
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

    // Drawable rectangle for the recording the drawable area of the screen
    Rect rect;

    class GameThread extends Thread {
        boolean running = true;

        public void run() {
            System.out.println("Running thread ...");
            while (running) {
                try {
                    rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                    // System.out.println("Bubble Thread: " + rect);
                    getModel().update(rect, Constants.delay);
                    view.postInvalidate();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView t = (TextView) findViewById(R.id.score);
                            t.setText(String.valueOf(getModel().getScore()));
                        }
                    });

                    Thread.sleep(Constants.delay);
                } catch (Exception e) {
                    System.out.println("BubbleThread: " + e);
                    e.printStackTrace();
                }
            }
        }
    }
}
