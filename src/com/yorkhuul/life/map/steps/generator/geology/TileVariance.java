package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;

public class TileVariance implements GenerationStep {

    //private float factor;
    private float noiseFrequency;
    private float amplitude;

    public TileVariance(float noiseFrequency, float amplitude) {
        this.noiseFrequency = noiseFrequency;
        this.amplitude = amplitude;
    }


    @Override
    public void apply(World world) {

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            NoiseService noise = world.getNoise();
            double factor = Math.exp(noise.sample(worldX, worldY, noiseFrequency) * amplitude);
            tile.multiplyAltitude((float) factor);
        });
    }
}
