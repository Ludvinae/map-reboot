package com.yorkhuul.life.map.zone;

@FunctionalInterface
public interface TileConsumer {
    void accept(Region region, int localX, int localY, int worldX, int worldY);
}
