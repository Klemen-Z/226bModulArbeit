package com.main.ptsd;

import java.util.HashMap;

public class Enemy extends Ship {
    private int wave;
    private int points;

    HashMap<Integer, Enemy_Projectile> projectiles = new HashMap<>();

    Enemy(int x, int y, int size, int health, int wave, int points){
        setPoints(points);
        setWave(wave);
        setSize(size);
        setY(y);
        setX(x);
    }

    public void shoot(){
        boolean b = true;
        Integer i = 0;
        if (!projectiles.isEmpty()){
            while (b){
                i++;
                if (!projectiles.containsKey(i)){
                    b = false;
                }
            }
        }
        projectiles.put(i, new Enemy_Projectile(8, 1, getX(), getY(), 1));
    }

    public int getPoints() {
        return points;
    }

    public int getWave() {
        return wave;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
