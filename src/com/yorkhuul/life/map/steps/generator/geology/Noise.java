package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Noise implements GenerationStep {

    private float frequency;
    private int offset;
    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();


    public Noise(float frequency, int offset, float strength) {
        this.frequency = frequency;
        this.offset = offset;
        this.strength = strength;
    }

    public Noise(float frequency, float strength) {
        this(frequency, 57, strength);
    }

    public Noise(float strength) {
        this(0.003f, 57, strength);
    }


    @Override
    public void apply(World world, EditorContext context) {
        NoiseService noise = world.getNoise();

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            float value = noise.sampleOffset(worldX, worldY, frequency, offset);
            region.getTile(localX, localY).setAltitude(value * strength);
        });
        //consoleFeedback("Noise");
    }

    @Override
    public String getName() {
        return "Noise";
    }

    @Override
    public List<Parameter<?>> getParameters() {
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
