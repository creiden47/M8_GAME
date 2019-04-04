package com.example.franciscogarzil.m8_game.PKG_CLASS;

import android.graphics.Bitmap;

public class Enemy {
    private Bitmap bitmapEnemy;
    private int posX, posY, move;
    private int width, heigth;
    private int hp;


    public Enemy(Bitmap bitmapEnemy, int posX, int posY, int move, int hp) {
        this.bitmapEnemy = bitmapEnemy;
        this.posX = posX;
        this.posY = posY;
        this.move = move;
        this.width = 100;
        this.heigth = 100;
        this.hp = hp;
    }

    public Bitmap getBitmapEnemy() {
        return bitmapEnemy;
    }

    public void setBitmapEnemy(Bitmap bitmapEnemy) {
        this.bitmapEnemy = bitmapEnemy;
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

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
