package com.main.ptsd;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Ship {
    //various values for player
    private boolean l;
    private int health;
    private boolean r;

    //screen dimensions
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    private boolean shoot;
    ArrayList<Player_Projectile> PProjectiles = new ArrayList<>();

    Player(int x, int y, int health){
        setX(x);
        setY(y);
        setHealth(health);
    }

    //shoot method for player bullets
    public void shoot(){
        PProjectiles.add(new Player_Projectile(8, 1, getX() + 25, getY(), 1));
    }

    //movement for player (based on pressed button)
    public void move(){
        int speed = 8;
        if(this.l && getX()  > 0) {
            setX(getX() - speed);
        } else if (this.r && getX() < size.getWidth()-150){
            setX(getX() + speed);
        }
    }

    //getters and setter for all values
    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void setL(boolean l) {
        this.l = l;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean getL() {
        return this.l;
    }

    public boolean getR() {
        return this.r;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}