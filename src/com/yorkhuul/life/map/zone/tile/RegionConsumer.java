package com.yorkhuul.life.map.zone.tile;

import com.yorkhuul.life.map.zone.region.Region;

@FunctionalInterface
public interface RegionConsumer {
    void accept(Region region, int localX, int localY, int worldX, int worldY);
}
