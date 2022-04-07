package com.main.ptsd;

import java.awt.*;

public abstract class Projectile {
    private int damage;
    private boolean hit;
    private int speed;
    private int size;
    private int x;
    private int y;

    Dimension SSize = Toolkit.getDefaultToolkit().getScreenSize();

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

    public boolean getHit(){
        return hit;
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

    public void setHit(boolean hit){
        this.hit = hit;
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

    public boolean outOfBoundsCheck(String bulet) {
        if (bulet.equals("e")) {
            return 0 < this.y && SSize.getHeight() < this.y;
        } else {
            return 0 > this.y;

        }
    }
}
