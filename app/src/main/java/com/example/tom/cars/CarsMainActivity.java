package com.example.tom.cars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
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
    // Drawable rectangle for recording the drawable area of the screen
    Rect rect;

    Button playButton;
    Button helpButton;
    TextView gameOver;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_cars_main);
        Log.d("Startup", "Content View set.");

        // Initialise view and model.
        model = new GameModel();
        view = (ObstacleView) findViewById(R.id.game);
        gameOver = (TextView) findViewById(R.id.gameOver);
        gameOver.setVisibility(View.INVISIBLE);
        Log.d("Startup", "Model and View initialised.");


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
        Log.d("Startup", "Swipe detection enables.");
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
        Log.d("Resume", "Activity resumed.");

        playButton = (Button) findViewById(R.id.play);
        helpButton = (Button) findViewById(R.id.help);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButton.setVisibility(View.INVISIBLE);
                helpButton.setVisibility(View.INVISIBLE);
                gameOver.setVisibility(View.INVISIBLE);
                model.resetScore();
                runner = new GameThread();
                runner.start();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(CarsMainActivity.this)
                        .setTitle("Help")
                        .setMessage("Dodge the square obstacles and try to " +
                                "collect as many circles as possible.")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing.
                            }
                        })
                        .show();
            }
        });
    }

    public void onPause() {
        super.onPause();
        Log.d("Pause", "Activity paused.");

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
            Log.d("Game Thread", "Running Thread...");
            while (running) {
                try {
                    rect = new Rect(0, 0, view.getWidth(), view.getHeight());
                    running = getModel().update(rect, Constants.delay);
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

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resetGame();
                }
            });
        }

        private void resetGame() {
            model = new GameModel();
            LaneManager manager = model.getLaneManager();
            SwipeGestureDetector swipey = new SwipeGestureDetector(manager);
            final GestureDetector gestureDetector =
                    new GestureDetector(CarsMainActivity.this, swipey);
            OnTouchListener gestureListener = new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            };
            view.setOnTouchListener(gestureListener);
            running = true;
            gameOver.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
            helpButton.setVisibility(View.VISIBLE);
        }
    }
}
