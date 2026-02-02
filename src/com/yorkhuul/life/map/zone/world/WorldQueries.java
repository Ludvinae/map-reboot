package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileConsumer;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;

import java.util.ArrayList;
import java.util.List;

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

    public static List<TileWithCoordinates> getNeighbors(World world, int worldX, int worldY){
        List<TileWithCoordinates> neighbors = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int x = worldX + j;
                int y = worldY + i;

                if (world.isInBounds(x, y)) {
                    Tile neighbor = world.getTileWithWorldCoordinates(x, y);
                    neighbors.add(new TileWithCoordinates(neighbor, x, y));
                }
            }
        }
        return neighbors;
    }

    public static TileWithCoordinates getLowestNeighbor(Tile tile, List<TileWithCoordinates> neighbors) {
        float altitude = tile.getAltitude();
        float minAltitude = altitude;
        TileWithCoordinates lowestNeighbor = null;

        for (TileWithCoordinates neighbor: neighbors) {
            float neighborAltitude = neighbor.getAltitude();
            if (neighborAltitude < minAltitude) {
                minAltitude = neighborAltitude;
                lowestNeighbor = neighbor;
            }
        }
        return lowestNeighbor;
    }


    public static float getSlope(Tile tile, Tile neighbor, float distance) {
        float altitude = tile.getAltitude();
        float neighborAltitude = neighbor.getAltitude();

        return (altitude - neighborAltitude) * distance;
    }
}
