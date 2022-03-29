package com.main.ptsd;

import java.awt.*;
import java.util.HashMap;

public class Enemy extends Ship {
    private boolean l = false;
    private boolean r = true;
    private int wave;
    private int points;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight()-100;
    private int width = (int) size.getWidth()-100;
    HashMap<Integer, Enemy_Projectile> EProjectiles = new HashMap<>();

    Enemy(int x, int y, int size, int health, int wave, int points){
        setPoints(points);
        setWave(wave);
        setSize(size);
        setY(y);
        setX(x);
    }

    public void move(){
        int speed = 3;
        if(this.l && getX() > 0) {
            setX(getX() - speed);
        } else if (this.r && getX() < width-50){
            setX(getX() + speed);
        }
    }

    public void shoot(){
        boolean b = true;
        Integer i = 0;
        if (!EProjectiles.isEmpty()){
            while (b){
                i++;
                if (!EProjectiles.containsKey(i)){
                    b = false;
                }
            }
        }
        EProjectiles.put(i, new Enemy_Projectile(8, 1, getX(), getY(), 1));
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

    public void setL(boolean l){
        this.l = l;
    }

    public void setR(boolean r){
        this.r = r;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
