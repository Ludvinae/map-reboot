package com.yorkhuul.life.display;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

import java.awt.*;

public class WorldRenderer {

    private World world;
    private Image image;

    public WorldRenderer(World world) {
        this.world = world;
        setImage();
    }

    public void setImage() {
        int height = world.getHeightInTiles();
        int width = world.getWidthInTiles();
        this.image = new Image(width, height);
    }

    public void generateImage() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(i, j);

                for (int y = 0; y < region.getLocalCoordinates().y(); y++) {
                    for (int x = 0; x < region.getLocalCoordinates().x(); x++) {
                        Tile tile = region.getTile(x, y);
                        Color color = interpretMap(tile);
                        image.setColorToPosition(x, y, color);
                    }
                }
            }
        }
    }

    public Color interpretMap(Tile tile) {
        return image.altitudeToColor(tile.getAltitude());
    }

    public void exportImage() {
        String name = world.getName() + "_" + System.currentTimeMillis() + ".png";
        ImageExporter.saveAsPng(image.getImage(), name);
    }
}
