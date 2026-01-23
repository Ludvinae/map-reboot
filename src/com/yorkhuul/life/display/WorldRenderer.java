package com.yorkhuul.life.display;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldRenderer {

    private World world;
    private BufferedImage image;
    int regionSize = Region.getSize();

    public WorldRenderer(World world) {
        this.world = world;
        setImage();
    }

    public void setImage() {
        int height = world.getHeightInTiles();
        int width = world.getWidthInTiles();
        System.out.println(height);
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void generateImage() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);

                for (int y = 0; y < regionSize; y++) {
                    for (int x = 0; x < regionSize; x++) {

                        int worldX = j * regionSize + x;
                        int worldY = i * regionSize + y;

                        Tile tile = region.getTile(x, y);
                        Color color = altitudeToColor(tile.getAltitude());
                        image.setRGB(worldX, worldY, color.getRGB());
                    }
                }
            }
        }
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
        } else if (altitude <= 1) {
            return Color.WHITE;
        } else {
            // indicates problem in altitude value
            return Color.RED;
        }
    }


    public void exportImage() {
        String name = world.getName() + "_" + System.currentTimeMillis() + ".png";
        ImageExporter.saveAsPng(image, name);
    }


}
