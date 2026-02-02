package com.yorkhuul.life.map.zone.world;

import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.tile.TileConsumer;
import com.yorkhuul.life.map.zone.tile.TileWithCoordinates;

public final class WorldIterations {
    // Parcours des elements du monde

    public static void forEachTile(World world, TileConsumer consumer) {
        for (int y = 0; y < world.getHeightInTiles(); y++) {
            for (int x = 0; x < world.getWidthInTiles(); x++) {
                consumer.accept(x, y, world.getTileWithWorldCoordinates(x, y));
            }
        }
    }

}
