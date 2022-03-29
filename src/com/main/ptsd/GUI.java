package com.main.ptsd;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JPanel implements ActionListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight()-100;
    private int width = (int) size.getWidth()-100;
    Player pl = new Player(width/2-25, height -100, 5, "player", 1);
    Image playerimg;
    Image enemyimg;
    Image backgroundimg;
    static final int tickrate = 10;
    Timer timer;
    int rtos = 1000;
    boolean runing = true;
    HashMap<Integer, Enemy> Enemy = new HashMap<>();
    GUI(){
        this.setPreferredSize(new Dimension(width,height));
        playerimg = new ImageIcon("playerimg.png").getImage();
        enemyimg = new ImageIcon("enemyimg.png").getImage();
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pl.setShoot(true);
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        pl.setL(true);
                        pl.setR(false);
                    }
                    case KeyEvent.VK_RIGHT -> {
                        pl.setR(true);
                        pl.setL(false);
                    }
                    case KeyEvent.VK_A -> {
                        pl.setL(true);
                        pl.setR(false);
                    }
                    case KeyEvent.VK_D -> {
                        pl.setR(true);
                        pl.setL(false);
                    }
                }
                }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> pl.setL(false);
                    case KeyEvent.VK_RIGHT -> pl.setR(false);
                    case KeyEvent.VK_A -> pl.setL(false);
                    case KeyEvent.VK_D -> pl.setR(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pl.setShoot(false);
                }
            }
        });
        makeenemy();
        startgame();
    }

    private void makeenemy() {
        for (int i = 0; i < 10; i++) {
            Enemy.put(i, new Enemy(i * 55, 100, 50, 1, 1, 1));
        }
    }

    private void startgame() {
        timer = new Timer(tickrate, this);
        if (runing) {
            timer.start();
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        backgroundimg = new ImageIcon("backgroundimg.png").getImage();
        g2d.drawImage(backgroundimg,0,0,width,height,null);
        g2d.drawRect(pl.getX(),pl.getY(),50,50);
        for(Player_Projectile pprojectile : pl.PProjectiles.values()){
            g2d.setColor(Color.GREEN);
            g2d.fillRect(pprojectile.getX()+25,pprojectile.getY(),2,20);
        }
        g2d.drawImage(playerimg,pl.getX(),pl.getY(),50,50,null);
        for(Enemy enemy : Enemy.values()){
            g2d.drawImage(enemyimg,enemy.getX(),enemy.getY(),enemy.getSize(),enemy.getSize(),null);
        }

    }

    private void hitCheckAll(){
        int temp1 = 0;

        if (Enemy.isEmpty()){
            //timer.stop();
        }
        if(pl.getHealth() == 0){
            runing = false;
        }

        for (Enemy enemy : Enemy.values()){
            for (Player_Projectile pp : pl.PProjectiles.values()){
                enemy.hit(pp.hitCheck(enemy.getX(), enemy.getY()));
                if (pp.hitCheck(enemy.getX(), enemy.getY())){
                    Enemy.remove(temp1);
                }
            }
            temp1++;
        }

        for (Enemy enemy : Enemy.values()){
            for (Enemy_Projectile ep : enemy.EProjectiles.values()){
                if (ep.hitCheck(pl.getX(), pl.getY())){
                    pl.setHealth(pl.getHealth() - 1);
                }
            }
        }
    }

    private void allmove() {
        pl.move();
        for(Enemy enemy : Enemy.values()){
            if (enemy.getX() <= 0) {
                for(Enemy enemy2 : Enemy.values()){
                    enemy2.setR(true);
                    enemy2.setL(false);
                }
            }
            if (enemy.getX() >= width-50) {
                for(Enemy enemy2 : Enemy.values()){
                    enemy2.setR(false);
                    enemy2.setL(true);
                }
            }
            enemy.move();
        }
        for(Player_Projectile pprojectile : pl.PProjectiles.values()){
            pprojectile.move();
        }
        if(rtos > 10 && pl.isShoot()) {
            pl.shoot();
            rtos = 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        allmove();
        hitCheckAll();
        repaint();
        rtos++;

    }



}

