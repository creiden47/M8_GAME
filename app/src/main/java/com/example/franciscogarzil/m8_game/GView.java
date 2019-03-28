package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.DisplayMetrics;
import android.view.View;

public class GView extends View implements SensorEventListener{
    private Context context = null;
    private float posX, posY, gyroscopeX, gyroscopeY, gyroscopeZ, gravitiyX, gravitiyY, gravitiyZ, accelX, accelY, accelZ;
    int width, height;
    private Paint paint = new Paint();
    private Bitmap bitmapBall;

    public GView(Context context) {
        super(context);
        this.context = context;
        paint.setColor(Color.RED);
        paint.setTextSize(40f);
        paint.setAntiAlias(true);
        paint.setDither(true);
        bitmapBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizard); //Get ball image
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float newPosX, newPosY;
            newPosX = posX - (int) sensorEvent.values[0];
            newPosY = posY + (int) sensorEvent.values[1];

            if ((newPosX <  width - 100) && (newPosX > 0)){
                posX = newPosX;
            }
            if ((newPosY <  height - 250) && (newPosY > 0)){
               posY = newPosY;
            }

            accelX = (int) sensorEvent.values[0] * 90 / 10;
            accelY = (int) sensorEvent.values[1] * 90 / 10;
            accelZ = (int) sensorEvent.values[2] * 90 / 10;
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeX = (int) sensorEvent.values[0];
            gyroscopeY = (int) sensorEvent.values[1];
            gyroscopeZ = (int) sensorEvent.values[2];
        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravitiyX = (int) sensorEvent.values[0];
            gravitiyY = (int) sensorEvent.values[1];
            gravitiyZ = (int) sensorEvent.values[2];
        }

        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBall, posX, 0 ,null);
    }
}
