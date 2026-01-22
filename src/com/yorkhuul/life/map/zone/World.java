package com.yorkhuul.life.map.zone;

import com.yorkhuul.life.map.effect.ShapeEffect;


public class World {

    // Attributes
    private final String name;
    private int height;
    private int width;
    private final Region[][] regions;
    private static final int DEFAULT_SIZE = 64;
    private static final String DEFAULT_NAME = "Gaïa";

    // Constructors
    public World() {
        this(DEFAULT_NAME, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public World(String name) {
        this(name, DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public World(String name, int width, int height) {
        this.name = name;
        setHeight(height);
        setWidth(width);
        this.regions = createRegions();
    }

    // Getters
    public String getName() {
        return name;
    }

    public Region getRegion(int x, int y) {
        return regions[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidthInTiles() {
        return width * Region.getSize();
    }

    public int getHeightInTiles() {
        return height * Region.getSize();
    }

    // Setters
    public void setHeight(int height) {
        if (height < 1) {
            this.height = 1;
        } else if (height > 1024) {
            this.height = 1024;
        } else {
            this.height = height;
        }
    }

    public void setWidth(int width) {
        if (width < 1) {
            this.width = 1;
        } else if (width > 1024) {
            this.width = 1024;
        } else {
            this.width = width;
        }
    }

    // Others
    @Override
    public String toString() {
        return name + ", a world of size " + height + " x " + width;
    }

    // Methods
    private Region[][] createRegions() {
        Region[][] result = new Region[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = new Region(x, y);
            }
        }
        return result;
    }

    public void applyShapeEffect(ShapeEffect effect) {
        for (int ry = 0; ry < height; ry++) {
            for (int rx = 0; rx < width; rx++) {
                Region region = regions[ry][rx];
                if (effect.intersectsRegion(region)) {
                    region.applyShapeEffect(effect);
                }
            }
        }
    }
}
