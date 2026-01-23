package com.yorkhuul.life.display;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Image {

    private int width;
    private int height;
    private BufferedImage image;


    public Image(int width, int height) {
        setWidth(width);
        setHeight(height);
        this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    protected BufferedImage getImage() {
        return this.image;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            width = 1;
        }
        this.width = width;
    }

    private void setHeight(int height) {
        if (height <= 0) {
            height = 1;
        }
        this.height = height;
    }

    public void setColorToPosition(int x, int y, Color color) {
        image.setRGB(x, y, color.getRGB());
    }

    public Color altitudeToColor(float altitude) {
        if (altitude < -0.5) {
            return Color.BLUE;
        } else if (altitude < 0.0001) {
            return Color.CYAN;
        } else if (altitude < 0.05) {
            return Color.YELLOW;
        } else if (altitude < 0.35) {
            return Color.GREEN;
        } else if (altitude < 0.6) {
            return Color.BLACK;
        } else if (altitude < 1) {
            return Color.WHITE;
        } else {
            // indicates problem in altitude value
            return Color.RED;
        }
    }
}
