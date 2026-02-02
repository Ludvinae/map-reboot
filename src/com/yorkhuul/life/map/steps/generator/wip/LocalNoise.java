package com.yorkhuul.life.map.steps.generator.wip;

import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.tools.RandomSeed;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.tile.Tile;
import com.yorkhuul.life.map.zone.world.World;
import libraries.FastNoiseLite;

public class LocalNoise implements GenerationStep {

    private final int seed;
    private final float frequency;
    private final float amplitude;

    public LocalNoise(int seed, float frequency, float amplitude) {
        this.seed = seed;
        this.frequency = frequency;
        this.amplitude = amplitude;
    }

    public LocalNoise(float amplitude) {
        this(RandomSeed.getRandomSeed(), 0.2f, amplitude);
    }

    @Override
    public void apply(World world) {
        FastNoiseLite noise = new FastNoiseLite(seed);
        noise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        noise.SetFrequency(frequency);

        for (int ry = 0; ry < world.getHeight(); ry++) {
            for (int rx = 0; rx < world.getWidth(); rx++) {
                Region region = world.getRegion(rx, ry);

                applyToRegion(region, noise, rx, ry);
            }
        }
    }

    private void applyToRegion(Region region, FastNoiseLite noise, int rx, int ry) {
        int size = Region.getSize();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                // coordonnées monde
                int worldX = rx * size + x;
                int worldY = ry * size + y;

                float localNoise = noise.GetNoise(worldX, worldY);

                Tile tile = region.getTile(x, y);
                float factor = Math.max(-0.1f, tile.getAltitude());

                tile.addAltitude(localNoise * amplitude * factor);
            }
        }
    }
}
