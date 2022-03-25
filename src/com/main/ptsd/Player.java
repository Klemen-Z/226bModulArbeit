package com.main.ptsd;

import java.util.HashMap;

public class Player extends Ship {
    private String name;
    private boolean l;
    private boolean r;
    HashMap<Integer, Player_Projectile> projectiles = new HashMap<>();

    Player(int x, int y, int health, String name, int size){
        setX(x);
        setY(y);
        setSize(size);
        setName(name);
    }

    public void shoot(){
        boolean b = true;
        Integer i = 0;
        if (!projectiles.isEmpty()){
            while (b){
                i++;
                if (!projectiles.containsKey(i)){
                    b = false;
                }
            }
        }
        projectiles.put(i, new Player_Projectile(8, 1, getX(), getY(), 1));
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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