package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.tools.RandomString;
import com.yorkhuul.life.map.zone.Region;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;
import libraries.FastNoiseLite;

public class Noise implements GenerationStep {

    private int seed;
    private float frequency;
    private int octaves;
    private float lacunarity;
    private float strength;


    public Noise(int seed, float frequency, int octaves, float lacunarity, float strength) {
        this.seed = seed;
        this.frequency = frequency;
        this.octaves = octaves;
        this.lacunarity = lacunarity;
        this.strength = strength;
    }

    public Noise(float strength) {
        this(RandomString.getRandomString(), 0.003f, 3, 1.5f, strength);
    }


    @Override
    public void apply(World world) {
        FastNoiseLite noise = createNoise(seed, frequency, octaves, lacunarity);

        world.forEachTile((region, localX, localY, worldX, worldY) -> {
            float value = noise.GetNoise(worldX, worldY);
            region.getTile(localX, localY).setAltitude(value * strength);
        });
    }

    private FastNoiseLite createNoise(int seed, float frequency, int octaves, float lacunarity) {
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetFractalType(FastNoiseLite.FractalType.FBm);
        noise.SetFractalOctaves(octaves);
        noise.SetFractalLacunarity(lacunarity);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);
        return noise;
    }

}
