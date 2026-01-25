package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.World;
import libraries.FastNoiseLite;

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

    public float[][] generateNoiseData() {
        // Create and configure FastNoise object
        FastNoiseLite noise = new FastNoiseLite();
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);

        // Gather noise data
        float[][] noiseData = new float[100][100];

        for (int y = 0; y < 100; y++)
        {
            for (int x = 0; x < 100; x++)
            {
                noiseData[y][x] = noise.GetNoise(y, x);
            }
        }

        return noiseData;
    }

}
