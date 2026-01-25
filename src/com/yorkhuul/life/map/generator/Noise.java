package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.tools.RandomString;
import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.World;
import libraries.FastNoiseLite;

import static com.yorkhuul.life.map.tools.RandomString.getRandomString;

public class Noise implements GenerationStep {

    private int width;
    private int height;
    private String seed;

    public Noise(int width, int height, String seed) {
        this.width = width;
        this.height = height;
        this.seed = seed;
    }

    public Noise(String seed) {
        this(100, 100, seed);
    }

    public Noise() {
        this(100, 100, getRandomString());
    }

    @Override
    public void apply(World world) {
        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                Region region = world.getRegion(j, i);
                region.applyNoise(1);
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
