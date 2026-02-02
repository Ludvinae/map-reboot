package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileContext;

public class WorldQueries {
    // Lecture / calcul a partir du monde

    public static TileContext getTileContext(World world, TileContext context) {

        Tile neighbor = world.getLowestNeighbors(context.);
        float slope = WorldQueries.getSlope();


    }
}
