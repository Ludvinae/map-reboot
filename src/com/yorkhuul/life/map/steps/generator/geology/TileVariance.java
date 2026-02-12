package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileVariance implements GenerationStep {

    //private float factor;
    private float noiseFrequency;
    private float amplitude;
    List<Parameter<?>> parameters = new ArrayList<>();

    public TileVariance(float noiseFrequency, float amplitude) {
        this.noiseFrequency = noiseFrequency;
        this.amplitude = amplitude;
    }


    @Override
    public void apply(World world, EditorContext context) {

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            Tile tile = region.getTile(localX, localY);
            NoiseService noise = world.getNoise();
            double factor = Math.exp(noise.sample(worldX, worldY, noiseFrequency) * amplitude);
            tile.multiplyAltitude((float) factor);
        });
    }

    @Override
    public String getName() {
        return "Altitude variance";
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return parameters;
    }
}
