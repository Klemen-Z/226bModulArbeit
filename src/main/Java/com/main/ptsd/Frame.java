package com.main.ptsd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Frame extends JFrame implements KeyListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight();
    private int width = (int) size.getWidth();
    //JLabel player = new JLabel();
    GUI panel;
    HashMap<Enemy, Integer> capitalCities = new HashMap<Enemy, Integer>();

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

    Frame() {
        panel = new GUI();
        ImageIcon icon = new ImageIcon(loadImage("iconimg.png", 150, 150));
        //ImageIcon playerimg = new ImageIcon("playerimg.png");
        //Image image = playerimg.getImage();
        //Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        //playerimg = new ImageIcon(newimg);
        //player.setBounds(pl.getX(),pl.getY(),50,50);
        //player.setIcon(playerimg);
        //player.setBackground(Color.black);
        //player.setOpaque(true);

        this.setTitle("Space Invaders (Java)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(width, height);
        this.setResizable(false);
        //this.setFocusable(true);
        //this.getContentPane().setBackground(Color.BLACK);
        //this.setLayout(null);
        //this.add(player);
        //this.addKeyListener(this);
        this.add(panel);
        this.pack();
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //validate();

        }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
