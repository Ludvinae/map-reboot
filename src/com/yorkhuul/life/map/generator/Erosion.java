package com.yorkhuul.life.map.generator;

import com.yorkhuul.life.map.tools.TileWithCoordinates;
import com.yorkhuul.life.map.zone.Tile;
import com.yorkhuul.life.map.zone.World;

public class Erosion implements GenerationStep {

    private int iterations;
    private float seaLevel;
    private float minHeightDelta;
    private float strength;

    public Erosion(int iterations, float seaLevel, float minHeightDelta, float strength) {
        setIterations(iterations);
        this.seaLevel = seaLevel;
        this.minHeightDelta = minHeightDelta;
        this.strength = strength;
    }

    public Erosion(float strength) {
        this(1, 0, 0.01f, strength);
    }

    public void setIterations(int iterations) {
        if (iterations < 1) iterations = 1;
        this.iterations = iterations;
    }

    @Override
    public void apply(World world) {
        float[][] buffer = new float[world.getHeightInTiles()][world.getWidthInTiles()];

        for (int i = 0; i < iterations; i++) {
            erode(world, buffer);
            world.forEachTile((region, localX, localY, worldX, worldY) -> {
                Tile tile = region.getTile(localX, localY);
                tile.add(buffer[worldY][worldX]);
            });
        }
        consoleFeedback("Erosion x " + iterations);
    }

    private void erode(World world, float[][] buffer) {

        world.forEachTileWithNeighbors(((region, localX, localY, worldX, worldY, tile, neighbors) -> {
            if (tile.getAltitude() <= seaLevel) return;

            for (TileWithCoordinates neighbor : neighbors) {
                float delta = tile.getAltitude() - neighbor.tile().getAltitude();

                if (delta > minHeightDelta) {
                    float amount = (delta - minHeightDelta) * strength;
                    buffer[worldY][worldX] -= amount;
                    buffer[neighbor.worldY()][neighbor.worldX()] += amount;
                }
            }

        }));
    }

}
