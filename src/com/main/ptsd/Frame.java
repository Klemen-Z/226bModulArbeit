package com.main.ptsd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Frame extends JFrame implements KeyListener {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight();
    private int width = (int) size.getWidth();
    //JLabel player = new JLabel();
    GUI panel;
    HashMap<Enemy, Integer> capitalCities = new HashMap<Enemy, Integer>();

    Frame() {
        panel = new GUI();
        ImageIcon icon = new ImageIcon("iconimg.png");
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
