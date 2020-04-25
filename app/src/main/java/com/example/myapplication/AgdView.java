

package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.StringBufferInputStream;

public class AgdView extends SurfaceView implements Runnable {
    public static int maxX = 540; // размер по горизонтали
    public static int maxY = 540; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int currentTime = 0;
    public AgdView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            control();
        }
    }
    public void stopthread(){
        this.gameRunning=false;
    }
    private void update() {
        if(!firstTime) {
            ship.update();
        }
    }
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;
                Log.d("alp", "w=" + String.valueOf(unitW));
                Log.d("alp", "h=" + String.valueOf(unitH));
                ship = new Ship(getContext()); // добавляем корабль
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным
            ship.drow(paint, canvas); // рисуем корабль
            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }
    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
