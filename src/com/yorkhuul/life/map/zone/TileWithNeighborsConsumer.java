package com.yorkhuul.life.map.zone;

@FunctionalInterface
public interface TileWithNeighborsConsumer {
    void accept(
            Region region,
            int localX, int localY,
            int worldX, int worldY,
            Tile tile,
            Tile[] neighbors
    );
}
