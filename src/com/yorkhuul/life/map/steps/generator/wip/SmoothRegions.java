package com.yorkhuul.life.map.steps.generator.wip;

import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.world.World;

public class SmoothRegions implements GenerationStep {

    private float strength;

    public SmoothRegions(float strength) {
        setStrength(strength);
    }

    public void setStrength(float strength) {
        this.strength = Math.max(0f, Math.min(1f, strength));
    }

    @Override
    public void apply(World world) {
        for (int ry = 0; ry < world.getHeight(); ry++) {
            for (int rx = 0; rx < world.getWidth(); rx++) {
                Region region = world.getRegion(rx, ry);
                region.normalize(strength);
            }
        }
        consoleFeedback("Smoothing regions");
    }
}
