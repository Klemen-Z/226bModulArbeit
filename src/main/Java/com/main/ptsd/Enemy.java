package com.main.ptsd;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Enemy extends Ship {
    private boolean l = false;
    private boolean r = true;
    private int wave;
    private int points;
    private int attackspeed;
    private int etos;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight()-100;
    private int width = (int) size.getWidth()-100;
    ArrayList<Enemy_Projectile> EProjectiles = new ArrayList<>();

    Enemy(int x, int y, int size, int health, int wave, int points, int attackspeed){
        setPoints(points);
        setWave(wave);
        setSize(size);
        setAttackspeed(attackspeed);
        setY(y);
        setX(x);
    }

    public void move(){
        int speed = 3;
        if(this.l) {
            setX(getX() - speed);
        } else if (this.r){
            setX(getX() + speed);
        }
    }

    public void shoot(){
        EProjectiles.add(new Enemy_Projectile(8, 1, getX() + 25, getY() + 50, 1));
    }

    public int getPoints() {
        return points;
    }

    public boolean getL(){
        return l;
    }

    public boolean getR(){
        return r;
    }

    public int getWave() {
        return wave;
    }
    public int getAttackspeed() {
        return attackspeed;
    }

    public int getEtos() {
        return etos;
    }

    public void setEtos(int etos) {
        this.etos = etos;
    }

    public void setAttackspeed(int attackspeed) {
        this.attackspeed = attackspeed;
    }
    public void setL(boolean l){
        this.l = l;
    }

    public void setR(boolean r){
        this.r = r;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void hit(boolean t){
        if (t){
            EProjectiles.clear();
        }
    }
    public void setWave(int wave) {
        this.wave = wave;
    }
}
