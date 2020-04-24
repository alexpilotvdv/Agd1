package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Ship {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    public Ship(Context context) {
        bitmapId = R.drawable.spr; // определяем начальные параметры
        size = 5;
        x=7;
        y=AgdView.maxY - size - 1;
        speed = (float) 0.2;
         // инициализируем корабль
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * AgdView.unitW), (int)(size * AgdView.unitH), false);
        cBitmap.recycle();
    }


    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(MainActivity.isLeftPressed && y >= 0){
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
        canvas.drawBitmap(bitmap, x*AgdView.unitW, y*AgdView.unitH, paint);
    }
}
