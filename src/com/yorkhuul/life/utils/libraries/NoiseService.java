package com.yorkhuul.life.utils.libraries;

import com.yorkhuul.life.utils.libraries.FastNoiseLite.*;

public class NoiseService {

    private final int seed;
    FastNoiseLite noise;

    public NoiseService(int seed) {
        this.seed = seed;
        createNoise();
    }

    private void createNoise() {
        this.noise = new FastNoiseLite(seed);
        noise.SetNoiseType(NoiseType.OpenSimplex2);
    }

    public void setFrequency(float frequency) {
        noise.SetFrequency(frequency);
    }

    public void setLacunarity(float lacunarity) {
        noise.SetFractalLacunarity(lacunarity);
    }

    public void setOctaves(int nbOctaves) {
        noise.SetFractalOctaves(nbOctaves);
    }

    public float sample(float x, float y, float frequency) {
        setFrequency(frequency);
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
        noise.SetFrequency(frequency);

        return noise.GetNoise(x + offset, y + offset);
    }

    /**
     * @param x
     * @param y
     * @param frequency : should be between 0.005 and 0.02
     * @return
     */
    public float sampleFromZeroToOne(float x, float y, float frequency, float factor) {
        float rain = noise.GetNoise(x, y); // [-1 ; 1]
        float ponderedRain = (rain + 1f) *05f; // [0 ; 1]

        // factor = altitude normalisée [0 ; 1]
        float altitudeFactor = 0.5f + factor * 0.5f;

        // pluie pondérée
        return ponderedRain * altitudeFactor; // [0 ; 1]
    }

    public void setFractalType(FractalType fractalType) {
        noise.SetFractalType(fractalType);
    }
}
