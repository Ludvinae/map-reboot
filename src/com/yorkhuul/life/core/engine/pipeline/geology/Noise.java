package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.LogParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.utils.libraries.NoiseService;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class Noise implements GenerationStep<NoiseConfig> {

    @Override
    public void apply(World world, NoiseConfig config) {
        NoiseService noise = world.getNoise();

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            float value = noise.sampleOffset(worldX, worldY, config.getFrequency(), config.getOffset());
            region.getTile(localX, localY).setAltitude(value * config.getStrength());
        });
        //consoleFeedback("Noise");
    }

    @Override
    public String getName() {
        return "Noise";
    }

    @Override
    public List<Parameter<?>> createParameters(NoiseConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new LogParameter("Pattern repetition frequency", 0.00001f, 0.1f, config.getFrequency(), 500, config::setFrequency));
        parameters.add(new FloatParameter("Altitude amplitude", 0.01f, 1f, config.getStrength(), 0.01f, config::setStrength));

        return parameters;
    }

    /* Deprecated
    private FastNoiseLite createNoise(int seed, float frequency, int octaves, float lacunarity) {
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetFractalType(FastNoiseLite.FractalType.FBm);
        noise.SetFractalOctaves(octaves);
        noise.SetFractalLacunarity(lacunarity);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);
        return noise;
    }

     */

}
