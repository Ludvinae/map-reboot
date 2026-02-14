package com.yorkhuul.life.editor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapDisplayPanel extends JPanel {

    private BufferedImage image;

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        System.out.println("PAINT CALLED " + getWidth() + "x" + getHeight());
    }
}
