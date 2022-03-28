package com.main.ptsd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel implements ActionListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight()-100;
    private int width = (int) size.getWidth()-100;
    Player pl = new Player(width/2-25, height -100, 5, "player", 1);
    Image playerimg;
    static final int tickrate = 10;
    Timer timer;
    GUI(){
        this.setPreferredSize(new Dimension(width,height));
        playerimg = new ImageIcon("playerimg.png").getImage();
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            pl.setL(true);
                            pl.setR(false);
                            break;
                        case KeyEvent.VK_RIGHT:
                            pl.setR(true);
                            pl.setL(false);
                            break;
                        case KeyEvent.VK_A:
                            pl.setL(true);
                            pl.setR(false);
                            break;
                        case KeyEvent.VK_D:
                            pl.setR(true);
                            pl.setL(false);
                            break;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pl.shoot();
                    }
                }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        pl.setR(false);
                        pl.setL(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        pl.setR(false);
                        pl.setL(false);
                        break;
                    case KeyEvent.VK_A:
                        pl.setR(false);
                        pl.setL(false);
                        break;
                    case KeyEvent.VK_D:
                        pl.setR(false);
                        pl.setL(false);
                        break;
                }
            }
        });
        startgame();
    }

    private void startgame() {
        timer = new Timer(tickrate, this);
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;


        g2d.drawRect(pl.getX(),pl.getY(),50,50);
        g2d.drawImage(playerimg,pl.getX(),pl.getY(),50,50,null);
    }

    private void playermove() {
        pl.move();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playermove();
        repaint();
    }

}

