package com.yorkhuul.life.map.zone.tile;

@FunctionalInterface
public interface TileConsumer {
    void accept(int worldX, int worldY, Tile tile);
}
