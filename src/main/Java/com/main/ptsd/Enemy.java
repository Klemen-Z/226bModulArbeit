package com.main.ptsd;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Ship {
    private boolean l = false;
    private boolean r = true;
    private int score;
    private int points;
    private int attackspeed;
    private int etos;



    private int health;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight()-100;
    private int width = (int) size.getWidth()-100;

    Enemy(int x, int y, int size, int health, int points, int attackspeed){
        setPoints(points);
        setSize(size);
        setAttackspeed(attackspeed);
        setY(y);
        setX(x);
        setHealth(health);


    }

    public void move(){
        int speed = 3;
        if(this.l) {
            setX(getX() - speed);
        } else if (this.r){
            setX(getX() + speed);
        }
    }

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public void shoot(){
    }

    public int getPoints() {
        return points;
    }

    public boolean getL(){
        return l;
    }

    public boolean getR(){
        return r;
    }

    public int getAttackspeed() {
        return attackspeed;
    }

    public int getEtos() {
        return etos;
    }

    public void setEtos(int etos) {
        this.etos = etos;
    }

    public void setAttackspeed(int attackspeed) {
        this.attackspeed = attackspeed;
    }
    public void setL(boolean l){
        this.l = l;
    }

    public void setR(boolean r){
        this.r = r;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void hit(boolean t){
        if (t){
         //   EProjectiles.clear();
        }
    }
}
