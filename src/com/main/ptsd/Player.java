package com.main.ptsd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.util.HashMap;

public class Player extends Ship {
    private String name;
    private boolean l;
    private int health;
    private boolean r;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    @Test
    public void Test()  throws ParameterResolutionException {
        Player p1 = new Player(40, 100, 3, "bob", 1);
        Player p2 = new Player(40, 100, 3, "bob", 1);

        p1.setL(true);
        p1.move();
        p1.setL(false);
        int x1 = p1.getX();

        p1.move();
        int x2 = p1.getX();

        assert (x1 == x2);

        int x3 = p2.getX();
        assert (x3 != x2);

        p1.setR(true);
        p1.move();
        p1.move();
        p1.setL(false);
        int x4 = p1.getX();

        assert (x3 != x4);
    }
}