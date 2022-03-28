package com.main.ptsd;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class GUI extends JFrame implements KeyListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight();
    private int width = (int) size.getWidth();
    JLabel player = new JLabel();
    Player pl = new Player(width/2-25, height -100, 5, "player", 1);
    HashMap<Enemy, Integer> capitalCities = new HashMap<Enemy, Integer>();

    GUI() {
        ImageIcon icon = new ImageIcon("iconimg.png");
        ImageIcon playerimg = new ImageIcon("playerimg.png");
        Image image = playerimg.getImage();
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        playerimg = new ImageIcon(newimg);
        player.setBounds(pl.getX(),pl.getY(),50,50);
        player.setIcon(playerimg);
        player.setBackground(Color.black);
        player.setOpaque(true);

        this.setTitle("Space Invaders (Java)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setFocusable(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(null);
        this.setVisible(true);
        this.add(player);
        this.addKeyListener(this);
        this.setIconImage(icon.getImage());
        validate();
        makenemey(); {

        }
    }

    private void makenemey() {

    }

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
}