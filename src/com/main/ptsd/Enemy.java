package com.main.ptsd;

public class Enemy extends Ship {
    private int wave;
    private int points;

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
