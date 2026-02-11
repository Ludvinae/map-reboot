package com.yorkhuul.life.display.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapDisplayPanel extends JPanel {

    private BufferedImage image;
    private JPanel mapPanel;

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
    }


}
