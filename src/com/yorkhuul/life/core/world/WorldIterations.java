package com.yorkhuul.life.core.world;

import com.yorkhuul.life.core.world.tile.TileConsumer;

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
