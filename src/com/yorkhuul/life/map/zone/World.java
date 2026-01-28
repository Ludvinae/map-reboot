package com.yorkhuul.life.map.zone;

import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.tools.TileWithCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class World {

    // Attributes
    private final String name;
    private int height;
    private int width;
    private final Region[][] regions;
    private static final int DEFAULT_SIZE = 64;
    private static final String DEFAULT_NAME = "Gaïa";
    private final NoiseService noise;

    // Constructors
    public World() {
        this(DEFAULT_NAME, DEFAULT_SIZE, DEFAULT_SIZE, RandomSeed.getRandomSeed());
    }

    public World(String name, int seed) {
        this(name, DEFAULT_SIZE, DEFAULT_SIZE, seed);
    }

    public World(String name, int width, int height, int seed) {
        this.name = name;
        setHeight(height);
        setWidth(width);
        this.regions = createRegions();
        this.noise = new NoiseService(seed);
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

    public NoiseService getNoise() {
        return noise;
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

    public void applyReliefToRegions() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                Region region = getRegion(j, i);
                region.calculRelief();
            }
        }
    }

    public void forEachTile(TileConsumer consumer) {
        int regionSize = Region.getSize();

        for (int ry = 0; ry < getHeight(); ry++) {
            for (int rx = 0; rx < getWidth(); rx++) {
                Region region = getRegion(rx, ry);

                for (int y = 0; y < regionSize; y++) {
                    for (int x = 0; x < regionSize; x++) {

                        int worldX = rx * regionSize + x;
                        int worldY = ry * regionSize + y;

                        consumer.accept(region, x, y, worldX, worldY);
                    }
                }
            }
        }
    }

    public void forEachTileWithNeighbors(TileWithNeighborsConsumer consumer) {
        int regionSize = Region.getSize();

        for (int ry = 0; ry < getHeight(); ry++) {
            for (int rx = 0; rx < getWidth(); rx++) {
                Region region = getRegion(rx, ry);

                for (int y = 0; y < regionSize; y++) {
                    for (int x = 0; x < regionSize; x++) {
                        Tile tile = region.getTile(x, y);

                        int worldX = rx * regionSize + x;
                        int worldY = ry * regionSize + y;

                        List<TileWithCoordinates> neighbors = getNeighbors(worldX, worldY);
                        consumer.accept(region, x, y, worldX, worldY, tile, neighbors);
                    }
                }
            }
        }
    }

    public List<TileWithCoordinates> getNeighbors(int worldX, int worldY) {
        List<TileWithCoordinates> neighbors = new ArrayList<>(4);

        addNeighborIfValid(neighbors, worldX - 1, worldY);
        addNeighborIfValid(neighbors, worldX + 1, worldY);
        addNeighborIfValid(neighbors, worldX, worldY - 1);
        addNeighborIfValid(neighbors, worldX, worldY + 1);

        return neighbors;
    }

    private void addNeighborIfValid(List<TileWithCoordinates> list, int wx, int wy) {
        if (wx < 0 || wy < 0) return;
        if (wx >= getWidthInTiles() || wy >= getHeightInTiles()) return;

        int size = Region.getSize();

        int regionX = wx / size;
        int regionY = wy / size;

        int localX = wx % size;
        int localY = wy % size;

        Region region = getRegion(regionX, regionY);
        list.add(new TileWithCoordinates(region.getTile(localX, localY), wx, wy));
    }

    public float percentImmerged() {
        AtomicInteger count = new AtomicInteger();
        forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            if (tile.getAltitude() >= 0) count.addAndGet(1);
        });

        int total = (height * width) * (Region.getSize() * Region.getSize());
        return (float) count.get() / total;
    }

    public void computeLowestTileNeighbor() {
        this.forEachTileWithNeighbors((region, localX, localY, worldX, worldY, tile, neighbors) -> {
            float altitude = tile.getAltitude();
            Tile lowestNeighbor = null;

            for (TileWithCoordinates neighbor : neighbors) {
                float neighborAlt = neighbor.tile().getAltitude();
                if (neighborAlt < altitude) {
                    lowestNeighbor = neighbor.tile();
                    altitude = neighborAlt;
                }
            }
            tile.setFlowTarget(lowestNeighbor);
        });
    }

}
