package com.example.franciscogarzil.m8_game;

import java.util.ArrayList;

public class base {
    int creepHP;
    String name;
    ArrayList<attacks> attacks = new ArrayList<attacks>();

    public base(){

    }

    public base(int creepHP, String name, ArrayList<com.example.franciscogarzil.m8_game.attacks> attacks) {
        this.creepHP = creepHP;
        this.name = name;
        this.attacks = attacks;
    }

    public int getCreepHP() {
        return creepHP;
    }

    public void setCreepHP(int creepHP) {
        this.creepHP = creepHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<com.example.franciscogarzil.m8_game.attacks> getAttacks() {
        return attacks;
    }

    public void setAttacks(ArrayList<com.example.franciscogarzil.m8_game.attacks> attacks) {
        this.attacks = attacks;
    }
}
