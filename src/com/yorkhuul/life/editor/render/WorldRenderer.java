package com.yorkhuul.life.editor.render;

import com.yorkhuul.life.core.viewdata.*;
import com.yorkhuul.life.core.world.World;
import com.yorkhuul.life.editor.render.color.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;


import static com.yorkhuul.life.utils.position.ArraytoMatrixIndex.getIndex;

public class WorldRenderer {

    private int width;
    private int height;
    private BufferedImage image;

    public WorldRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        setImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage() {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private void applyMapView(World world, MapView mapView, MapToColor mapToColor) {
        float[] map = mapView.generateMap(world);
        //System.out.println(Arrays.toString(map));

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = getIndex(x, y, width);
                Color color = mapToColor.getColor(map[index]);
                image.setRGB(x, y, color.getRGB());
            }
        }
    }

    public void generateAltitudeImage(World world) {
        AltitudeMapView mapView = new AltitudeMapView();
        AltitudeMapToColor mapColor = new AltitudeMapToColor();

        applyMapView(world, mapView, mapColor);
    }

    public void generateHeightmapImage(World world) {
        AltitudeMapView mapView = new AltitudeMapView();
        HeightMapToColor mapColor = new HeightMapToColor();

        applyMapView(world, mapView, mapColor);
    }

    public void generateFlowImage(World world) {
        CumulativeFlowMapView mapView = new CumulativeFlowMapView();
        FlowMapToColor mapColor = new FlowMapToColor();

        applyMapView(world, mapView, mapColor);
    }

    public void generateRiverImage(World world) {
        RiverMapView mapView = new RiverMapView();
        RiverMapToColor mapColor = new RiverMapToColor();

        applyMapView(world, mapView, mapColor);
    }


    public void exportImage(String name) {
        //String name = world.getName() + "_" + System.currentTimeMillis() + type + ".png";
        ImageExporter.saveAsPng(image, "image_output/" + name + ".png");
    }

    /*
    public void generateReliefImage() {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);
                Color color = reliefToColor(region.getRelief());
                image.setRGB(j, i, color.getRGB());
            }
        }
    }

    public void generateWaterImage() {
        //world.adjustWaterLevel();
        HydrologyContext context = world.getHydrologyContext();
        int width = WorldQueries.getWorldWidth();

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            int index = getIndex(worldX, worldY, width);
            Color color;
            float water = context.water[index];
            if (water + tile.getAltitude() <= 0) context.water[index] = 1; // temp fix to display sea water
            color = waterToColor(water);
            image.setRGB(worldX, worldY, color.getRGB());
        });
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

    public Color waterToColor(float water) {
        if (water == 0) return new Color(255, 255, 255);
        else if (water <= 0.1) return new Color(230, 225, 250);
        else if (water <= 0.2) return new Color(200, 200, 240);
        else if (water <= 0.3) return new Color(153, 175, 230);
        else if (water <= 0.4) return new Color(102, 150, 220);
        else if (water <= 0.5) return new Color(51, 125, 210);
        else if (water <= 0.6) return new Color(0, 100, 190);
        else if (water <= 0.7) return new Color(0, 75, 160);
        else if (water <= 0.8) return new Color(0, 50, 130);
        else if (water <= 0.9) return new Color(0, 25, 100);
        else return new Color(0, 0, 80);
    }

     */






}
