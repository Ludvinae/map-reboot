package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.Region;

public class RegionShape implements Shape {

    private float strength;

    public RegionShape(float strength) {
        this.strength = strength;
    }


    @Override
    public float influence(Coordinates coords) {
        return strength;
    }

    @Override
    public boolean intersectsRegion(Region region) {
        return true;
    }
}
