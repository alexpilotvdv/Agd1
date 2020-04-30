

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
    public static int horH = 0; //высота и ширина изображения фона
    public static int horW = 0;
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    public static int ecrW = 0; //ширина экрана и высота
    public static int ecrH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private float turnp;
    private float kh=0;
    private Ship ship;
    private Plane plane;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int currentTime = 0;
    private float pixgradus=1;
    private float ky=0;
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
            //ship.update();
           // plane.update();
        }
    }
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstTime) { // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                ecrW = surfaceHolder.getSurfaceFrame().width();
                ecrH = surfaceHolder.getSurfaceFrame().height();

                if (ecrW > ecrH) {
                   /* horW = ecrH + 170; //если ширина больше высоты, то ширина фона равна высоте
                    horH = ecrH * 4;
*/
                   horW=ecrH+170;
                   kh= (float) (2*horW*6/4);
                   horH= (int) kh;

                } else {
                  /*  horW = ecrW;
                    horH = ecrW * 4;
*/
                  horW=ecrW;
                  kh= (float) (2*horW*6/4);
                  horH= (int) kh;

                }
                pixgradus= (float) (0.7*(6*(float)horW/4)/90);
                ky=-30 -(horH-ecrH)/2;
                ship = new Ship(getContext()); // добавляем объекты
                plane=new Plane(getContext());
            }
            //рассчитаем угол поворота

            if(NastrActivity.fbtx>0){
                turnp=180-(NastrActivity.fbtx-NastrActivity.corx) ;
            }
            if(NastrActivity.fbtx<0){
                turnp=-(180+(NastrActivity.fbtx+NastrActivity.corx) );
            }

            try {
                canvas = surfaceHolder.lockCanvas(); // закрываем canvas
                canvas.drawColor(Color.BLACK); // заполняем фон чёрным
                //ship.drowxy(paint, canvas, 0, -30 -(horH-ecrH)/2+NastrActivity.fbty * 7);
                ship.drowxy(paint, canvas, 0, ky+NastrActivity.fbty * pixgradus);
                plane.drowangel(paint,canvas,turnp);
                // ship.drow(paint, canvas); // рисуем корабль
                surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
