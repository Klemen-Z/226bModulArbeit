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

public class Frame extends JFrame {
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int) size.getHeight();
    private int width = (int) size.getWidth();
    GUI panel;

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
        this.setTitle("Space Invaders (Java)");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(panel);
        this.pack();
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        }

}
