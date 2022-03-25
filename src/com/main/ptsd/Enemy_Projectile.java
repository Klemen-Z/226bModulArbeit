package com.main.ptsd;

public class Enemy_Projectile extends Projectile{

    Enemy_Projectile(int speed, int damage, int x, int y, int size) {
        super(speed, damage, x, y, size);
    }

    public void move(){
        int speed = 8;
        setY(getY() + speed);
    }
}
