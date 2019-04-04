package com.example.franciscogarzil.m8_game.PKG_GAME;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.franciscogarzil.m8_game.PKG_ACTIVITIES.MainMenu;
import com.example.franciscogarzil.m8_game.PKG_CLASS.AudioPlayer;
import com.example.franciscogarzil.m8_game.R;

public class GView extends View implements SensorEventListener{
    private Context context = null;
    public static int posX;
    public static int width, height;
    private Paint paint = new Paint();
    Bitmap bitmapPlayer;
    public static int numberKills = 0;
    private int numberHits = 0;


    public GView(Context context) {
        super(context);
        this.context = context;

        paint.setColor(Color.RED);
        paint.setTextSize(40f);
        paint.setAntiAlias(true);
        paint.setDither(true);
        bitmapPlayer = BitmapFactory.decodeResource(context.getResources(), R.drawable.wizardplayer); //Get ball image
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        posX = width / 2 - 100;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int newPosX;
            newPosX = posX - (int) sensorEvent.values[0];

            if ((newPosX <  width - 100) && (newPosX > 0)){
                posX = newPosX;
            }

        }

        this.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        AudioPlayer au = new AudioPlayer();

        //Draw Player
        canvas.drawBitmap(bitmapPlayer, posX, height - 300 ,null);

        //Draw timer
        if (inGame.seconds < 10 || inGame.minutes < 10) {
            if (inGame.seconds < 10 && inGame.minutes >= 10) {
                canvas.drawText(inGame.minutes + ":0" + inGame.seconds,0,200, paint);
            } else if (inGame.minutes < 10 && inGame.seconds >= 10) {
                canvas.drawText("0" + inGame.minutes + ":" + inGame.seconds,0,200, paint);
            } else if (inGame.seconds < 10 && inGame.minutes < 10) {
                canvas.drawText("0" + inGame.minutes + ":0" + inGame.seconds,0,200, paint);
            }
        }else {
            canvas.drawText(inGame.minutes + ":" + inGame.seconds,0,200, paint);
        }



        //Draw Enemies
        for (int i = 0; i < inGame.alEnemies.size(); i++){
            inGame.alEnemies.get(i).setPosY(inGame.alEnemies.get(i).getPosY() + inGame.alEnemies.get(i).getMove());
            canvas.drawBitmap(inGame.alEnemies.get(i).getBitmapEnemy(), inGame.alEnemies.get(i).getPosX(), inGame.alEnemies.get(i).getPosY() ,null);

            Rect rectEnemy = new Rect((inGame.alEnemies.get(i).getPosX()), (inGame.alEnemies.get(i).getPosY()),
                    (inGame.alEnemies.get(i).getPosX())+(inGame.alEnemies.get(i).getWidth()),
                    (inGame.alEnemies.get(i).getPosY())+(inGame.alEnemies.get(i).getHeigth()));
            inGame.alRectEnemies.set(i, rectEnemy);
        }

        //Draw fireballs
        for (int i = 0; i < inGame.alFireballs.size(); i++){
            inGame.alFireballs.get(i).setPosY(inGame.alFireballs.get(i).getPosY() - 10);
            canvas.drawBitmap(inGame.alFireballs.get(i).getBitmapFireBall(), inGame.alFireballs.get(i).getPosX(), inGame.alFireballs.get(i).getPosY() ,null);

            Rect rectFireball = new Rect((inGame.alFireballs.get(i).getPosX()), (inGame.alFireballs.get(i).getPosY()),
                    (inGame.alFireballs.get(i).getPosX())+(inGame.alFireballs.get(i).getWidth()),
                    (inGame.alFireballs.get(i).getPosY())+(inGame.alFireballs.get(i).getHeigth()));

            inGame.alRectFireballs.set(i, rectFireball);
        }


        //Check if enemy touch a fireball
        for (int i = 0; i < inGame.alEnemies.size(); i++){
            for (int j = 0; j < inGame.alFireballs.size(); j++){
                if (inGame.alRectEnemies.get(i).intersect(inGame.alRectFireballs.get(j))){
                    inGame.alFireballs.remove(j);
                    inGame.alRectFireballs.remove(j);
                    inGame.alEnemies.get(i).setHp(inGame.alEnemies.get(i).getHp() - MainMenu.currentCharacter.get_powerStat());

                    if (inGame.alEnemies.get(i).getHp() <= 0){
                        au.play(getContext(), R.raw.enemysound);
                        inGame.alEnemies.remove(i);
                        inGame.alRectEnemies.remove(i);
                        numberKills++;
                    } else {
                        au.play(getContext(), R.raw.fireballsound);
                    }
                    break;
                }
            }
        }
        //Check if enemy touch base = lose hp;
        for (int i = 0; i < inGame.alEnemies.size(); i++){
            if (inGame.alEnemies.get(i).getPosY() > height - 300){

                inGame.alEnemies.remove(i);
                inGame.alRectEnemies.remove(i);
                inGame.current_hp = inGame.current_hp - 10;
                numberHits++;
                if (inGame.current_hp <= 0 ){
                    au.play(getContext(), R.raw.sound);
                    // Start new game:
                    inGame.game_over = true;
                }

            }
        }

        //Draw number of kills
        canvas.drawText(numberKills + " Kills",0,400, paint);
        canvas.drawText(numberHits + " Hits",0,600, paint);
    }


}
