package com.main.ptsd;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JLabel player = new JLabel("bröder");
    Player pl = new Player();

    GUI() {
        ImageIcon icon = new ImageIcon("../ScreenshotStarfield.png");
        pl.setY(50);
        this.setTitle("Space Invaders (Java)");
        this.setSize(500, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setFocusable(true);
        this.add(player);
        player.setBounds(pl.getX(),pl.getY(),5,5);
        player.setBackground(Color.red);
        player.setVisible(true);
        this.setVisible(true);
    }
}
