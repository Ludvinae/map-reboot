package com.yorkhuul.life.map.generator.hydrology;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.zone.World;

public class WaterErosion implements GenerationStep {

    private float strength;

    public WaterErosion(float strength) {
        this.strength = strength;
    }

    @Override
    public void apply(World world) {

    }
}
