package com.yorkhuul.life.map.shape;

import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.zone.Region;


public class BlanketShape implements Shape{


    private float strength;

    public BlanketShape(float strength) {
        this.strength = strength;
    }


    @Override
    public float influence(Coordinates coords) {
        return (float) (-strength + Math.random() * (strength * 2));
    }

    @Override
    public boolean intersectsRegion(Region region) {
        return true;
    }
}
