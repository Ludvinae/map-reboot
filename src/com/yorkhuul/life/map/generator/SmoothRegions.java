package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.World;

public class SmoothRegions implements GenerationStep{

    private float strength;

    public SmoothRegions(float strength) {
        this.strength = strength;
    }


    @Override
    public void apply(World world) {

    }
}
