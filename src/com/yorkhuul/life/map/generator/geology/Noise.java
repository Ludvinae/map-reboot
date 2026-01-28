package com.yorkhuul.life.map.generator.geology;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.tools.NoiseService;
import com.yorkhuul.life.map.zone.World;

public class Noise implements GenerationStep {

    private float frequency;
    private int offset;
    private float strength;


    public Noise(float frequency, int offset, float strength) {
        this.frequency = frequency;
        this.offset = offset;
        this.strength = strength;
    }

    public Noise(float strength) {
        this(0.003f, 57, strength);
    }


    @Override
    public void apply(World world) {
        NoiseService noise = world.getNoise();

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            float value = noise.sampleOffset(worldX, worldY, frequency, offset);
            region.getTile(localX, localY).setAltitude(value * strength);
        });
        consoleFeedback("Noise");
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
