package com.yorkhuul.life.map.zone.tile;

@FunctionalInterface
public interface TileContext {
    void accept(int worldX, int worldY, Tile tile);
}
