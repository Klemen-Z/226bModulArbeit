package com.main.ptsd;

public class Player_Projectile extends Projectile{


    Player_Projectile(int speed, int damage, int x, int y, int size) {
        super(speed, damage, x, y, size);
    }

    public void move(){
        int speed = 8;
        setY(getY() - speed);
    }
}
