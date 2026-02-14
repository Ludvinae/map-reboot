package com.yorkhuul.life.core.engine.shape;

import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.core.world.region.Region;

public interface Shape {

    float influence(Coordinates coords);
    boolean intersectsRegion(Region region);

    boolean contains(int worldX, int worldY);
}
