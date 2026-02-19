package com.yorkhuul.life.core.world;

import com.yorkhuul.life.core.engine.pipeline.foundation.WorldConfig;
import com.yorkhuul.life.core.engine.pipeline.hydrology.HydrologyContext;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.core.world.tile.*;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.utils.random.RandomSeed;
import com.yorkhuul.life.core.world.region.Region;

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
    private float seaLevel = 0;
    private HydrologyContext context;

    // Constructors
    public World() {
        this(DEFAULT_NAME, DEFAULT_SIZE, DEFAULT_SIZE, RandomSeed.getRandomSeed(), 50);
    }

    public World(WorldConfig config) {
        this(config.getName(), config.getWidth(), config.getHeight(), config.getSeed().hashCode(), config.getEquatorTemp());
    }

    public World(String name, int seed) {
        this(name, DEFAULT_SIZE, DEFAULT_SIZE, seed, 50);
    }

    public World(String name, int width, int height, int seed, int equatorTemp) {
        this.name = name;
        setHeight(height);
        setWidth(width);
        this.regions = createRegions();
        this.noise = new NoiseService(seed);

        int regionSize = Region.getSize();
        WorldQueries.worldHeight = height * regionSize;
        WorldQueries.worldWidth = width * regionSize;
        WorldQueries.equatorTemp = equatorTemp;
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

    public float getSeaLevel() {
        return seaLevel;
    }

    public HydrologyContext getHydrologyContext() {
        return context;
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

    public void setSeaLevel(float seaLevel) {
        if (seaLevel < -1f) seaLevel = -1f;
        if (seaLevel > 1f) seaLevel = 1f;
        this.seaLevel = seaLevel;
    }

    // Others
    @Override
    public String toString() {
        return name + ", a world of size " + height + " x " + width;
    }

    // Methods
    private Region[][] createRegions() {
        Region[][] result = new Region[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = new Region(x, y);
            }
        }
        return result;
    }

    public Tile getTileWithWorldCoordinates(int worldX, int worldY) {
        int size = Region.getSize();

        worldX = worldX % getWidthInTiles();
        worldY = worldY % getHeightInTiles();

        int regionX = worldX / size;
        int regionY = worldY / size;

        int localX = worldX % size;
        int localY = worldY % size;

        Region region = getRegion(regionX, regionY);
        return region.getTile(localX, localY);
    }

    public void applyShapeEffect(ShapeEffect effect) {
        for (int ry = 0; ry < height; ry++) {
            for (int rx = 0; rx < width; rx++) {
                Region region = regions[ry][rx];
                if (effect.intersectsRegion(region)) {
                    region.applyShapeEffect(effect, this);
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

    public void forEachTile(RegionConsumer consumer) {
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
        List<TileWithCoordinates> neighbors = new ArrayList<>(8);

        addNeighborIfValid(neighbors, worldX - 1, worldY);
        addNeighborIfValid(neighbors, worldX + 1, worldY);
        addNeighborIfValid(neighbors, worldX, worldY - 1);
        addNeighborIfValid(neighbors, worldX, worldY + 1);
        addNeighborIfValid(neighbors, worldX - 1, worldY - 1);
        addNeighborIfValid(neighbors, worldX + 1, worldY + 1);
        addNeighborIfValid(neighbors, worldX + 1, worldY - 1);
        addNeighborIfValid(neighbors, worldX - 1, worldY + 1);

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
        // temporary change, need to refactor
        list.add(new TileWithCoordinates(region.getTile(localX, localY), wx, wy));
    }

    public float percentImmerged() {
        AtomicInteger count = new AtomicInteger();
        forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            if (tile.getAltitude() >= seaLevel) count.addAndGet(1);
        });

        int total = (height * width) * (Region.getSize() * Region.getSize());
        return (float) count.get() / total;
    }

    public boolean isInBounds(int worldX, int worldY) {
        if (worldX < 0 || worldX >= getWidthInTiles()) return false;
        if (worldY < 0 || worldY >= getHeightInTiles()) return false;
        return true;
    }

    public void newHydrologyContext() {
        this.context = new HydrologyContext();
    }

    public void applyBaseTemp() {
        WorldIterations.forEachTile(this, ((worldX, worldY, tile) -> {
            float temp = WorldQueries.getLattitudeInfluence(worldY);
            tile.setBaseTemp(temp);
        }));
    }
}
