package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.Region;

public interface Shape {

    float influence(Coordinates coords);
    boolean intersectsRegion(Region region);
}
