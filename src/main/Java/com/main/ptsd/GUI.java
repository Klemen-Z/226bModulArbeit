package com.main.ptsd;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import org.json.simple.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import javax.sound.sampled.*;

public class GUI extends JPanel implements ActionListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int height = (int) size.getHeight() - 100;
    private final int width = (int) size.getWidth() - 100;
    Player pl = new Player(width / 2 - 25, height - 100, 5, "player", 1);
    ArrayList<Enemy_Projectile> EProjectiles = new ArrayList<>();
    DataDealer dataDealer;

    {
        try {
            dataDealer = new DataDealer("Highscore.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ArrayList<Enemy> Enemy = new ArrayList<>();
    Image playerimg;
    Image enemyimg;
    Image backgroundimg;
    static final int tickrate = 1;
    Timer timer;
    int rtos = -90;
    boolean runing = false;
    boolean startscreen = true;
    boolean win = false;
    boolean lose = false;
    static int difficulity = 1;
    int score = 0;
    int etime = 100;
    int invincibilityframe = 0;
    boolean bro = true;
    int highscore = 0;

    GUI() {
        this.setPreferredSize(new Dimension(width, height));
        playerimg = new ImageIcon("src/main/Resources/playerimg.png").getImage();
        enemyimg = new ImageIcon("src/main/Resources/enemyimg.png").getImage();
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (runing) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pl.setShoot(true);
                        try {
                            music();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                            ex.printStackTrace();
                        }
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
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && !lose && !win && etime > 0) {
                        runing = true;
                        startscreen = false;
                        makeenemy();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && etime < 0 && lose || win && e.getKeyCode() == KeyEvent.VK_SPACE && etime < 0) {
                        startscreen = true;
                        etime = 1000;
                        score = 1000;
                        bro = true;
                        lose = false;
                        win = false;
                    }
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> {
                            if (GUI.difficulity > 1) {
                                GUI.difficulity--;
                            }
                        }
                        case KeyEvent.VK_RIGHT -> {
                            if (GUI.difficulity < 3) {
                                GUI.difficulity++;
                            }
                        }
                        case KeyEvent.VK_L -> {
                            GUI.difficulity = 4;
                        }
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
        startgame();
    }

    private void makeenemy() {
        if (difficulity == 1) {
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(200, 400);
                Enemy.add(new Enemy(i * 55, 100, 50, 1, 100, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(200, 400);
                Enemy.add(new Enemy(i * 55 + 25, 160, 50, 1, 100, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(200, 400);
                Enemy.add(new Enemy(i * 55, 220, 50, 1, 100, r)
                );
            }
        } else if (difficulity == 2) {
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(150, 300);
                Enemy.add(new Enemy(i * 55, 100, 50, 2, 125, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(150, 300);
                Enemy.add(new Enemy(i * 55 + 25, 160, 50, 2, 125, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(150, 300);
                Enemy.add(new Enemy(i * 55, 220, 50, 2, 125, r)
                );
            }
        } else if (difficulity == 3) {
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(100, 200);
                Enemy.add(new Enemy(i * 55, 100, 50, 3, 150, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(100, 200);
                Enemy.add(new Enemy(i * 55 + 25, 160, 50, 3, 150, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(100, 200);
                Enemy.add(new Enemy(i * 55, 220, 50, 3, 500, r)
                );
            }
        } else if (difficulity == 4) {
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(1, 2);
                Enemy.add(new Enemy(i * 55, 100, 50, 5, 500, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(1, 2);
                Enemy.add(new Enemy(i * 55 + 25, 160, 50, 5, 500, r)
                );
            }
            for (int i = 1; i <= 20; i++) {
                Random random = new Random();
                int r = random.nextInt(1, 2);
                Enemy.add(new Enemy(i * 55, 220, 50, 5, 500, r)
                );
            }
        }
    }

    private void startgame() {
        timer = new Timer(tickrate, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (runing) {
            backgroundimg = new ImageIcon("src/main/Resources/backgroundimg.png").getImage();
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
            for (Enemy_Projectile eprojectile : EProjectiles) {
                g2d.setColor(Color.RED);
                g2d.fillRect(eprojectile.getX(), eprojectile.getY(), 2, 10);
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Calibri", Font.BOLD, 20));
            g2d.drawString("Score:" + String.valueOf(score), 10, 20);
            if (score < highscore) {
                g2d.drawString("High-Score:" + String.valueOf(highscore), 10, 40);
            } else {
                g2d.drawString("High-Score:" + String.valueOf(score), 10, 40);
            }

        } else if (lose) {
            backgroundimg = new ImageIcon("src/main/Resources/backgroundimg.png").getImage();
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Calibri", Font.BOLD, 40));
            g2d.drawString("you lost", width / 2, height / 2);
        } else if (win) {
            backgroundimg = new ImageIcon("src/main/Resources/backgroundimg.png").getImage();
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Calibri", Font.BOLD, 40));
            g2d.drawString("you won", width / 2, height / 2);
            g2d.drawString("Score:" + String.valueOf(score), 10, 40);
        } else if (startscreen) {
            Font selected = new Font("Calibri", Font.BOLD, 40);
            Font normal = new Font("Calibri", Font.PLAIN, 20);
            backgroundimg = new ImageIcon("src/main/Resources/backgroundimg.png").getImage();
            g2d.setColor(Color.WHITE);
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.drawString("press space to start", width / 2, height / 2);

            if (difficulity == 1) {
                g2d.setFont(selected);
                g2d.drawString("Easy", width / 4, 100);
                g2d.setFont(normal);
                g2d.drawString("Normal", width / 2, 100);
                g2d.drawString("Russian", width / 2 + width / 4, 100);
            } else if (difficulity == 2) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.setFont(selected);
                g2d.drawString("Normal", width / 2, 100);
                g2d.setFont(normal);
                g2d.drawString("Russian", width / 2 + width / 4, 100);
            } else if (difficulity == 3) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.drawString("Normal", width / 2, 100);
                g2d.setFont(selected);
                g2d.drawString("Russian", width / 2 + width / 4, 100);
            } else if (difficulity == 4) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.drawString("Normal", width / 2, 100);
                g2d.drawString("Russian", width / 2 + width / 4, 100);
                g2d.setFont(selected);
                g2d.drawString("Korean", width / 2, 200);
            }
        }
    }

    private void hitCheckAll() {
        Integer delete1 = null;
        Integer delete2 = null;
        Integer delete3 = null;
        Integer delHelp = null;

        if (Enemy.isEmpty()) {
            win = true;
            runing = false;
        }
        if (pl.getHealth() <= 0) {
            runing = false;
            lose = true;
        }

        for (Enemy enemy : Enemy) {
            for (Player_Projectile pp : pl.PProjectiles) {
                if (pp.hitCheck(enemy.getX(), enemy.getY())) {
                    delete1 = Enemy.indexOf(enemy);
                    delete2 = pl.PProjectiles.indexOf(pp);
                    pp.setHit(true);
                }
            }
        }

        for (Enemy_Projectile ep : EProjectiles) {
            if (ep.hitCheck(pl.getX(), pl.getY()) && invincibilityframe <= 0) {
                invincibilityframe = 30;
                pl.setHealth(pl.getHealth() - 1);
                delete3 = EProjectiles.indexOf(ep);
                ep.setHit(true);
            }
        }


        if (delete1 != null) {
            score = score + Enemy.get(delete1).getPoints();
            Enemy.remove((int) delete1);
        }
        if (delete2 != null) {
            pl.PProjectiles.remove((int) delete2);
        }
        if (delete3 != null) {
            EProjectiles.remove((int) delete3);
        }
    }

    private void allmove() {
        ArrayList<Integer> delete1 = new ArrayList<>();
        Integer delete2 = null;
        Integer delHelp = null;

        pl.move();
        for (Enemy enemy : Enemy) {
            if (enemy.getX() <= 0) {
                for (Enemy enemy2 : Enemy) {
                    enemy2.setR(true);
                    enemy2.setL(false);
                    enemy2.setY(enemy2.getY() + 5);
                }
            }
            if (enemy.getX() >= width - 50) {
                for (Enemy enemy2 : Enemy) {
                    enemy2.setR(false);
                    enemy2.setL(true);
                    enemy2.setY(enemy2.getY() + 5);
                }
            }
        }
        for (Enemy enemy : Enemy) {
            enemy.move();
            if (enemy.getY() >= pl.getY() - 50) {
                lose = true;
                runing = false;
            }
        }
        for (Player_Projectile pprojectile : pl.PProjectiles) {
            pprojectile.move();
        }
        if (rtos > 10 && pl.isShoot()) {
            pl.shoot();
            rtos = 0;
        }
        for (Enemy_Projectile eprojectile : EProjectiles) {
            eprojectile.move();
        }
        for (Enemy_Projectile ep : EProjectiles) {
            if (ep.outOfBoundsCheck("e")) {
                delete1.add(EProjectiles.indexOf(ep));
            }
        }
        for (Player_Projectile pp : pl.PProjectiles) {
            if (pp.outOfBoundsCheck("p")) {
                delete2 = pl.PProjectiles.indexOf(pp);
            }
        }
        for (Integer d1 : delete1) {
            EProjectiles.remove((int) d1);
        }
        delete1.clear();
        if (delete2 != null) {
            pl.PProjectiles.remove((int) delete2);
        }
    }

    public void Win() {
        if (bro) {
            dataDealer.dataStore(new Date(), score);
            bro = false;
        }
        Restart();
    }

    public void Lose() {
        Restart();
    }

    public void Restart() {
        runing = false;
        pl.setHealth(5);
        pl.setX(width / 2 - 25);
        pl.setY(height - 100);
        Enemy.clear();
        pl.PProjectiles.clear();

    }

    private void theygotthatgat() {
        for (Enemy enemy : Enemy) {
            enemy.setEtos(enemy.getEtos() + 1);
            if (enemy.getEtos() > enemy.getAttackspeed()) {
                EProjectiles.add(new Enemy_Projectile(8, 1, enemy.getX() + 25, enemy.getY() + 50, 1));
                enemy.setEtos(0);
            }
        }
    }
    public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        //Java liest Musik

        File file = new File("src/main/Resources/shoot2.wav");

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (runing) {
            allmove();
            hitCheckAll();
            theygotthatgat();
            rtos++;
            invincibilityframe--;
            if (score > 0) {
                score--;
            }
            etime = 100;
        } else if (win) {
            Win();
            etime--;
        } else if (lose) {
            Lose();
            etime--;
        }
        repaint();
    }
}



