package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.World;

public class Noise implements GenerationStep {

    private float strength;

    public Noise(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);
                region.applyNoise(strength);
            }
        }
    }
}
