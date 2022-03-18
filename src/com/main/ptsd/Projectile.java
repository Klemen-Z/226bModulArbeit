package com.main.ptsd;

public abstract class Projectile {
    private int damage;
    private int speed;
    private int size;
    private int x;
    private int y;

    Projectile(int speed, int damage, int x, int y, int size){
        setDamage(damage);
        setSpeed(speed);
        setX(x);
        setY(y);
        setSize(size);
    }

    public int getSpeed() {
        return speed;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDamage() {
        return damage;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
