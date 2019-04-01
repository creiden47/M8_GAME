package com.example.franciscogarzil.m8_game;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class inGame extends AppCompatActivity {

    private GView gView = null;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;
    private MyTimerTask task;
    private Timer timer;
    private int interval=100;
    public static int minutes = 0, seconds = 0, ds = 0;
    private Context context;
    public static ArrayList<Enemy> alEnemies;
    public static ArrayList<FireBall> alFireballs;
    public static ArrayList<Rect> alRectEnemies;
    public static ArrayList<Rect> alRectFireballs;
    public static int current_hp;
    public static boolean game_over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gView = new GView(this);
        setContentView(gView);
        alEnemies = new ArrayList<Enemy>();
        alFireballs = new ArrayList<FireBall>();
        alRectEnemies = new ArrayList<Rect>();
        alRectFireballs = new ArrayList<Rect>();
        current_hp = MainMenu.currentCharacter.get_hpStat();
        game_over = false;
        StartSensor();
        startTimer();
    }

    @Override
    public void onBackPressed () {

    }
    private void StartSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume(){
        super.onResume();
        sensorManager.registerListener((SensorEventListener) gView,
                accelerometer, SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gView);
    }

    class MyTimerTask extends TimerTask {
        Handler handler = new Handler();
        Context context;
        public MyTimerTask(Context context)
        {
            this.context=context;}
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    ((inGame) context).taskTimer();
                }});
        }
    }

    public void taskTimer() {
        float attackSpeed = 0;
        try {
            if (!game_over){
                ds++;
                int posXAleatory=(int)Math.floor(Math.random()*(130-GView.width-90)+GView.width-90);
                if ((ds % 10) == 0){
                    seconds++;
                    Bitmap bitmapEnemy = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy1);
                    int moveAleatory=(int)Math.floor(Math.random()*(5-8)+8);
                    Enemy enemy = new Enemy(bitmapEnemy, posXAleatory, 0, moveAleatory);
                    alEnemies.add(enemy);
                    Rect enemyRect = new Rect();
                    alRectEnemies.add(enemyRect);
                }
                if (seconds == 60) {
                    minutes++;
                    seconds = 00;
                } else if (minutes == 60) {
                    minutes = 0;
                    seconds = 0;
                }


                attackSpeed = MainMenu.currentCharacter.get_asStat() * 10;

                if (ds % attackSpeed == 0) {
                    Bitmap bitmapFireBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireball);
                    FireBall fireBall = new FireBall(bitmapFireBall, GView.posX + 30, GView.height - 300);
                    alFireballs.add(fireBall);
                    Rect fireBallRect = new Rect();
                    alRectFireballs.add(fireBallRect);
                }

            } else {
                int numPoints = 0;
                if (minutes > 1){
                    if (minutes > 1 && minutes < 3){
                        numPoints = 1;
                    } else if (minutes > 3 && minutes < 7){
                        numPoints = 2;
                    } else if (minutes > 7){
                        numPoints = 3;
                    }
                    Toast.makeText(getApplicationContext(), ConnectorSQL.incrementMasteryPoints(MainMenu.currentCharacter.get_name(), numPoints), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You didn't earn any mastery point", Toast.LENGTH_LONG).show();
                }

                //Back to menu:
                Intent myIntent = new Intent(inGame.this, CharacterPage.class);
                inGame.this.startActivity(myIntent);

            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void startTimer(){
        context = this;
        task=new MyTimerTask(context);
        timer = new Timer("myTimer");
        timer.schedule(task, 0, interval);
    }


}
