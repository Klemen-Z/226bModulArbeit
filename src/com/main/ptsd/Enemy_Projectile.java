package com.main.ptsd;

import java.awt.*;

public class Enemy_Projectile extends Projectile{

    Enemy_Projectile(int speed, int damage, int x, int y, int size) {
        super(speed, damage, x, y, size);
    }

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public void move(){
        int speed = 8;
        if (getY() - 25 < size.getHeight()){
            setY(getY() + speed);
        }
    }

    public boolean hitCheck(int px, int py){
        return this.getX() >= px && this.getX() <= px + 50 && this.getY() >= py && this.getY() <= py + 50;
    }
}
