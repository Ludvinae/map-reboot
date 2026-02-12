package com.yorkhuul.life.map.steps.generator.wip;

import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.zone.region.Region;
import com.yorkhuul.life.map.zone.world.World;
import libraries.FastNoiseLite;

import java.util.ArrayList;
import java.util.List;

import static com.yorkhuul.life.map.tools.RandomSeed.getRandomSeed;

public class RegionNoise implements GenerationStep {

    private int width;
    private int height;
    private int seed;
    private float strength;
    List<Parameter<?>> parameters = new ArrayList<>();

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
        this(100, 100, getRandomSeed(), strength);
    }

    @Override
    public void apply(World world, EditorContext editorContext) {
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
                // multiply x position to get large spacing
                noiseData[y][x] = noise.GetNoise(x * 999, y * 999);
            }
        }
        return noiseData;
    }


    @Override
    public String getName() {
        return "Large noise brush";
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return parameters;
    }
}
