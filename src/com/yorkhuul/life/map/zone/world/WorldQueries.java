package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileConsumer;

import java.util.ArrayList;
import java.util.List;

public class WorldQueries {
    // Lecture / calcul a partir du monde

    private static final float SQRT2 = 1.41f;

    public static TileConsumer getTileContext(World world, TileConsumer context) {

        Tile neighbor = world.getLowestNeighbors(context);
        float distance; // 1 if orthogonal, 1.41 otherwise
        float slope = WorldQueries.getSlope(tile, neighbor, distance);


    }

    public static List<Tile> getNeighbor(World world, int worldX, int worldY){
        List<Tile> neighbors = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                Tile neighbor = world
                neighbors.add(neighbor);
            }
        }
    }


    public static float getSlope(Tile tile, Tile neighbor, float distance) {
        float altitude = tile.getAltitude();
        float neighborAltitude = neighbor.getAltitude();

        return (altitude - neighborAltitude) * distance;
    }
}
