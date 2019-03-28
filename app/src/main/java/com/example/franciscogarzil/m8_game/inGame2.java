package com.example.franciscogarzil.m8_game;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

public class inGame2 extends AppCompatActivity {

    private GView gView = null;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;
    private Sensor gravity = null;
    private Sensor gyroscope = null;
    public static int x;
    public static int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gView = new GView(this);
        setContentView(gView);
        StartSensor();
    }

    private void StartSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        //gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener((SensorEventListener) gView,
                accelerometer, SensorManager.SENSOR_DELAY_GAME);
        /*sensorManager.registerListener((SensorEventListener) gView,
                gravity, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener((SensorEventListener) gView,
                gyroscope, SensorManager.SENSOR_DELAY_FASTEST);*/

    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gView);
    }


}
