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
        /*
        int height = world.getHeightInTiles();
        int width = world.getWidthInTiles();
        float[][] noiseData = generateNoiseData(width, height, seed, frequency);
        int size = Region.getSize();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int regionX = Math.floorDiv(j, size);
                int regionY = Math.floorDiv(i, size);
                Region region = world.getRegion(regionX, regionY);

                int x = j % size;
                int y = i % size;
                Tile tile = region.getTile(x, y);

                tile.setAltitude(noiseData[i][j] * strength);
            }
        }
         */
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

    public float[][] generateNoiseData(int width, int height, int seed, float frequency) {
        // Create and configure FastNoise object
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetFractalType(FastNoiseLite.FractalType.FBm);
        noise.SetFractalOctaves(5);
        noise.SetFractalLacunarity(1.7f);
        noise.SetFractalGain(0.5f);
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
