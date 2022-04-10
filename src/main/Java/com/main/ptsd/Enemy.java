package com.main.ptsd;

public class Enemy extends Ship {

    //variables for how enemies should behave
    private boolean l = false;
    private boolean r = true;
    private int points;
    private int attackspeed;
    private int etos;
    private int health;

    Enemy(int x, int y, int size, int health, int points, int attackspeed){
        setPoints(points);
        setSize(size);
        setAttackspeed(attackspeed);
        setY(y);
        setX(x);
        setHealth(health);
    }

    //enemy movement (based on where the outermost enemies are)
    public void move(){
        int speed = 3;
        if(this.l) {
            setX(getX() - speed);
        } else if (this.r){
            setX(getX() + speed);
        }
    }

    //getters and setters for all values
    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public int getPoints() {
        return points;
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
}
