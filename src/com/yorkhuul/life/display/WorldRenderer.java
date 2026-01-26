package com.yorkhuul.life.display;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.RegionRelief;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldRenderer {

    private World world;
    private BufferedImage image;
    int regionSize = Region.getSize();

    public WorldRenderer(World world, boolean onlyRegions) {
        this.world = world;
        setImage(onlyRegions);
    }

    public void setImage(boolean onlyRegions) {
        int width;
        int height;

        if (onlyRegions) {
            height = world.getHeight();
            width = world.getWidth();
        } else {
            height = world.getHeightInTiles();
            width = world.getWidthInTiles();
        }
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void generateElevationImage() {
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

    public void generateReliefImage() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);
                Color color = reliefToColor(region.getRelief());
                image.setRGB(j, i, color.getRGB());
            }
        }
    }


    public Color altitudeToColor(float altitude) {
        if (altitude < -0.8) {
            return new Color(0, 17, 26);
        } else if (altitude < -0.6) {
            return new Color(1, 42, 65);
        } else if (altitude < -0.4) {
            return new Color(1, 76, 117);
        } else if (altitude < -0.2) {
            return new Color(142, 235, 237);
        } else if (altitude < 0) {
            return new Color(226, 202, 118);
        } else if (altitude < 0.2) {
            return new Color(63, 155, 11);
        } else if (altitude < 0.4) {
            return new Color(134, 181, 4);
        } else if (altitude < 0.6) {
            return new Color(122, 77, 58);
        } else if (altitude < 0.8) {
            return new Color(17, 17, 30);
        } else if (altitude <= 1) {
            return new Color(255, 250, 250);
        } else {
            // indicates problem in altitude value
            return new Color(255, 0, 0);
        }
    }

    public Color reliefToColor(RegionRelief relief) {
            return switch (relief) {
                case SEA -> Color.BLUE;
                case SHORE -> Color.CYAN;
                case BEACH -> Color.YELLOW;
                case PLAIN -> Color.GREEN;
                case HILLS -> Color.BLACK;
                case MOUNTAINS -> Color.WHITE;
                case null -> Color.WHITE;
        };
    }


    public void exportImage(String type) {
        String name = world.getName() + type + "_" + System.currentTimeMillis() + ".png";
        ImageExporter.saveAsPng(image, "image_output/" + name);
    }


}
