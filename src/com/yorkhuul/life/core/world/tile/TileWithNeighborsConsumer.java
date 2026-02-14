package com.yorkhuul.life.core.world.tile;

import com.yorkhuul.life.core.world.region.Region;

import java.util.List;

@FunctionalInterface
public interface TileWithNeighborsConsumer {
    void accept(
            Region region,
            int localX, int localY,
            int worldX, int worldY,
            Tile tile,
            List<TileWithCoordinates> neighbors
    );
}
