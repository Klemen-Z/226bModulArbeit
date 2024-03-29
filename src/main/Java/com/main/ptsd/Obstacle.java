package com.main.ptsd;

public class Obstacle {
    //just necessary variables
    private int size;
    private int y;
    private int x;
    private int hp;

    Obstacle(int size,int hp ,int x,int y){
        //set all variables
        setX(x);
        setY(y);
        setSize(size);
        setHp(hp);
    }

    //getters and setters for all values
    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
