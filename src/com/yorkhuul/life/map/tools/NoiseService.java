package com.yorkhuul.life.map.tools;

import libraries.FastNoiseLite;

public class NoiseService {

    private final int seed;

    public NoiseService(int seed) {
        this.seed = seed;
    }

    private FastNoiseLite createNoise(float frequency) {
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);

        return noise;
    }

    public float sample(float x, float y, float frequency) {
        FastNoiseLite noise = createNoise(frequency);
        return noise.GetNoise(x, y);
    }

    /**
     *
     * @param x
     * @param y
     * @param frequency
     * @param offset
     * @return noise with an offset to avoid getting 0 as a return value when x and y are 0
     */
    public float sampleOffset(float x, float y, float frequency, int offset) {
        FastNoiseLite noise = new FastNoiseLite(seed + offset);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);

        return noise.GetNoise(x, y);
    }

    /**
     * @param x
     * @param y
     * @param frequency : should be between 0.005 and 0.02
     * @return
     */
    public float sampleFromZeroToOne(float x, float y, float frequency, float factor) {
        FastNoiseLite noise = createNoise(frequency);
        float rain = noise.GetNoise(x, y); // [-1 ; 1]
        float ponderedRain = (rain + 1f) *05f; // [0 ; 1]

        // factor = altitude normalisée [0 ; 1]
        float altitudeFactor = 0.5f + factor * 0.5f;

        // pluie pondérée
        return ponderedRain * altitudeFactor; // [0 ; 1]
    }
}
