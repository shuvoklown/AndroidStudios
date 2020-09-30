package com.example.game_shald3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    Paint drawGraphics = new Paint();
    Paint rectPoint = new Paint();
    int backgroundColor = Color.DKGRAY;
    Bitmap art1, art2;
    int art1X = 0, art1Y = 0, art1XSpeed = 5, art1YSpeed = 20;
    int art2X = 500, art2Y = 500, art2XSpeed = 45, art2YSpeed = 12;
    Rect art1Rect, art2Rect;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        this.setContentView(gameView);
        art1 = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        art2 = BitmapFactory.decodeResource(getResources(), R.drawable.chick);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    public class GameView extends SurfaceView implements Runnable {

        Thread ViewThread = null;
        SurfaceHolder holder;
        boolean threadRunning = true;

        public GameView(Context context) {
            super(context);
            holder = this.getHolder();
        }

        @Override
        public void run() {
            while (threadRunning == true) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }
                Canvas gameCanvas = holder.lockCanvas();
                art1Rect = new Rect(art1X, art1Y, art1X + art1.getWidth(), art1Y + art1.getHeight());
                art2Rect = new Rect(art2X, art2Y, art1Y + art2.getWidth(), art2Y + art2.getHeight());

                drawHere(gameCanvas);
                holder.unlockCanvasAndPost(gameCanvas);
            }
        }

        protected void drawHere(Canvas canvas) {
            drawGraphics.setAlpha(255);
            canvas.drawColor(backgroundColor);

            art1X += art1XSpeed*3;
            art1Y += art1YSpeed*3;
            art2X += art2XSpeed*2;
            art2Y += art2YSpeed*2;

            canvas.drawBitmap(art1, art1X, art1Y, drawGraphics);
            canvas.drawBitmap(art2, art2X, art2Y, drawGraphics);

            if (Rect.intersects(art1Rect, art2Rect)) {
                if (backgroundColor == Color.DKGRAY) {
                    backgroundColor = Color.WHITE;
                    art1XSpeed = art1XSpeed-12;
                    art1YSpeed = art1YSpeed-5;
                    art2XSpeed = art2XSpeed-10;
                    art2YSpeed = art2YSpeed-7;
                } else {
                    backgroundColor = Color.DKGRAY;
                }
            }

            if (art1X < 0 || art1X > (canvas.getWidth()-20)) {
                art1XSpeed = art1XSpeed * -1;
            }

            if (art1Y < 0 || art1Y > (canvas.getWidth()-20)) {
                art1YSpeed = art1YSpeed * -1;
            }

            if (art2X < 0 || art2X > (canvas.getWidth()-20)) {
                art2XSpeed = art2XSpeed * -1;
            }

            if (art2Y < 0 || art2Y > (canvas.getWidth()-20)) {
                art2YSpeed = art2YSpeed * -1;
            }
        }

        public void pause() {
            threadRunning = false;
            while (true) {
                try {
                    ViewThread.join();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                break;
            }
            ViewThread = null;
        }

        public void resume() {
            threadRunning = true;
            ViewThread = new Thread(this);
            ViewThread.start();
        }
    }
}
