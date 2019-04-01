package com.example.franciscogarzil.m8_game;

import java.util.Date;

public class Character {
    private String _name;
    private String _user;
    private int _masteryPoints;
    private int _powerStat;
    private float _asStat;
    private int _hpStat;
    private Date _dateCreate;


    public Character(String name, String user, int masteryPoints, int powerStat, float asStat, int hpStat, Date dateCreate) {
        this._name = name;
        this._user = user;
        this._masteryPoints = masteryPoints;
        this._powerStat = powerStat;
        this._asStat = asStat;
        this._hpStat = hpStat;
        this._dateCreate = dateCreate;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String user) {
        this._user = user;
    }

    public int get_masteryPoints() {
        return _masteryPoints;
    }

    public void set_masteryPoints(int masteryPoints) {
        this._masteryPoints = masteryPoints;
    }

    public int get_powerStat() {
        return _powerStat;
    }

    public void set_powerStat(int powerStat) {
        this._powerStat = powerStat;
    }

    public float get_asStat() {
        return _asStat;
    }

    public void set_asStat(int asStat) {
        this._asStat = asStat;
    }

    public int get_hpStat() {
        return _hpStat;
    }

    public void set_hpStat(int hpStat) {
        this._hpStat = hpStat;
    }

    public Date get_dateCreate() {
        return _dateCreate;
    }

    public void set_dateCreate(Date dateCreate) {
        this._dateCreate = dateCreate;
    }
}
