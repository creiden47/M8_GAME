package com.example.franciscogarzil.m8_game;

public class attacks {
    String nameAttack;
    int dmgAttack;

    public attacks(){

    }

    public attacks(String nameAttack, int dmgAttack) {
        this.nameAttack = nameAttack;
        this.dmgAttack = dmgAttack;
    }

    public String getNameAttack() {
        return nameAttack;
    }

    public void setNameAttack(String nameAttack) {
        this.nameAttack = nameAttack;
    }

    public int getDmgAttack() {
        return dmgAttack;
    }

    public void setDmgAttack(int dmgAttack) {
        this.dmgAttack = dmgAttack;
    }
}
