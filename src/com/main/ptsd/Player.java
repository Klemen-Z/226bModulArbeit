package com.main.ptsd;

import java.util.HashMap;

public class Player extends Ship {
    private String name;
    private boolean l;
    private boolean r;
    HashMap<Integer, Player_Projectile> PProjectiles = new HashMap<>();

    Player(int x, int y, int health, String name, int size){
        setX(x);
        setY(y);
        setSize(size);
        setName(name);
    }

    public void shoot(){
        boolean b = true;
        Integer i = 0;
        if (!PProjectiles.isEmpty()){
            while (b){
                i++;
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
        if(this.l) {
            setX(getX() - speed);
        } else if (this.r){
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
}