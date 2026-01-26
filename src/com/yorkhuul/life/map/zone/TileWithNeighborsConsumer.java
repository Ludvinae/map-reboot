package com.yorkhuul.life.map.zone;

import com.yorkhuul.life.map.tools.TileWithCoordinates;

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
