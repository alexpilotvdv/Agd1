package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ship {
    protected float x; // координаты
    protected float y;
    protected int lenght; // размер
    protected int weight;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    public Ship(Context context) {
        bitmapId = R.drawable.agdfon; // определяем начальные параметры
        lenght=700;
        weight=1400;
        size = 540;
        x=7;
        y=AgdView.maxY - size - 1;
        speed = (float) 10;
         // инициализируем корабль
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        //bitmap = Bitmap.createScaledBitmap(
        //        cBitmap, (int)(size * AgdView.unitW), (int)(4 * size * AgdView.unitH), false);
       // cBitmap.recycle();
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, lenght, weight, false);
        cBitmap.recycle();

    }


    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(MainActivity.isLeftPressed ){
            y -= speed;

        }
       // if(MainActivity.isRightPressed && y <= AgdView.maxY - 5){
       //     y += speed;
      //  }
        if(MainActivity.isRightPressed && y <= 2000){
            y += speed;

        }
    }

    void drow(Paint paint, Canvas canvas){ // рисуем картинку
        Log.d("kordy", String.valueOf(y*AgdView.unitH));
        canvas.drawBitmap(bitmap, x*AgdView.unitW, y*AgdView.unitH, paint);
    }
}
