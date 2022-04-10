package com.main.ptsd;

public class Enemy_Projectile extends Projectile{

    Enemy_Projectile(int speed, int damage, int x, int y, int size) {
        super(speed, damage, x, y, size);
    }

    //movement downwards
    public void move(){
        int speed = 8;
        setY(getY() + speed);
    }

    //hit checking on projectile
    public boolean hitCheck(int px, int py, int size){
        return this.getX() >= px && this.getX() <= px + size && this.getY() >= py && this.getY() <= py + size;
    }
}
