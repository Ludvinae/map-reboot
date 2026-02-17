package com.yorkhuul.life.utils.libraries;

import com.yorkhuul.life.utils.libraries.FastNoiseLite.*;

public class NoiseService {

    private final int seed;
    FastNoiseLite noise;

    public NoiseService(int seed) {
        this.seed = seed;
    }

    private FastNoiseLite createNoise(float frequency) {
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetNoiseType(NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);

        return noise;
    }

    public void setLacunarity(float lacunarity) {
        noise.SetFractalLacunarity(lacunarity);
    }

    public void setOctaves(int nbOctaves) {
        noise.SetFractalOctaves(nbOctaves);
    }

    public float sample(float x, float y, float frequency) {
        noise = createNoise(frequency);
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
        noise.SetNoiseType(NoiseType.OpenSimplex2);
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

    public void setFractalType(String fractalType) {
        FastNoiseLite.FractalType type;
        switch (fractalType) {
            case "PingPong": type = FractalType.PingPong; break;
            case "FBn": type = FractalType.FBm; break;
            case "Ridged": type = FractalType.Ridged; break;
            case "Progressive": type = FractalType.DomainWarpProgressive; break;
            case "Independant": type = FractalType.DomainWarpIndependent; break;
            default: type = FractalType.None; break;
        }

        noise.SetFractalType(type);
    }
}
