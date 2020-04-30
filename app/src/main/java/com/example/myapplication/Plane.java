package com.example.myapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Plane {
    protected float x; // координаты
    protected float y;
    protected int lenght; // размер
    protected int weight;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка
    public final Matrix transform = new Matrix();
    public Plane(Context context) {
        bitmapId = R.drawable.planene; // определяем начальные параметры
        lenght=AgdView.horW - 10;
        weight=lenght/4;
        size = 540;
        x=7;
        y=(AgdView.ecrH/2) -100;
        speed = (float) 10;
        // инициализируем корабль
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
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
    void drowangel(Paint paint, Canvas canvas, float ang){ // рисуем картинку
        canvas.rotate(ang, x + lenght / 2, y + weight / 2);
        canvas.drawBitmap(bitmap, x, y, paint);
        //canvas.restore();
    }
    void drow(Paint paint, Canvas canvas){ // рисуем картинку

        canvas.drawBitmap(bitmap, x*AgdView.unitW, y*AgdView.unitH, paint);
    }


    /*

   public void rotate() {
        transform.preRotate(1, bmp.getWidth() / 2, bmp.getHeight() / 2);
    }


    public void onDraw(Canvas canvas, float x, float y) {
        rotate();
        this.x = x;
        this.y = y;

        canvas.save();
        canvas.translate(x, y);
        canvas.drawBitmap(bmp, transform, null);
        canvas.restore();
    }

    */
}
