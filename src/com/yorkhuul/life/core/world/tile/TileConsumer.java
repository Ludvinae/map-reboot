package com.yorkhuul.life.core.world.tile;

@FunctionalInterface
public interface TileConsumer {
    void accept(int worldX, int worldY, Tile tile);
}
