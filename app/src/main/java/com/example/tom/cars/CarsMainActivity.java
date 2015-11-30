package com.example.tom.cars;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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
    // Drawable rectangle for the recording the drawable area of the screen
    Rect rect;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_cars_main);

        // Initialise view and model.
        model = new GameModel();
        view = (ObstacleView) findViewById(R.id.game);

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

        rect = new Rect(0, 0, view.getWidth(), view.getHeight());
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

    class GameThread extends Thread {
        boolean running = true;

        public void run() {
            Log.d("Game Thread", "Running Thread");
            while (running) {
                try {
                    rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                    getModel().update(rect, Constants.delay);
                    view.postInvalidate();

                    // Update the score.
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
