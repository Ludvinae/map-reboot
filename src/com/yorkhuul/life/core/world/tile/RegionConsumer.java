package com.yorkhuul.life.core.world.tile;

import com.yorkhuul.life.core.world.region.Region;

@FunctionalInterface
public interface RegionConsumer {
    void accept(Region region, int localX, int localY, int worldX, int worldY);
}
