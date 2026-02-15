package com.yorkhuul.life.editor.ui;

import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.editor.render.ImageExporter;
import com.yorkhuul.life.editor.render.WorldRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {

    private BufferedImage image;

    public void setWorld(World world) {

        if (world == null) {
            image = null;
            repaint();
            return;
        }

        WorldRenderer renderer = new WorldRenderer(
                world.getWidthInTiles(),
                world.getHeightInTiles()
        );

        renderer.generateAltitudeImage(world);
        image = renderer.getImage();
        renderer.exportImage(world.getName());

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) return;

        Graphics2D g2d = (Graphics2D) g;

        // Option 1 : afficher taille réelle
        // g2d.drawImage(image, 0, 0, null);

        // Option 2 (recommandé) : adapter à la taille du panel
        g2d.drawImage(image,0,0, getWidth(), getHeight(),null);
    }
}

