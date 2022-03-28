package com.main.ptsd;

import java.awt.*;
import java.util.HashMap;

public class Player extends Ship {
    private String name;
    private boolean l;
    private int health;
    private boolean r;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    private boolean shoot;
    HashMap<Integer, Player_Projectile> PProjectiles = new HashMap<>();

    Player(int x, int y, int health, String name, int size){
        setX(x);
        setY(y);
        setHealth(health);
        setSize(size);
        setName(name);
    }

    public void shoot(){
        boolean b = true;
        Integer i = 0;
        if (!PProjectiles.isEmpty()){
            while (b){
                i++;
                System.out.println(i);
                if (!PProjectiles.containsKey(i)){
                    b = false;
                }
            }
        }
        PProjectiles.put(i, new Player_Projectile(8, 1, getX(), getY(), 1));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void move(){
        int speed = 8;
        if(this.l && getX()  > 50) {
            setX(getX() - speed);
        } else if (this.r && getX() + 50 < size.getWidth()){
            setX(getX() + speed);
        }
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