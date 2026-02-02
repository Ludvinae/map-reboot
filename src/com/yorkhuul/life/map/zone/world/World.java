package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.steps.GenerationPipeline;
import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.RegionConsumer;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;
import com.yorkhuul.life.map.zone.tile.TileWithNeighborsConsumer;

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
    private GenerationPipeline pipeline;

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

    public float getSeaLevel() {
        return seaLevel;
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

    public void setPipeline(GenerationPipeline pipeline) {
        this.pipeline = pipeline;
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
        Region[][] result = new Region[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = new Region(x, y);
            }
        }
        return result;
    }

    public Tile getTileWithWorldCoordinates(int worldX, int worldY) {
        int size = Region.getSize();

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
            if (tile.getAltitude() >= 0) count.addAndGet(1);
        });

        int total = (height * width) * (Region.getSize() * Region.getSize());
        return (float) count.get() / total;
    }


    public List<Tile> getAllTiles() {
        List<Tile> tiles = new ArrayList<>();
        forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            tiles.add(tile);
        });

        return tiles;
    }


    public HydrologyContext getTilesContext() {
        List<TileWithCoordinates> tiles = new ArrayList<>();

        forEachTileWithNeighbors((region, localX, localY, worldX, worldY, tile, neighbors) -> {

            TileWithCoordinates lowestNeighbor = getLowestNeighbor(tile, neighbors);
            float slope = 0;
            float flow = 0;

            if (lowestNeighbor != null) {
                float distance;
                if (lowestNeighbor.getWorldX() == worldX || lowestNeighbor.getWorldY() == worldY) distance = 1;
                else distance = (float) Math.sqrt(2);
                slope = (tile.getAltitude() - lowestNeighbor.getAltitude()) / distance;
            }
            TileWithCoordinates currentTile = new TileWithCoordinates(tile, worldX, worldY, lowestNeighbor, slope, flow);
            tiles.add(currentTile);
        });
        return new HydrologyContext(tiles);
    }

    private TileWithCoordinates getLowestNeighbor(Tile tile, List<TileWithCoordinates> neighbors) {
        float altitude = tile.getAltitude();
        TileWithCoordinates lowestNeighbor = null;

        float altDeltaWeight = 1f;
        float flowWeight = 1f;
        float maxScore = 0;

        for (TileWithCoordinates neighbor : neighbors) {
            float neighborAltitude = neighbor.getAltitude();
            float altDelta = altitude - neighborAltitude;

            if (altDelta < 0) continue;

            float score = (altDelta * altDeltaWeight) + (neighbor.getFlow() * flowWeight);
            if (score > maxScore) {
                maxScore = score;
                lowestNeighbor = neighbor;
            }
        }
        return lowestNeighbor;
    }

    /*
    private TileWithCoordinates getLowestNeighbor(Tile tile, List<TileWithCoordinates> neighbors) {
        float surface = tile.waterSurface();
        TileWithCoordinates lowestNeighbor = null;

        for (TileWithCoordinates neighbor : neighbors) {
            float neighborSurface = neighbor.getTile().waterSurface();
            if (neighborSurface < surface) {
                surface = neighborSurface;
                lowestNeighbor = neighbor;
            }
        }
        return lowestNeighbor;
    }
     */


    public HydrologyContext getHydrologyContext() {
        if (pipeline == null) return null;
        else return pipeline.getContext();
    }

    public float getMaxCumulativeFlow() {
        float maxCumulativeFlow = 0;
        List<Tile> tiles = getAllTiles();
        for (Tile tile: tiles) {
            float cumulativeFlow = tile.getCumulativeFlow();
            if (cumulativeFlow > maxCumulativeFlow) maxCumulativeFlow = cumulativeFlow;
        }
        return maxCumulativeFlow;

    }

    public boolean isInBounds(int worldX, int worldY) {
        if (worldX < 0 || worldX > width) return false;
        if (worldY < 0 || worldY > height) return false;
        return true;
    }

}
