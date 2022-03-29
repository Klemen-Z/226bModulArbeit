package com.main.ptsd;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JPanel implements ActionListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight() - 100;
    private int width = (int) size.getWidth() - 100;
    Player pl = new Player(width / 2 - 25, height - 100, 5, "player", 1);
    Image playerimg;
    Image enemyimg;
    Image backgroundimg;
    static final int tickrate = 1;
    Timer timer;
    int rtos = 10;
    boolean runing = true;
    boolean win = false;
    ArrayList<Enemy> Enemy = new ArrayList<>();
    boolean lose = false;
    GUI() {
        this.setPreferredSize(new Dimension(width, height));
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
        for (int i = 1; i <= 20; i++) {
            Random random = new Random();
            int r = random.nextInt(50, 80);
            Enemy.add(new Enemy(i * 55, 100, 50, 1, 1, 1, r)
            );
        }
    }

    private void startgame() {
        timer = new Timer(tickrate, this);
        if (runing) {
            timer.start();
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (runing) {
            backgroundimg = new ImageIcon("backgroundimg.png").getImage();
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.drawRect(pl.getX(), pl.getY(), 50, 50);
            for (Player_Projectile pprojectile : pl.PProjectiles) {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(pprojectile.getX(), pprojectile.getY(), 2, 10);
            }
            g2d.drawImage(playerimg, pl.getX(), pl.getY(), 50, 50, null);
            for (Enemy enemy : Enemy) {
                g2d.drawImage(enemyimg, enemy.getX(), enemy.getY(), enemy.getSize(), enemy.getSize(), null);
            }
            for (Enemy enemy : Enemy) {
                for (Enemy_Projectile eprojectile : enemy.EProjectiles) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(eprojectile.getX(), eprojectile.getY(), 2, 10);
                }
            }
        } else if (lose){
            backgroundimg = new ImageIcon("backgroundimg.png").getImage();
            g2d.setColor(Color.WHITE);
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.drawString("you lost", width / 2, height / 2);
        } else if (win){
            backgroundimg = new ImageIcon("backgroundimg.png").getImage();
            g2d.setColor(Color.WHITE);
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.drawString("you won", width / 2, height / 2);
        }
    }

    private void hitCheckAll(){
        Integer delete1 = null;
        Integer delete2 = null;
        Integer delete3 = null;
        Integer delHelp = null;

        if (Enemy.isEmpty()) {
            win = true;
            runing = false;
            win = true;
        }
        if (pl.getHealth() <= 0) {
            runing = false;
            lose = true;
        }

        for (Enemy enemy : Enemy){
            for (Player_Projectile pp : pl.PProjectiles){
                if (pp.hitCheck(enemy.getX(), enemy.getY())){
                    delete1 = Enemy.indexOf(enemy);
                    delete2 = pl.PProjectiles.indexOf(pp);
                    pp.setHit(true);
                }
            }
        }

        for (Enemy enemy : Enemy){
            for (Enemy_Projectile ep : enemy.EProjectiles){
                System.out.println("bulet x : " + ep.getX());
                System.out.println("bulet y : " + ep.getY());

                if (ep.hitCheck(pl.getX(), pl.getY())){
                    pl.setHealth(pl.getHealth() - 1);
                    delete3 = enemy.EProjectiles.indexOf(ep);
                    delHelp = Enemy.indexOf(enemy);
                    ep.setHit(true);
                }
            }

        }

        if (delete1 != null) {
            Enemy.remove((int) delete1);
        }
        if (delete2 != null) {
            pl.PProjectiles.remove((int) delete2);
        }
        if (delete3 != null){
            Enemy.get(delHelp).EProjectiles.remove((int)delete3);
        }
    }

    private void allmove() {
        pl.move();
        for (Enemy enemy : Enemy) {
            if (enemy.getX() <= 0) {
                for (Enemy enemy2 : Enemy) {
                    enemy2.setR(true);
                    enemy2.setL(false);
                }
            }
            if (enemy.getX() >= width - 50) {
                for (Enemy enemy2 : Enemy) {
                    enemy2.setR(false);
                    enemy2.setL(true);
                }
            }
            enemy.move();
        }
        for (Player_Projectile pprojectile : pl.PProjectiles) {
            pprojectile.move();
        }
        if (rtos > 10 && pl.isShoot()) {
            pl.shoot();
            rtos = 0;
        }
        for (Enemy enemy : Enemy) {
            for (Enemy_Projectile eprojectile : enemy.EProjectiles) {
                eprojectile.move();
            }
        }
    }

    public void Win(){

    }
    public void Lose(){

    }

    public void gameover(){

    }

    private void theygotthatgat() {
        for (Enemy enemy : Enemy) {
            enemy.setEtos(enemy.getEtos() + 1);
            if (enemy.getEtos() > enemy.getAttackspeed()) {
                System.out.println(enemy.getEtos());
                enemy.shoot();
                enemy.setEtos(0);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (runing) {
            allmove();
            hitCheckAll();
            theygotthatgat();
            repaint();
            rtos++;
        } else if (win){
            Win();
            gameover();
        } else {
            Lose();
            gameover();
        }
    }
}



