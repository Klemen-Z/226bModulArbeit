package com.main.ptsd;

//Imports from God knows how many sources
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.sound.sampled.*;

public class GUI extends JPanel implements ActionListener {
    //Get size of user's display
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int height = (int) size.getHeight() - 100;
    private final int width = (int) size.getWidth() - 100;

    //create player and Arraylists for enemy projectiles and Enemies
    Player pl = new Player(width / 2 - 25, height - 100, 5);
    ArrayList<Enemy_Projectile> EProjectiles = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ArrayList<Enemy> Enemy = new ArrayList<>();

    //Create JSON File interactor and score saving class (Scoreboard)
    DataDealer dataDealer;
    Scoreboard scoreboard = new Scoreboard();
    {
        try {
            dataDealer = new DataDealer("Highscore.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    int highscore;
    int score = 0;

    //create image variables
    Image playerimg;
    Image enemyimg;
    Image backgroundimg;

    //Create variables for game to function (how often it updates and if it has finished or not)
    static final int tickrate = 1;
    Timer timer;
    int rtos = -90;
    boolean runing = false;
    boolean startscreen = true;
    boolean win = false;
    boolean lose = false;
    static int difficulity = 1;
    int etime = 100;
    int invincibilityframe = 0;
    boolean bro = true;


    GUI() {
        //get highscore from file
        long highscore2 = scoreboard.getHighscore();
        highscore = (int) highscore2;

        //load images from resources folder
        backgroundimg = new ImageIcon(loadImage("backgroundimg.png", width, height)).getImage();
        playerimg = new ImageIcon(loadImage("playerimg.png", 50, 50)).getImage();
        enemyimg = new ImageIcon(loadImage("enemyimg.png", 50, 50)).getImage();

        //Frame things (size, background, etc.)
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        //create keylisteners for WASD, space and arrow keys
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (runing) {
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
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && !lose && !win && etime > 0) {
                        runing = true;
                        startscreen = false;
                        makeenemy();
                        makeobstacles();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE && etime < 0 && lose || win && e.getKeyCode() == KeyEvent.VK_SPACE && etime < 0) {
                        startscreen = true;
                        etime = 1000;
                        score = 1000;
                        bro = true;
                        lose = false;
                        win = false;
                        long highscore2 = scoreboard.getHighscore();
                        highscore = (int) highscore2;
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
                        case KeyEvent.VK_L -> GUI.difficulity = 4;
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

    //Image loader for JAR file (Resource Folder is accessed)
    public Image loadImage(String name, int width, int height){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(name);
        BufferedImage image = null;
        try {
            assert input != null;
            image = ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        return image.getScaledInstance(width, height,  Image.SCALE_SMOOTH);
    }

    //Create Obstacless
    private void makeobstacles() {
        for (int i = 1; i <= 4; i++) {
            obstacles.add(new Obstacle(100,50,width*i/5,height-300));
        }
    }

    //Create Enemies (Based on difficulty chosen)
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
            g2d.drawString("Score:" + score, 10, 20);
            if (score < highscore) {
                g2d.drawString("High-Score:" + highscore, 10, 40);
            } else {
                g2d.drawString("High-Score:" + score, 10, 40);
            }
            for (int i=0; pl.getHealth() >= i; i++) {
                g2d.fillOval(5,height-i*25,25,25);
            }
            for (Obstacle obstacle: obstacles) {
                g2d.fillRect(obstacle.getX(),obstacle.getY(),obstacle.getSize(),obstacle.getSize());
            }
        } else if (lose) {
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Calibri", Font.BOLD, 40));
            g2d.drawString("you lost", width / 2, height / 2);
        } else if (win) {
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Calibri", Font.BOLD, 40));
            g2d.drawString("you won", width / 2, height / 2);
            g2d.drawString("Score:" + score, 10, 40);
        } else if (startscreen) {
            Font selected = new Font("Calibri", Font.BOLD, 40);
            Font normal = new Font("Calibri", Font.PLAIN, 20);
            g2d.setColor(Color.WHITE);
            g2d.drawImage(backgroundimg, 0, 0, width, height, null);
            g2d.drawString("press space to start", width / 2, height / 2);

            if (difficulity == 1) {
                g2d.setFont(selected);
                g2d.drawString("Easy", width / 4, 100);
                g2d.setFont(normal);
                g2d.drawString("Normal", width / 2, 100);
                g2d.drawString("Hard", width / 2 + width / 4, 100);
            } else if (difficulity == 2) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.setFont(selected);
                g2d.drawString("Normal", width / 2, 100);
                g2d.setFont(normal);
                g2d.drawString("Hard", width / 2 + width / 4, 100);
            } else if (difficulity == 3) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.drawString("Normal", width / 2, 100);
                g2d.setFont(selected);
                g2d.drawString("Hard", width / 2 + width / 4, 100);
            } else if (difficulity == 4) {
                g2d.setFont(normal);
                g2d.drawString("Easy", width / 4, 100);
                g2d.drawString("Normal", width / 2, 100);
                g2d.drawString("Hard", width / 2 + width / 4, 100);
                g2d.setFont(selected);
                g2d.drawString("Korean", width / 2, 200);
            }
        }
    }

    //method to check (every frame) if something has been hit
    private void hitCheckAll() {
        //Variables for deleted objects
        Integer delete1 = null;
        Integer delete2 = null;
        Integer delete3 = null;
        Integer delete4 = null;

        //check for end of game conditions
        if (Enemy.isEmpty()) {
            win = true;
            runing = false;
        }
        if (pl.getHealth() <= 0) {
            runing = false;
            lose = true;
        }

        //hit checking for player shooting at enemy
        for (Enemy enemy : Enemy) {
            for (Player_Projectile pp : pl.PProjectiles) {
                if (pp.hitCheck(enemy.getX(), enemy.getY(), 50)) {
                    enemy.setHealth(enemy.getHealth()-1);
                    if (enemy.getHealth() <= 0) {
                        delete1 = Enemy.indexOf(enemy);
                    }
                    delete2 = pl.PProjectiles.indexOf(pp);
                    pp.setHit(true);
                }
            }
        }

        //Hit checking for Enemy shooting on player
        for (Enemy_Projectile ep : EProjectiles) {
            if (ep.hitCheck(pl.getX(), pl.getY(),50) && invincibilityframe <= 0) {
                invincibilityframe = 30;
                pl.setHealth(pl.getHealth() - 1);
                delete3 = EProjectiles.indexOf(ep);
                ep.setHit(true);
            } else if (ep.hitCheck(pl.getX(), pl.getY(), 50)) {
                delete3 = EProjectiles.indexOf(ep);
                ep.setHit(true);
            }
        }

        //Hit check for anyone shooting at Obstacles
        for (Obstacle obstacle: obstacles) {
            for (Enemy_Projectile ep : EProjectiles) {
                if (ep.hitCheck(obstacle.getX(), obstacle.getY(), obstacle.getSize())) {
                    obstacle.setHp(obstacle.getHp() - 1);
                    delete3 = EProjectiles.indexOf(ep);
                    ep.setHit(true);
                }
            }
            for (Player_Projectile pp : pl.PProjectiles) {
                if (pp.hitCheck(obstacle.getX(), obstacle.getY(), obstacle.getSize())) {
                    obstacle.setHp(obstacle.getHp() - 1);
                    delete2 = pl.PProjectiles.indexOf(pp);
                    pp.setHit(true);
                }
            }
            if (obstacle.getHp() <= 0) {
                delete4 = obstacles.indexOf(obstacle);
            }
        }


        //Delete the requested items
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
        if (delete4 != null) {
            obstacles.remove((int) delete4);
        }
    }

    //Method to move everything every frame
    private void allmove() {
        //for Bullets to be deleted of screen
        ArrayList<Integer> delete1 = new ArrayList<>();
        Integer delete2 = null;

        //move player
        pl.move();

        //enemy out-of-bounds check
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
        //move enemies
        for (Enemy enemy : Enemy) {
            enemy.move();
            //if enemies reached player, player loses
            if (enemy.getY() >= pl.getY() - 50) {
                lose = true;
                runing = false;
            }
        }

        //move player projectiles
        for (Player_Projectile pprojectile : pl.PProjectiles) {
            pprojectile.move();
        }

        //
        if (rtos > 10 && pl.isShoot()) {
            try {
                music();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
            pl.shoot();
            rtos = 0;
        }

        //move enemy projectiles
        for (Enemy_Projectile eprojectile : EProjectiles) {
            eprojectile.move();
        }

        //Enemy projectiles out-of-bounds check
        for (Enemy_Projectile ep : EProjectiles) {
            if (ep.outOfBoundsCheck("e")) {
                delete1.add(EProjectiles.indexOf(ep));
            }
        }

        //Player projectiles out-of-bounds check
        for (Player_Projectile pp : pl.PProjectiles) {
            if (pp.outOfBoundsCheck("p")) {
                delete2 = pl.PProjectiles.indexOf(pp);
            }
        }

        //Remove offending projectiles
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
            try {
                scoreboard.insertValues();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        EProjectiles.clear();
        for (Enemy enemy: Enemy) {
            enemy.setEtos(0);
        }

    }

    private void theygotthatgat() {
        for (Enemy enemy : Enemy) {
            enemy.setEtos(enemy.getEtos() + 1);
            if (enemy.getEtos() > enemy.getAttackspeed()) {
                if (difficulity != 4) {
                    try {
                        music();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    EProjectiles.add(new Enemy_Projectile(8, 1, enemy.getX() + 25, enemy.getY() + 50, 1));
                    enemy.setEtos(0);
                } else {
                    EProjectiles.add(new Enemy_Projectile(8, 1, enemy.getX() + 25, enemy.getY() + 50, 1));
                    enemy.setEtos(0);
                }
            }
        }
    }

    public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/main/Resources/shoot1.wav").getAbsoluteFile());
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



