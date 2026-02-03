package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.tools.ToFloatFunction;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;


public final class WorldQueries {
    // Lecture / calcul a partir du monde

    private static final float SQRT2 = 1.41421356f;

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

    public static TileWithCoordinates getLowestSurfaceNeighbor(
            World world, int x, int y
    ) {
        return getLowestNeighborBy(world, x, y, Tile::waterSurface);
    }



    public static float getSlope(int worldX, int worldY, Tile tile, TileWithCoordinates neighbor) {
        float altitude = tile.getAltitude();
        float neighborAltitude = neighbor.getAltitude();
        float distance;
        if (worldX == neighbor.getWorldX() || worldY == neighbor.getWorldY()) distance = 1;
        else distance = SQRT2;

        return (altitude - neighborAltitude) * distance;
    }

    public static float getWaterSurface(World world, int worldX, int worldY) {
        Tile tile = world.getTileWithWorldCoordinates(worldX, worldY);
        return tile.getAltitude() + tile.getWater();
    }
}
