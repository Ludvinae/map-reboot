package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.World;
import libraries.FastNoiseLite;

import static com.yorkhuul.life.map.tools.RandomString.getRandomString;

public class RegionNoise implements GenerationStep {

    private int width;
    private int height;
    private int seed;
    private float strength;

    public RegionNoise(int width, int height, int seed, float strength) {
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.strength = strength;
    }

    public RegionNoise(int seed, float strength) {
        this(100, 100, seed, strength);
    }

    public RegionNoise(float strength) {
        this(100, 100, getRandomString(), strength);
    }

    @Override
    public void apply(World world) {
        float[][] regionNoise = generateNoiseData(seed, 0.2f);
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);

                float regionalValue = regionNoise[i][j];
                System.out.println("Region (" + j + "," + i + ") noise = " + regionalValue);

                region.setTargetAltitude(regionalValue);
                region.pullToTarget(strength);
            }
        }
    }

    public float[][] generateNoiseData(int seed, float frequency) {
        // Create and configure FastNoise object
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);

        // Gather noise data
        float[][] noiseData = new float[height][width];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                noiseData[y][x] = noise.GetNoise(x + 999, y + 999);
            }
        }
        return noiseData;
    }


}
