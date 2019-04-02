package com.example.franciscogarzil.m8_game.PKG_CLASS;

import android.graphics.Bitmap;

public class FireBall {
    private Bitmap bitmapFireBall;
    private int posX, posY;
    private int width, heigth;

    public FireBall(Bitmap bitmapFireBall, int posX, int posY) {
        this.bitmapFireBall = bitmapFireBall;
        this.posX = posX;
        this.posY = posY;
        width = 30;
        heigth = 50;
    }

    public Bitmap getBitmapFireBall() {
        return bitmapFireBall;
    }

    public void setBitmapFireBall(Bitmap bitmapFireBall) {
        this.bitmapFireBall = bitmapFireBall;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }
}
