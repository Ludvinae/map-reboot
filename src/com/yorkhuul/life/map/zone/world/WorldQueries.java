package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.steps.generator.hydrology.HydrologyContext;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.ToFloatFunction;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;

import java.util.ArrayList;
import java.util.List;


public class WorldQueries {
    // Lecture / calcul a partir du monde

    private static final float SQRT2 = 1.41421356f;
    public static final int BUCKETS = 100;
    protected static int worldHeight;
    protected static int worldWidth;

    /*
    public static TileConsumer getTileContext(World world, int worldX, int worldY) {
        Tile tile = world.getTileWithWorldCoordinates(worldX, worldY);
        TileWithCoordinates neighbor = getLowestNeighbor(tile, getNeighbors(world, worldX, worldY));
        float distance; // 1 if orthogonal, 1.41 otherwise
        if (neighbor.getWorldX() == worldX || neighbor.getWorldY() == worldY) distance = 1f;
        else distance = SQRT2;
        float slope = WorldQueries.getSlope(tile, neighbor.getTile(), distance);

    }
     */

    private static TileWithCoordinates getLowestNeighborBy(World world, int worldX, int worldY, ToFloatFunction<Tile> metric) {
        float minValue = metric.applyAsFloat(world.getTileWithWorldCoordinates(worldX, worldY));
        TileWithCoordinates lowestNeighbor = null;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int x = worldX + j;
                int y = worldY + i;
                if (!world.isInBounds(x, y)) continue;
                Tile neighbor = world.getTileWithWorldCoordinates(x, y);
                //System.out.println(neighbor);
                float value = metric.applyAsFloat(neighbor);
                //System.out.println(value);
                if (value < minValue) {
                    minValue = value;
                    lowestNeighbor = new TileWithCoordinates(neighbor, x, y);
                }
            }
        }
        return lowestNeighbor;
    }

    public static TileWithCoordinates getLowestAltitudeNeighbor(
            World world, int x, int y
    ) {

        return getLowestNeighborBy(world, x, y, Tile::getAltitude);
    }


    public static float getSlope(int worldX, int worldY, Tile tile, TileWithCoordinates neighbor) {
        float altitude = tile.getAltitude();
        float neighborAltitude = neighbor.getAltitude();
        float distance;
        if (worldX == neighbor.getWorldX() || worldY == neighbor.getWorldY()) distance = 1;
        else distance = SQRT2;

        return (altitude - neighborAltitude) * distance;
    }


    public static int getWorldHeight() {
        return worldHeight;
    }

    public static int getWorldWidth(){
        return worldWidth;
    }

    private static int getBucketFromAltitude(float altitude) {
        int bucket = (int) (((altitude + 1f) * 0.5f) * (BUCKETS - 1));
        return Math.clamp(bucket, 0, BUCKETS - 1);
    }

    public static List<Coordinates>[] getTilesFromBuckets(World world) {
        List<Coordinates>[] buckets = new ArrayList[BUCKETS];
        for (int i = 0; i < BUCKETS; i++) buckets[i] = new ArrayList<>();

        WorldIterations.forEachTile(world, (x, y, tile) -> {
            Coordinates coords = new Coordinates(x, y);
            int b = getBucketFromAltitude(tile.getAltitude());
            buckets[b].add(coords);
        });
        return buckets;
    }
}
