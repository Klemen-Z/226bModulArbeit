package com.main.ptsd;

public class Obstacle {
    private int size;
    private int health;
    private int x;
    private int y;

    Obstacle(int size, int health, int x, int y){
        setHealth(health);
        setSize(size);
        setX(x);
        setY(y);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSize() {
        return size;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getHealth() {
        return health;
    }
}
